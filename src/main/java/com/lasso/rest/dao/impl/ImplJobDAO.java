package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.JobDAO;
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
	public Job getJobOfUserById(int __idUser, int __idJob) {
		return (Job) this.sessionFactory.getCurrentSession().createCriteria(Job.class)
				.add(Restrictions.idEq(__idJob)).add(Restrictions.eq("accountId", __idUser))
				.add(Restrictions.eq("deleted", (byte) 0)).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.JobDAO#getListJobsOfUser(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getListJobsOfUser(Integer __idUser) {
		return this.sessionFactory.getCurrentSession().createCriteria(Job.class)
				.add(Restrictions.eq("accountId", __idUser))
				.add(Restrictions.eq("status", (byte) 1)).add(Restrictions.eq("deleted", (byte) 0))
				.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.JobDAO#saveJob(com.lasso.rest.model.datasource.Job)
	 */
	@Override
	public Integer saveJob(Job __job) {
		return (Integer) this.sessionFactory.getCurrentSession().save(__job);
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
