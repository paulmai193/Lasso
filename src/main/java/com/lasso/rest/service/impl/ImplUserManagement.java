package com.lasso.rest.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.ws.rs.NotFoundException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.dao.JobAccountDAO;
import com.lasso.rest.dao.JobDAO;
import com.lasso.rest.dao.JobTypeDAO;
import com.lasso.rest.model.api.request.CreateNewJobRequest;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.JobsAccount;
import com.lasso.rest.model.datasource.JobsType;
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
		Job _job = new Job(__createNewJobRequest);
		_job.setAccountId(__user.getId());
		Integer _idJob = this.jobDAO.saveJob(_job);

		List<JobsType> _jobsTypes = new ArrayList<>();
		__createNewJobRequest.getIdTypes()
		.forEach(_idType -> _jobsTypes.add(new JobsType(_idJob, _idType)));
		this.jobTypeDAO.saveListJobsTypes(_jobsTypes);

		// Copy portfolio images from temporary directory to resource directory
		String _webContextStoragePath = this.getGenericManagement()
				.loadWebContextStoragePath(__user.getAppSession());
		for (String _tempFileName : __createNewJobRequest.getReference()) {
			File _tempFile = new File(
					_webContextStoragePath + this.getTemporaryStoragePath() + "/" + _tempFileName);
			if (_tempFile.exists()) {
				// Move original file
				FileUtils.copyFileToDirectory(_tempFile,
						new File(_webContextStoragePath + this.jobStoragePath + "/Original"),
						false);

				// Resize into 3 other size
				File _icon = new File(
						_webContextStoragePath + this.jobStoragePath + "/Icon/" + _tempFileName);
				this.getUploadImageManagement().resizeImage(_tempFile, _icon, 120D, 184D);
				File _small = new File(
						_webContextStoragePath + this.jobStoragePath + "/Small/" + _tempFileName);
				this.getUploadImageManagement().resizeImage(_tempFile, _small, 182D, 280D);
				File _retina = new File(
						_webContextStoragePath + this.jobStoragePath + "/Retina/" + _tempFileName);
				this.getUploadImageManagement().resizeImage(_tempFile, _retina, 364D, 560D);
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
