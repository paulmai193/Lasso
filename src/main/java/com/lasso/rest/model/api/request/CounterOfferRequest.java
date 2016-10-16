/*
 * 
 */
package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfirmOrderRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CounterOfferRequest extends BaseRequest {

	/** The amount. */
	@JsonProperty("amount")
	private Double amount;

	/** The id job. */
	@JsonProperty("job_id")
	private Integer idJob;

	/**
	 * Instantiates a new confirm order request.
	 */
	public CounterOfferRequest() {
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public Double getAmount() {
		return this.amount;
	}

	/**
	 * Gets the id job.
	 *
	 * @return the idJob
	 */
	public Integer getIdJob() {
		return this.idJob;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.amount == null || this.amount.doubleValue() <= 0) {
			throw new ObjectParamException("Invalid amount");
		}
		if (this.idJob == null) {
			throw new ObjectParamException("Invalid job");
		}
	}

}
