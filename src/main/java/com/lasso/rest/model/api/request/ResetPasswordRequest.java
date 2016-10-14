/*
 * 
 */
package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.lasso.exception.ObjectParamException;

/**
 * The Class ResetPasswordRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResetPasswordRequest extends BaseRequest {

	/** The password. */
	private String password;

	/**
	 * Instantiates a new reset password request.
	 */
	public ResetPasswordRequest() {
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.password == null) {
			throw new ObjectParamException("Invalid password");
		}
	}

}
