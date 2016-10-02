package com.lasso.rest.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.FileUtils;
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
import com.lasso.rest.service.MessageManagement;
import com.lasso.rest.service.UserManagement;
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

	/** The message management. */
	@Autowired
	private MessageManagement	messageManagement;

	/** The promo DAO. */
	@Autowired
	private PromoDAO			promoDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#applyPayment(com.lasso.rest.model.datasource.Account,
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
			this.jobDAO.updateJob(_job);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#applyPromoCodeForOrder(com.lasso.rest.model.datasource.
	 * Account, com.lasso.rest.model.api.request.UsePromoCodeForOrder)
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
	 * com.lasso.rest.service.UserManagement#chooseDesignerForOrder(com.lasso.rest.model.datasource.
	 * Account, com.lasso.rest.model.api.request.ChooseDesignerForOrderRequest)
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
			List<Message> _messages = new ArrayList<>();
			List<Object[]> _pushs = new ArrayList<>();
			__chooseDesignerForJobRequest.getDesignerIds().forEach(_idDesigner -> {
				Account _designer = this.accountDAO.getAccountById(_idDesigner);
				if (_designer.getRole().equals(Constant.ROLE_DESIGNER)) {
					_jobsAccounts.add(new JobsAccount(_idDesigner, _job.getId()));
					String _title = "New offer from " + __user.getName();
					String _message = "Hi, please check this offer.";
					_messages.add(new Message(__user.getId(), _job.getId(), _message, _title,
							_idDesigner));

					Object[] data = { _designer, _message };
					_pushs.add(data);
				}
			});
			// Save designer seleted and message will send to them
			this.jobAccountDAO.saveJobAccounts(_jobsAccounts);
			this.messageDAO.saveMessages(_messages);

			// Everything success, update step of job
			_job.setStep(JobStepConstant.JOB_STEP_CHOOSE_DESIGNER.getStepCode());
			this.jobDAO.updateJob(_job);

			// All setting success, send message to designer
			new Thread(new Runnable() {

				@Override
				public void run() {
					_pushs.forEach(_push -> {
						SendPushRequest _pushRequest = new SendPushRequest();
						_pushRequest.setNotification(
								new PushNotification(((Message) _push[1]).getTitle(),
										((Message) _push[1]).getMessage()));
						_pushRequest.setTo(((Account) _push[1]).getDeviceId());
						try {
							ImplUserManagement.this.messageManagement.sendPush(_pushRequest);
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
	 * @see com.lasso.rest.service.UserManagement#completeJob(com.lasso.rest.model.api.request.
	 * CompleteJobRequest)
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
				__completeJobRequest.getCommunication(), __completeJobRequest.getExperience(),
				__completeJobRequest.getIdJob());
		this.accountRatingDAO.saveRating(_accountsRating);
		Account _designer = this.accountDAO.getAccountById(__completeJobRequest.getIdDesigner());
		if (_designer != null) {
			_designer.setRewards(__completeJobRequest.getQuality());
			this.accountDAO.updateAccount(_designer);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#confirmOrder(com.lasso.rest.model.datasource.Account,
	 * com.lasso.rest.model.api.request.ConfirmOrderRequest)
	 */
	@Override
	public void confirmOrder(Account __user, ConfirmOrderRequest __confirmOrderRequest) {
		Job _job = this.jobDAO.getJobOfUserById(__user.getId(), __confirmOrderRequest.getIdJob());
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else if (this.jobAccountDAO.getByJobId(__confirmOrderRequest.getIdJob()) != null) {
			throw new IllegalArgumentException(
					"This job was confirm before. Denied re-other confirm");
		}
		else {
			JobsAccount _jobsAccount = this.jobAccountDAO.getByJobAndDesignerId(
					__confirmOrderRequest.getIdJob(), __confirmOrderRequest.getIdDesigner());
			if (_jobsAccount == null) {
				throw new NullPointerException(
						"Order to this designer not valid, or designer do not accept this order");
			}
			else {
				_jobsAccount.setConfirm(JobConfirmationConstant.JOB_CONFIRM.getCode());
				this.jobAccountDAO.saveJobAccount(_jobsAccount);

				// Check if have counter offer, re-calculate order amount
				double _counterOffer = _jobsAccount.getCounter();
				if (_counterOffer > 0) {
					_job.setBudget(_counterOffer);
					_job.setFee(_counterOffer * this.genericManagement.getServiceFee() / 100);

					this.jobDAO.updateJob(_job);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#createNewOrder(com.lasso.rest.model.datasource.Account,
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

			// Copy portfolio images from temporary directory to resource directory
			for (String _tempFileName : __createNewOrderRequest.getReference()) {
				File _tempFile = new File(
						_webContextStoragePath + this.temporaryStoragePath + "/" + _tempFileName);
				if (_tempFile.exists()) {
					// Move original file
					FileUtils.copyFileToDirectory(_tempFile,
							new File(_webContextStoragePath + this.jobStoragePath + "/Original"),
							false);

					// Resize into 3 other size
					File _icon = new File(_webContextStoragePath + this.jobStoragePath + "/Icon/"
							+ _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _icon, 120D, 184D);
					File _small = new File(_webContextStoragePath + this.jobStoragePath + "/Small/"
							+ _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _small, 182D, 280D);
					File _retina = new File(_webContextStoragePath + this.jobStoragePath
							+ "/Retina/" + _tempFileName);
					this.uploadImageManagement.resizeImage(_tempFile, _retina, 364D, 560D);
				}
				else {
					Logger.getLogger(this.getClass())
					.warn("Job temporary file not exist. Check this path: "
							+ _tempFile.getAbsolutePath());
				}
			}

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
	 * @see com.lasso.rest.service.UserManagement#editJob(com.lasso.rest.model.datasource.Account,
	 * com.lasso.rest.model.api.request.EditOrderRequest)
	 */
	@Override
	public void editOrder(Account __user, EditOrderRequest __editJobRequest)
			throws UnirestException, IOException {
		Job _job = this.jobDAO.getJobOfUserById(__user.getId(), __editJobRequest.getIdJob());
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else if (!_job.getStep().equals(JobStepConstant.JOB_STEP_BRIEF.getStepCode())) {
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

				// Remove old job types
				this.jobStyleDAO.removeJobStyleByJobId(_job.getId());

				// Update new job types
				List<JobsStyle> _jobStyles = new ArrayList<>();
				__editJobRequest.getIdStyles()
				.forEach(_id -> _jobStyles.add(new JobsStyle(_job.getId(), _id)));
				this.jobStyleDAO.saveListJobStyles(_jobStyles);

				// Copy portfolio images from temporary directory to resource directory
				for (String _tempFileName : __editJobRequest.getReference()) {
					File _tempFile = new File(_webContextStoragePath + this.temporaryStoragePath
							+ "/" + _tempFileName);
					if (_tempFile.exists()) {
						// Move original file
						FileUtils.copyFileToDirectory(_tempFile,
								new File(
										_webContextStoragePath + this.jobStoragePath + "/Original"),
								false);

						// Resize into 3 other size
						File _icon = new File(_webContextStoragePath + this.jobStoragePath
								+ "/Icon/" + _tempFileName);
						this.uploadImageManagement.resizeImage(_tempFile, _icon, 120D, 184D);
						File _small = new File(_webContextStoragePath + this.jobStoragePath
								+ "/Small/" + _tempFileName);
						this.uploadImageManagement.resizeImage(_tempFile, _small, 182D, 280D);
						File _retina = new File(_webContextStoragePath + this.jobStoragePath
								+ "/Retina/" + _tempFileName);
						this.uploadImageManagement.resizeImage(_tempFile, _retina, 364D, 560D);
					}
					else {
						Logger.getLogger(this.getClass())
						.warn("Job temporary file not exist. Check this path: "
								+ _tempFile.getAbsolutePath());
					}
				}
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
	 * com.lasso.rest.service.UserManagement#getJobDataOfUserById(com.lasso.rest.model.datasource.
	 * Account, int)
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
					.getByJobId(_job.getId());
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
	 * @see
	 * com.lasso.rest.service.UserManagement#getListJobsDataOfUser(com.lasso.rest.model.datasource.
	 * Account)
	 */
	@Override
	public List<Object[]> getListJobsDataOfUser(Account __user) {
		List<Object[]> _datas = new ArrayList<>();
		List<Job> _jobs = this.jobDAO.getListJobsOfUser(__user.getId());
		if (_jobs.isEmpty()) {
			return new ArrayList<>();
		}
		else {
			_jobs.forEach(new Consumer<Job>() {

				@Override
				public void accept(Job __job) {
					try {
						String _designerName = "";
						JobsAccount _jobsAccount = ImplUserManagement.this.jobAccountDAO
								.getByJobId(__job.getId());
						if (_jobsAccount != null) {
							Account _designer = ImplUserManagement.this.accountDAO
									.getAccountById(_jobsAccount.getAccountId());
							if (_designer != null) {
								_designerName = _designer.getName();
							}
							else {
								return;
							}
						}
						List<Integer> _styleIds = new ArrayList<>();
						ImplUserManagement.this.jobStyleDAO.getListJobStylesByJobId(__job.getId())
						.forEach(_jobStyle -> _styleIds.add(_jobStyle.getStyleId()));
						List<Style> _styles = ImplUserManagement.this.styleDAO
								.getListByByListIds(_styleIds);
						if (_styles.isEmpty()) {
							return;
						}
						Type _type = ImplUserManagement.this.typeDAO.getTypeById(__job.getTypeId());
						if (_type == null) {
							return;
						}
						Category _category = ImplUserManagement.this.categoryDAO
								.getCategoryById(__job.getCategoryId());
						if (_category == null) {
							return;
						}
						Object[] _data = { __job, _designerName, _styles, _type, _category };

						_datas.add(_data);
					}
					catch (Exception _ex) {
						Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
					}
				}
			});

			return _datas;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.UserManagement#getListPortfoliosByCondition(int, int, int,
	 * java.util.List, int, java.lang.Number[])
	 */
	@Override
	public List<Object[]> getListPortfoliosByCondition(int __index, int __size, int __idCategory,
			List<Integer> _idsStyle, int __idType, Number[] __filter) {
		List<Object[]> _datas = new ArrayList<>();

		List<Integer> _idsType = new ArrayList<>();
		_idsType.add(__idType);

		if (__filter[0].intValue() > 0) {
			_idsStyle.remove(__filter[0]);
		}

		// Get portfolio by conditions
		List<PortfolioType> _portfolioTypes = this.portfolioTypeDAO.getListByIdTypes(_idsType);
		List<Portfolio> _portfolios = this.portfolioDAO.searchPortfolios(__index, __size,
				__idCategory, _idsStyle, _portfolioTypes, __filter[1].doubleValue());

		// Get desiger of this portolios
		Set<Account> _tmpSetAccounts = new HashSet<>();
		_portfolios.forEach(new Consumer<Portfolio>() {

			@Override
			public void accept(Portfolio __portfolio) {
				Account _designer = ImplUserManagement.this.accountDAO
						.getAccountById(__portfolio.getAccountId());
				if (!_tmpSetAccounts.contains(_designer)) {
					Object[] _data = { __portfolio, _designer };

					_datas.add(_data);
					_tmpSetAccounts.add(_designer);
				}
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
		this.jobAccountDAO.getByOfferId(__idJob).forEach(_jobsAccount -> {
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
		Object[] _orderData = { _job, _designersJobs, _styles, _type, _category, _serviceFee };
		return _orderData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#getPaymentDetailOfOrder(com.lasso.rest.model.datasource
	 * .Account, int)
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
	 * @param __accountRatingDAO the accountRatingDAO to set
	 */
	public void setAccountRatingDAO(AccountRatingDAO __accountRatingDAO) {
		this.accountRatingDAO = __accountRatingDAO;
	}

	/**
	 * Sets the message management.
	 *
	 * @param __messageManagement the messageManagement to set
	 */
	public void setMessageManagement(MessageManagement __messageManagement) {
		this.messageManagement = __messageManagement;
	}

	/**
	 * Sets the promo DAO.
	 *
	 * @param __promoDAO the new promo DAO
	 */
	public void setPromoDAO(PromoDAO __promoDAO) {
		this.promoDAO = __promoDAO;
	}

}
