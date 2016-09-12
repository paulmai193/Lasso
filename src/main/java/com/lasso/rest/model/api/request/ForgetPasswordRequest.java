package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;
import com.lasso.rest.model.variable.EmailParam;

/**
 * The Class ForgotPasswordRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForgetPasswordRequest extends BaseRequest {

	/** The email. */
	private EmailParam email;

	/**
	 * Instantiates a new forgot password request.
	 */
	public ForgetPasswordRequest() {
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
	 * Sets the email.
	 *
	 * @param __email the new email
	 */
	@JsonProperty("email")
	public void setEmail(String __email) {
		try {
			this.email = new EmailParam(__email);
		}
		catch (Exception _ex) {
			this.email = null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#checkNotNull()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.email == null) {
			throw new ObjectParamException("Invalid email");
		}
	}

}
