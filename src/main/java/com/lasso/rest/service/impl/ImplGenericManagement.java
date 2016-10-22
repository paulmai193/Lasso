/*
 * 
 */
package com.lasso.rest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.dao.ConfigurationDAO;
import com.lasso.rest.dao.ContactDAO;
import com.lasso.rest.dao.CountryDAO;
import com.lasso.rest.model.datasource.Contact;
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
	/** The contact DAO. */
	@Autowired
	private ContactDAO			contactDAO;

	/** The country DAO. */
	@Autowired
	private CountryDAO			countryDAO;

	/** The http host. */
	private String				httpHost;

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
	 * @see com.lasso.rest.service.GenericManagement#getCountryById(java.lang.
	 * Integer)
	 */
	@Override
	public Country getCountryById(Integer __countryId) {
		return this.countryDAO.getById(__countryId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.GenericManagement#getCountryIdByCode(java.lang.
	 * String)
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
	 * @see com.lasso.rest.service.GenericManagement#getServiceFee()
	 */
	@Override
	public float getServiceFee() {
		return Float.parseFloat(this.loadConfig().get("Site.fee"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.GenericManagement#loadConfig()
	 */
	@Override
	public Map<String, String> loadConfig() {
		Map<String, String> _mapConfig = new HashMap<>();
		this.configurationDAO.loadConfig()
		        .forEach(_c -> _mapConfig.put(_c.getName(), _c.getValue()));
		return _mapConfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.GenericManagement#loadWebContextStoragePath(java.
	 * lang.String)
	 */
	@Override
	public String loadWebContextStoragePath(String __app_session) throws UnirestException {
		if (this.webContextStoragePath == null || this.webContextStoragePath.isEmpty()) {
			HttpResponse<String> _response = Unirest.post(this.httpHost + "/image_path")
			        .header("cache-control", "no-cache")
			        .header("content-type", "application/x-www-form-urlencoded")
			        .body("app_session=" + __app_session).asString();
			this.webContextStoragePath = _response.getBody();
		}

		return this.webContextStoragePath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.GenericManagement#saveContact(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, byte)
	 */
	@Override
	public void saveContact(String __email, String __phone, String __name, String __message,
	        byte __type) {
		Contact _contact = new Contact(__email, __message, __name, __phone, __type);
		this.contactDAO.save(_contact);
	}

	/**
	 * Sets the configuration DAO.
	 *
	 * @param __configurationDAO
	 *        the new configuration DAO
	 */
	public void setConfigurationDAO(ConfigurationDAO __configurationDAO) {
		this.configurationDAO = __configurationDAO;
	}

	/**
	 * Sets the contact DAO.
	 *
	 * @param __contactDAO
	 *        the new contact DAO
	 */
	public void setContactDAO(ContactDAO __contactDAO) {
		this.contactDAO = __contactDAO;
	}

	/**
	 * Sets the country DAO.
	 *
	 * @param __countryDAO
	 *        the new country DAO
	 */
	public void setCountryDAO(CountryDAO __countryDAO) {
		this.countryDAO = __countryDAO;
	}

	/**
	 * Sets the http host.
	 *
	 * @param __httpHost the new http host
	 */
	public void setHttpHost(String __httpHost) {
		this.httpHost = __httpHost;
	}

	/**
	 * Sets the web context storage path.
	 *
	 * @param __webContextStoragePath
	 *        the new web context storage path
	 */
	public void setWebContextStoragePath(String __webContextStoragePath) {
		this.webContextStoragePath = __webContextStoragePath;
	}

}
