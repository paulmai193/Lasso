package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(content = Include.NON_NULL)
public class BaseResponse {

	private String	detail;
	private boolean	error;
	private String	message;

	public BaseResponse() {
		this(false);
	}

	public BaseResponse(boolean __error) {
		this(__error, null);
	}

	public BaseResponse(boolean __error, String __message) {
		this(__error, __message, null);
	}

	public BaseResponse(boolean __error, String __message, String __detail) {
		this.error = __error;
		this.message = __message;
		this.detail = __detail;
	}

	public String getDetail() {
		return this.detail;
	}

	public String getMessage() {
		return this.message;
	}

	public boolean isError() {
		return this.error;
	}

	public void setDetail(String __detail) {
		this.detail = __detail;
	}

	public void setError(boolean __error) {
		this.error = __error;
	}

	public void setMessage(String __message) {
		this.message = __message;
	}

}
