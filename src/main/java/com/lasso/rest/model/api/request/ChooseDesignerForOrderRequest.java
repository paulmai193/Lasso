package com.lasso.rest.model.api.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

/**
 * The Class ChooseDesignerForOrderRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChooseDesignerForOrderRequest extends BaseRequest {

	/** The designer ids. */
	@JsonProperty("designer_ids")
	private List<Integer>	designerIds;

	/** The id job. */
	@JsonProperty("job_id")
	private Integer			idJob;

	/**
	 * Instantiates a new choose designer for order request.
	 */
	public ChooseDesignerForOrderRequest() {
	}

	/**
	 * Gets the designer ids.
	 *
	 * @return the designer ids
	 */
	public List<Integer> getDesignerIds() {
		return this.designerIds;
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
		if (this.idJob == null) {
			throw new ObjectParamException("Invalid job ID");
		}
		if (this.designerIds == null || this.designerIds.isEmpty()) {
			throw new ObjectParamException("Invalid list designer");
		}
	}

}
