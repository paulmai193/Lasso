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
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the promo_codes database table.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "promo_codes")
public class PromoCode implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The code. */
	private String				code;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	/** The deleted. */
	private byte				deleted;

	/** The description. */
	@Lob
	private String				description;

	/** The discount. */
	private double				discount;

	/** The end date. */
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date				endDate;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int					id;

	/** The limit. */
	private int					limit;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	/** The start date. */
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date				startDate;

	/** The status. */
	private byte				status;

	/** The title. */
	private String				title;

	/** The type. */
	private byte				type;

	/**
	 * Instantiates a new promo code.
	 */
	public PromoCode() {
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return this.code;
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public double getDiscount() {
		return this.discount;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return this.endDate;
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
	 * Gets the limit.
	 *
	 * @return the limit
	 */
	public int getLimit() {
		return this.limit;
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
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return this.startDate;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public byte getType() {
		return this.type;
	}

	/**
	 * Sets the code.
	 *
	 * @param code
	 *        the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Sets the created.
	 *
	 * @param created
	 *        the new created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param deleted
	 *        the new deleted
	 */
	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *        the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the discount.
	 *
	 * @param discount
	 *        the new discount
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate
	 *        the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *        the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the limit.
	 *
	 * @param limit
	 *        the new limit
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * Sets the modified.
	 *
	 * @param modified
	 *        the new modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate
	 *        the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *        the new status
	 */
	public void setStatus(byte status) {
		this.status = status;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *        the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *        the new type
	 */
	public void setType(byte type) {
		this.type = type;
	}

}