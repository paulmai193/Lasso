/*
 * 
 */
package com.lasso.rest.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.define.Constant;
import com.lasso.define.JobConfirmationConstant;
import com.lasso.define.JobStepConstant;
import com.lasso.rest.dao.AccountRatingDAO;
import com.lasso.rest.dao.PromoDAO;
import com.lasso.rest.model.api.request.ChooseDesignerForOrderRequest;
import com.lasso.rest.model.api.request.CompleteJobRequest;
import com.lasso.rest.model.api.request.ConfirmOrderRequest;
import com.lasso.rest.model.api.request.CreateNewOrderRequest;
import com.lasso.rest.model.api.request.EditOrderRequest;
import com.lasso.rest.model.api.request.PaymentForOrderRequest;
import com.lasso.rest.model.api.request.UsePromoCodeForOrder;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.AccountSettings;
import com.lasso.rest.model.datasource.AccountsRating;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.JobsAccount;
import com.lasso.rest.model.datasource.JobsStyle;
import com.lasso.rest.model.datasource.Message;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.PortfolioType;
import com.lasso.rest.model.datasource.PromoCode;
import com.lasso.rest.model.datasource.PromoHistory;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.model.push.PushNotification;
import com.lasso.rest.model.push.SendPushRequest;
import com.lasso.rest.service.UserManagement;
import com.lasso.template.DesignerNewOfferEmail;
import com.lasso.template.EmailTemplate;
import com.lasso.util.EmailUtil;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * The Class ImplUserManagement.
 *
 * @author Paul Mai
 */
@Service
@Transactional
public class ImplUserManagement extends ImplProjectManagement implements UserManagement {

	/** The account rating DAO. */
	@Autowired
	private AccountRatingDAO	accountRatingDAO;

	/** The email util. */
	private EmailUtil			emailUtil;

	/** The http host. */
	private String				httpHost;

	// /** The message management. */
	// @Autowired
	// private MessageManagement messageManagement;

