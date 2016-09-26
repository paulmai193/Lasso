package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.JobStyleDAO;
import com.lasso.rest.model.datasource.JobsStyle;

/**
 * The Class ImplJobStyleDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplJobStyleDAO implements JobStyleDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new impl job style DAO.
	 */
	public ImplJobStyleDAO() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.JobStyleDAO#getListJobStylesByJobId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<JobsStyle> getListJobStylesByJobId(int __idJob) {
		return this.sessionFactory.getCurrentSession().createCriteria(JobsStyle.class)
				.add(Restrictions.eq("jobId", __idJob)).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.JobStyleDAO#removeJobStyleByJobId(java.lang.Integer)
	 */
	@Override
	public void removeJobStyleByJobId(Integer __idJob) {
		this.sessionFactory.getCurrentSession().createQuery("delete JobsStyle where jobId = :id")
		.setInteger("id", __idJob).executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.JobStyleDAO#saveListJobStyles(java.util.List)
	 */
	@Override
	public void saveListJobStyles(List<JobsStyle> __jobsTypes) {
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

}
