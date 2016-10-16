/*
 * 
 */
package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the accounts_ratings database table.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "accounts_ratings")
public class AccountsRating implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The account id. */
	@Column(name = "account_id")
	private int accountId;

	/** The communication. */
	private int communication;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	/** The deleted. */
	private byte deleted;

	/** The experience. */
	private int experience;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** The job id. */
	@Column(name = "job_id")
	private int jobId;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	/** The quality. */
	private int quality;

	/**
	 * Instantiates a new accounts rating.
	 */
	public AccountsRating() {
	}

	/**
	 * Instantiates a new accounts rating.
	 *
	 * @param __designerId
	 *            the designer id
	 * @param __jobId
	 *            the job id
	 * @param __communication
	 *            the communication
	 * @param __experience
	 *            the experience
	 * @param __quality
	 *            the quality
	 */
	public AccountsRating(int __designerId, int __jobId, int __communication, int __experience, int __quality) {
		super();
		this.accountId = __designerId;
		this.communication = __communication;
		this.experience = __experience;
		this.jobId = __jobId;
		this.quality = __quality;
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
	 * Gets the communication.
	 *
	 * @return the communication
	 */
	public int getCommunication() {
		return this.communication;
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
	 * Gets the experience.
	 *
	 * @return the experience
	 */
	public int getExperience() {
		return this.experience;
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
	 * Gets the job id.
	 *
	 * @return the job id
	 */
	public int getJobId() {
		return this.jobId;
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
	 * Gets the quality.
	 *
	 * @return the quality
	 */
	public int getQuality() {
		return this.quality;
	}

	/**
	 * Sets the account id.
	 *
	 * @param accountId
	 *            the new account id
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	/**
	 * Sets the communication.
	 *
	 * @param communication
	 *            the new communication
	 */
	public void setCommunication(int communication) {
		this.communication = communication;
	}

	/**
	 * Sets the created.
	 *
	 * @param created
	 *            the new created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param deleted
	 *            the new deleted
	 */
	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}

	/**
	 * Sets the experience.
	 *
	 * @param experience
	 *            the new experience
	 */
	public void setExperience(int experience) {
		this.experience = experience;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the job id.
	 *
	 * @param jobId
	 *            the new job id
	 */
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	/**
	 * Sets the modified.
	 *
	 * @param modified
	 *            the new modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * Sets the quality.
	 *
	 * @param quality
	 *            the new quality
	 */
	public void setQuality(int quality) {
		this.quality = quality;
	}

}