package com.lasso.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.dao.JobAccountDAO;
import com.lasso.rest.dao.JobDAO;
import com.lasso.rest.dao.JobTypeDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.JobsAccount;
import com.lasso.rest.model.datasource.JobsType;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.service.UserManagement;

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
			String _designerName, _typeTitle;
			JobsAccount _jobsAccount = this.jobAccountDAO.getByJobId(__idJob);
			_designerName = _jobsAccount == null ? ""
			        : this.getAccountDAO().getAccountById(_jobsAccount.getAccountId()).getName();
			JobsType _jobsType = this.jobTypeDAO.getByJobId(__idJob);
			if (_jobsType != null) {
				Type _type = this.getTypeDAO().getById(_jobsType.getTypeId());
				_typeTitle = _type.getTitle();
			}
			else {
				_typeTitle = "";
			}
			Style _style = this.getStyleDAO().getById(_job.getStyleId());

			Object[] _data = { _job, _designerName, _typeTitle, _style.getTitle() };
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
					String _designerName, _typeTitle;
					JobsAccount _jobsAccount = ImplUserManagement.this.jobAccountDAO
					        .getByJobId(__job.getId());
					_designerName = _jobsAccount == null ? ""
					        : ImplUserManagement.this.getAccountDAO()
					                .getAccountById(_jobsAccount.getAccountId()).getName();
					JobsType _jobsType = ImplUserManagement.this.jobTypeDAO
					        .getByJobId(__job.getId());
					if (_jobsType != null) {
						Type _type = ImplUserManagement.this.getTypeDAO()
						        .getById(_jobsType.getTypeId());
						_typeTitle = _type.getTitle();
					}
					else {
						_typeTitle = "";
					}
					Style _style = ImplUserManagement.this.getStyleDAO()
					        .getById(__job.getStyleId());

					Object[] _data = { __job, _designerName, _typeTitle, _style.getTitle() };
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
	 * Sets the job type DAO.
	 *
	 * @param __jobTypeDAO the new job type DAO
	 */
	public void setJobTypeDAO(JobTypeDAO __jobTypeDAO) {
		this.jobTypeDAO = __jobTypeDAO;
	}
}
