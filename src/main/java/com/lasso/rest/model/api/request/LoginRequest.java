package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;
import com.lasso.rest.model.variable.EmailParam;

/**
 * The Class LoginRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest extends BaseRequest {

	/** The email. */
	private EmailParam	email;

	/** The email string. */
	private String		emailString;

	/** The password. */
	@JsonProperty("password")
	private String		password;

	/** The push token. */
	@JsonProperty("push_token")
	private String		pushToken;

	/**
	 * Instantiates a new login request.
	 */
	public LoginRequest() {
	}

	/**
	 * Gets the email param.
	 *
	 * @return the email param
	 */
	public EmailParam getEmailParam() {
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
	 * Gets the push token.
	 *
	 * @return the push token
	 */
	public String getPushToken() {
		return this.pushToken;
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
	@JsonProperty("email")
	public void setEmailString(String __emailString) {
		this.emailString = __emailString;
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
	 * Sets the push token.
	 *
	 * @param __pushToken the new push token
	 */
	public void setPushToken(String __pushToken) {
		this.pushToken = __pushToken;
	}

	/**
	 * Check not null.
	 *
	 * @throws ObjectParamException the object param exception
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.emailString == null) {
			throw new ObjectParamException("Invalid email");
		}
		else {
			this.email = new EmailParam(this.emailString);
		}
		if (this.password == null) {
			throw new ObjectParamException("Invalid password");
		}
		if (this.pushToken == null) {
			throw new ObjectParamException("Invalid push token");
		}
	}

}
