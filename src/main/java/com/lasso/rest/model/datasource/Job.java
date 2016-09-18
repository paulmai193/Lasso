package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * The persistent class for the jobs database table.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "jobs")
public class Job implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The account. */
	// bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account				account;

	/** The assets url. */
	@Column(name = "assets_url", length = 250)
	private String				assetsUrl;

	/** The budget. */
	@Column(nullable = false)
	private Double				budget;

	/** The category. */
	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category			category;

	/** The completed. */
	private Byte				completed;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	/** The deleted. */
	private Byte				deleted;

	/** The description. */
	@Column(nullable = false, length = 200)
	private String				description;

	/** The discount. */
	private Double				discount;

	/** The fee. */
	@Column(nullable = false)
	private Double				fee;

	/** The further information. */
	@Lob
	@Column(name = "further_information")
	private String				furtherInformation;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer				id;

	/** The jobs accounts. */
	// bi-directional many-to-one association to JobsAccount
	@OneToMany(mappedBy = "job")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<JobsAccount>	jobsAccounts;

	/** The jobs types. */
	// bi-directional many-to-one association to JobsType
	@OneToMany(mappedBy = "job")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<JobsType>		jobsTypes;

	/** The latest submission. */
	@Temporal(TemporalType.DATE)
	@Column(name = "latest_submission", nullable = false)
	private Date				latestSubmission;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	/** The objective. */
	@Column(length = 200)
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

	/** The style. */
	// bi-directional many-to-one association to Style
	@ManyToOne
	@JoinColumn(name = "style_id", nullable = false)
	private Style				style;

	/** The submission. */
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date				submission;

	/**
	 * Instantiates a new job.
	 */
	public Job() {
	}

	/**
	 * Adds the jobs account.
	 *
	 * @param jobsAccount the jobs account
	 * @return the jobs account
	 */
	public JobsAccount addJobsAccount(JobsAccount jobsAccount) {
		this.getJobsAccounts().add(jobsAccount);
		jobsAccount.setJob(this);

		return jobsAccount;
	}

	/**
	 * Adds the jobs type.
	 *
	 * @param jobsType the jobs type
	 * @return the jobs type
	 */
	public JobsType addJobsType(JobsType jobsType) {
		this.getJobsTypes().add(jobsType);
		jobsType.setJob(this);

		return jobsType;
	}

	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	public Account getAccount() {
		return this.account;
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
	 * Gets the category.
	 *
	 * @return the category
	 */
	public Category getCategory() {
		return this.category;
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
	 * Gets the jobs accounts.
	 *
	 * @return the jobs accounts
	 */
	public Set<JobsAccount> getJobsAccounts() {
		return this.jobsAccounts;
	}

	/**
	 * Gets the jobs types.
	 *
	 * @return the jobs types
	 */
	public Set<JobsType> getJobsTypes() {
		return this.jobsTypes;
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
	 * Gets the style.
	 *
	 * @return the style
	 */
	public Style getStyle() {
		return this.style;
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
	 * Removes the jobs account.
	 *
	 * @param jobsAccount the jobs account
	 * @return the jobs account
	 */
	public JobsAccount removeJobsAccount(JobsAccount jobsAccount) {
		this.getJobsAccounts().remove(jobsAccount);
		jobsAccount.setJob(null);

		return jobsAccount;
	}

	/**
	 * Removes the jobs type.
	 *
	 * @param jobsType the jobs type
	 * @return the jobs type
	 */
	public JobsType removeJobsType(JobsType jobsType) {
		this.getJobsTypes().remove(jobsType);
		jobsType.setJob(null);

		return jobsType;
	}

	/**
	 * Sets the account.
	 *
	 * @param account the new account
	 */
	public void setAccount(Account account) {
		this.account = account;
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
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(Category category) {
		this.category = category;
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
	 * Sets the jobs accounts.
	 *
	 * @param jobsAccounts the new jobs accounts
	 */
	public void setJobsAccounts(Set<JobsAccount> jobsAccounts) {
		this.jobsAccounts = jobsAccounts;
	}

	/**
	 * Sets the jobs types.
	 *
	 * @param jobsTypes the new jobs types
	 */
	public void setJobsTypes(Set<JobsType> jobsTypes) {
		this.jobsTypes = jobsTypes;
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
	 * Sets the style.
	 *
	 * @param style the new style
	 */
	public void setStyle(Style style) {
		this.style = style;
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