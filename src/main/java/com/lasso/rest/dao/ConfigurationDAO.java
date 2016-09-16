package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Configuration;

public interface ConfigurationDAO {

	/**
	 * Load config.
	 *
	 * @return the list
	 */
	List<Configuration> loadConfig();
}
