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
import com.lasso.define.JobStepConstant;
import com.lasso.rest.dao.JobAccountDAO;
import com.lasso.rest.dao.JobDAO;
import com.lasso.rest.dao.JobTypeDAO;
import com.lasso.rest.dao.MessageDAO;
import com.lasso.rest.model.api.request.ChooseDesignerForJobRequest;
import com.lasso.rest.model.api.request.CreateNewJobRequest;
import com.lasso.rest.model.api.request.EditJobRequest;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.JobsAccount;
import com.lasso.rest.model.datasource.JobsType;
import com.lasso.rest.model.datasource.Message;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.PortfolioType;
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

	/** The job account DAO. */
	@Autowired
	private JobAccountDAO	jobAccountDAO;

	/** The job DAO. */
	@Autowired
	private JobDAO			jobDAO;

	/** The job storage path. */
	private String			jobStoragePath;

	/** The job type DAO. */
	@Autowired
	private JobTypeDAO		jobTypeDAO;

	@Autowired
	private MessageDAO		messageDAO;

	/**
	 * Sets the message DAO.
	 *
	 * @param __messageDAO the new message DAO
	 */
	public void setMessageDAO(MessageDAO __messageDAO) {
		this.messageDAO = __messageDAO;
	}

	/**
	 * Instantiates a new impl user management.
	 */
	public ImplUserManagement() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.UserManagement#createNewJob(com.lasso.rest.model.datasource.Account,
	 * com.lasso.rest.model.api.request.CreateNewJobRequest)
	 */
	@Override
	public void createNewJob(Account __user, CreateNewJobRequest __createNewJobRequest)
	        throws UnirestException, IOException {
		String _webContextStoragePath = this.getGenericManagement()
		        .loadWebContextStoragePath(__user.getAppSession());
		try {
			Job _job = new Job(__createNewJobRequest);
			_job.setFee(_job.getBudget() * this.getGenericManagement().getServiceFee() / 100);
			_job.setAccountId(__user.getId());
			Integer _idJob = this.jobDAO.saveJob(_job);

			List<JobsType> _jobsTypes = new ArrayList<>();
			__createNewJobRequest.getIdTypes()
			        .forEach(_idType -> _jobsTypes.add(new JobsType(_idJob, _idType)));
			this.jobTypeDAO.saveListJobsTypes(_jobsTypes);

			// Copy portfolio images from temporary directory to resource directory
			for (String _tempFileName : __createNewJobRequest.getReference()) {
				File _tempFile = new File(_webContextStoragePath + this.getTemporaryStoragePath()
				        + "/" + _tempFileName);
				if (_tempFile.exists()) {
					// Move original file
					FileUtils.copyFileToDirectory(_tempFile,
					        new File(_webContextStoragePath + this.jobStoragePath + "/Original"),
					        false);

					// Resize into 3 other size
					File _icon = new File(_webContextStoragePath + this.jobStoragePath + "/Icon/"
					        + _tempFileName);
					this.getUploadImageManagement().resizeImage(_tempFile, _icon, 120D, 184D);
					File _small = new File(_webContextStoragePath + this.jobStoragePath + "/Small/"
					        + _tempFileName);
					this.getUploadImageManagement().resizeImage(_tempFile, _small, 182D, 280D);
					File _retina = new File(_webContextStoragePath + this.jobStoragePath
					        + "/Retina/" + _tempFileName);
					this.getUploadImageManagement().resizeImage(_tempFile, _retina, 364D, 560D);
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
	 * com.lasso.rest.model.api.request.EditJobRequest)
	 */
	@Override
	public void editJob(Account __user, EditJobRequest __editJobRequest)
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
			String _webContextStoragePath = this.getGenericManagement()
			        .loadWebContextStoragePath(__user.getAppSession());
			try {
				// Update brief of job
				_job.setFee(_job.getBudget() * this.getGenericManagement().getServiceFee() / 100);
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
					File _tempFile = new File(_webContextStoragePath
					        + this.getTemporaryStoragePath() + "/" + _tempFileName);
					if (_tempFile.exists()) {
						// Move original file
						FileUtils.copyFileToDirectory(_tempFile,
						        new File(
						                _webContextStoragePath + this.jobStoragePath + "/Original"),
						        false);

						// Resize into 3 other size
						File _icon = new File(_webContextStoragePath + this.jobStoragePath
						        + "/Icon/" + _tempFileName);
						this.getUploadImageManagement().resizeImage(_tempFile, _icon, 120D, 184D);
						File _small = new File(_webContextStoragePath + this.jobStoragePath
						        + "/Small/" + _tempFileName);
						this.getUploadImageManagement().resizeImage(_tempFile, _small, 182D, 280D);
						File _retina = new File(_webContextStoragePath + this.jobStoragePath
						        + "/Retina/" + _tempFileName);
						this.getUploadImageManagement().resizeImage(_tempFile, _retina, 364D, 560D);
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

	@Override
	public void chooseDesignerForJob(Account __user,
	        ChooseDesignerForJobRequest __chooseDesignerForJobRequest) {
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
			        .stream().filter(_idDesigner -> getAccountDAO().getAccountById(_idDesigner)
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
			        : ImplUserManagement.this.getAccountDAO()
			                .getAccountById(_jobsAccount.getAccountId()).getName();
			List<Integer> _typeIds = new ArrayList<>();
			ImplUserManagement.this.jobTypeDAO.getListJobsTypesByJobId(_job.getId())
			        .forEach(_jt -> _typeIds.add(_jt.getTypeId()));
			List<Type> _types = ImplUserManagement.this.getTypeDAO().getListByByListIds(_typeIds);

			Style _style = ImplUserManagement.this.getStyleDAO().getById(_job.getStyleId());

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
					        : ImplUserManagement.this.getAccountDAO()
					                .getAccountById(_jobsAccount.getAccountId()).getName();
					List<Integer> _typeIds = new ArrayList<>();
					ImplUserManagement.this.jobTypeDAO.getListJobsTypesByJobId(__job.getId())
					        .forEach(_jt -> _typeIds.add(_jt.getTypeId()));
					List<Type> _types = ImplUserManagement.this.getTypeDAO()
					        .getListByByListIds(_typeIds);

					Style _style = ImplUserManagement.this.getStyleDAO()
					        .getById(__job.getStyleId());

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
		List<PortfolioType> _portfolioTypes = this.getPortfolioTypeDAO()
		        .getListByIdTypes(__idsType);
		List<Portfolio> _portfolios = this.getPortfolioDAO().searchPortfolios(__index, __size,
		        __idCategory, __idStyle, _portfolioTypes);

		// Get desiger of this portolios
		Set<Account> _tmpSetAccounts = new HashSet<>();
		_portfolios.forEach(new Consumer<Portfolio>() {

			@Override
			public void accept(Portfolio __portfolio) {
				Account _designer = ImplUserManagement.this.getAccountDAO()
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
					Logger.getLogger(getClass()).warn("Unwanted error", _ex);
					return 0;
				}
			}
		});

		return _datas;
	}

	/**
	 * Sets the job account DAO.
	 *
	 * @param __jobAccountDAO the new job account DAO
	 */
	public void setJobAccountDAO(JobAccountDAO __jobAccountDAO) {
		this.jobAccountDAO = __jobAccountDAO;
	}

	/**
	 * Sets the job DAO.
	 *
	 * @param __jobDAO the new job DAO
	 */
	public void setJobDAO(JobDAO __jobDAO) {
		this.jobDAO = __jobDAO;
	}

	/**
	 * Sets the job storage path.
	 *
	 * @param __jobStoragePath the jobStoragePath to set
	 */
	public void setJobStoragePath(String __jobStoragePath) {
		this.jobStoragePath = __jobStoragePath;
	}

	/**
	 * Sets the job type DAO.
	 *
	 * @param __jobTypeDAO the new job type DAO
	 */
	public void setJobTypeDAO(JobTypeDAO __jobTypeDAO) {
		this.jobTypeDAO = __jobTypeDAO;
	}

}
