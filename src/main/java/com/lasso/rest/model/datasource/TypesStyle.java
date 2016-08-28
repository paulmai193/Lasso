package com.lasso.rest.model.datasource;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the types_styles database table.
 * 
 */
@Entity
@Table(name="types_styles")
@NamedQuery(name="TypesStyle.findAll", query="SELECT t FROM TypesStyle t")
public class TypesStyle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	@Column(name="style_id")
	private int styleId;

	@Column(name="type_id")
	private int typeId;

	public TypesStyle() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public int getStyleId() {
		return this.styleId;
	}

	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

	public int getTypeId() {
		return this.typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

}