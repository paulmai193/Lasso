/*
 * 
 */
package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Country;

/**
 * The Interface CountryDAO.
 *
 * @author Paul Mai
 */
public interface CountryDAO {

	/**
	 * Gets the country ids by code.
	 *
	 * @param __code the code
	 * @return the country ids by code
	 */
	public List<Country> getCountryIdsByCode(String __code);
}
