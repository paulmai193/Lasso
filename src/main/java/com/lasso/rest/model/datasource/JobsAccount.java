package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * The persistent class for the jobs_accounts database table.
 * 
 */
@Entity
@Table(name = "jobs_accounts")
@DynamicInsert(true)
@DynamicUpdate(true)
public class JobsAccount implements Serializable {

	private static final long	serialVersionUID	= 1L;
	private JobsAccountPK		id;
	private byte				confirm;
	private Date				created;
	private Date				modified;

	public JobsAccount() {
	}

	@EmbeddedId
	public JobsAccountPK getId() {
		return this.id;
	}

	public void setId(JobsAccountPK id) {
		this.id = id;
	}

	public byte getConfirm() {
		return this.confirm;
	}

	public void setConfirm(byte confirm) {
		this.confirm = confirm;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

}