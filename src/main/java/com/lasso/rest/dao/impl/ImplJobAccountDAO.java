package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	 * @see com.lasso.rest.dao.JobAccountDAO#getByJobId(int)
	 */
	@Override
	public JobsAccount getByJobId(int __idJob) {
		return (JobsAccount) this.sessionFactory.getCurrentSession()
		        .createCriteria(JobsAccount.class).add(Restrictions.eq("jobId", __idJob))
		        .add(Restrictions.eq("deleted", (byte) 0)).add(Restrictions.eq("confirm", (byte) 2))
		        .uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobsAccount> getByOfferId(int __idJob) {
		return this.sessionFactory.getCurrentSession().createCriteria(JobsAccount.class)
		        .add(Restrictions.eq("jobId", __idJob)).add(Restrictions.eq("deleted", (byte) 0))
		        .list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.HibernateSession#setSessionFactory(org.hibernate.SessionFactory)
	 */
	@Override
	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

	@Override
	public void saveJobAccounts(List<JobsAccount> __jobsAccounts) {
		__jobsAccounts
		        .forEach(_jobAccount -> this.sessionFactory.getCurrentSession().save(_jobAccount));
	}

}
