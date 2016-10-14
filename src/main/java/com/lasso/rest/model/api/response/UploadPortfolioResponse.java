/*
 * 
 */
package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class UploadPortfolioResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class UploadPortfolioResponse extends BaseResponse {

	/** The name. */
	@JsonProperty("file_name")
	private String name;

	/**
	 * Instantiates a new upload portfolio response.
	 *
	 * @param __error the error
	 */
	public UploadPortfolioResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new upload portfolio response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public UploadPortfolioResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new upload portfolio response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public UploadPortfolioResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new upload portfolio response.
	 *
	 * @param __name the name
	 */
	public UploadPortfolioResponse(String __name) {
		super();
		this.name = __name;
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
	 * @param __name the name to set
	 */
	public void setName(String __name) {
		this.name = __name;
	}

}
