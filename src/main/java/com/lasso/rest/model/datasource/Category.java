package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * The Class Category.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "categories")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Category implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	/** The deleted. */
	private byte				deleted;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int					id;

	/** The image. */
	private String				image;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	/** The status. */
	private byte				status;

	/** The title. */
	private String				title;

	/** The types. */
	// bi-directional many-to-one association to Type
	@OneToMany(mappedBy = "category")
	private List<Type>			types;

	/**
	 * Instantiates a new category.
	 */
	public Category() {
	}

	/**
	 * Adds the type.
	 *
	 * @param type the type
	 * @return the type
	 */
	public Type addType(Type type) {
		this.getTypes().add(type);
		type.setCategory(this);

		return type;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
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
	 * Gets the types.
	 *
	 * @return the types
	 */
	public List<Type> getTypes() {
		return this.types;
	}

	/**
	 * Removes the type.
	 *
	 * @param type the type
	 * @return the type
	 */
	public Type removeType(Type type) {
		this.getTypes().remove(type);
		type.setCategory(null);

		return type;
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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(String image) {
		this.image = image;
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
	 * Sets the types.
	 *
	 * @param types the new types
	 */
	public void setTypes(List<Type> types) {
		this.types = types;
	}

}
