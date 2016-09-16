package com.lasso.rest.model.datasource;

import java.io.Serializable;
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
	private int					accountId;

	/** The assets url. */
	@Column(name = "assets_url")
	private String				assetsUrl;

	/** The budget. */
	private double				budget;

	/** The category id. */
	@Column(name = "category_id")
	private int					categoryId;

	/** The completed. */
	private byte				completed;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	/** The deleted. */
	private byte				deleted;

	/** The description. */
	private String				description;

	/** The discount. */
	private double				discount;

	/** The further information. */
	@Lob
	@Column(name = "further_information")
	private String				furtherInformation;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int					id;

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
	private byte				paid;

	/** The reference. */
	@Lob
	private String				reference;

	/** The stage. */
	private byte				stage;

	/** The stage date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "stage_date")
	private Date				stageDate;

	/** The status. */
	private byte				status;

	/** The step. */
	private byte				step;

	/** The style id. */
	@Column(name = "style_id")
	private int					styleId;

	/** The submission. */
	@Temporal(TemporalType.DATE)
	private Date				submission;

	/**
	 * Instantiates a new job.
	 */
	public Job() {
	}

	/**
	 * Gets the account id.
	 *
	 * @return the account id
	 */
	public int getAccountId() {
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
	public double getBudget() {
		return this.budget;
	}

	/**
	 * Gets the category id.
	 *
	 * @return the category id
	 */
	public int getCategoryId() {
		return this.categoryId;
	}

	/**
	 * Gets the completed.
	 *
	 * @return the completed
	 */
	public byte getCompleted() {
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
	public byte getDeleted() {
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
	public double getDiscount() {
		return this.discount;
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
	public int getId() {
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
	public byte getPaid() {
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
	public byte getStage() {
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
	public byte getStatus() {
		return this.status;
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
	 * Gets the style id.
	 *
	 * @return the style id
	 */
	public int getStyleId() {
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
	public void setAccountId(int accountId) {
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
	public void setBudget(double budget) {
		this.budget = budget;
	}

	/**
	 * Sets the category id.
	 *
	 * @param categoryId the new category id
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Sets the completed.
	 *
	 * @param completed the new completed
	 */
	public void setCompleted(byte completed) {
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
	public void setDeleted(byte deleted) {
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
	public void setDiscount(double discount) {
		this.discount = discount;
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
	public void setId(int id) {
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
	public void setPaid(byte paid) {
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
	public void setStage(byte stage) {
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
	public void setStatus(byte status) {
		this.status = status;
	}

	/**
	 * Sets the step.
	 *
	 * @param step the new step
	 */
	public void setStep(byte step) {
		this.step = step;
	}

	/**
	 * Sets the style id.
	 *
	 * @param styleId the new style id
	 */
	public void setStyleId(int styleId) {
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

}