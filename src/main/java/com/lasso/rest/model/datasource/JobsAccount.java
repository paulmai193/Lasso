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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the jobs_accounts database table.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "jobs_accounts")
@DynamicInsert(true)
@DynamicUpdate(true)
public class JobsAccount implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The account id. */
	@Column(name = "account_id")
	private Integer accountId;

	/** The confirm. */
	private Byte confirm;

	/** The counter. */
	private Double counter;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	/** The deleted. */
	private Byte deleted;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** The job id. */
	@Column(name = "job_id")
	private Integer jobId;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	/**
	 * Instantiates a new jobs account.
	 */
	public JobsAccount() {
	}

	/**
	 * Instantiates a new jobs account.
	 *
	 * @param __accountId
	 *            the account id
	 * @param __jobId
	 *            the job id
	 */
	public JobsAccount(Integer __accountId, Integer __jobId) {
		super();
		this.accountId = __accountId;
		this.jobId = __jobId;
		this.created = this.modified = new Date();
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
	 * Gets the confirm.
	 *
	 * @return the confirm
	 */
	public Byte getConfirm() {
		return this.confirm;
	}

	/**
	 * Gets the counter.
	 *
	 * @return the counter
	 */
	public Double getCounter() {
		return this.counter;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Gets the job id.
	 *
	 * @return the job id
	 */
	public Integer getJobId() {
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
	 * Sets the account id.
	 *
	 * @param accountId
	 *            the new account id
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * Sets the confirm.
	 *
	 * @param confirm
	 *            the new confirm
	 */
	public void setConfirm(Byte confirm) {
		this.confirm = confirm;
	}

	/**
	 * Sets the counter.
	 *
	 * @param counter
	 *            the new counter
	 */
	public void setCounter(Double counter) {
		this.counter = counter;
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
	public void setDeleted(Byte deleted) {
		this.deleted = deleted;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Sets the job id.
	 *
	 * @param jobId
	 *            the new job id
	 */
	public void setJobId(Integer jobId) {
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

}