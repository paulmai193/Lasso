package com.lasso.rest.model.api.request;

import org.springframework.util.Assert;

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
public class LoginRequest extends BaseRequest {

	/** The email. */
	private EmailParam	email;

	/** The password. */
	@JsonProperty("password")
	private String		password;

	/**
	 * Instantiates a new login request.
	 */
	public LoginRequest() {
	}

	/**
	 * Check not null.
	 *
	 * @throws ObjectParamException the object param exception
	 */
	@Override
	public void checkNotNull() throws ObjectParamException {
		try {
			Assert.notNull(this.email);
			Assert.notNull(this.password);
		}
		catch (Exception _ex) {
			throw new ObjectParamException("Email or password not valid");
		}
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
	 * Sets the email param.
	 *
	 * @param __email the new email param
	 */
	@JsonProperty("email")
	public void setEmailParam(String __email) {
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
	 * @param __password the new password
	 */
	public void setPassword(String __password) {
		this.password = __password;
	}

}
