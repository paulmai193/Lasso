package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lasso.exception.ObjectParamException;
import com.lasso.rest.model.datasource.Country;
import com.lasso.rest.model.variable.PhoneParam;

/**
 * The Class AccountChangeDetailRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountChangeDetailRequest extends BaseRequest {

	/** The country. */
	private Country		country;

	/** The country code. */
	@JsonProperty(value = "country_code")
	private String		countryCode;

	/** The phone. */
	private PhoneParam	phone;

	/**
	 * Instantiates a new account change detail request.
	 */
	public AccountChangeDetailRequest() {
	}

	/**
	 * Check country valid.
	 *
	 * @throws ObjectParamException the object param exception
	 */
	public void checkCountryValid() throws ObjectParamException {
		if (this.country == null) {
			throw new ObjectParamException("Illegal country code");
		}
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry() {
		return this.country;
	}

	/**
	 * Gets the country code.
	 *
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return this.countryCode;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public PhoneParam getPhone() {
		return this.phone;
	}

	/**
	 * Sets the country.
	 *
	 * @param __country the country to set
	 */
	public void setCountry(Country __country) {
		this.country = __country;
	}

	/**
	 * Sets the country code.
	 *
	 * @param __countryCode the countryCode to set
	 */
	public void setCountryCode(String __countryCode) {
		this.countryCode = __countryCode;
	}

	/**
	 * Sets the phone.
	 *
	 * @param __phone the new phone
	 */
	@JsonProperty(value = "phone")
	public void setPhone(String __phone) {
		try {
			this.phone = new PhoneParam(__phone);
		}
		catch (Exception _ex) {
			this.phone = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		}
		catch (JsonProcessingException ex) {
			return super.toString();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#checkNotNull()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.countryCode == null) {
			throw new ObjectParamException("Invalid country code");
		}
		if (this.phone == null) {
			throw new ObjectParamException("Invalid phone number");
		}
	}

}
