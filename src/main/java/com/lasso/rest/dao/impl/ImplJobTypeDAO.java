package com.lasso.rest.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.JobTypeDAO;
import com.lasso.rest.model.datasource.JobsType;

/**
 * The Class ImplJobTypeDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplJobTypeDAO implements JobTypeDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new impl job type DAO.
	 */
	public ImplJobTypeDAO() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.JobTypeDAO#getByJobId(int)
	 */
	@Override
	public JobsType getByJobId(int __idJob) {
		return (JobsType) this.sessionFactory.getCurrentSession().createCriteria(JobsType.class)
		        .add(Restrictions.eq("jobId", __idJob)).uniqueResult();
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

}
