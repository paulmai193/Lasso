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
	 * @param __code the code. If code is <strong>"all"</strong> that mean get all of countries were
	 *        supported
	 * @return the country ids by code
	 */
	public List<Country> getCountryIdsByCode(String __code);
}
