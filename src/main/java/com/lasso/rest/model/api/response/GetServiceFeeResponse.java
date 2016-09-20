package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class GetServiceFeeResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class GetServiceFeeResponse extends BaseResponse {

	/** The fee. */
	private float fee;

	/**
	 * Instantiates a new gets the service fee response.
	 *
	 * @param __error the error
	 */
	public GetServiceFeeResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new gets the service fee response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public GetServiceFeeResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new gets the service fee response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public GetServiceFeeResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new gets the service fee response.
	 *
	 * @param __fee the fee
	 */
	public GetServiceFeeResponse(float __fee) {
		super();
		this.fee = __fee;
	}

	/**
	 * Gets the fee.
	 *
	 * @return the fee
	 */
	public float getFee() {
		return this.fee;
	}
}
