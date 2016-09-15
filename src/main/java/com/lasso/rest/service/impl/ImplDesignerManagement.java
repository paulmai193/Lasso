package com.lasso.rest.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.model.api.request.CreatePortfolioRequest;
import com.lasso.rest.model.api.request.EditPortfolioRequest;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.PortfolioType;
import com.lasso.rest.service.DesignerManagement;

/**
 * The Class ImplDesignerManagement.
 *
 * @author Paul Mai
 */
@Service
@Transactional
public class ImplDesignerManagement extends ImplProjectManagement implements DesignerManagement {

	/** The portfolio storage path. */
	private String	portfolioStoragePath;

	/** The temporary storage path. */
	private String	temporaryStoragePath;

	/** The web context storage path. */
	private String	webContextStoragePath;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#createPortfolio(com.lasso.rest.model.datasource.
	 * Account, com.lasso.rest.model.api.request.CreatePortfolioRequest)
	 */
	@Override
	public void createPortfolio(Account __desiger, CreatePortfolioRequest __createPortfolioRequest)
			throws IOException {
		String _image = Arrays.toString(__createPortfolioRequest.getImages().toArray());
		_image = _image.substring(1, _image.length() - 1);
		Portfolio _portfolio = new Portfolio(__createPortfolioRequest.getAmount(), new Date(),
				__desiger.getId(), __createPortfolioRequest.getIdCategory(),
				__createPortfolioRequest.getIdStyle(), _image, __createPortfolioRequest.getInfo(),
				new Date(), (byte) 1, __createPortfolioRequest.getTitle());
		int _id = this.getPortfolioDAO().createPortfolio(_portfolio);
		for (int _idType : __createPortfolioRequest.getIdTypes()) {
			PortfolioType _portfolioType = new PortfolioType(new Date(), new Date(), _id, _idType);
			this.getPortfolioTypeDAO().createPortfolioType(_portfolioType);
		}

		// Copy portfolio images from temporary directory to resource directory
		for (String _tempFileName : __createPortfolioRequest.getImages()) {
			File _tempFile = new File(
					this.webContextStoragePath + this.temporaryStoragePath + "/" + _tempFileName);
			if (_tempFile.exists()) {
				FileUtils.moveFileToDirectory(_tempFile,
						new File(this.webContextStoragePath + this.portfolioStoragePath), false);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.DesignerManagement#editPortfolio(com.lasso.rest.model.datasource.
	 * Account, com.lasso.rest.model.api.request.EditPortfolioRequest)
	 */
	@Override
	public void editPortfolio(Account __desiger, EditPortfolioRequest __editPortfolioRequest)
			throws IOException {
		String _image = Arrays.toString(__editPortfolioRequest.getImages().toArray());
		_image = _image.substring(1, _image.length() - 1);
		Portfolio _portfolio = this.getPortfolioDAO()
				.getPortfolioById(__editPortfolioRequest.getId());
		if (_portfolio == null) {
			throw new NullPointerException("Portfolio not found");
		}
		_portfolio.update(__editPortfolioRequest);
		this.getPortfolioDAO().updatePortfolio(_portfolio);

		// Remove all old portfolio type
		this.getPortfolioTypeDAO().removeByPortfolioId(_portfolio.getId());

		// Insert new portfolio type
		for (int _idType : __editPortfolioRequest.getIdTypes()) {
			PortfolioType _portfolioType = new PortfolioType(new Date(), new Date(),
					_portfolio.getId(), _idType);
			this.getPortfolioTypeDAO().createPortfolioType(_portfolioType);
		}

		// Copy portfolio images from temporary directory to resource directory
		for (String _tempFileName : __editPortfolioRequest.getImages()) {
			File _tempFile = new File(
					this.webContextStoragePath + this.temporaryStoragePath + "/" + _tempFileName);
			if (_tempFile.exists()) {
				FileUtils.moveFileToDirectory(_tempFile,
						new File(this.webContextStoragePath + this.portfolioStoragePath), false);
			}
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
		return this.getPortfolioDAO().getAllPortfoliosOfAccount(__account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.DesignerManagement#getPortfolio(com.lasso.rest.model.datasource.
	 * Account, java.lang.Integer)
	 */
	@Override
	public Portfolio getPortfolio(Account __account, Integer __id) {
		return this.getPortfolioDAO().getPortfolioOfAccount(__account, __id);
	}

	/**
	 * Sets the portfolio storage path.
	 *
	 * @param __portfolioStoragePath the new portfolio storage path
	 */
	public void setPortfolioStoragePath(String __portfolioStoragePath) {
		this.portfolioStoragePath = __portfolioStoragePath;
	}

	/**
	 * Sets the temporary storage path.
	 *
	 * @param __temporaryStoragePath the new temporary storage path
	 */
	public void setTemporaryStoragePath(String __temporaryStoragePath) {
		this.temporaryStoragePath = __temporaryStoragePath;
	}

	/**
	 * Sets the web context storage path.
	 *
	 * @param __webContextStoragePath the new web context storage path
	 */
	public void setWebContextStoragePath(String __webContextStoragePath) {
		this.webContextStoragePath = __webContextStoragePath;
	}

}
