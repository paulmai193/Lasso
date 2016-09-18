package com.lasso.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.dao.JobDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.JobsAccount;
import com.lasso.rest.model.datasource.JobsType;
import com.lasso.rest.service.UserManagement;

import javassist.NotFoundException;

/**
 * The Class ImplUserManagement.
 *
 * @author Paul Mai
 */
@Service
@Transactional
public class ImplUserManagement extends ImplProjectManagement implements UserManagement {

	/** The job DAO. */
	@Autowired
	private JobDAO jobDAO;

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
		Job _job = this.jobDAO.getOfUserById(__user, __idJob);
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		else {
			String _designerName, _typeTitle;
			JobsAccount _jobsAccount = _job.getJobsAccounts().stream()
					.filter(_ja -> _ja.getConfirm().equals((byte) 2)).findFirst().get();
			_designerName = _jobsAccount == null ? "" : _jobsAccount.getAccount().getName();
			JobsType _jobsType = _job.getJobsTypes().stream().findAny().get();
			_typeTitle = _jobsType == null ? "" : _jobsType.getType().getTitle();
			Object[] _data = { _job, _designerName, _typeTitle, _job.getStyle().getTitle() };
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
		__user.getJobs().stream().filter(_j -> (_j.getDeleted() == 0 && _j.getStatus() == 1))
		.forEach(new Consumer<Job>() {

			@Override
			public void accept(Job __job) {
				String _designerName, _typeTitle;
				JobsAccount _jobsAccount = __job.getJobsAccounts().stream()
						.filter(_ja -> _ja.getConfirm().equals((byte) 2)).findFirst().get();
				_designerName = _jobsAccount == null ? ""
						: _jobsAccount.getAccount().getName();
				JobsType _jobsType = __job.getJobsTypes().stream().findAny().get();
				_typeTitle = _jobsType == null ? "" : _jobsType.getType().getTitle();
				Object[] _data = { __job, _designerName, _typeTitle,
						__job.getStyle().getTitle() };
				_datas.add(_data);
			}
		});
		;

		return _datas;
	}

	/**
	 * Sets the job DAO.
	 *
	 * @param __jobDAO the new job DAO
	 */
	public void setJobDAO(JobDAO __jobDAO) {
		this.jobDAO = __jobDAO;
	}
}
