package com.lasso.rest.model.api.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

@JsonInclude(value = Include.NON_NULL)
public class ChooseDesignerForJobRequest extends BaseRequest {

	@JsonProperty("job_id")
	private Integer			idJob;

	@JsonProperty("designer_ids")
	private List<Integer>	designerIds;

	public ChooseDesignerForJobRequest() {
	}

	@Override
	public void validate() throws ObjectParamException {
		if (idJob == null) {
			throw new ObjectParamException("Invalid job ID");
		}
		if (designerIds == null || designerIds.isEmpty()) {
			throw new ObjectParamException("Invalid list designer");
		}
	}

	/**
	 * @return the idJob
	 */
	public Integer getIdJob() {
		return this.idJob;
	}

	public List<Integer> getDesignerIds() {
		return this.designerIds;
	}

}
