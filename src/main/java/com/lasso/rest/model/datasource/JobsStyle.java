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

/**
 * The persistent class for the jobs_styles database table.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "jobs_styles")
@NamedQuery(name = "JobsStyle.findAll", query = "SELECT j FROM JobsStyle j")
public class JobsStyle implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int					id;

	/** The job id. */
	@Column(name = "job_id")
	private int					jobId;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	/** The style id. */
	@Column(name = "style_id")
	private int					styleId;

	/**
	 * Instantiates a new jobs style.
	 */
	public JobsStyle() {
	}

	/**
	 * Instantiates a new jobs style.
	 *
	 * @param __jobId the job id
	 * @param __styleId the style id
	 */
	public JobsStyle(int __jobId, int __styleId) {
		super();
		this.jobId = __jobId;
		this.styleId = __styleId;
		this.created = this.modified = new Date();
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
	 * Gets the style id.
	 *
	 * @return the style id
	 */
	public int getStyleId() {
		return this.styleId;
	}

	/**
	 * Sets the created.
	 *
	 * @param created the new created
	 */
	public void setCreated(Date created) {
		this.created = created;
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
	 * Sets the modified.
	 *
	 * @param modified the new modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * Sets the style id.
	 *
	 * @param styleId the new style id
	 */
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

}