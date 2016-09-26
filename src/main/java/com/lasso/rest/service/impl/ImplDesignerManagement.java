package com.lasso.rest.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.model.api.request.ConfirmOfferRequest;
import com.lasso.rest.model.api.request.CounterOfferRequest;
import com.lasso.rest.model.api.request.CreatePortfolioRequest;
import com.lasso.rest.model.api.request.EditPortfolioRequest;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.JobsAccount;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.PortfolioType;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.service.DesignerManagement;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * The Class ImplDesignerManagement.
 *
 * @author Paul Mai
 */
@Service
@Transactional
public class ImplDesignerManagement extends ImplProjectManagement implements DesignerManagement {

	/** The portfolio storage path. */
	private String portfolioStoragePath;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.DesignerManagement#confirmOffer(com.lasso.rest.model.datasource.
	 * Account, com.lasso.rest.model.api.request.ConfirmOfferRequest)
	 */
	@Override
	public void confirmOffer(Account __designer, ConfirmOfferRequest __confirmOfferRequest) {
		Job _job = this.jobDAO.getJobById(__confirmOfferRequest.getIdJob());
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else {
			JobsAccount _jobsAccount = this.jobAccountDAO.getByJobAndDesignerId(_job.getId(),
					__designer.getId());
			if (_jobsAccount == null) {
				throw new NotFoundException("Offer for found");
			}
			else {
				_jobsAccount.setConfirm(__confirmOfferRequest.getStatus());
				this.jobAccountDAO.update(_jobsAccount);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.DesignerManagement#counterOffer(com.lasso.rest.model.datasource.
	 * Account, com.lasso.rest.model.api.request.CounterOfferRequest)
	 */
	@Override
	public void counterOffer(Account __designer, CounterOfferRequest __counterOfferRequest) {
		Job _job = this.jobDAO.getJobById(__counterOfferRequest.getIdJob());
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else {
			JobsAccount _jobsAccount = this.jobAccountDAO.getByJobAndDesignerId(_job.getId(),
					__designer.getId());
			if (_jobsAccount == null) {
				throw new NotFoundException("Offer for found");
			}
			else {
				_jobsAccount.setCounter(__counterOfferRequest.getAmount());
				this.jobAccountDAO.update(_jobsAccount);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#createPortfolio(com.lasso.rest.model.datasource.
	 * Account, com.lasso.rest.model.api.request.CreatePortfolioRequest)
	 */
	@Override
	public void createPortfolio(Account __desiger, CreatePortfolioRequest __createPortfolioRequest)
			throws IOException, UnirestException {
		String _webContextStoragePath = this.genericManagement
				.loadWebContextStoragePath(__desiger.getAppSession());
		try {
			Category _category = this.categoryDAO
					.getCategoryById(__createPortfolioRequest.getIdCategory());
			Style _style = this.styleDAO.getById(__createPortfolioRequest.getIdStyle());
			if (_category == null || _category.getDeleted().equals((byte) 1)) {
				throw new NotFoundException("Category not found");
			}
			else if (_style == null || _style.getDeleted().equals((byte) 1)) {
				throw new NotFoundException("Style not found");
			}

			String _image = Arrays.toString(__createPortfolioRequest.getImages().toArray());
			_image = _image.substring(1, _image.length() - 1);
			Portfolio _portfolio = new Portfolio(__createPortfolioRequest.getAmount(), new Date(),
					__desiger.getId(), __createPortfolioRequest.getIdCategory(),
					__createPortfolioRequest.getIdStyle(), _image,
					__createPortfolioRequest.getInfo(), new Date(), (byte) 1,
					__createPortfolioRequest.getTitle());
			int _id = this.portfolioDAO.createPortfolio(_portfolio);
			for (int _idType : __createPortfolioRequest.getIdTypes()) {
				Type _type = this.typeDAO.getTypeById(_idType);
				if (_type != null && _type.getDeleted().equals((byte) 0)) {
					PortfolioType _portfolioType = new PortfolioType(new Date(), new Date(), _id,
							_idType);
					this.portfolioTypeDAO.createPortfolioType(_portfolioType);
				}
			}

			// Copy portfolio images from temporary directory to resource directory
			for (String _tempFileName : __createPortfolioRequest.getImages()) {
				File _tempFile = new File(
						_webContextStoragePath + this.temporaryStoragePath + "/" + _tempFileName);
				if (_tempFile.exists()) {
					// Move original file
					FileUtils.copyFileToDirectory(_tempFile, new File(
							_webContextStoragePath + this.portfolioStoragePath + "/Original/"),
							false);

					// Resize into 3 other size
					File _icon = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Icon/" + _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _icon, 120D, 184D);
					File _small = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Small/" + _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _small, 182D, 280D);
					File _retina = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Retina/" + _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _retina, 364D, 560D);
				}
				else {
					Logger.getLogger(this.getClass())
					.warn("Portfolio temporary file not exist. Check this path: "
							+ _tempFile.getAbsolutePath());
				}
			}
		}
		finally {
			// Remove temporary directory which were older than 2 days
			this.removeOldTemporaryFiles(_webContextStoragePath);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#deletePortfolio(com.lasso.rest.model.datasource.
	 * Portfolio)
	 */
	@Override
	public void deletePortfolio(Portfolio __portfolio) {
		// Remove all old portfolio type
		this.portfolioTypeDAO.removeByPortfolioId(__portfolio.getId());

		// Delete this portfolio
		__portfolio.setDeleted((byte) 1);
		this.portfolioDAO.updatePortfolio(__portfolio);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.DesignerManagement#editPortfolio(com.lasso.rest.model.datasource.
	 * Portfolio, com.lasso.rest.model.api.request.EditPortfolioRequest)
	 */
	@Override
	public void editPortfolio(Account __desiger, Portfolio __portfolio,
			EditPortfolioRequest __editPortfolioRequest) throws IOException, UnirestException {
		String _webContextStoragePath = this.genericManagement
				.loadWebContextStoragePath(__desiger.getAppSession());
		try {
			__portfolio.update(__editPortfolioRequest);
			this.portfolioDAO.updatePortfolio(__portfolio);

			// Remove all old portfolio type
			this.portfolioTypeDAO.removeByPortfolioId(__portfolio.getId());

			// Insert new portfolio type
			for (int _idType : __editPortfolioRequest.getIdTypes()) {
				PortfolioType _portfolioType = new PortfolioType(new Date(), new Date(),
						__portfolio.getId(), _idType);
				this.portfolioTypeDAO.createPortfolioType(_portfolioType);
			}

			// Copy portfolio images from temporary directory to resource directory
			for (String _tempFileName : __editPortfolioRequest.getImages()) {
				File _tempFile = new File(
						_webContextStoragePath + this.temporaryStoragePath + "/" + _tempFileName);
				if (_tempFile.exists()) {
					// Move original file
					FileUtils.copyFileToDirectory(_tempFile, new File(
							_webContextStoragePath + this.portfolioStoragePath + "/Original/"),
							false);

					// Resize into 3 other size
					File _icon = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Icon/" + _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _icon, 120D, 184D);
					File _small = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Small/" + _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _small, 182D, 280D);
					File _retina = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Retina/" + _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _retina, 364D, 560D);
				}
				else {
					Logger.getLogger(this.getClass())
					.warn("Portfolio temporary file not exist. Check this path: "
							+ _tempFile.getAbsolutePath());
				}
			}
		}
		finally {
			// Remove temporary directory which were older than 2 days
			this.removeOldTemporaryFiles(_webContextStoragePath);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#getAllPortfolios(com.lasso.rest.model.datasource.
	 * Account)
	 */
	@Override
	public List<Portfolio> getAllPortfolios(Account __account) {
		return this.portfolioDAO.getAllPortfoliosOfAccount(__account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.DesignerManagement#getOfferDataById(int)
	 */
	@Override
	public Object[] getOfferDataById(int __idJob) {
		Job _job = this.jobDAO.getJobById(__idJob);
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		Account _user = this.accountDAO.getAccountById(_job.getAccountId());
		if (_user == null) {
			throw new NotFoundException("User send offer not found");
		}
		List<Integer> _styleIds = new ArrayList<>();
		this.jobStyleDAO.getListJobStylesByJobId(__idJob)
		.forEach(_jt -> _styleIds.add(_jt.getStyleId()));
		List<Type> _types = this.typeDAO.getListByByListIds(_styleIds);
		if (_styleIds.isEmpty()) {
			throw new NotFoundException("Types not found");
		}
		Type _type = this.typeDAO.getTypeById(_job.getTypeId());
		if (_type == null) {
			throw new NotFoundException("Style not found");
		}
		Category _category = this.categoryDAO.getCategoryById(_job.getCategoryId());
		if (_category == null) {
			throw new NotFoundException("Category not found");
		}

		Object[] _orderData = { _job, _user, _types, _type, _category };
		return _orderData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.DesignerManagement#getPortfolio(com.lasso.rest.model.datasource.
	 * Account, java.lang.Integer)
	 */
	@Override
	public Portfolio getPortfolio(Account __account, Integer __id) {
		return this.portfolioDAO.getPortfolioOfAccount(__account, __id);
	}

	/**
	 * Sets the portfolio storage path.
	 *
	 * @param __portfolioStoragePath the new portfolio storage path
	 */
	public void setPortfolioStoragePath(String __portfolioStoragePath) {
		this.portfolioStoragePath = __portfolioStoragePath;
	}

}
