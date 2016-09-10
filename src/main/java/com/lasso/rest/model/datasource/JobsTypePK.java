package com.lasso.rest.model.datasource;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the jobs_types database table.
 * 
 */
@Embeddable
public class JobsTypePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int id;
	private int jobId;
	private int typeId;

	public JobsTypePK() {
	}

	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="job_id", unique=true, nullable=false)
	public int getJobId() {
		return this.jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	@Column(name="type_id", unique=true, nullable=false)
	public int getTypeId() {
		return this.typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JobsTypePK)) {
			return false;
		}
		JobsTypePK castOther = (JobsTypePK)other;
		return 
			(this.id == castOther.id)
			&& (this.jobId == castOther.jobId)
			&& (this.typeId == castOther.typeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.jobId;
		hash = hash * prime + this.typeId;
		
		return hash;
	}
}