/*
 * 
 */
package com.lasso.rest.model.api.request;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;
import com.lasso.rest.model.datasource.Country;
import com.lasso.rest.model.variable.EmailParam;
import com.lasso.rest.model.variable.PhoneParam;

/**
 * The Class AccountRegisterRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountRegisterRequest extends BaseRequest {

	/** The alternative contact. */
	@JsonProperty("alt_contact")
	private String		alternativeContact;

	/** The country. */
	private Country		country;

	/** The country code. */
	@JsonProperty(value = "localtion")
	private String		countryCode;

	/** The email. */
	private EmailParam	email;

	/** The name. */
	@JsonProperty(value = "name")
	private String		name;

	/** The password. */
	@JsonProperty(value = "password")
	private String		password;

	/** The phone. */
	private PhoneParam	phone;

	/** The role. */
	private Byte		role;

	/** The subscribe. */
	@JsonProperty(value = "subscribe")
	private Boolean		subscribe;

	/**
	 * Instantiates a new account register request.
	 *
	 * @param __role the role
	 */
	public AccountRegisterRequest(Byte __role) {
		this.role = __role;
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
			Assert.notNull(this.alternativeContact);
			Assert.notNull(this.countryCode);
			Assert.notNull(this.email);
			Assert.notNull(this.name);
			Assert.notNull(this.password);
			Assert.notNull(this.phone);
			Assert.notNull(this.role);
			Assert.notNull(this.subscribe);
		}
		catch (Throwable _ex) {
			throw new ObjectParamException("Some fields invalid");
		}

	}

	/**
	 * Gets the alternative contact.
	 *
	 * @return the alternative contact
	 */
	public String getAlternativeContact() {
		return this.alternativeContact;
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
	 * @return the country code
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
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
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Byte getRole() {
		return this.role;
	}

	/**
	 * Gets the subscribe.
	 *
	 * @return the subscribe
	 */
	public Boolean getSubscribe() {
		return this.subscribe;
	}

	/**
	 * Sets the alternative contact.
	 *
	 * @param __alternativeContact the new alternative contact
	 */
	public void setAlternativeContact(String __alternativeContact) {
		this.alternativeContact = __alternativeContact;
	}

	/**
	 * Sets the country.
	 *
	 * @param __country the new country
	 */
	public void setCountry(Country __country) {
		this.country = __country;
	}

	/**
	 * Sets the country code.
	 *
	 * @param __countryCode the new country code
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
	 * Sets the name.
	 *
	 * @param __name the new name
	 */
	public void setName(String __name) {
		this.name = __name;
	}

	/**
	 * Sets the password.
	 *
	 * @param __password the new password
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

	/**
	 * Sets the role.
	 *
	 * @param __role the new role
	 */
	public void setRole(Byte __role) {
		this.role = __role;
	}

	/**
	 * Sets the subscribe.
	 *
	 * @param __subscribe the new subscribe
	 */
	public void setSubscribe(Boolean __subscribe) {
		this.subscribe = __subscribe;
	}
}
