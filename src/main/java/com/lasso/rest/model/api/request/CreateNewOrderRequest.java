/*
 * 
 */
package com.lasso.rest.model.api.request;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

/**
 * The Class CreateNewOrderRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateNewOrderRequest extends BaseRequest {

	/** The asset url. */
	@JsonProperty("asset_url")
	private String			assetUrl;

	/** The budget. */
	@JsonProperty("job_budget")
	private Double			budget;

	/** The description. */
	@JsonProperty("job_description")
	private String			description;

	/** The further. */
	@JsonProperty("further_information")
	private String			further;

	/** The id category. */
	@JsonProperty("category_id")
	private Integer			idCategory;

	/** The id types. */
	@JsonProperty("style_id")
	private List<Integer>	idStyles;

	/** The id style. */
	@JsonProperty("type_id")
	private Integer			idType;

	/** The last submission. */
	private Date			lastSubmission;

	/** The objective. */
	@JsonProperty("objective")
	private String			objective;

	/** The reference. */
	@JsonProperty("reference")
	private List<String>	reference;

	/** The step. */
	@JsonProperty("save_type")
	private Byte			step;

	/** The submission. */
	private Date			submission;

	/**
	 * Instantiates a new creates the new offer request.
	 */
	public CreateNewOrderRequest() {
	}

	/**
	 * Gets the asset url.
	 *
	 * @return the assetUrl
	 */
	public String getAssetUrl() {
		return this.assetUrl;
	}

	/**
	 * Gets the budget.
	 *
	 * @return the budget
	 */
	public Double getBudget() {
		return this.budget;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the further.
	 *
	 * @return the further
	 */
	public String getFurther() {
		return this.further;
	}

	/**
	 * Gets the id category.
	 *
	 * @return the idCategory
	 */
	public Integer getIdCategory() {
		return this.idCategory;
	}

	/**
	 * Gets the id styles.
	 *
	 * @return the idStyles
	 */
	public List<Integer> getIdStyles() {
		return this.idStyles;
	}

	/**
	 * Gets the id type.
	 *
	 * @return the idType
	 */
	public Integer getIdType() {
		return this.idType;
	}

	/**
	 * Gets the last submission.
	 *
	 * @return the lastSubmission
	 */
	public Date getLastSubmission() {
		return this.lastSubmission;
	}

	/**
	 * Gets the objective.
	 *
	 * @return the objective
	 */
	public String getObjective() {
		return this.objective;
	}

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public List<String> getReference() {
		return this.reference;
	}

	/**
	 * Gets the step.
	 *
	 * @return the step
	 */
	public byte getStep() {
		return this.step;
	}

	/**
	 * Gets the submission.
	 *
	 * @return the submission
	 */
	public Date getSubmission() {
		return this.submission;
	}

	/**
	 * Sets the last submission.
	 *
	 * @param __lastSubmission the lastSubmission to set
	 */
	@JsonProperty("last_submission")
	public void setLastSubmission(String __lastSubmission) {
		DateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.lastSubmission = _dateFormat.parse(__lastSubmission);
		}
		catch (ParseException ex) {
			this.lastSubmission = null;
		}
	}

	/**
	 * Sets the submission.
	 *
	 * @param __submission the submission to set
	 */
	@JsonProperty("submission")
	public void setSubmission(String __submission) {
		DateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.submission = _dateFormat.parse(__submission);
		}
		catch (ParseException ex) {
			this.submission = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.description == null || this.description.isEmpty()) {
			throw new ObjectParamException("Invalid description");
		}
		if (this.idType == null) {
			throw new ObjectParamException("Invalid type");
		}
		if (this.idCategory == null) {
			throw new ObjectParamException("Invalid category");
		}
		if (this.idStyles == null || this.idStyles.isEmpty()) {
			throw new ObjectParamException("Invalid styles");
		}
		if (this.reference == null) {
			throw new ObjectParamException("Invalid reference");
		}
		if (this.budget == null || this.budget.doubleValue() <= 0) {
			throw new ObjectParamException("Invalid budget");
		}
		if (this.submission == null) {
			throw new ObjectParamException("Invalid submission");
		}
		if (this.lastSubmission == null || this.lastSubmission.compareTo(this.submission) < 0) {
			throw new ObjectParamException("Invalid last submission");
		}
		if (this.objective == null) {
			throw new ObjectParamException("Invalid objective");
		}
		if (this.assetUrl == null) {
			throw new ObjectParamException("Invalid assets");
		}
		else if (!this.assetUrl.trim().isEmpty()) {
			try {
				new URL(this.assetUrl);
			}
			catch (Exception _ex) {
				throw new ObjectParamException("Invalid assets", _ex);
			}
		}
		if (this.further == null) {
			throw new ObjectParamException("Invalid further information");
		}
		if (this.step == null) {
			this.step = 1;
		}
	}

}
