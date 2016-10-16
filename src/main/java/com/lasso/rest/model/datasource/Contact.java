/*
 * 
 */
package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the contacts database table.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The address. */
	private String address;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	/** The deleted. */
	private byte deleted;

	/** The email. */
	private String email;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** The message. */
	@Lob
	private String message;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	/** The name. */
	private String name;

	/** The phone. */
	private String phone;

	/** The status. */
	private byte status;

	/** The type. */
	private byte type;

	/**
	 * Instantiates a new contact.
	 */
	public Contact() {
	}

	/**
	 * Instantiates a new contact.
	 *
	 * @param __email
	 *            the email
	 * @param __message
	 *            the message
	 * @param __name
	 *            the name
	 * @param __phone
	 *            the phone
	 * @param __type
	 *            the type
	 */
	public Contact(String __email, String __message, String __name, String __phone, byte __type) {
		super();
		this.email = __email;
		this.message = __message;
		this.name = __name;
		this.phone = __phone;
		this.type = __type;
		this.created = this.modified = new Date();
		this.status = 1;
		this.deleted = 0;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return this.phone;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public byte getType() {
		return this.type;
	}

	/**
	 * Sets the address.
	 *
	 * @param address
	 *            the new address
	 */
	public void setAddress(String address) {
		this.address = address;
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
	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            the new message
	 */
	public void setMessage(String message) {
		this.message = message;
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

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone
	 *            the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(byte status) {
		this.status = status;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(byte type) {
		this.type = type;
	}

}