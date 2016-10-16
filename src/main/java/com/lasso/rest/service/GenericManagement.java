/*
 * 
 */
package com.lasso.rest.service;

import java.util.List;
import java.util.Map;

import com.lasso.rest.model.datasource.Country;
import com.mashape.unirest.http.exceptions.UnirestException;

// TODO: Auto-generated Javadoc
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
	 * Gets the country by id.
	 *
	 * @param __countryId
	 *        the country id
	 * @return the country by id
	 */
	Country getCountryById(Integer __countryId);

	/**
	 * Gets the country id by code.
	 *
	 * @param __countryCode
	 *        the country code
	 * @return the country id by code
	 */
	Country getCountryIdByCode(String __countryCode);

	/**
	 * Gets the service fee.
	 *
	 * @return the service fee
	 */
	float getServiceFee();

	/**
	 * Load config.
	 *
	 * @return the map
	 */
	Map<String, String> loadConfig();

	/**
	 * Load web context storage path.
	 *
	 * @param __app_session
	 *        the app session
	 * @return the string
	 * @throws UnirestException
	 *         the unirest exception
	 */
	String loadWebContextStoragePath(String __app_session) throws UnirestException;

	/**
	 * Save contact.
	 *
	 * @param __email
	 *        the email
	 * @param __phone
	 *        the phone
	 * @param __name
	 *        the name
	 * @param __message
	 *        the message
	 * @param __type
	 *        the type
	 */
	void saveContact(String __email, String __phone, String __name, String __message, byte __type);

}
