package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

/**
 * The Class ConfirmOrderRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmOfferRequest extends BaseRequest {

	/** The id job. */
	@JsonProperty("job_id")
	private Integer	idJob;

	private Byte	status;

	/**
	 * Instantiates a new confirm order request.
	 */
	public ConfirmOfferRequest() {
	}

	public byte getStatus() {
		return this.status;
	}

	@JsonProperty("confirm_status")
	public void setStatus(String __status) {
		this.status = __status.equals("accept") ? (byte) 1
		        : __status.equals("reject") ? (byte) -1 : null;
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
		if (this.status == null) {
			throw new ObjectParamException("Invalid confirm status");
		}
		if (this.idJob == null) {
			throw new ObjectParamException("Invalid job");
		}
	}

}
