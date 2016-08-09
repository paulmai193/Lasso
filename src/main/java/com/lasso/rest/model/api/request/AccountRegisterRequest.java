/*
 * 
 */
package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.rest.model.datasource.Country;

/**
 * The Class AccountRegisterRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountRegisterRequest {

	/** The alternative contact. */
	@JsonProperty("alt_contact")
	private String	alternativeContact;

	/** The country code. */
	@JsonProperty("localtion")
	private String	countryCode;

	/** The country. */
	private Country	country;

	/** The email. */
	@JsonProperty("email")
	private String	email;

	/** The name. */
	@JsonProperty("name")
	private String	name;

	/** The password. */
	@JsonProperty("password")
	private String	password;

	/** The payment. */
	@JsonProperty("payment")
	private Byte	payment;

	/** The phone. */
	@JsonProperty("phone")
	private String	phone;

	/** The role. */
	@JsonProperty("role")
	private Byte	role;

	/**
	 * Instantiates a new account register request.
	 */
	public AccountRegisterRequest() {
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
	 * Sets the alternative contact.
	 *
	 * @param __alternativeContact the new alternative contact
	 */
	public void setAlternativeContact(String __alternativeContact) {
		this.alternativeContact = __alternativeContact;
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
	 * Sets the country code.
	 *
	 * @param __countryCode the new country code
	 */
	public void setCountryCode(String __countryCode) {
		this.countryCode = __countryCode;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param __email the new email
	 */
	public void setEmail(String __email) {
		this.email = __email;
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
	 * Sets the name.
	 *
	 * @param __name the new name
	 */
	public void setName(String __name) {
		this.name = __name;
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
	 * Sets the password.
	 *
	 * @param __password the new password
	 */
	public void setPassword(String __password) {
		this.password = __password;
	}

	/**
	 * Gets the payment.
	 *
	 * @return the payment
	 */
	public Byte getPayment() {
		return this.payment;
	}

	/**
	 * Sets the payment.
	 *
	 * @param __payment the new payment
	 */
	public void setPayment(Byte __payment) {
		this.payment = __payment;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param __phone the new phone
	 */
	public void setPhone(String __phone) {
		this.phone = __phone;
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
	 * Sets the role.
	 *
	 * @param __role the new role
	 */
	public void setRole(Byte __role) {
		this.role = __role;
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
	 * Sets the country.
	 *
	 * @param __country the new country
	 */
	public void setCountry(Country __country) {
		this.country = __country;
	}

}
