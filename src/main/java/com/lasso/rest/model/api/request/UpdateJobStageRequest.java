package com.lasso.rest.model.api.request;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.define.JobStageConstant;
import com.lasso.exception.ObjectParamException;

/**
 * The Class ConfirmOrderRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateJobStageRequest extends BaseRequest {

	/** The delivery date. */
	private Date	deliveryDate;

	/** The id job. */
	@JsonProperty("job_id")
	private Integer	idJob;

	/** The stage. */
	private Byte	stage;

	/**
	 * Instantiates a new confirm order request.
	 */
	public UpdateJobStageRequest() {
	}

	/**
	 * Gets the delivery date.
	 *
	 * @return the delivery date
	 */
	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	/**
	 * Gets the id job.
	 *
	 * @return the idJob
	 */
	public Integer getIdJob() {
		return this.idJob;
	}

	/**
	 * Gets the stage.
	 *
	 * @return the stage
	 */
	public Byte getStage() {
		return this.stage;
	}

	/**
	 * Sets the delivery date.
	 *
	 * @param __deliveryDate the new delivery date
	 */
	@JsonProperty("delivery_date")
	public void setDeliveryDate(String __deliveryDate) {
		DateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.deliveryDate = _dateFormat.parse(__deliveryDate);
		}
		catch (Exception _ex) {
			this.deliveryDate = null;
		}
	}

	/**
	 * Sets the stage.
	 *
	 * @param __stage the new stage
	 */
	@JsonProperty("job_stage")
	public void setStage(Byte __stage) {
		if (__stage != null) {
			if (__stage.equals(JobStageConstant.JOB_STAGE_1ST_DRAFT.getCode())
					|| (__stage.equals(JobStageConstant.JOB_STAGE_REVISED.getCode()))
					|| (__stage.equals(JobStageConstant.JOB_STAGE_FINAL_ARTWORK.getCode()))
					|| (__stage.equals(JobStageConstant.JOB_STAGE_COMPLETED.getCode()))) {
				this.stage = __stage;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.stage == null) {
			throw new ObjectParamException("Invalid job stage");
		}
		if (this.idJob == null) {
			throw new ObjectParamException("Invalid job");
		}
		if (this.deliveryDate == null) {
			throw new ObjectParamException("Invalid delivery date");
		}
	}

}
