package com.lasso.rest.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.ws.rs.NotAllowedException;
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
import com.lasso.rest.dao.PromoDAO;
import com.lasso.rest.model.api.request.ChooseDesignerForOrderRequest;
import com.lasso.rest.model.api.request.ConfirmOrderRequest;
import com.lasso.rest.model.api.request.CreateNewOrderRequest;
import com.lasso.rest.model.api.request.EditOrderRequest;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.JobsAccount;
import com.lasso.rest.model.datasource.JobsType;
import com.lasso.rest.model.datasource.Message;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.PortfolioType;
import com.lasso.rest.model.datasource.PromoCode;
import com.lasso.rest.model.datasource.PromoHistory;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
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

	/** The promo DAO. */
	@Autowired
	private PromoDAO promoDAO;

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
		else if (!_job.getStep().equals(JobStepConstant.JOB_STEP_BRIEF)) {
			throw new NotAllowedException(
			        "This job cannot edit at "
			                + JobStepConstant.getByCode(_job.getStep()).getStepName(),
			        Response.status(Status.FORBIDDEN).build());
		}
		else {
			// update designer chosen by user
			List<JobsAccount> _jobsAccounts = new ArrayList<>();
			List<Message> _messages = new ArrayList<>();
			__chooseDesignerForJobRequest.getDesignerIds()
			        .stream().filter(_idDesigner -> this.accountDAO.getAccountById(_idDesigner)
			                .getRole().equals(Constant.ROLE_DESIGNER))
			        .forEach(new Consumer<Integer>() {

				        @Override
				        public void accept(Integer __idDesigner) {
					        _jobsAccounts.add(new JobsAccount(__idDesigner, _job.getId()));

					        String _title = "New offer from " + __user.getName();
					        String _message = "Hi, please check this offer.";
					        _messages.add(new Message(__user.getId(), _job.getId(), _message,
					                _title, __idDesigner));
				        }
			        });
			// Save designer seleted and message will send to them
			this.jobAccountDAO.saveJobAccounts(_jobsAccounts);
			this.messageDAO.saveMessages(_messages);

			// TODO All setting success, send message to designer

			// Everything success, update step of job
			_job.setStep(JobStepConstant.JOB_STEP_CHOOSE_DESIGNER.getStepCode());
			this.jobDAO.updateJob(_job);
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
	 * com.lasso.rest.service.UserManagement#createNewOffer(com.lasso.rest.model.datasource.Account,
	 * com.lasso.rest.model.api.request.CreateNewOrderRequest)
	 */
	@Override
	public void createNewOrder(Account __user, CreateNewOrderRequest __createNewOrderRequest)
	        throws UnirestException, IOException {
		String _webContextStoragePath = this.genericManagement
		        .loadWebContextStoragePath(__user.getAppSession());
		try {
			Job _job = new Job(__createNewOrderRequest);
			_job.setFee(_job.getBudget() * this.genericManagement.getServiceFee() / 100);
			_job.setAccountId(__user.getId());
			Integer _idJob = this.jobDAO.saveJob(_job);

			List<JobsType> _jobsTypes = new ArrayList<>();
			__createNewOrderRequest.getIdTypes()
			        .forEach(_idType -> _jobsTypes.add(new JobsType(_idJob, _idType)));
			this.jobTypeDAO.saveListJobsTypes(_jobsTypes);

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
		else if (!_job.getStep().equals(JobStepConstant.JOB_STEP_BRIEF)) {
			throw new NotAllowedException(
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
				this.jobTypeDAO.removeJobsTypesByJobId(_job.getId());

				// Update new job types
				List<JobsType> _jobsTypes = new ArrayList<>();
				__editJobRequest.getIdTypes()
				        .forEach(_idType -> _jobsTypes.add(new JobsType(_job.getId(), _idType)));
				this.jobTypeDAO.saveListJobsTypes(_jobsTypes);

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
			List<Integer> _typeIds = new ArrayList<>();
			ImplUserManagement.this.jobTypeDAO.getListJobsTypesByJobId(_job.getId())
			        .forEach(_jt -> _typeIds.add(_jt.getTypeId()));
			List<Type> _types = ImplUserManagement.this.typeDAO.getListByByListIds(_typeIds);

			Style _style = ImplUserManagement.this.styleDAO.getById(_job.getStyleId());

			Object[] _data = { _job, _designerName, _types, _style.getTitle() };
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
			throw new NotFoundException("Data not found");
		}
		else {
			_jobs.forEach(new Consumer<Job>() {

				@Override
				public void accept(Job __job) {
					String _designerName = "";
					JobsAccount _jobsAccount = ImplUserManagement.this.jobAccountDAO
					        .getByJobId(__job.getId());
					_designerName = _jobsAccount == null ? ""
					        : ImplUserManagement.this.accountDAO
					                .getAccountById(_jobsAccount.getAccountId()).getName();
					List<Integer> _typeIds = new ArrayList<>();
					ImplUserManagement.this.jobTypeDAO.getListJobsTypesByJobId(__job.getId())
					        .forEach(_jt -> _typeIds.add(_jt.getTypeId()));
					List<Type> _types = ImplUserManagement.this.typeDAO
					        .getListByByListIds(_typeIds);

					Style _style = ImplUserManagement.this.styleDAO.getById(__job.getStyleId());

					Object[] _data = { __job, _designerName, _types, _style.getTitle() };
					_datas.add(_data);
				}
			});

			return _datas;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.UserManagement#getListPortfoliosByCondition(int, int, int, int,
	 * java.util.List)
	 */
	@Override
	public List<Object[]> getListPortfoliosByCondition(int __index, int __size, int __idCategory,
	        int __idStyle, List<Integer> __idsType) {
		List<Object[]> _datas = new ArrayList<>();

		// Get portfolio by conditions
		List<PortfolioType> _portfolioTypes = this.portfolioTypeDAO.getListByIdTypes(__idsType);
		List<Portfolio> _portfolios = this.portfolioDAO.searchPortfolios(__index, __size,
		        __idCategory, __idStyle, _portfolioTypes);

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
					return ((Account) __o2[1]).getRewards() - ((Account) __o1[1]).getRewards();
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
			throw new NullPointerException("Job not found");
		}
		List<Object[]> _designersJobs = new ArrayList<>();
		this.jobAccountDAO.getByOfferId(__idJob).forEach(new Consumer<JobsAccount>() {

			@Override
			public void accept(JobsAccount __jobsAccount) {
				Account _designer = ImplUserManagement.this.accountDAO
				        .getAccountById(__jobsAccount.getAccountId());
				if (_designer != null) {
					Object[] designerJob = { __jobsAccount, _designer };
					_designersJobs.add(designerJob);
				}
			}
		});
		List<Integer> _typeIds = new ArrayList<>();
		ImplUserManagement.this.jobTypeDAO.getListJobsTypesByJobId(__idJob)
		        .forEach(_jt -> _typeIds.add(_jt.getTypeId()));
		List<Type> _types = ImplUserManagement.this.typeDAO.getListByByListIds(_typeIds);
		if (_typeIds.isEmpty()) {
			throw new NotFoundException("Types not found");
		}
		Style _style = ImplUserManagement.this.styleDAO.getById(_job.getStyleId());
		if (_style == null) {
			throw new NullPointerException("Style not found");
		}
		Category _category = this.categoryDAO.getCategoryById(_job.getCategoryId());
		if (_category == null) {
			throw new NullPointerException("Category not found");
		}

		Object[] _orderData = { _job, _designersJobs, _types, _style, _category };
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
			Object[] _data = { _job, null, null, null };
			PromoHistory _promoHistory = this.promoDAO.getPromoHistroyByJobId(__idJob);
			if (_promoHistory != null) {
				_data[1] = _promoHistory;
				PromoCode _promoCode = this.promoDAO
				        .getPromoCodeById(_promoHistory.getPromoCodeId());
				_data[2] = _promoCode;
			}
			List<Type> _types = new ArrayList<>();
			this.jobTypeDAO.getListJobsTypesByJobId(__idJob).forEach(
			        _jobsType -> _types.add(this.typeDAO.getTypeById(_jobsType.getTypeId())));
			_data[3] = _types;
			Style _style = this.styleDAO.getById(_job.getStyleId());
			_data[4] = _style;
			return _data;
		}
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
