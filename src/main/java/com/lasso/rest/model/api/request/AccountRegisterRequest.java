/*
 * 
 */
package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRegisterRequest extends BaseRequest {

	/** The country. */
	private Country		country;

	/** The country code. */
	@JsonProperty(value = "country_code")
	private String		countryCode;

	/** The email. */
	private EmailParam	email;

	/** The email string. */
	private String		emailString;

	/** The name. */
	@JsonProperty(value = "name")
	private String		name;

	/** The password. */
	@JsonProperty(value = "password")
	private String		password;

	/** The phone. */
	private PhoneParam	phone;

	/** The phone string. */
	private String		phoneString;

	/** The push token. */
	@JsonProperty("push_token")
	private String		pushToken;

	/** The value. */
	private Byte		role;

	/** The subscribe. */
	@JsonProperty(value = "subscribe")
	private Boolean		subscribe;

	/**
	 * Instantiates a new account register request.
	 *
	 * @param __role the value
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
	 * Gets the push token.
	 *
	 * @return the push token
	 */
	public String getPushToken() {
		return this.pushToken;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
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
	public void setEmail(EmailParam __email) {
		this.email = __email;
	}

	/**
	 * Sets the email string.
	 *
	 * @param __emailString the new email string
	 */
	@JsonProperty(value = "email")
	public void setEmailString(String __emailString) {
		this.emailString = __emailString;
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
	public void setPhone(PhoneParam __phone) {
		this.phone = __phone;
	}

	/**
	 * Sets the phone string.
	 *
	 * @param __phoneString the new phone string
	 */
	@JsonProperty(value = "phone")
	public void setPhoneString(String __phoneString) {
		this.phoneString = __phoneString;
	}

	/**
	 * Sets the push token.
	 *
	 * @param __pushToken the new push token
	 */
	public void setPushToken(String __pushToken) {
		this.pushToken = __pushToken;
	}

	/**
	 * Sets the value.
	 *
	 * @param __role the new value
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
		if (this.emailString == null) {
			throw new ObjectParamException("Invalid email");
		}
		else {
			this.email = new EmailParam(this.emailString);
		}
		if (this.name == null) {
			throw new ObjectParamException("Invalid name");
		}
		if (this.password == null) {
			throw new ObjectParamException("Invalid password");
		}
		if (this.phoneString == null) {
			throw new ObjectParamException("Invalid phone");
		}
		else {
			this.phone = new PhoneParam(this.phoneString);
		}
		if (this.pushToken == null) {
			throw new ObjectParamException("Invalid push token");
		}
		if (this.role == null) {
			throw new ObjectParamException("Invalid role");
		}
		if (this.subscribe == null) {
			throw new ObjectParamException("Invalid subcribe value");
		}
	}
}
