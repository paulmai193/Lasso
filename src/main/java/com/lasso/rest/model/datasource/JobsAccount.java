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
 * @author Paul Mai
 */
@Entity
@Table(name = "jobs_accounts")
@DynamicInsert(true)
@DynamicUpdate(true)
public class JobsAccount implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The confirm. */
	private byte				confirm;

	/** The created. */
	private Date				created;

	/** The id. */
	private JobsAccountPK		id;

	/** The modified. */
	private Date				modified;

	/**
	 * Instantiates a new jobs account.
	 */
	public JobsAccount() {
	}

	/**
	 * Gets the confirm.
	 *
	 * @return the confirm
	 */
	public byte getConfirm() {
		return this.confirm;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated() {
		return this.created;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@EmbeddedId
	public JobsAccountPK getId() {
		return this.id;
	}

	/**
	 * Gets the modified.
	 *
	 * @return the modified
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModified() {
		return this.modified;
	}

	/**
	 * Sets the confirm.
	 *
	 * @param confirm the new confirm
	 */
	public void setConfirm(byte confirm) {
		this.confirm = confirm;
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
	public void setId(JobsAccountPK id) {
		this.id = id;
	}

	/**
	 * Sets the modified.
	 *
	 * @param modified the new modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

}