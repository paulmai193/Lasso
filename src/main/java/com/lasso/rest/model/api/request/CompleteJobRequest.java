/*
 * 
 */
package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

/**
 * The Class EditOrderRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompleteJobRequest extends BaseRequest {

	/** The communication. */
	@JsonProperty("rating_communication")
	private Integer	communication	= 0;

	/** The experience. */
	@JsonProperty("rating_experience")
	private Integer	experience		= 0;

	/** The id designer. */
	@JsonProperty("designer_id")
	private Integer	idDesigner;

	/** The id job. */
	@JsonProperty("job_id")
	private Integer	idJob;

	/** The quality. */
	@JsonProperty("rating_quality")
	private Integer	quality			= 0;

	/**
	 * Instantiates a new edits the job request.
	 */
	public CompleteJobRequest() {
	}

	/**
	 * Gets the communication.
	 *
	 * @return the communication
	 */
	public Integer getCommunication() {
		return this.communication;
	}

	/**
	 * Gets the experience.
	 *
	 * @return the experience
	 */
	public Integer getExperience() {
		return this.experience;
	}

	/**
	 * Gets the id designer.
	 *
	 * @return the id designer
	 */
	public Integer getIdDesigner() {
		return this.idDesigner;
	}

	/**
	 * Gets the id job.
	 *
	 * @return the id job
	 */
	public Integer getIdJob() {
		return this.idJob;
	}

	/**
	 * Gets the quality.
	 *
	 * @return the quality
	 */
	public Integer getQuality() {
		return this.quality;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.CreateNewOrderRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.idJob == null) {
			throw new ObjectParamException("Invalid job ID");
		}
		if (this.idDesigner == null) {
			throw new ObjectParamException("Invalid designer ID");
		}
	}

}
