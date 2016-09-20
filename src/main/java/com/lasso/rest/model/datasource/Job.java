package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.lasso.rest.model.api.request.CreateNewJobRequest;
import com.lasso.rest.model.api.request.EditJobRequest;

/**
 * The persistent class for the jobs database table.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "jobs")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Job implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The account id. */
	@Column(name = "account_id")
	private Integer				accountId;

	/** The assets url. */
	@Column(name = "assets_url")
	private String				assetsUrl;

	/** The budget. */
	private Double				budget;

	/** The category id. */
	@Column(name = "category_id")
	private Integer				categoryId;

	/** The completed. */
	private Byte				completed;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	/** The deleted. */
	private Byte				deleted;

	/** The description. */
	private String				description;

	/** The discount. */
	private Double				discount;

	/** The fee. */
	private Double				fee;

	/** The further information. */
	@Lob
	@Column(name = "further_information")
	private String				furtherInformation;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				id;

	/** The latest submission. */
	@Temporal(TemporalType.DATE)
	@Column(name = "latest_submission")
	private Date				latestSubmission;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	/** The objective. */
	private String				objective;

	/** The paid. */
	private Byte				paid;

	/** The reference. */
	@Lob
	private String				reference;

	/** The stage. */
	private Byte				stage;

	/** The stage date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "stage_date")
	private Date				stageDate;

	/** The status. */
	private Byte				status;

	/** The step. */
	private Byte				step;

	/** The style id. */
	@Column(name = "style_id")
	private Integer				styleId;

	/** The submission. */
	@Temporal(TemporalType.DATE)
	private Date				submission;

	/**
	 * Instantiates a new job.
	 */
	public Job() {
	}

	/**
	 * Instantiates a new job.
	 *
	 * @param __newJobRequest the new job request
	 */
	public Job(CreateNewJobRequest __newJobRequest) {
		this.assetsUrl = __newJobRequest.getAssetUrl();
		this.budget = __newJobRequest.getBudget();
		this.description = __newJobRequest.getDescription();
		this.furtherInformation = __newJobRequest.getFurther();
		this.categoryId = __newJobRequest.getIdCategory();
		this.created = this.modified = new Date();
		this.stage = 0;
		this.step = 1;
		this.styleId = __newJobRequest.getIdStyle();
		this.latestSubmission = __newJobRequest.getLastSubmission();
		this.objective = __newJobRequest.getObjective();
		String _reference = Arrays.toString(__newJobRequest.getReference().toArray());
		this.reference = _reference.substring(1, _reference.length() - 1);
		this.submission = __newJobRequest.getSubmission();
	}

	/**
	 * Gets the account id.
	 *
	 * @return the account id
	 */
	public Integer getAccountId() {
		return this.accountId;
	}

	/**
	 * Gets the assets url.
	 *
	 * @return the assets url
	 */
	public String getAssetsUrl() {
		return this.assetsUrl;
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
	 * Gets the category id.
	 *
	 * @return the category id
	 */
	public Integer getCategoryId() {
		return this.categoryId;
	}

	/**
	 * Gets the completed.
	 *
	 * @return the completed
	 */
	public Byte getCompleted() {
		return this.completed;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public Date getCreated() {
		return this.created;
	}

	/**
	 * Gets the deleted.
	 *
	 * @return the deleted
	 */
	public Byte getDeleted() {
		return this.deleted;
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
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public Double getDiscount() {
		return this.discount;
	}

	/**
	 * Gets the fee.
	 *
	 * @return the fee
	 */
	public Double getFee() {
		return this.fee;
	}

	/**
	 * Gets the further information.
	 *
	 * @return the further information
	 */
	public String getFurtherInformation() {
		return this.furtherInformation;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Gets the latest submission.
	 *
	 * @return the latest submission
	 */
	public Date getLatestSubmission() {
		return this.latestSubmission;
	}

	/**
	 * Gets the modified.
	 *
	 * @return the modified
	 */
	public Date getModified() {
		return this.modified;
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
	 * Gets the paid.
	 *
	 * @return the paid
	 */
	public Byte getPaid() {
		return this.paid;
	}

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference() {
		return this.reference;
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
	 * Gets the stage date.
	 *
	 * @return the stage date
	 */
	public Date getStageDate() {
		return this.stageDate;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Byte getStatus() {
		return this.status;
	}

	/**
	 * Gets the step.
	 *
	 * @return the step
	 */
	public Byte getStep() {
		return this.step;
	}

	/**
	 * Gets the style id.
	 *
	 * @return the style id
	 */
	public Integer getStyleId() {
		return this.styleId;
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
	 * Sets the account id.
	 *
	 * @param accountId the new account id
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * Sets the assets url.
	 *
	 * @param assetsUrl the new assets url
	 */
	public void setAssetsUrl(String assetsUrl) {
		this.assetsUrl = assetsUrl;
	}

	/**
	 * Sets the budget.
	 *
	 * @param budget the new budget
	 */
	public void setBudget(Double budget) {
		this.budget = budget;
	}

	/**
	 * Sets the category id.
	 *
	 * @param categoryId the new category id
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Sets the completed.
	 *
	 * @param completed the new completed
	 */
	public void setCompleted(Byte completed) {
		this.completed = completed;
	}

	/**
	 * Sets the created.
	 *
	 * @param created the new created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param deleted the new deleted
	 */
	public void setDeleted(Byte deleted) {
		this.deleted = deleted;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the discount.
	 *
	 * @param discount the new discount
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/**
	 * Sets the fee.
	 *
	 * @param fee the new fee
	 */
	public void setFee(Double fee) {
		this.fee = fee;
	}

	/**
	 * Sets the further information.
	 *
	 * @param furtherInformation the new further information
	 */
	public void setFurtherInformation(String furtherInformation) {
		this.furtherInformation = furtherInformation;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Sets the latest submission.
	 *
	 * @param latestSubmission the new latest submission
	 */
	public void setLatestSubmission(Date latestSubmission) {
		this.latestSubmission = latestSubmission;
	}

	/**
	 * Sets the modified.
	 *
	 * @param modified the new modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * Sets the objective.
	 *
	 * @param objective the new objective
	 */
	public void setObjective(String objective) {
		this.objective = objective;
	}

	/**
	 * Sets the paid.
	 *
	 * @param paid the new paid
	 */
	public void setPaid(Byte paid) {
		this.paid = paid;
	}

	/**
	 * Sets the reference.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Sets the stage.
	 *
	 * @param stage the new stage
	 */
	public void setStage(Byte stage) {
		this.stage = stage;
	}

	/**
	 * Sets the stage date.
	 *
	 * @param stageDate the new stage date
	 */
	public void setStageDate(Date stageDate) {
		this.stageDate = stageDate;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * Sets the step.
	 *
	 * @param step the new step
	 */
	public void setStep(Byte step) {
		this.step = step;
	}

	/**
	 * Sets the style id.
	 *
	 * @param styleId the new style id
	 */
	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}

	/**
	 * Sets the submission.
	 *
	 * @param submission the new submission
	 */
	public void setSubmission(Date submission) {
		this.submission = submission;
	}

	public void update(EditJobRequest __editJobRequest) {
		this.assetsUrl = __editJobRequest.getAssetUrl();
		this.budget = __editJobRequest.getBudget();
		this.description = __editJobRequest.getDescription();
		this.furtherInformation = __editJobRequest.getFurther();
		this.categoryId = __editJobRequest.getIdCategory();
		this.created = this.modified = new Date();
		this.styleId = __editJobRequest.getIdStyle();
		this.latestSubmission = __editJobRequest.getLastSubmission();
		this.objective = __editJobRequest.getObjective();
		String _reference = Arrays.toString(__editJobRequest.getReference().toArray());
		this.reference = _reference.substring(1, _reference.length() - 1);
		this.submission = __editJobRequest.getSubmission();
	}

}