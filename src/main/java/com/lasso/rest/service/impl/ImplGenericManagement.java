/*
 * 
 */
package com.lasso.rest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.dao.ConfigurationDAO;
import com.lasso.rest.dao.CountryDAO;
import com.lasso.rest.model.datasource.Configuration;
import com.lasso.rest.model.datasource.Country;
import com.lasso.rest.service.GenericManagement;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * The Class ImplGenericManagement.
 *
 * @author Paul Mai
 */
@Service
@Transactional
public class ImplGenericManagement implements GenericManagement {

	/** The configuration DAO. */
	@Autowired
	private ConfigurationDAO	configurationDAO;
	/** The country DAO. */
	@Autowired
	private CountryDAO			countryDAO;

	/** The web context storage path. */
	private String				webContextStoragePath;

	/**
	 * Instantiates a new impl generic management.
	 */
	public ImplGenericManagement() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.GenericManagement#getAllCountries()
	 */
	@Override
	public List<Country> getAllCountries() {
		return this.countryDAO.getCountryIdsByCode("all");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.GenericManagement#loadConfig()
	 */
	@Override
	public Map<String, String> loadConfig() {
		Map<String, String> _mapConfig = new HashMap<>();
		this.configurationDAO.loadConfig().forEach(new Consumer<Configuration>() {

			@Override
			public void accept(Configuration __t) {
				_mapConfig.put(__t.getName(), __t.getValue());
			}
		});
		;
		return _mapConfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.GenericManagement#loadWebContextStoragePath(java.lang.String)
	 */
	@Override
	public String loadWebContextStoragePath(String __app_session) throws UnirestException {
		if (this.webContextStoragePath == null || this.webContextStoragePath.isEmpty()) {
			HttpResponse<String> _response = Unirest.post("http://lasso.voolatech.com/image_path")
					.header("cache-control", "no-cache")
					.header("content-type", "application/x-www-form-urlencoded")
					.body("app_session=" + __app_session).asString();
			this.webContextStoragePath = _response.getBody();
		}

		return this.webContextStoragePath;
	}

	/**
	 * Sets the configuration DAO.
	 *
	 * @param __configurationDAO the new configuration DAO
	 */
	public void setConfigurationDAO(ConfigurationDAO __configurationDAO) {
		this.configurationDAO = __configurationDAO;
	}

	/**
	 * Sets the country DAO.
	 *
	 * @param __countryDAO the new country DAO
	 */
	public void setCountryDAO(CountryDAO __countryDAO) {
		this.countryDAO = __countryDAO;
	}

	/**
	 * Sets the web context storage path.
	 *
	 * @param __webContextStoragePath the new web context storage path
	 */
	public void setWebContextStoragePath(String __webContextStoragePath) {
		this.webContextStoragePath = __webContextStoragePath;
	}

}
