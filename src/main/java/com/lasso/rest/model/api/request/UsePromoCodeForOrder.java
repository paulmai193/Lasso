package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

/**
 * The Class UsePromoCodeForOrder.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsePromoCodeForOrder extends BaseRequest {

	/** The id job. */
	@JsonProperty("job_id")
	private Integer	idJob;

	/** The promo code. */
	@JsonProperty("promo_code")
	private String	promoCode;

	/**
	 * Instantiates a new use promo code for order.
	 */
	public UsePromoCodeForOrder() {
	}

	/**
	 * Gets the id job.
	 *
	 * @return the idJob
	 */
	public int getIdJob() {
		return this.idJob;
	}

	/**
	 * Gets the promo code.
	 *
	 * @return the promoCode
	 */
	public String getPromoCode() {
		return this.promoCode;
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
		if (this.promoCode == null) {
			throw new ObjectParamException("Invalid promo code");
		}
	}

}
