/*
 * 
 */
package com.lasso.rest.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.define.JobConfirmationConstant;
import com.lasso.define.JobStageConstant;
import com.lasso.rest.model.api.request.ConfirmOfferRequest;
import com.lasso.rest.model.api.request.CounterOfferRequest;
import com.lasso.rest.model.api.request.CreatePortfolioRequest;
import com.lasso.rest.model.api.request.EditPortfolioRequest;
import com.lasso.rest.model.api.request.UpdateJobStageRequest;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.AccountSettings;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.JobsAccount;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.PortfolioType;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.model.push.PushJobDetailMessage;
import com.lasso.rest.model.push.PushNotification;
import com.lasso.rest.model.push.PushOrderDetailMessage;
import com.lasso.rest.model.push.SendPushRequest;
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
	private String	portfolioStoragePath;

	/** The project storage path. */
	private String	projectStoragePath;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#confirmOffer(com.lasso.rest.
	 * model.datasource. Account,
	 * com.lasso.rest.model.api.request.ConfirmOfferRequest)
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
			else if (_jobsAccount.getConfirm().byteValue() == JobConfirmationConstant.JOB_ACCEPT
					.getCode()) {
				throw new ForbiddenException("Offer was accepted");
			}
			else if (_jobsAccount.getConfirm().byteValue() == JobConfirmationConstant.JOB_CONFIRM
					.getCode()) {
				throw new ForbiddenException("Offer was confirmed");
			}
			else if (_jobsAccount.getConfirm().byteValue() == JobConfirmationConstant.JOB_REJECT
					.getCode()) {
				throw new ForbiddenException("Offer was rejected");
			}
			else {
				_jobsAccount.setConfirm(__confirmOfferRequest.getStatus());
				_jobsAccount.setModified(new Date());
				this.jobAccountDAO.update(_jobsAccount);

				// Send push
				Account _user = ImplDesignerManagement.this.accountDAO
						.getAccountById(_job.getAccountId());
				new Thread(new Runnable() {

					@Override
					public void run() {
						AccountSettings _accountSettings;
						try {
							_accountSettings = _user.getSettings();

							// Send push in-app
							if (_accountSettings.getAppSettings().getStatus_update() != null
									&& _accountSettings.getAppSettings().getStatus_update()
									.equals("on")) {
								SendPushRequest _pushRequest = new SendPushRequest();
								_pushRequest.setNotification(new PushNotification(
										"Confirm the order", "Designer " + __designer.getName()
										+ " was confirm your order"));
								_pushRequest.setData(new PushOrderDetailMessage(_job.getId()));
								_pushRequest.setTo(_user.getDeviceId());
								ImplDesignerManagement.this.messageManagement
								.sendPush(_pushRequest);
							}
						}
						catch (Exception _ex) {
							Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
						}
					}
				}).start();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#counterOffer(com.lasso.rest.
	 * model.datasource. Account,
	 * com.lasso.rest.model.api.request.CounterOfferRequest)
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
			else if (_jobsAccount.getConfirm().byteValue() == JobConfirmationConstant.JOB_ACCEPT
					.getCode()) {
				throw new ForbiddenException("Offer was accepted");
			}
			else if (_jobsAccount.getConfirm().byteValue() == JobConfirmationConstant.JOB_CONFIRM
					.getCode()) {
				throw new ForbiddenException("Offer was confirmed");
			}
			else if (_jobsAccount.getConfirm().byteValue() == JobConfirmationConstant.JOB_REJECT
					.getCode()) {
				throw new ForbiddenException("Offer was rejected");
			}
			else {
				_jobsAccount.setCounter(__counterOfferRequest.getAmount());
				_jobsAccount.setConfirm(JobConfirmationConstant.JOB_CONFIRM.getCode());
				_jobsAccount.setModified(new Date());
				this.jobAccountDAO.update(_jobsAccount);

				// Send push
				Account _user = ImplDesignerManagement.this.accountDAO
						.getAccountById(_job.getAccountId());
				new Thread(new Runnable() {

					@Override
					public void run() {
						AccountSettings _accountSettings;
						try {
							_accountSettings = _user.getSettings();

							// Send push in-app
							if (_accountSettings.getAppSettings().getStatus_update() != null
									&& _accountSettings.getAppSettings().getStatus_update()
									.equals("on")) {
								SendPushRequest _pushRequest = new SendPushRequest();
								_pushRequest.setNotification(
										new PushNotification("Counter offer for order",
												"Designer " + __designer.getName()
												+ " was counter offer your order"));
								_pushRequest.setData(new PushOrderDetailMessage(_job.getId()));
								_pushRequest.setTo(_user.getDeviceId());
								ImplDesignerManagement.this.messageManagement
								.sendPush(_pushRequest);
							}
						}
						catch (Exception _ex) {
							Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
						}
					}
				}).start();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#createPortfolio(com.lasso.rest.
	 * model.datasource. Account,
	 * com.lasso.rest.model.api.request.CreatePortfolioRequest)
	 */
	@Override
	public void createPortfolio(Account __desiger, CreatePortfolioRequest __createPortfolioRequest)
			throws IOException, UnirestException, URISyntaxException {
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

			String _image = Arrays.toString(__createPortfolioRequest.getImages().toArray())
					.replace(" ", "");
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

			// Copy portfolio images from temporary directory to resource
			// directory
			for (String _tempFileName : __createPortfolioRequest.getImages()) {
				File _tempFile = new File(
						_webContextStoragePath + this.temporaryStoragePath + "/" + _tempFileName);
				if (_tempFile.exists()) {
					// // Copy original file
					// this.uploadImageManagement.copyImage(_tempFile, new File(
					// _webContextStoragePath + this.portfolioStoragePath + "/Original/"));

					// Add wartermark to original image
					File _original = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Original/" + _tempFileName);
					File _wartermark = new File(
							this.getClass().getClassLoader().getResource("watermark.png").toURI());
					Logger.getLogger(this.getClass())
					.debug("Watermark full path: " + _wartermark.getAbsolutePath());
					this.uploadImageManagement.addWatermark(_tempFile, _wartermark, _original);

					// Resize into 3 other size
					File _icon = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Icon/" + _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _icon, 120, 184);
					File _small = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Small/" + _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _small, 182, 280);
					File _retina = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Retina/" + _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _retina, 364, 560);
				}
				else {
					Logger.getLogger(this.getClass())
					.warn("Portfolio temporary file not exist. Check this path: "
							+ _tempFile.getAbsolutePath());
				}
			}

			// // XXX Create project base on this portofolio
			// _portfolio.setId(_id);
			// Project _project = new Project(_portfolio);
			// this.projectDAO.saveProject(_project);
			// // Copy project images from 1st portfolio directory
			// // directory
			// for (String _tempFileName : __createPortfolioRequest.getImages()) {
			// // Copy original file
			// this.uploadImageManagement.copyImage(
			// new File(_webContextStoragePath + this.portfolioStoragePath + "/Original/"
			// + _tempFileName),
			// new File(_webContextStoragePath + this.projectStoragePath + "/Original/"));
			//
			// // Copy icon file
			// this.uploadImageManagement.copyImage(
			// new File(_webContextStoragePath + this.portfolioStoragePath + "/Icon/"
			// + _tempFileName),
			// new File(_webContextStoragePath + this.projectStoragePath + "/Icon/"));
			// // Copy icon file
			// this.uploadImageManagement.copyImage(
			// new File(_webContextStoragePath + this.portfolioStoragePath + "/Small/"
			// + _tempFileName),
			// new File(_webContextStoragePath + this.projectStoragePath + "/Small/"));
			// // Copy icon file
			// this.uploadImageManagement.copyImage(
			// new File(_webContextStoragePath + this.portfolioStoragePath + "/Retina/"
			// + _tempFileName),
			// new File(_webContextStoragePath + this.projectStoragePath + "/Retina/"));
			// break;
			// }
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
	 * com.lasso.rest.service.DesignerManagement#deletePortfolio(com.lasso.rest.
	 * model.datasource. Portfolio)
	 */
	@Override
	public void deletePortfolio(Portfolio __portfolio) {
		// Remove all old portfolio type
		this.portfolioTypeDAO.removeByPortfolioId(__portfolio.getId());

		// Delete this portfolio
		__portfolio.setDeleted((byte) 1);
		__portfolio.setModified(new Date());
		this.portfolioDAO.updatePortfolio(__portfolio);

		// // XXX Delete relate project
		// Project _project = this.projectDAO.getProjectByIdPortfolio(__portfolio.getId());
		// if (_project != null) {
		// _project.setDeleted((byte) 1);
		// _project.setModified(new Date());
		// this.projectDAO.updateProject(_project);
		// }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#editPortfolio(com.lasso.rest.
	 * model.datasource. Portfolio,
	 * com.lasso.rest.model.api.request.EditPortfolioRequest)
	 */
	@Override
	public void editPortfolio(Account __desiger, Portfolio __portfolio,
			EditPortfolioRequest __editPortfolioRequest)
					throws IOException, UnirestException, URISyntaxException {
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

			// Copy portfolio images from temporary directory to resource
			// directory
			for (String _tempFileName : __editPortfolioRequest.getImages()) {
				File _tempFile = new File(
						_webContextStoragePath + this.temporaryStoragePath + "/" + _tempFileName);
				if (_tempFile.exists()) {
					// // Move original file
					// FileUtils.copyFileToDirectory(_tempFile, new File(
					// _webContextStoragePath + this.portfolioStoragePath + "/Original/"),
					// false);

					// Add wartermark to original image
					File _original = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Original/" + _tempFileName);
					File _wartermark = new File(
							this.getClass().getClassLoader().getResource("watermark.png").toURI());
					Logger.getLogger(this.getClass())
					.debug("Watermark full path: " + _wartermark.getAbsolutePath());
					this.uploadImageManagement.addWatermark(_tempFile, _wartermark, _original);

					// Resize into 3 other size
					File _icon = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Icon/" + _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _icon, 120, 184);
					File _small = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Small/" + _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _small, 182, 280);
					File _retina = new File(_webContextStoragePath + this.portfolioStoragePath
							+ "/Retina/" + _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _retina, 364, 560);
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
	 * com.lasso.rest.service.DesignerManagement#getAllPortfolios(com.lasso.rest
	 * .model.datasource. Account)
	 */
	@Override
	public List<Portfolio> getAllPortfolios(Account __account) {
		return this.portfolioDAO.getAllPortfoliosOfAccount(__account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#getJobDataOfDesignerById(com.
	 * lasso.rest.model. datasource.Account, int)
	 */
	@Override
	public Object[] getJobDataOfDesignerById(Account __designer, int __idJob) {
		JobsAccount _jobsAccount = this.jobAccountDAO.getAcceptByJobAndDesignerId(__idJob,
				__designer.getId());
		if (_jobsAccount == null) {
			throw new NotFoundException("Job not found");
		}
		else {
			String _userName = "";
			Job _job = this.jobDAO.getJobById(_jobsAccount.getJobId());
			_userName = _job == null ? ""
					: this.accountDAO.getAccountById(_job.getAccountId()).getName();
			List<Integer> _styleIds = new ArrayList<>();
			this.jobStyleDAO.getListJobStylesByJobId(_job.getId())
			.forEach(_jobStyle -> _styleIds.add(_jobStyle.getStyleId()));
			List<Style> _styles = this.styleDAO.getListByByListIds(_styleIds);

			Type _type = this.typeDAO.getTypeById(_job.getTypeId());

			Object[] _data = { _job, _userName, _styles, _type.getTitle() };
			return _data;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#getListJobsDataOfDesigner(com.
	 * lasso.rest.model. datasource.Account)
	 */
	@Override
	public List<Object[]> getListJobsDataOfDesigner(Account __designer) {
		List<Object[]> _datas = new ArrayList<>();
		List<JobsAccount> _jobsAccounts = this.jobAccountDAO
				.getListJobsAccountOfDesigner(__designer.getId());
		if (_jobsAccounts.isEmpty()) {
			return new ArrayList<>();
		}
		else {
			_jobsAccounts.forEach(_jobAccount -> {
				try {
					if (_jobAccount.getConfirm().byteValue() == JobConfirmationConstant.JOB_ACCEPT
							.getCode()) {
						String _userName = "";
						Job _job = ImplDesignerManagement.this.jobDAO
								.getJobById(_jobAccount.getJobId());
						if (_job.getPaid().equals((byte) 0)) {
							return;
						}
						else {
							_userName = _job == null ? ""
									: ImplDesignerManagement.this.accountDAO
									.getAccountById(_job.getAccountId()).getName();
							List<Integer> _styleIds = new ArrayList<>();
							ImplDesignerManagement.this.jobStyleDAO
							.getListJobStylesByJobId(_job.getId())
							.forEach(_jobStyle -> _styleIds.add(_jobStyle.getStyleId()));
							List<Style> _styles = ImplDesignerManagement.this.styleDAO
									.getListByByListIds(_styleIds);
							Type _type = ImplDesignerManagement.this.typeDAO
									.getTypeById(_job.getTypeId());
							Category _category = ImplDesignerManagement.this.categoryDAO
									.getCategoryById(_job.getCategoryId());
							Object[] _data = { _job, _userName, _styles, _type, _category };

							_datas.add(_data);
						}
					}
				}
				catch (Exception _ex) {
					Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
				}
			});

			return _datas;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#getOfferDataById(com.lasso.rest
	 * .model.datasource. Account, int)
	 */
	@Override
	public Object[] getOfferDataById(Account __designer, int __idJob) {
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
		.forEach(_jobStyle -> _styleIds.add(_jobStyle.getStyleId()));
		List<Style> _styles = this.styleDAO.getListByByListIds(_styleIds);
		if (_styleIds.isEmpty()) {
			throw new NotFoundException("Styles not found");
		}
		Type _type = this.typeDAO.getTypeById(_job.getTypeId());
		if (_type == null) {
			throw new NotFoundException("Type not found");
		}
		Category _category = this.categoryDAO.getCategoryById(_job.getCategoryId());
		if (_category == null) {
			throw new NotFoundException("Category not found");
		}
		JobsAccount _jobsAccount = this.jobAccountDAO.getByJobAndDesignerId(__idJob,
				__designer.getId());
		if (_jobsAccount == null) {
			throw new NotFoundException("Offer not found");
		}
		Object[] _orderData = { _job, _user, _styles, _type, _category, _jobsAccount };
		return _orderData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#getOrderDataForMessageById(com.lasso.rest.model.
	 * datasource.Account, int)
	 */
	@Override
	public Object[] getOrderDataForMessageById(Account __designer, int __idJob) {
		Job _job = this.jobDAO.getJobById(__idJob);
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		List<Integer> _styleIds = new ArrayList<>();
		this.jobStyleDAO.getListJobStylesByJobId(__idJob)
		.forEach(_jobStyle -> _styleIds.add(_jobStyle.getStyleId()));
		List<Style> _styles = this.styleDAO.getListByByListIds(_styleIds);
		if (_styleIds.isEmpty()) {
			throw new NotFoundException("Styles not found");
		}
		Type _type = this.typeDAO.getTypeById(_job.getTypeId());
		if (_type == null) {
			throw new NotFoundException("Type not found");
		}
		Category _category = this.categoryDAO.getCategoryById(_job.getCategoryId());
		if (_category == null) {
			throw new NotFoundException("Category not found");
		}
		List<Object[]> _designersJobs = new ArrayList<>();
		boolean _isReject = false;
		for (JobsAccount _jobsAccount : this.jobAccountDAO.getByJobId(__idJob)) {
			Account _designer = this.accountDAO.getAccountById(_jobsAccount.getAccountId());
			if (_designer != null) {
				List<Portfolio> _portfolios = this.portfolioDAO
						.getAllPortfoliosOfAccount(_designer);
				_portfolios.stream().allMatch(
						_portfolio -> _portfolio.getCategoryId().equals(_category.getId()));

				Object[] designerJob = { _jobsAccount, _designer,
						_portfolios.isEmpty() ? new Portfolio() : _portfolios.get(0) };
				_designersJobs.add(designerJob);
				if (_designer.getId().equals(__designer.getId()) && _jobsAccount.getConfirm()
						.byteValue() == JobConfirmationConstant.JOB_REJECT.getCode()) {
					_isReject = true;
				}
			}
		}
		float _serviceFee = this.genericManagement.getServiceFee();
		Object[] _orderData = { _job, _designersJobs, _styles, _type, _category, _serviceFee,
				_isReject };
		return _orderData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#getPortfolio(com.lasso.rest.
	 * model.datasource. Account, java.lang.Integer)
	 */
	@Override
	public Portfolio getPortfolio(Account __account, Integer __id) {
		return this.portfolioDAO.getPortfolioOfAccount(__account, __id);
	}

	/**
	 * Sets the portfolio storage path.
	 *
	 * @param __portfolioStoragePath
	 *        the new portfolio storage path
	 */
	public void setPortfolioStoragePath(String __portfolioStoragePath) {
		this.portfolioStoragePath = __portfolioStoragePath;
	}

	/**
	 * Sets the project storage path.
	 *
	 * @param __projectStoragePath the new project storage path
	 */
	public void setProjectStoragePath(String __projectStoragePath) {
		this.projectStoragePath = __projectStoragePath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.DesignerManagement#updateStage(com.lasso.rest.
	 * model.datasource. Account,
	 * com.lasso.rest.model.api.request.UpdateJobStageRequest)
	 */
	@Override
	public void updateStage(Account __designer, UpdateJobStageRequest __updateJobStageRequest) {
		Job _job = this.jobDAO.getJobById(__updateJobStageRequest.getIdJob());
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else if (_job.getCompleted().equals((byte) 1)) {
			throw new ForbiddenException("Job was completed");
		}
		else if (_job.getPaid().equals((byte) 0)) {
			throw new ForbiddenException("Job not paid");
		}
		else {
			JobsAccount _jobsAccount = this.jobAccountDAO.getAcceptByJobAndDesignerId(_job.getId(),
					__designer.getId());
			if (_jobsAccount == null) {
				throw new NotFoundException("Designer not have this job");
			}
			else if (_jobsAccount.getConfirm().byteValue() != JobConfirmationConstant.JOB_ACCEPT
					.getCode()) {
				throw new ForbiddenException("Job not accepted by user");
			}
			else {
				_job.setStage(__updateJobStageRequest.getStage());
				_job.setStageDate(__updateJobStageRequest.getDeliveryDate());
				_job.setModified(new Date());
				this.jobDAO.updateJob(_job);

				// Send push
				Account _user = ImplDesignerManagement.this.accountDAO
						.getAccountById(_job.getAccountId());
				new Thread(new Runnable() {

					@Override
					public void run() {
						AccountSettings _accountSettings;
						try {
							_accountSettings = _user.getSettings();

							// Send push in-app
							if (_accountSettings.getAppSettings().getStatus_update() != null
									&& _accountSettings.getAppSettings().getStatus_update()
									.equals("on")) {
								SendPushRequest _pushRequest = new SendPushRequest();
								_pushRequest
								.setNotification(new PushNotification("Job update",
										"Job " + _job.getDescription() + " was updated "
												+ JobStageConstant.getByCode(
														__updateJobStageRequest.getStage())
												.getName()));
								_pushRequest.setData(new PushJobDetailMessage(_job.getId()));
								_pushRequest.setTo(_user.getDeviceId());
								ImplDesignerManagement.this.messageManagement
								.sendPush(_pushRequest);
							}
						}
						catch (Exception _ex) {
							Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
						}
					}
				}).start();
			}
		}
	}

}
