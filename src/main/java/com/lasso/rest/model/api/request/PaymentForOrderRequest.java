package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentForOrderRequest extends BaseRequest {

	@JsonProperty("job_id")
	private Integer	idJob;

	@JsonProperty("payment")
	private byte	payment;

	public PaymentForOrderRequest() {
	}

	@Override
	public void validate() throws ObjectParamException {
		if (idJob == null) {
			throw new ObjectParamException("Invalid job ID");
		}
		if (payment != 0 && payment != 1) {
			throw new ObjectParamException("Invalid payment method");
		}
	}

	/**
	 * @return the idJob
	 */
	public Integer getIdJob() {
		return this.idJob;
	}

	/**
	 * @return the payment
	 */
	public byte getPayment() {
		return this.payment;
	}

}
