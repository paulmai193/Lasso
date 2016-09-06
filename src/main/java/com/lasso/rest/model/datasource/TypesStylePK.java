package com.lasso.rest.model.datasource;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the types_styles database table.
 * 
 */
@Embeddable
public class TypesStylePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int id;
	private int typeId;
	private int styleId;

	public TypesStylePK() {
	}

	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="type_id", unique=true, nullable=false)
	public int getTypeId() {
		return this.typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Column(name="style_id", unique=true, nullable=false)
	public int getStyleId() {
		return this.styleId;
	}
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TypesStylePK)) {
			return false;
		}
		TypesStylePK castOther = (TypesStylePK)other;
		return 
			(this.id == castOther.id)
			&& (this.typeId == castOther.typeId)
			&& (this.styleId == castOther.styleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.typeId;
		hash = hash * prime + this.styleId;
		
		return hash;
	}
}