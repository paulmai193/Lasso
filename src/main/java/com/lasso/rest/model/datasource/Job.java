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
 */
@Entity
@Table(name = "jobs")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Job implements Serializable {

	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					accountId;
	private String				assetsUrl;
	private double				budget;
	private int					categoryId;
	private Date				created;
	private String				description;
	private String				furtherInformation;
	private Date				latestSubmission;
	private Date				modified;
	private String				objective;
	private String				reference;
	private byte				status;
	private int					styleId;
	private Date				submission;

	public Job() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "account_id", nullable = false)
	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Column(name = "assets_url", length = 250)
	public String getAssetsUrl() {
		return this.assetsUrl;
	}

	public void setAssetsUrl(String assetsUrl) {
		this.assetsUrl = assetsUrl;
	}

	@Column(nullable = false)
	public double getBudget() {
		return this.budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	@Column(name = "category_id", nullable = false)
	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(nullable = false, length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Lob
	@Column(name = "further_information")
	public String getFurtherInformation() {
		return this.furtherInformation;
	}

	public void setFurtherInformation(String furtherInformation) {
		this.furtherInformation = furtherInformation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "latest_submission", nullable = false)
	public Date getLatestSubmission() {
		return this.latestSubmission;
	}

	public void setLatestSubmission(Date latestSubmission) {
		this.latestSubmission = latestSubmission;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Column(length = 200)
	public String getObjective() {
		return this.objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	@Lob
	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Column(name = "style_id", nullable = false)
	public int getStyleId() {
		return this.styleId;
	}

	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getSubmission() {
		return this.submission;
	}

	public void setSubmission(Date submission) {
		this.submission = submission;
	}

}