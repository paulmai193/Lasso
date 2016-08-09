/*
 * 
 */
package com.lasso.rest.service;

import com.lasso.rest.model.datasource.Country;

/**
 * The Interface GenericManagement.
 *
 * @author Paul Mai
 */
public interface GenericManagement {

	/**
	 * Gets the country id by code.
	 *
	 * @param __countryCode the country code
	 * @return the country id by code
	 */
	public Country getCountryIdByCode(String __countryCode);
}
