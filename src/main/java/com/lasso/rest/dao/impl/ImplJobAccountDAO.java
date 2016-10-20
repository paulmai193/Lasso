/*
 * 
 */
package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.define.JobConfirmationConstant;
import com.lasso.rest.dao.JobAccountDAO;
import com.lasso.rest.model.datasource.JobsAccount;

/**
 * The Class ImplJobAccountDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplJobAccountDAO implements JobAccountDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new impl job account DAO.
	 */
	public ImplJobAccountDAO() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.dao.JobAccountDAO#getAcceptByJobAndDesignerId(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public JobsAccount getAcceptByJobAndDesignerId(Integer __idJob, Integer __idDesigner) {
		return (JobsAccount) this.sessionFactory.getCurrentSession()
		        .createCriteria(JobsAccount.class).add(Restrictions.eq("jobId", __idJob))
		        .add(Restrictions.eq("accountId", __idDesigner))
		        .add(Restrictions.eq("confirm", JobConfirmationConstant.JOB_ACCEPT.getCode()))
		        .uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.dao.JobAccountDAO#getByJobAndDesignerId(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public JobsAccount getByJobAndDesignerId(Integer __idJob, Integer __idDesigner) {
		return (JobsAccount) this.sessionFactory.getCurrentSession()
		        .createCriteria(JobsAccount.class).add(Restrictions.eq("jobId", __idJob))
		        .add(Restrictions.eq("accountId", __idDesigner)).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.JobAccountDAO#getByJobId(int)
	 */
	@Override
	public JobsAccount getAcceptByJobId(int __idJob) {
		return (JobsAccount) this.sessionFactory.getCurrentSession()
		        .createCriteria(JobsAccount.class).add(Restrictions.eq("jobId", __idJob))
		        .add(Restrictions.eq("deleted", (byte) 0))
		        .add(Restrictions.eq("confirm", JobConfirmationConstant.JOB_ACCEPT.getCode()))
		        .uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.JobAccountDAO#getByOfferId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<JobsAccount> getByJobId(int __idJob) {
		return this.sessionFactory.getCurrentSession().createCriteria(JobsAccount.class)
		        .add(Restrictions.eq("jobId", __idJob)).add(Restrictions.eq("deleted", (byte) 0))
		        .list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.dao.JobAccountDAO#getByJobAndDesignerId(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public JobsAccount getConfirmByJobAndDesignerId(Integer __idJob, Integer __idDesigner) {
		return (JobsAccount) this.sessionFactory.getCurrentSession()
		        .createCriteria(JobsAccount.class).add(Restrictions.eq("jobId", __idJob))
		        .add(Restrictions.eq("accountId", __idDesigner))
		        .add(Restrictions.eq("confirm", JobConfirmationConstant.JOB_CONFIRM.getCode()))
		        .uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.dao.JobAccountDAO#getListJobsAccountOfDesigner(java.lang.
	 * Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<JobsAccount> getListJobsAccountOfDesigner(Integer __idDesigner) {
		return this.sessionFactory.getCurrentSession().createCriteria(JobsAccount.class)
		        .add(Restrictions.eq("accountId", __idDesigner))
		        .add(Restrictions.eq("confirm", JobConfirmationConstant.JOB_ACCEPT.getCode()))
		        .addOrder(Order.desc("created")).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.dao.JobAccountDAO#getUnConfirmByJobAndDesignerId(java.lang
	 * .Integer, java.lang.Integer)
	 */
	@Override
	public JobsAccount getUnConfirmByJobAndDesignerId(Integer __idJob, Integer __idDesigner) {
		return (JobsAccount) this.sessionFactory.getCurrentSession()
		        .createCriteria(JobsAccount.class).add(Restrictions.eq("jobId", __idJob))
		        .add(Restrictions.eq("accountId", __idDesigner))
		        .add(Restrictions.eq("confirm", JobConfirmationConstant.JOB_UN_CONFIRM.getCode()))
		        .uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.dao.JobAccountDAO#saveJobAccount(com.lasso.rest.model.
	 * datasource.JobsAccount)
	 */
	@Override
	public void saveJobAccount(JobsAccount __jobsAccount) {
		this.sessionFactory.getCurrentSession().save(__jobsAccount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.JobAccountDAO#saveJobAccounts(java.util.List)
	 */
	@Override
	public void saveJobAccounts(List<JobsAccount> __jobsAccounts) {
		__jobsAccounts.forEach(_jobAccount -> this.saveJobAccount(_jobAccount));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.HibernateSession#setSessionFactory(org.hibernate.
	 * SessionFactory)
	 */
	@Override
	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.dao.JobAccountDAO#update(com.lasso.rest.model.datasource.
	 * JobsAccount)
	 */
	@Override
	public void update(JobsAccount __jobsAccount) {
		this.sessionFactory.getCurrentSession().update(__jobsAccount);
	}

}
