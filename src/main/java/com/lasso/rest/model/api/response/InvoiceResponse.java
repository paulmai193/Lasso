package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class InvoiceResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class InvoiceResponse extends BaseResponse {

	/** The content. */
	@JsonProperty("data")
	private String content;

	/**
	 * Instantiates a new invoice response.
	 *
	 * @param __content the content
	 */
	public InvoiceResponse(String __content) {
		super();
		this.content = __content;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return this.content;
	}
}
