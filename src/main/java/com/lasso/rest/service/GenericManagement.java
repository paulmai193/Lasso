/*
 * 
 */
package com.lasso.rest.service;

import java.util.List;
import java.util.Map;

import com.lasso.rest.model.datasource.Country;

/**
 * The Interface GenericManagement.
 *
 * @author Paul Mai
 */
public interface GenericManagement {

	/**
	 * Gets the all countries.
	 *
	 * @return the all countries
	 */
	List<Country> getAllCountries();

	/**
	 * Gets the country id by code.
	 *
	 * @param __countryCode the country code
	 * @return the country id by code
	 */
	Country getCountryIdByCode(String __countryCode);

	/**
	 * Load config.
	 *
	 * @return the map
	 */
	Map<String, String> loadConfig();
}
