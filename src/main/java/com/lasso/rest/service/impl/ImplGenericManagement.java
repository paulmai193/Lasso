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

@Service
@Transactional
public class ImplGenericManagement implements GenericManagement {

	@Autowired
	private CountryDAO countryDAO;

	public CountryDAO getCountryDAO() {
		return this.countryDAO;
	}

	public void setCountryDAO(CountryDAO __countryDAO) {
		this.countryDAO = __countryDAO;
	}

	public ImplGenericManagement() {
	}

	@Override
	public Country getCountryIdByCode(String __countryCode) {
		List<Country> _ids = countryDAO.getCountryIdsByCode(__countryCode);
		if (_ids.size() == 0 || _ids.size() > 1) {
			throw new IllegalArgumentException("Illegal country code");
		}
		else {
			return _ids.get(0);
		}
	}

}
