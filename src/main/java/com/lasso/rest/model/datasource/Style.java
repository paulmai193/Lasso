package com.lasso.rest.model.datasource;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * The Class Style.
 *
 * @author Paul Mai
 */
@Entity
@Table(catalog = "art_design", name = "styles")
@DynamicInsert(true)
@DynamicUpdate(true)
public final class Style {

	// /** The all types. */
	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "type", cascade = CascadeType.ALL,
	// orphanRemoval = true)
	// private Set<Type_Style> allTypes = new HashSet<>();

	/** The created. */
	@Column(length = 19, name = "created")
	private Date	created;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(length = 11, name = "id")
	private Integer	id;

	/** The image. */
	@Column(length = 45, name = "image")
	private String	image;

	/** The modified. */
	@Column(length = 19, name = "modified")
	private Date	modified;

	/** The sort. */
	@Column(length = 11, name = "sort")
	private Integer	sort;

	/** The status. */
	@Column(length = 1, name = "status")
	private Byte	status;

	/** The title. */
	@Column(length = 250, name = "title")
	private String	title;

	/**
	 * Instantiates a new style.
	 */
	public Style() {
	}

	// /**
	// * Gets the all types.
	// *
	// * @return the allTypes
	// */
	// public Set<Type_Style> getAllTypes() {
	// return this.allTypes;
	// }

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

	// /**
	// * Sets the all types.
	// *
	// * @param __allTypes the allTypes to set
	// */
	// public void setAllTypes(Set<Type_Style> __allTypes) {
	// this.allTypes = __allTypes;
	// }

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

}
