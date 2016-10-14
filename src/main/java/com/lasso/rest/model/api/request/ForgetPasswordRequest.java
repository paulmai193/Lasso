/*
 * 
 */
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
	private EmailParam	email;

	/** The email string. */
	private String		emailString;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#checkNotNull()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.emailString == null) {
			throw new ObjectParamException("Invalid email");
		}
		else {
			this.email = new EmailParam(this.emailString);
		}
	}

}
