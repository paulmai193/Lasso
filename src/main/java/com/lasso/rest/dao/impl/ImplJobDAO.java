package com.lasso.rest.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.JobDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Job;

/**
 * The Class ImplJobDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplJobDAO implements JobDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new impl job DAO.
	 */
	public ImplJobDAO() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.JobDAO#getOfUserById(com.lasso.rest.model.datasource.Account, int)
	 */
	@Override
	public Job getOfUserById(Account __account, int __id) {
		return (Job) this.sessionFactory.getCurrentSession().createCriteria(Job.class)
				.add(Restrictions.idEq(__id)).add(Restrictions.eq("account", __account))
				.add(Restrictions.eq("deleted", (byte) 0)).uniqueResult();
	}

	/**
	 * Sets the session factory.
	 *
	 * @param __sessionFactory the new session factory
	 */
	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}
}
