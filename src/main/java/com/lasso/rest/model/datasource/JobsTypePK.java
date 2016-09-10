package com.lasso.rest.model.datasource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the jobs_types database table.
 *
 * @author Paul Mai
 */
@Embeddable
public class JobsTypePK implements Serializable {

	/** The Constant serialVersionUID. */
	// default serial version id, required for serializable classes.
	private static final long	serialVersionUID	= 1L;

	/** The id. */
	private int					id;

	/** The job id. */
	private int					jobId;

	/** The type id. */
	private int					typeId;

	/**
	 * Instantiates a new jobs type PK.
	 */
	public JobsTypePK() {
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
		if (!(other instanceof JobsTypePK)) {
			return false;
		}
		JobsTypePK castOther = (JobsTypePK) other;
		return (this.id == castOther.id) && (this.jobId == castOther.jobId)
				&& (this.typeId == castOther.typeId);
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

	/**
	 * Gets the type id.
	 *
	 * @return the type id
	 */
	@Column(name = "type_id", unique = true, nullable = false)
	public int getTypeId() {
		return this.typeId;
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
		hash = hash * prime + this.jobId;
		hash = hash * prime + this.typeId;

		return hash;
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

	/**
	 * Sets the type id.
	 *
	 * @param typeId the new type id
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
}