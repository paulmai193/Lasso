package com.lasso.rest.model.datasource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the types_styles database table.
 *
 * @author Paul Mai
 */
@Embeddable
public class TypesStylePK implements Serializable {

	/** The Constant serialVersionUID. */
	// default serial version id, required for serializable classes.
	private static final long	serialVersionUID	= 1L;

	/** The id. */
	private int					id;

	/** The style id. */
	private int					styleId;

	/** The type id. */
	private int					typeId;

	/**
	 * Instantiates a new types style PK.
	 */
	public TypesStylePK() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TypesStylePK)) {
			return false;
		}
		TypesStylePK castOther = (TypesStylePK) other;
		return (this.id == castOther.id) && (this.typeId == castOther.typeId)
				&& (this.styleId == castOther.styleId);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Column(unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	/**
	 * Gets the style id.
	 *
	 * @return the style id
	 */
	@Column(name = "style_id", unique = true, nullable = false)
	public int getStyleId() {
		return this.styleId;
	}

	/**
	 * Gets the type id.
	 *
	 * @return the type id
	 */
	@Column(name = "type_id", unique = true, nullable = false)
	public int getTypeId() {
		return this.typeId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.typeId;
		hash = hash * prime + this.styleId;

		return hash;
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
	 * Sets the style id.
	 *
	 * @param styleId the new style id
	 */
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

	/**
	 * Sets the type id.
	 *
	 * @param typeId the new type id
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
}