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

/**
 * The persistent class for the styles database table.
 * 
 */
@Entity
@Table(catalog = "art_design", name = "styles")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Style implements Serializable {

	private static final long	serialVersionUID	= 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int					id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	private String				image;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	private int					sort;

	private byte				status;

	private String				title;

	public Style() {
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

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}