package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

/**
 * The Class PaymentForOrderRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentForOrderRequest extends BaseRequest {

	/** The id job. */
	@JsonProperty("job_id")
	private Integer	idJob;

	/** The payment. */
	@JsonProperty("payment")
	private byte	payment;

	/**
	 * Instantiates a new payment for order request.
	 */
	public PaymentForOrderRequest() {
	}

	/**
	 * Gets the id job.
	 *
	 * @return the idJob
	 */
	public Integer getIdJob() {
		return this.idJob;
	}

	/**
	 * Gets the payment.
	 *
	 * @return the payment
	 */
	public byte getPayment() {
		return this.payment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.idJob == null) {
			throw new ObjectParamException("Invalid job ID");
		}
		if (this.payment != 0 && this.payment != 1) {
			throw new ObjectParamException("Invalid payment method");
		}
	}

}
