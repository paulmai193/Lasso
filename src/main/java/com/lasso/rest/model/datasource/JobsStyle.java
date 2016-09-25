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
 */
@Entity
@Table(name = "jobs_styles")
@NamedQuery(name = "JobsStyle.findAll", query = "SELECT j FROM JobsStyle j")
public class JobsStyle implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int					id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	@Column(name = "job_id")
	private int					jobId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	@Column(name = "style_id")
	private int					styleId;

	public JobsStyle() {
	}

	public JobsStyle(int __jobId, int __styleId) {
		super();
		this.jobId = __jobId;
		this.styleId = __styleId;
		this.created = this.modified = new Date();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
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

	public int getStyleId() {
		return this.styleId;
	}

	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

}