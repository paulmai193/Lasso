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

import com.lasso.rest.model.api.request.CreateNewOrderRequest;
import com.lasso.rest.model.api.request.EditOrderRequest;

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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				id;

	@Column(name = "account_id")
	private Integer				accountId;

	@Column(name = "assets_url")
	private String				assetsUrl;

	private Double				budget;

	@Column(name = "category_id")
	private Integer				categoryId;

	private Byte				completed;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	private Byte				deleted;

	private String				description;

	private Double				discount;

	private Double				fee;

	@Lob
	@Column(name = "further_information")
	private String				furtherInformation;

	@Temporal(TemporalType.DATE)
	@Column(name = "latest_submission")
	private Date				latestSubmission;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	private String				objective;

	private Byte				paid;

	@Lob
	private String				reference;

	private Byte				stage;

	@Temporal(TemporalType.DATE)
	@Column(name = "stage_date")
	private Date				stageDate;

	private Byte				status;

	private Byte				step;

	@Temporal(TemporalType.DATE)
	private Date				submission;

	@Column(name = "type_id")
	private Integer				typeId;

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
	public Job(CreateNewOrderRequest __newJobRequest) {
		this.assetsUrl = __newJobRequest.getAssetUrl();
		this.budget = __newJobRequest.getBudget();
		this.description = __newJobRequest.getDescription();
		this.furtherInformation = __newJobRequest.getFurther();
		this.categoryId = __newJobRequest.getIdCategory();
		this.created = this.modified = new Date();
		this.stage = 0;
		this.step = 1;
		this.typeId = __newJobRequest.getIdType();
		this.latestSubmission = __newJobRequest.getLastSubmission();
		this.objective = __newJobRequest.getObjective();
		String _reference = Arrays.toString(__newJobRequest.getReference().toArray());
		this.reference = _reference.substring(1, _reference.length() - 1);
		this.submission = __newJobRequest.getSubmission();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAssetsUrl() {
		return this.assetsUrl;
	}

	public void setAssetsUrl(String assetsUrl) {
		this.assetsUrl = assetsUrl;
	}

	public Double getBudget() {
		return this.budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Byte getCompleted() {
		return this.completed;
	}

	public void setCompleted(Byte completed) {
		this.completed = completed;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Byte getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Byte deleted) {
		this.deleted = deleted;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getFee() {
		return this.fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getFurtherInformation() {
		return this.furtherInformation;
	}

	public void setFurtherInformation(String furtherInformation) {
		this.furtherInformation = furtherInformation;
	}

	public Date getLatestSubmission() {
		return this.latestSubmission;
	}

	public void setLatestSubmission(Date latestSubmission) {
		this.latestSubmission = latestSubmission;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getObjective() {
		return this.objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public Byte getPaid() {
		return this.paid;
	}

	public void setPaid(Byte paid) {
		this.paid = paid;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Byte getStage() {
		return this.stage;
	}

	public void setStage(Byte stage) {
		this.stage = stage;
	}

	public Date getStageDate() {
		return this.stageDate;
	}

	public void setStageDate(Date stageDate) {
		this.stageDate = stageDate;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getStep() {
		return this.step;
	}

	public void setStep(Byte step) {
		this.step = step;
	}

	public Date getSubmission() {
		return this.submission;
	}

	public void setSubmission(Date submission) {
		this.submission = submission;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * Update.
	 *
	 * @param __editJobRequest the edit job request
	 */
	public void update(EditOrderRequest __editJobRequest) {
		this.assetsUrl = __editJobRequest.getAssetUrl();
		this.budget = __editJobRequest.getBudget();
		this.description = __editJobRequest.getDescription();
		this.furtherInformation = __editJobRequest.getFurther();
		this.categoryId = __editJobRequest.getIdCategory();
		this.modified = new Date();
		this.typeId = __editJobRequest.getIdType();
		this.latestSubmission = __editJobRequest.getLastSubmission();
		this.objective = __editJobRequest.getObjective();
		String _reference = Arrays.toString(__editJobRequest.getReference().toArray());
		this.reference = _reference.substring(1, _reference.length() - 1);
		this.submission = __editJobRequest.getSubmission();
	}

}