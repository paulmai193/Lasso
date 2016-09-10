package com.lasso.rest.model.datasource;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the jobs_accounts database table.
 * 
 */
@Embeddable
public class JobsAccountPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int id;
	private int accountId;
	private int jobId;

	public JobsAccountPK() {
	}

	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="account_id", unique=true, nullable=false)
	public int getAccountId() {
		return this.accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Column(name="job_id", unique=true, nullable=false)
	public int getJobId() {
		return this.jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JobsAccountPK)) {
			return false;
		}
		JobsAccountPK castOther = (JobsAccountPK)other;
		return 
			(this.id == castOther.id)
			&& (this.accountId == castOther.accountId)
			&& (this.jobId == castOther.jobId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.accountId;
		hash = hash * prime + this.jobId;
		
		return hash;
	}
}