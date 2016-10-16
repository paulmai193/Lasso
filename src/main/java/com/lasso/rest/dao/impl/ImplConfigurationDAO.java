/*
 * 
 */
package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.ConfigurationDAO;
import com.lasso.rest.model.datasource.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class ImplConfigurationDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplConfigurationDAO implements ConfigurationDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new impl configuration DAO.
	 */
	public ImplConfigurationDAO() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.ConfigurationDAO#loadConfig()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Configuration> loadConfig() {
		return this.sessionFactory.getCurrentSession().createCriteria(Configuration.class).list();
	}

	/**
	 * Sets the session factory.
	 *
	 * @param __sessionFactory
	 *        the new session factory
	 */
	@Override
	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

}
