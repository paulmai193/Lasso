package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
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

	/** The id. */
	private int					id;

	/** The created. */
	private Date				created;

	/** The image. */
	private String				image;

	/** The modified. */
	private Date				modified;

	/** The sort. */
	private int					sort;

	/** The status. */
	private byte				status;

	/** The title. */
	private String				title;

	/** The types. */
	private Set<Type>			types;

	/**
	 * Instantiates a new category.
	 */
	public Category() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public int getId() {
		return this.id;
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
	 * Gets the created.
	 *
	 * @return the created
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated() {
		return this.created;
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
	 * Gets the image.
	 *
	 * @return the image
	 */
	@Column(nullable = false, length = 45)
	public String getImage() {
		return this.image;
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
	 * Gets the modified.
	 *
	 * @return the modified
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModified() {
		return this.modified;
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
	 * Gets the sort.
	 *
	 * @return the sort
	 */
	public int getSort() {
		return this.sort;
	}

	/**
	 * Sets the sort.
	 *
	 * @param sort the new sort
	 */
	public void setSort(int sort) {
		this.sort = sort;
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
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(byte status) {
		this.status = status;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	@Column(nullable = false, length = 250)
	public String getTitle() {
		return this.title;
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
	 * Gets the types.
	 *
	 * @return the types
	 */
	// bi-directional many-to-one association to Type
	@OneToMany(mappedBy = "category")
	public Set<Type> getTypes() {
		return this.types;
	}

	/**
	 * Sets the types.
	 *
	 * @param types the new types
	 */
	public void setTypes(Set<Type> types) {
		this.types = types;
	}

	/**
	 * Adds the type.
	 *
	 * @param type the type
	 * @return the type
	 */
	public Type addType(Type type) {
		getTypes().add(type);
		type.setCategory(this);

		return type;
	}

	/**
	 * Removes the type.
	 *
	 * @param type the type
	 * @return the type
	 */
	public Type removeType(Type type) {
		getTypes().remove(type);
		type.setCategory(null);

		return type;
	}

}
