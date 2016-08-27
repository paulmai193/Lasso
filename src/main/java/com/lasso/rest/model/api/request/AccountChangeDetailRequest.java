package com.lasso.rest.model.api.request;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;
import com.lasso.rest.model.datasource.Country;
import com.lasso.rest.model.variable.EmailParam;
import com.lasso.rest.model.variable.PhoneParam;

/**
 * The Class AccountChangeDetailRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountChangeDetailRequest extends BaseRequest {

	/** The country. */
	private Country		country;

	/** The country code. */
	@JsonProperty(value = "country_code")
	private String		countryCode;

	/** The email. */
	private EmailParam	email;

	/** The password. */
	@JsonProperty(value = "password")
	private String		password;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#checkNotNull()
	 */
	@Override
	public void checkNotNull() throws ObjectParamException {
		try {
			Assert.notNull(this.countryCode);
			Assert.notNull(this.email);
			Assert.notNull(this.password);
			Assert.notNull(this.phone);
		}
		catch (Throwable _ex) {
			throw new ObjectParamException("Some fields invalid");
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public EmailParam getEmail() {
		return this.email;
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
	 * Sets the email.
	 *
	 * @param __email the new email
	 */
	@JsonProperty(value = "email")
	public void setEmail(String __email) {
		try {
			this.email = new EmailParam(__email);
		}
		catch (Exception _ex) {
			this.email = null;
		}
	}

	/**
	 * Sets the password.
	 *
	 * @param __password the password to set
	 */
	public void setPassword(String __password) {
		this.password = __password;
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

}
