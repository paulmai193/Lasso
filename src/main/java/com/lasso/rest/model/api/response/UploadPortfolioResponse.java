package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class UploadPortfolioResponse extends BaseResponse {

	@JsonProperty("file_name")
	private String name;

	public UploadPortfolioResponse(String __name) {
		super();
		this.name = __name;
	}

	public UploadPortfolioResponse(boolean __error) {
		super(__error);
	}

	public UploadPortfolioResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	public UploadPortfolioResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param __name the name to set
	 */
	public void setName(String __name) {
		this.name = __name;
	}

}
