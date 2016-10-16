/*
 * 
 */
package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class UserStatusResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class UserStatusResponse extends BaseResponse {

	/** The status. */
	@JsonProperty("status")
	private String status;

	/**
	 * Instantiates a new user status response.
	 *
	 * @param __error
	 *            the error
	 */
	public UserStatusResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new user status response.
	 *
	 * @param __error
	 *            the error
	 * @param __message
	 *            the message
	 */
	public UserStatusResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new user status response.
	 *
	 * @param __error
	 *            the error
	 * @param __message
	 *            the message
	 * @param __detail
	 *            the detail
	 */
	public UserStatusResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new user status response.
	 *
	 * @param __status
	 *            the status
	 */
	public UserStatusResponse(byte __status) {
		super();
		this.status = __status == 0 ? "in_activate" : "activate";
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}
}
