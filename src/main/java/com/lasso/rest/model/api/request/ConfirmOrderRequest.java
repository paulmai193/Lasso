package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmOrderRequest extends BaseRequest {

	@JsonProperty("job_id")
	private Integer	idJob;

	@JsonProperty("designer_id")
	private Integer	idDesigner;

	public ConfirmOrderRequest() {
	}

	@Override
	public void validate() throws ObjectParamException {
		if (idDesigner == null) {
			throw new ObjectParamException("Invalid designer");
		}
		if (idJob == null) {
			throw new ObjectParamException("Invalid job");
		}
	}

	/**
	 * @return the idJob
	 */
	public Integer getIdJob() {
		return this.idJob;
	}

	/**
	 * @return the idDesigner
	 */
	public Integer getIdDesigner() {
		return this.idDesigner;
	}

}
