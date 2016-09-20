package com.lasso.rest.dao.impl;

import java.util.List;

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
	 * @see com.lasso.rest.dao.JobTypeDAO#getListJobsTypesByJobId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<JobsType> getListJobsTypesByJobId(int __idJob) {
		return this.sessionFactory.getCurrentSession().createCriteria(JobsType.class)
		        .add(Restrictions.eq("jobId", __idJob)).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.JobTypeDAO#saveListJobsTypes(java.util.List)
	 */
	@Override
	public void saveListJobsTypes(List<JobsType> __jobsTypes) {
		__jobsTypes.forEach(_jt -> this.sessionFactory.getCurrentSession().save(_jt));
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
	public void removeJobsTypesByJobId(Integer __idJob) {
		this.sessionFactory.getCurrentSession().createQuery("delete JobsType where jobId = :id")
		        .setInteger("id", __idJob).executeUpdate();
	}

}
