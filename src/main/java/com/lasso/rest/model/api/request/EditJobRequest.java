package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

/**
 * The Class EditJobRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class EditJobRequest extends CreateNewJobRequest {

	/** The id job. */
	@JsonProperty("job_id")
	private Integer idJob;

	/**
	 * Instantiates a new edits the job request.
	 */
	public EditJobRequest() {
	}

	/**
	 * Gets the id job.
	 *
	 * @return the id job
	 */
	public Integer getIdJob() {
		return this.idJob;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.CreateNewJobRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.idJob == null) {
			throw new ObjectParamException("Invalid job ID");
		}
		super.validate();
	}

}
