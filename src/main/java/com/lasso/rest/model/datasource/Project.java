package com.lasso.rest.model.datasource;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the projects database table.
 * 
 */
@Entity
@Table(name="projects")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProjectPK id;
	private int categoryId;
	private Date created;
	private String image;
	private Date modified;
	private byte status;
	private String title;

	public Project() {
	}


	@EmbeddedId
	public ProjectPK getId() {
		return this.id;
	}

	public void setId(ProjectPK id) {
		this.id = id;
	}


	@Column(name="category_id", nullable=false)
	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}


	@Column(nullable=false, length=45)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}


	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}


	@Column(nullable=false, length=45)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}