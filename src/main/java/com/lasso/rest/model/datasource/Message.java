package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * The persistent class for the messages database table.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "messages")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Message implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	/** The deleted. */
	private byte				deleted;

	/** The from account id. */
	@Column(name = "from_account_id")
	private int					fromAccountId;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int					id;

	/** The is read. */
	@Column(name = "is_read")
	private byte				isRead;

	/** The job id. */
	@Column(name = "job_id")
	private int					jobId;

	/** The message. */
	@Lob
	private String				message;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	/** The parent id. */
	@Column(name = "parent_id")
	private int					parentId;

	/** The status. */
	private byte				status;

	/** The title. */
	private String				title;

	/** The to account id. */
	@Column(name = "to_account_id")
	private int					toAccountId;

	/** The user id. */
	@Column(name = "user_id")
	private int					userId;

	/**
	 * Instantiates a new message.
	 */
	public Message() {
	}

	public Message(int __fromAccountId, int __jobId, String __message, String __title,
	        int __toAccountId) {
		super();
		this.created = this.modified = new Date();
		this.fromAccountId = __fromAccountId;
		this.jobId = __jobId;
		this.message = __message;
		this.parentId = 0;
		this.status = 1;
		this.title = __title;
		this.toAccountId = __toAccountId;
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
	public byte getDeleted() {
		return this.deleted;
	}

	/**
	 * Gets the from account id.
	 *
	 * @return the from account id
	 */
	public int getFromAccountId() {
		return this.fromAccountId;
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
	 * Gets the checks if is read.
	 *
	 * @return the checks if is read
	 */
	public byte getIsRead() {
		return this.isRead;
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
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
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
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	public int getParentId() {
		return this.parentId;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public byte getStatus() {
		return this.status;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Gets the to account id.
	 *
	 * @return the to account id
	 */
	public int getToAccountId() {
		return this.toAccountId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return this.userId;
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
	 * Sets the deleted.
	 *
	 * @param deleted the new deleted
	 */
	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}

	/**
	 * Sets the from account id.
	 *
	 * @param fromAccountId the new from account id
	 */
	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
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
	 * Sets the checks if is read.
	 *
	 * @param isRead the new checks if is read
	 */
	public void setIsRead(byte isRead) {
		this.isRead = isRead;
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
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
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
	 * Sets the parent id.
	 *
	 * @param parentId the new parent id
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(byte status) {
		this.status = status;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the to account id.
	 *
	 * @param toAccountId the new to account id
	 */
	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

}