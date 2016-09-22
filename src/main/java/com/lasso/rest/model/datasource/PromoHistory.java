package com.lasso.rest.model.datasource;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the promo_histories database table.
 * 
 */
@Entity
@Table(name="promo_histories")
@NamedQuery(name="PromoHistory.findAll", query="SELECT p FROM PromoHistory p")
public class PromoHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="account_id")
	private int accountId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private byte deleted;

	@Column(name="job_id")
	private int jobId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	@Column(name="promo_code_id")
	private int promoCodeId;

	public PromoHistory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public byte getDeleted() {
		return this.deleted;
	}

	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}

	public int getJobId() {
		return this.jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public int getPromoCodeId() {
		return this.promoCodeId;
	}

	public void setPromoCodeId(int promoCodeId) {
		this.promoCodeId = promoCodeId;
	}

}