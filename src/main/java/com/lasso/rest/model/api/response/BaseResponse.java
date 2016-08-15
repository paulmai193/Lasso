/*
 * 
 */
package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class BaseResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class BaseResponse {

	/** The detail. */
	private String	detail;

	/** The error. */
	private boolean	error;

	/** The message. */
	private String	message;

	/**
	 * Instantiates a new base response.
	 */
	public BaseResponse() {
		this(false, null, null);
	}

	/**
	 * Instantiates a new base response.
	 *
	 * @param __error the error
	 */
	public BaseResponse(boolean __error) {
		this(__error, "Unknown Error");
	}

	/**
	 * Instantiates a new base response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public BaseResponse(boolean __error, String __message) {
		this(__error, __message, "");
	}

	/**
	 * Instantiates a new base response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public BaseResponse(boolean __error, String __message, String __detail) {
		this.error = __error;
		this.message = __message;
		this.detail = __detail;
	}

	/**
	 * Gets the detail.
	 *
	 * @return the detail
	 */
	public String getDetail() {
		return this.detail;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Checks if is error.
	 *
	 * @return true, if is error
	 */
	public boolean isError() {
		return this.error;
	}

	/**
	 * Sets the detail.
	 *
	 * @param __detail the new detail
	 */
	public void setDetail(String __detail) {
		this.detail = __detail;
	}

	/**
	 * Sets the error.
	 *
	 * @param __error the new error
	 */
	public void setError(boolean __error) {
		this.error = __error;
	}

	/**
	 * Sets the message.
	 *
	 * @param __message the new message
	 */
	public void setMessage(String __message) {
		this.message = __message;
	}

}
