package com.lasso.rest.model.datasource;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * The Class Category.
 *
 * @author Paul Mai
 */
@Entity
@Table(catalog = "art_design", name = "styles")
@DynamicInsert(true)
@DynamicUpdate(true)
public final class Category {

	/** The created. */
	@Column(length = 19, name = "created")
	private Date		created;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(length = 11, name = "id")
	private Integer		id;

	/** The image. */
	@Column(length = 45, name = "image")
	private String		image;

	/** The modified. */
	@Column(length = 19, name = "modified")
	private Date		modified;

	/** The sort. */
	@Column(length = 11, name = "sort")
	private Integer		sort;

	/** The status. */
	@Column(length = 1, name = "status")
	private Byte		status;

	/** The title. */
	@Column(length = 45, name = "title")
	private String		title;

	/** The types. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Type>	types	= new HashSet<>();

	/**
	 * Instantiates a new category.
	 */
	public Category() {
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
	 * Gets all types of this category.
	 *
	 * @return the types
	 */
	public Set<Type> getTypes() {
		return this.types;
	}

	/**
	 * Sets the created.
	 *
	 * @param __created the created to set
	 */
	public void setCreated(Date __created) {
		this.created = __created;
	}

	/**
	 * Sets the id.
	 *
	 * @param __id the id to set
	 */
	public void setId(Integer __id) {
		this.id = __id;
	}

	/**
	 * Sets the image.
	 *
	 * @param __image the image to set
	 */
	public void setImage(String __image) {
		this.image = __image;
	}

	/**
	 * Sets the modified.
	 *
	 * @param __modified the modified to set
	 */
	public void setModified(Date __modified) {
		this.modified = __modified;
	}

	/**
	 * Sets the sort.
	 *
	 * @param __sort the sort to set
	 */
	public void setSort(Integer __sort) {
		this.sort = __sort;
	}

	/**
	 * Sets the status.
	 *
	 * @param __status the status to set
	 */
	public void setStatus(Byte __status) {
		this.status = __status;
	}

	/**
	 * Sets the title.
	 *
	 * @param __title the title to set
	 */
	public void setTitle(String __title) {
		this.title = __title;
	}

	/**
	 * Sets all types to this category.
	 *
	 * @param __types the types to set
	 */
	public void setTypes(Set<Type> __types) {
		this.types = __types;
	}

}
