/*
 * 
 */
package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lasso.define.Constant;
import com.lasso.exception.ObjectParamException;
import com.lasso.rest.model.datasource.Country;
import com.lasso.rest.model.variable.PhoneParam;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountChangeDetailRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountChangeDetailRequest extends BaseRequest {

	/** The country. */
	private Country country;

	/** The country code. */
	@JsonProperty(value = "country_code")
	private String countryCode;

	/** The gender. */
	@JsonProperty(value = "gender")
	private Short gender;

	/** The password. */
	@JsonProperty("password")
	private String password;

	/** The phone. */
	private PhoneParam phone;

	/** The phone string. */
	private String phoneString;

	/**
	 * Instantiates a new account change detail request.
	 */
	public AccountChangeDetailRequest() {
	}

	/**
	 * Check country valid.
	 *
	 * @throws ObjectParamException
	 *             the object param exception
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
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public Short getGender() {
		return this.gender;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
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
	 * @param __country
	 *            the country to set
	 */
	public void setCountry(Country __country) {
		this.country = __country;
	}

	/**
	 * Sets the country code.
	 *
	 * @param __countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String __countryCode) {
		this.countryCode = __countryCode;
	}

	/**
	 * Sets the gender.
	 *
	 * @param __gender
	 *            the new gender
	 */
	public void setGender(Short __gender) {
		this.gender = __gender;
	}

	/**
	 * Sets the password.
	 *
	 * @param __password
	 *            the password to set
	 */
	public void setPassword(String __password) {
		this.password = __password;
	}

	/**
	 * Sets the phone.
	 *
	 * @param __phone
	 *            the new phone
	 */
	public void setPhone(PhoneParam __phone) {
		this.phone = __phone;
	}

	/**
	 * Sets the phone string.
	 *
	 * @param __phoneString
	 *            the new phone string
	 */
	@JsonProperty(value = "phone")
	public void setPhoneString(String __phoneString) {
		this.phoneString = __phoneString;
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
		} catch (JsonProcessingException ex) {
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
		if (this.countryCode == null || this.countryCode.isEmpty()) {
			throw new ObjectParamException("Invalid country code");
		}
		if (this.phoneString == null || this.phoneString.isEmpty()) {
			throw new ObjectParamException("Invalid phone");
		} else {
			this.phone = new PhoneParam(this.phoneString);
		}
		if (this.gender == null || (this.gender.shortValue() == Constant.GENDER_MALE
				&& this.gender.shortValue() == Constant.GENDER_FEMALE)) {
			this.gender = Constant.GENDER_MALE;
		}
	}

}
