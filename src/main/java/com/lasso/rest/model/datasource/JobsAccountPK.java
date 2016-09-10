package com.lasso.rest.model.datasource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the jobs_accounts database table.
 *
 * @author Paul Mai
 */
@Embeddable
public class JobsAccountPK implements Serializable {

	/** The Constant serialVersionUID. */
	// default serial version id, required for serializable classes.
	private static final long	serialVersionUID	= 1L;

	/** The account id. */
	private int					accountId;

	/** The id. */
	private int					id;

	/** The job id. */
	private int					jobId;

	/**
	 * Instantiates a new jobs account PK.
	 */
	public JobsAccountPK() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JobsAccountPK)) {
			return false;
		}
		JobsAccountPK castOther = (JobsAccountPK) other;
		return (this.id == castOther.id) && (this.accountId == castOther.accountId)
				&& (this.jobId == castOther.jobId);
	}

	/**
	 * Gets the account id.
	 *
	 * @return the account id
	 */
	@Column(name = "account_id", unique = true, nullable = false)
	public int getAccountId() {
		return this.accountId;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Column(unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	/**
	 * Gets the job id.
	 *
	 * @return the job id
	 */
	@Column(name = "job_id", unique = true, nullable = false)
	public int getJobId() {
		return this.jobId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.accountId;
		hash = hash * prime + this.jobId;

		return hash;
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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the job id.
	 *
	 * @param jobId the new job id
	 */
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
}