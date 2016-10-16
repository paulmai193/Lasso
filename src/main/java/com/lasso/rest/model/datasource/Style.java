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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the styles database table.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "styles")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Style implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	/** The deleted. */
	private Byte deleted;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** The image. */
	private String image;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	/** The sort. */
	private Integer sort;

	/** The status. */
	private Byte status;

	/** The title. */
	private String title;

	/**
	 * Instantiates a new style.
	 */
	public Style() {
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
	public Byte getDeleted() {
		return this.deleted;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public String getImage() {
		return this.image;
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
	 * Gets the sort.
	 *
	 * @return the sort
	 */
	public Integer getSort() {
		return this.sort;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Byte getStatus() {
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
	public void setDeleted(Byte deleted) {
		this.deleted = deleted;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Sets the image.
	 *
	 * @param image
	 *            the new image
	 */
	public void setImage(String image) {
		this.image = image;
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
	 * Sets the sort.
	 *
	 * @param __sort
	 *            the new sort
	 */
	public void setSort(Integer __sort) {
		this.sort = __sort;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}