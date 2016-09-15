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
	private int					accountId;

	/** The assets url. */
	private String				assetsUrl;

	/** The budget. */
	private double				budget;

	/** The category id. */
	private int					categoryId;

	/** The created. */
	private Date				created;

	/** The description. */
	private String				description;

	/** The further information. */
	private String				furtherInformation;

	/** The id. */
	private int					id;

	/** The latest submission. */
	private Date				latestSubmission;

	/** The modified. */
	private Date				modified;

	/** The objective. */
	private String				objective;

	/** The reference. */
	private String				reference;

	/** The status. */
	private byte				status;

	/** The style id. */
	private int					styleId;

	/** The submission. */
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
	@Column(name = "account_id", nullable = false)
	public int getAccountId() {
		return this.accountId;
	}

	/**
	 * Gets the assets url.
	 *
	 * @return the assets url
	 */
	@Column(name = "assets_url", length = 250)
	public String getAssetsUrl() {
		return this.assetsUrl;
	}

	/**
	 * Gets the budget.
	 *
	 * @return the budget
	 */
	@Column(nullable = false)
	public double getBudget() {
		return this.budget;
	}

	/**
	 * Gets the category id.
	 *
	 * @return the category id
	 */
	@Column(name = "category_id", nullable = false)
	public int getCategoryId() {
		return this.categoryId;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated() {
		return this.created;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	@Column(nullable = false, length = 200)
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the further information.
	 *
	 * @return the further information
	 */
	@Lob
	@Column(name = "further_information")
	public String getFurtherInformation() {
		return this.furtherInformation;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	/**
	 * Gets the latest submission.
	 *
	 * @return the latest submission
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "latest_submission", nullable = false)
	public Date getLatestSubmission() {
		return this.latestSubmission;
	}

	/**
	 * Gets the modified.
	 *
	 * @return the modified
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModified() {
		return this.modified;
	}

	/**
	 * Gets the objective.
	 *
	 * @return the objective
	 */
	@Column(length = 200)
	public String getObjective() {
		return this.objective;
	}

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	@Lob
	public String getReference() {
		return this.reference;
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
	 * Gets the style id.
	 *
	 * @return the style id
	 */
	@Column(name = "style_id", nullable = false)
	public int getStyleId() {
		return this.styleId;
	}

	/**
	 * Gets the submission.
	 *
	 * @return the submission
	 */
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
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
	 * Sets the created.
	 *
	 * @param created the new created
	 */
	public void setCreated(Date created) {
		this.created = created;
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
	 * Sets the reference.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
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