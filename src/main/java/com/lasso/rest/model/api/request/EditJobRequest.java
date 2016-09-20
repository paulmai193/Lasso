package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

@JsonInclude(value = Include.NON_NULL)
public class EditJobRequest extends CreateNewJobRequest {

	@JsonProperty("job_id")
	private Integer idJob;

	public EditJobRequest() {
	}

	public Integer getIdJob() {
		return this.idJob;
	}

	@Override
	public void validate() throws ObjectParamException {
		if (idJob == null) {
			throw new ObjectParamException("Invalid job ID");
		}
		super.validate();
	}

}
