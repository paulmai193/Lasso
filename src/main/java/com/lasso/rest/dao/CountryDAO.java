/*
 * 
 */
package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Country;

// TODO: Auto-generated Javadoc
/**
 * The Interface CountryDAO.
 *
 * @author Paul Mai
 */
public interface CountryDAO extends HibernateSession {

	/**
	 * Gets the by id.
	 *
	 * @param __countryId
	 *        the country id
	 * @return the by id
	 */
	public Country getById(Integer __countryId);

	/**
	 * Gets the country ids by code.
	 *
	 * @param __code
	 *        the code. If code is <strong>"all"</strong> that mean get all
	 *        of countries were supported
	 * @return the country ids by code
	 */
	public List<Country> getCountryIdsByCode(String __code);
}
