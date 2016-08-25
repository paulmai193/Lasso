/*
 * 
 */
package com.lasso.rest.service.impl;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.dao.CountryDAO;
import com.lasso.rest.model.datasource.Country;
import com.lasso.rest.service.GenericManagement;

/**
 * The Class ImplGenericManagement.
 *
 * @author Paul Mai
 */
@Service
@Transactional
public class ImplGenericManagement implements GenericManagement {

	/** The country DAO. */
	@Autowired
	private CountryDAO countryDAO;

	/**
	 * Instantiates a new impl generic management.
	 */
	public ImplGenericManagement() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.GenericManagement#getCountryIdByCode(java.lang.String)
	 */
	@Override
	public Country getCountryIdByCode(String __countryCode) {
		List<Country> _ids = this.countryDAO.getCountryIdsByCode(__countryCode);
		if (_ids.size() == 0 || _ids.size() > 1) {
			return null;
		}
		else {
			return _ids.get(0);
		}
	}

	/**
	 * Sets the country DAO.
	 *
	 * @param __countryDAO the new country DAO
	 */
	public void setCountryDAO(CountryDAO __countryDAO) {
		this.countryDAO = __countryDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.GenericManagement#getAllCountries()
	 */
	@Override
	public List<Country> getAllCountries() {
		return countryDAO.getCountryIdsByCode("all");
	}

}