	/** The promo DAO. */
	@Autowired
	private PromoDAO			promoDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#applyPayment(com.lasso.rest.model.
	 * datasource.Account,
	 * com.lasso.rest.model.api.request.PaymentForOrderRequest)
	 */
	@Override
	public void applyPayment(Account __user, PaymentForOrderRequest __paymentForJobRequest) {
		Job _job = this.jobDAO.getJobOfUserById(__user.getId(), __paymentForJobRequest.getIdJob());
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else {
			_job.setStep(JobStepConstant.JOB_STEP_PAY.getStepCode());
			_job.setModified(new Date());
			this.jobDAO.updateJob(_job);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.UserManagement#applyPaypal(int, int)
	 */
	@Override
	public void applyPaypal(int __idUser, int __idJob) {
		Job _job = this.jobDAO.getJobById(__idJob);
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else {
			_job.setStep(JobStepConstant.JOB_STEP_COMPLETE.getStepCode());
			_job.setPaid((byte) 1);
			this.jobDAO.updateJob(_job);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#applyPromoCodeForOrder(com.lasso.
	 * rest.model.datasource. Account,
	 * com.lasso.rest.model.api.request.UsePromoCodeForOrder)
	 */
	@Override
	public void applyPromoCodeForOrder(Account __user,
	        UsePromoCodeForOrder __usePromoCodeForOrder) {
		Job _job = this.jobDAO.getJobOfUserById(__user.getId(), __usePromoCodeForOrder.getIdJob());
		PromoCode _promoCode = this.promoDAO
		        .getPromoCodeByCode(__usePromoCodeForOrder.getPromoCode());
		if (_promoCode == null) {
			throw new NotFoundException("Code not found");
		}
		else if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else {
			PromoHistory _promoHistory = new PromoHistory(__user.getId(),
			        __usePromoCodeForOrder.getIdJob(), _promoCode.getId());
			this.promoDAO.savePromoHistory(_promoHistory);

			_job.setDiscount(_job.getBudget() * _promoCode.getDiscount());
			this.jobDAO.updateJob(_job);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#chooseDesignerForOrder(com.lasso.
	 * rest.model.datasource. Account,
	 * com.lasso.rest.model.api.request.ChooseDesignerForOrderRequest)
	 */
	@Override
	public void chooseDesignerForOrder(Account __user,
	        ChooseDesignerForOrderRequest __chooseDesignerForJobRequest) {
		Job _job = this.jobDAO.getJobOfUserById(__user.getId(),
		        __chooseDesignerForJobRequest.getIdJob());
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else if (!_job.getStep().equals(JobStepConstant.JOB_STEP_BRIEF.getStepCode())) {
			throw new ForbiddenException("This job cannot edit at "
			        + JobStepConstant.getByCode(_job.getStep()).getStepName());
		}
		else {
			// update designer chosen by user
			List<JobsAccount> _jobsAccounts = new ArrayList<>();
			Set<Message> _messages = new HashSet<>(); // Use to save into DB
			List<Object[]> _pushs = new ArrayList<>(); // Use to send push
			__chooseDesignerForJobRequest.getDesignerIds().forEach(_idDesigner -> {
				Account _designer = this.accountDAO.getAccountById(_idDesigner);
				if (_designer.getRole().equals(Constant.ROLE_DESIGNER)) {
					_jobsAccounts.add(new JobsAccount(_idDesigner, _job.getId()));

					String _title = _job.getDescription();
					String _content = "Hi, please check this offer.";
					Message _message = new Message(__user.getId(), _job.getId(), _content, _title,
					        _idDesigner);
					_messages.add(_message);

					Object[] data = { _designer, _message };
					_pushs.add(data);
				}
			});
			// Save designer seleted and message will send to them
			this.jobAccountDAO.saveJobAccounts(_jobsAccounts);
			this.messageDAO.saveMessages(new ArrayList<>(_messages));

			// Everything success, update step of job
			_job.setStep(JobStepConstant.JOB_STEP_CHOOSE_DESIGNER.getStepCode());
			this.jobDAO.updateJob(_job);

			// All setting success, send message to designer
			new Thread(new Runnable() {

				@Override
				public void run() {
					_pushs.forEach(_push -> {
						Account _designer = (Account) _push[0];
						try {
							AccountSettings _accountSettings = _designer.getSettings();

							// Send push in-app
							if (_accountSettings.getAppSettings().getOffer() != null
							        && _accountSettings.getAppSettings().getOffer().equals("on")) {
								SendPushRequest _pushRequest = new SendPushRequest();
								_pushRequest.setNotification(
								        new PushNotification(((Message) _push[1]).getTitle(),
								                ((Message) _push[1]).getMessage()));
								_pushRequest.setTo(_designer.getDeviceId());
								ImplUserManagement.this.messageManagement.sendPush(_pushRequest);
							}

							// Send email
							if (_accountSettings.getEmailSettings().getOffer() != null
							        && _accountSettings.getEmailSettings().getOffer()
							                .equals("on")) {
								String _link = ImplUserManagement.this.httpHost + "/job-offer-"
								        + _job.getId() + ".html?device_id="
								        + _designer.getDeviceId();
								EmailTemplate _emailTemplate = new DesignerNewOfferEmail(
								        _designer.getName(), _link);
								ImplUserManagement.this.emailUtil.sendEmailByTemplate(
								        _designer.getEmail(), "New job offer",
								        _emailTemplate.getContent(),
								        javax.mail.Message.RecipientType.TO,
								        _emailTemplate.getTemplate());
							}
						}
						catch (Exception _ex) {
							Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
						}

					});

				}
			}).start();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#completeJob(com.lasso.rest.model.
	 * api.request. RatingDetailResponse)
	 */
	@Override
	public void completeJob(CompleteJobRequest __completeJobRequest) {
		Job _job = this.jobDAO.getJobById(__completeJobRequest.getIdJob());
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		_job.setCompleted((byte) 1);
		this.jobDAO.updateJob(_job);
		AccountsRating _accountsRating = new AccountsRating(__completeJobRequest.getIdDesigner(),
		        __completeJobRequest.getIdJob(), __completeJobRequest.getCommunication(),
		        __completeJobRequest.getExperience(), __completeJobRequest.getQuality());
		this.accountRatingDAO.saveRating(_accountsRating);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#confirmOrder(com.lasso.rest.model.
	 * datasource.Account, com.lasso.rest.model.api.request.ConfirmOrderRequest)
	 */
	@Override
	public void confirmOrder(Account __user, ConfirmOrderRequest __confirmOrderRequest) {
		Job _job = this.jobDAO.getJobOfUserById(__user.getId(), __confirmOrderRequest.getIdJob());
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else {
			// JobsAccount _jobsAccount = this.jobAccountDAO.getByJobAndDesignerId(
			// __confirmOrderRequest.getIdJob(), __confirmOrderRequest.getIdDesigner());
			// if (_jobsAccount == null) {
			// throw new NullPointerException("Order to this designer not valid");
			// }
			// else if (_jobsAccount.getConfirm().byteValue() < JobConfirmationConstant.JOB_CONFIRM
			// .getCode()) {
			// throw new ForbiddenException("Designer not confirm this job");
			// }
			// else {
			// _jobsAccount.setConfirm(JobConfirmationConstant.JOB_ACCEPT.getCode());
			// this.jobAccountDAO.saveJobAccount(_jobsAccount);
			//
			// // Update job
			// _job.setStep(JobStepConstant.JOB_STEP_CONFIRM.getStepCode());
			//
			// // // Check if have counter offer, re-calculate order amount
			// double _counterOffer = _jobsAccount.getCounter();
			// if (_counterOffer > 0) {
			// _job.setBudget(_counterOffer);
			// _job.setFee(_counterOffer * this.genericManagement.getServiceFee() / 100);
			// }
			//
			// this.jobDAO.updateJob(_job);
			// }

			// XXX New implement
			List<JobsAccount> _jobsAccounts = this.jobAccountDAO
			        .getByJobId(__confirmOrderRequest.getIdJob());
			for (int _i = 0; _i < _jobsAccounts.size(); _i++) {
				JobsAccount _tempJobsAccount = _jobsAccounts.get(_i);
				if (_tempJobsAccount.getAccountId().equals(__confirmOrderRequest.getIdDesigner())) {
					if (_tempJobsAccount.getConfirm()
					        .byteValue() < JobConfirmationConstant.JOB_CONFIRM.getCode()) {
						throw new ForbiddenException("Designer not confirm this job");
					}
					else {
						_tempJobsAccount.setConfirm(JobConfirmationConstant.JOB_ACCEPT.getCode());

						// Update job
						_job.setStep(JobStepConstant.JOB_STEP_CONFIRM.getStepCode());

						// // Check if have counter offer, re-calculate order amount
						double _counterOffer = _tempJobsAccount.getCounter();
						if (_counterOffer > 0) {
							_job.setBudget(_counterOffer);
							_job.setFee(
							        _counterOffer * this.genericManagement.getServiceFee() / 100);
						}

						this.jobDAO.updateJob(_job);
					}
				}
				else {
					_tempJobsAccount.setConfirm(JobConfirmationConstant.JOB_REJECT.getCode());
				}

				this.jobAccountDAO.saveJobAccount(_tempJobsAccount);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#createNewOrder(com.lasso.rest.model
	 * .datasource.Account,
	 * com.lasso.rest.model.api.request.CreateNewOrderRequest)
	 */
	@Override
	public Integer createNewOrder(Account __user, CreateNewOrderRequest __createNewOrderRequest)
	        throws UnirestException, IOException {
		String _webContextStoragePath = this.genericManagement
		        .loadWebContextStoragePath(__user.getAppSession());
		try {
			Job _job = new Job(__createNewOrderRequest);
			_job.setFee(_job.getBudget() * this.genericManagement.getServiceFee() / 100);
			_job.setAccountId(__user.getId());
			Integer _idJob = this.jobDAO.saveJob(_job);

			List<JobsStyle> _jobStyles = new ArrayList<>();
			__createNewOrderRequest.getIdStyles()
			        .forEach(_id -> _jobStyles.add(new JobsStyle(_idJob, _id)));
			this.jobStyleDAO.saveListJobStyles(_jobStyles);

			// Copy portfolio images from temporary directory to resource
			// directory
			__createNewOrderRequest.getReference().forEach(_tempFileName -> {
				File _tempFile = new File(
				        _webContextStoragePath + this.temporaryStoragePath + "/" + _tempFileName);
				if (_tempFile.exists()) {

					try {
						// Move original file
						this.uploadImageManagement.copyImage(_tempFile, new File(
						        _webContextStoragePath + this.jobStoragePath + "/Original"));

						// Resize into 3 other size
						File _icon = new File(_webContextStoragePath + this.jobStoragePath
						        + "/Icon/" + _tempFileName);
						this.uploadImageManagement.resizeImage(_tempFile, _icon, 35, 64);
						File _small = new File(_webContextStoragePath + this.jobStoragePath
						        + "/Small/" + _tempFileName);
						this.uploadImageManagement.resizeImage(_tempFile, _small, 70, 128);
						File _retina = new File(_webContextStoragePath + this.jobStoragePath
						        + "/Retina/" + _tempFileName);
						this.uploadImageManagement.resizeImage(_tempFile, _retina, 140, 256);
					}
					catch (IOException _ex) {
						Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
					}
				}
				else {
					Logger.getLogger(this.getClass())
					        .warn("Job temporary file not exist. Check this path: "
					                + _tempFile.getAbsolutePath());
				}
			});

			return _job.getId();
		}
		finally {
			// Remove temporary directory which were older than 2 days
			this.removeOldTemporaryFiles(_webContextStoragePath);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.UserManagement#editJob(com.lasso.rest.model.
	 * datasource.Account, com.lasso.rest.model.api.request.EditOrderRequest)
	 */
	@Override
	public void editOrder(Account __user, EditOrderRequest __editJobRequest)
	        throws UnirestException, IOException {
		Job _job = this.jobDAO.getJobOfUserById(__user.getId(), __editJobRequest.getIdJob());
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else if (_job.getStep().byteValue() > JobStepConstant.JOB_STEP_CONFIRM.getStepCode()) {
			throw new ForbiddenException(
			        "This job cannot edit at "
			                + JobStepConstant.getByCode(_job.getStep()).getStepName(),
			        Response.status(Status.FORBIDDEN).build());
		}
		else {
			String _webContextStoragePath = this.genericManagement
			        .loadWebContextStoragePath(__user.getAppSession());
			try {
				// Update brief of job
				_job.setFee(_job.getBudget() * this.genericManagement.getServiceFee() / 100);
				_job.update(__editJobRequest);
				this.jobDAO.updateJob(_job);

				// Copy portfolio images from temporary directory to resource
				// directory
				__editJobRequest.getReference().forEach(_tempFileName -> {
					File _tempFile = new File(_webContextStoragePath + this.temporaryStoragePath
					        + "/" + _tempFileName);
					if (_tempFile.exists()) {
						// Move original file
						try {
							this.uploadImageManagement.copyImage(_tempFile, new File(
							        _webContextStoragePath + this.jobStoragePath + "/Original"));

							// Resize into 3 other size
							File _icon = new File(_webContextStoragePath + this.jobStoragePath
							        + "/Icon/" + _tempFileName);
							this.uploadImageManagement.resizeImage(_tempFile, _icon, 120, 184);
							File _small = new File(_webContextStoragePath + this.jobStoragePath
							        + "/Small/" + _tempFileName);
							this.uploadImageManagement.resizeImage(_tempFile, _small, 182, 280);
							File _retina = new File(_webContextStoragePath + this.jobStoragePath
							        + "/Retina/" + _tempFileName);
							this.uploadImageManagement.resizeImage(_tempFile, _retina, 364, 560);
						}
						catch (IOException _ex) {
							Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
						}
					}
					else {
						Logger.getLogger(this.getClass())
						        .warn("Job temporary file not exist. Check this path: "
						                + _tempFile.getAbsolutePath());
					}
				});
			}
			finally {
				// Remove temporary directory which were older than 2 days
				this.removeOldTemporaryFiles(_webContextStoragePath);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#getJobDataOfUserById(com.lasso.rest
	 * .model.datasource. Account, int)
	 */
	@Override
	public Object[] getJobDataOfUserById(Account __user, int __idJob) throws NotFoundException {
		Job _job = this.jobDAO.getJobOfUserById(__user.getId(), __idJob);
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else {
			String _designerName = "";
			JobsAccount _jobsAccount = ImplUserManagement.this.jobAccountDAO
			        .getAcceptByJobId(_job.getId());
			_designerName = _jobsAccount == null ? ""
			        : ImplUserManagement.this.accountDAO.getAccountById(_jobsAccount.getAccountId())
			                .getName();
			List<Integer> _styleIds = new ArrayList<>();
			ImplUserManagement.this.jobStyleDAO.getListJobStylesByJobId(_job.getId())
			        .forEach(_jobStyle -> _styleIds.add(_jobStyle.getStyleId()));
			List<Style> _styles = ImplUserManagement.this.styleDAO.getListByByListIds(_styleIds);

			Type _type = ImplUserManagement.this.typeDAO.getTypeById(_job.getTypeId());

			Object[] _data = { _job, _designerName, _styles, _type.getTitle() };
			return _data;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.UserManagement#getJobRatingDetail(int)
	 */
	@Override
	public Object[] getJobRatingDetail(int __idJob) {
		JobsAccount _jobsAccount = this.jobAccountDAO.getAcceptByJobId(__idJob);
		if (_jobsAccount == null) {
			throw new NotFoundException("Job account not found");
		}
		Account _designer = this.accountDAO.getAccountById(_jobsAccount.getAccountId());
		if (_designer == null) {
			throw new NotFoundException("Designer not found");
		}
		AccountsRating _rating = this.accountRatingDAO.getByAccountAndJob(_designer.getId(),
		        __idJob);

		Object[] _data = { _designer, _rating };
		return _data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#getListJobsDataOfUser(com.lasso.
	 * rest.model.datasource. Account)
	 */
	@Override
	public List<Object[]> getListJobsDataOfUser(Account __user) {
		List<Object[]> _datas = new ArrayList<>();
		List<Job> _jobs = this.jobDAO.getListJobsOfUser(__user.getId());
		if (_jobs.isEmpty()) {
			return new ArrayList<>();
		}
		else {
			_jobs.forEach(_job -> {
				try {
					String _designerName = "";
					JobsAccount _jobsAccount = ImplUserManagement.this.jobAccountDAO
					        .getAcceptByJobId(_job.getId());
					if (_jobsAccount != null) {
						_designerName = ImplUserManagement.this.accountDAO
						        .getAccountById(_jobsAccount.getAccountId()).getName();
					}
					List<Integer> _styleIds = new ArrayList<>();
					ImplUserManagement.this.jobStyleDAO.getListJobStylesByJobId(_job.getId())
					        .forEach(_jobStyle -> _styleIds.add(_jobStyle.getStyleId()));
					List<Style> _styles = ImplUserManagement.this.styleDAO
					        .getListByByListIds(_styleIds);
					// if (_styles.isEmpty()) {
					// return;
					// }
					Type _type = ImplUserManagement.this.typeDAO.getTypeById(_job.getTypeId());
					if (_type == null) {
						return;
					}
					Category _category = ImplUserManagement.this.categoryDAO
					        .getCategoryById(_job.getCategoryId());
					if (_category == null) {
						return;
					}
					Object[] _data = { _job, _designerName, _styles, _type, _category };

					_datas.add(_data);
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
	 * com.lasso.rest.service.UserManagement#getListPortfoliosByCondition(int,
	 * int, int, java.util.List, int, java.lang.Number[])
	 */
	@Override
	public List<Object[]> getListPortfoliosByCondition(int __index, int __size, int __idCategory,
	        List<Integer> _idsStyle, int __idType, Number[] __filter) {
		List<Object[]> _datas = new ArrayList<>();

		List<Integer> _idsType = new ArrayList<>();
		_idsType.add(__idType);

		// if (__filter[0].intValue() > 0) {
		// _idsStyle.remove(__filter[0]);
		// }

		// Get portfolio by conditions
		List<PortfolioType> _portfolioTypes = this.portfolioTypeDAO.getListByIdTypes(_idsType);
		List<Portfolio> _portfolios = this.portfolioDAO.searchPortfolios(__index, __size,
		        __idCategory, _idsStyle, _portfolioTypes, __filter[1].doubleValue());

		// Get desiger of this portolios
		Set<Account> _tmpSetAccounts = new HashSet<>();
		_portfolios.forEach(__portfolio -> {
			Account _designer = ImplUserManagement.this.accountDAO
			        .getAccountById(__portfolio.getAccountId());
			if (!_tmpSetAccounts.contains(_designer)) {
				Object[] _data = { __portfolio, _designer };

				_datas.add(_data);
				_tmpSetAccounts.add(_designer);
			}
		});

		// Sort data by designer rank
		_datas.sort(new Comparator<Object[]>() {

			@Override
			public int compare(Object[] __o1, Object[] __o2) {
				try {
					Account _designer1 = (Account) __o1[1];
					Account _designer2 = (Account) __o2[1];
					switch (__filter[2].intValue()) {
						case 1:
							return _designer2.getFeatured().compareTo(_designer1.getFeatured());
						case 2:
							return _designer2.getRecommended()
							        .compareTo(_designer1.getRecommended());
						case 3:
							return _designer2.getRewards().compareTo(_designer1.getRewards());

						default:
							return (_designer2.getFeatured().intValue()
							        + _designer2.getRecommended().intValue()
							        + _designer2.getRewards().intValue())
							        - (_designer1.getFeatured().intValue()
							                + _designer1.getRecommended().intValue()
							                + _designer1.getRewards().intValue());
					}
				}
				catch (Exception _ex) {
					Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
					return 0;
				}
			}
		});

		return _datas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.UserManagement#getOrderDataById(int)
	 */
	@Override
	public Object[] getOrderDataById(int __idJob) {
		Job _job = this.jobDAO.getJobById(__idJob);
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		List<Integer> _styleIds = new ArrayList<>();
		ImplUserManagement.this.jobStyleDAO.getListJobStylesByJobId(__idJob)
		        .forEach(_jobStyle -> _styleIds.add(_jobStyle.getStyleId()));
		List<Style> _styles = ImplUserManagement.this.styleDAO.getListByByListIds(_styleIds);
		if (_styleIds.isEmpty()) {
			throw new NotFoundException("Styles not found");
		}
		Type _type = ImplUserManagement.this.typeDAO.getTypeById(_job.getTypeId());
		if (_type == null) {
			throw new NotFoundException("Type not found");
		}
		Category _category = this.categoryDAO.getCategoryById(_job.getCategoryId());
		if (_category == null) {
			throw new NotFoundException("Category not found");
		}
		List<Object[]> _designersJobs = new ArrayList<>();
		this.jobAccountDAO.getByJobId(__idJob).forEach(_jobsAccount -> {
			Account _designer = ImplUserManagement.this.accountDAO
			        .getAccountById(_jobsAccount.getAccountId());
			if (_designer != null) {
				List<Portfolio> _portfolios = ImplUserManagement.this.portfolioDAO
				        .getAllPortfoliosOfAccount(_designer);
				_portfolios.stream().allMatch(
				        _portfolio -> _portfolio.getCategoryId().equals(_category.getId()));

				Object[] designerJob = { _jobsAccount, _designer,
				        _portfolios.isEmpty() ? new Portfolio() : _portfolios.get(0) };
				_designersJobs.add(designerJob);
			}
		});
		float _serviceFee = this.genericManagement.getServiceFee();
		boolean _isReject = false; // Add this to avoid error of message detail
		Object[] _orderData = { _job, _designersJobs, _styles, _type, _category, _serviceFee,
		        _isReject };
		return _orderData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#getPaymentDetailOfOrder(com.lasso.
	 * rest.model.datasource .Account, int)
	 */
	@Override
	public Object[] getPaymentDetailOfOrder(Account __user, int __idJob) {
		Job _job = this.jobDAO.getJobOfUserById(__user.getId(), __idJob);
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else {
			Object[] _data = { _job, null, null, null, null };
			PromoHistory _promoHistory = this.promoDAO.getPromoHistroyByJobId(__idJob);
			if (_promoHistory != null) {
				PromoCode _promoCode = this.promoDAO
				        .getPromoCodeById(_promoHistory.getPromoCodeId());
				_data[1] = _promoCode;
			}
			List<Style> _styles = new ArrayList<>();
			this.jobStyleDAO.getListJobStylesByJobId(__idJob).forEach(
			        _jobStyle -> _styles.add(this.styleDAO.getById(_jobStyle.getStyleId())));
			_data[2] = _styles;
			Type _type = this.typeDAO.getTypeById(_job.getTypeId());
			_data[3] = _type;
			Category _category = this.categoryDAO.getCategoryById(_job.getCategoryId());
			_data[4] = _category;

			return _data;
		}
	}

	/**
	 * Sets the account rating DAO.
	 *
	 * @param __accountRatingDAO
	 *        the accountRatingDAO to set
	 */
	public void setAccountRatingDAO(AccountRatingDAO __accountRatingDAO) {
		this.accountRatingDAO = __accountRatingDAO;
	}

	/**
	 * Sets the email util.
	 *
	 * @param __emailUtil
	 *        the new email util
	 */
	public void setEmailUtil(EmailUtil __emailUtil) {
		this.emailUtil = __emailUtil;
	}

	/**
	 * Sets the http host.
	 *
	 * @param __httpHost
	 *        the new http host
	 */
	public void setHttpHost(String __httpHost) {
		this.httpHost = __httpHost;
	}

	// /**
	// * Sets the message management.
	// *
	// * @param __messageManagement
	// * the messageManagement to set
	// */
	// public void setMessageManagement(MessageManagement __messageManagement) {
	// this.messageManagement = __messageManagement;
	// }

	/**
	 * Sets the promo DAO.
	 *
	 * @param __promoDAO
	 *        the new promo DAO
	 */
	public void setPromoDAO(PromoDAO __promoDAO) {
		this.promoDAO = __promoDAO;
	}

}
