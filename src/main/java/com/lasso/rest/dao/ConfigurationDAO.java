/*
 * 
 */
package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Configuration;

/**
 * The Interface ConfigurationDAO.
 *
 * @author Paul Mai
 */
public interface ConfigurationDAO extends HibernateSession {

	/**
	 * Load config.
	 *
	 * @return the list
	 */
	List<Configuration> loadConfig();
}
