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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the promo_histories database table.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "promo_histories")
@NamedQuery(name = "PromoHistory.findAll", query = "SELECT p FROM PromoHistory p")
public class PromoHistory implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The account id. */
	@Column(name = "account_id")
	private int accountId;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	/** The deleted. */
	private byte deleted;

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

	/** The promo code id. */
	@Column(name = "promo_code_id")
	private int promoCodeId;

	/**
	 * Instantiates a new promo history.
	 */
	public PromoHistory() {
	}

	/**
	 * Instantiates a new promo history.
	 *
	 * @param __accountId
	 *            the account id
	 * @param __jobId
	 *            the job id
	 * @param __promoCodeId
	 *            the promo code id
	 */
	public PromoHistory(int __accountId, int __jobId, int __promoCodeId) {
		super();
		this.created = this.modified = new Date();
		this.accountId = __accountId;
		this.jobId = __jobId;
		this.promoCodeId = __promoCodeId;
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
	 * Gets the promo code id.
	 *
	 * @return the promo code id
	 */
	public int getPromoCodeId() {
		return this.promoCodeId;
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
	 * Sets the promo code id.
	 *
	 * @param promoCodeId
	 *            the new promo code id
	 */
	public void setPromoCodeId(int promoCodeId) {
		this.promoCodeId = promoCodeId;
	}

}