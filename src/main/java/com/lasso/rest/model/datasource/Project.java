package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
 * The persistent class for the projects database table.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "projects")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Project implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The account id. */
	@Column(name = "account_id")
	private int					accountId;

	/** The category id. */
	@Column(name = "category_id")
	private int					categoryId;

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

	/** The portfolio id. */
	@Column(name = "portfolio_id")
	private int					portfolioId;

	/** The status. */
	private byte				status;

	/** The style id. */
	@Column(name = "style_id")
	private int					styleId;

	/** The title. */
	private String				title;

	/** The type id. */
	@Column(name = "type_id")
	private int					typeId;

	/**
	 * Instantiates a new project.
	 */
	public Project() {
	}

	/**
	 * Gets the account id.
	 *
	 * @return the account id
	 */
	public int getAccountId() {
		return this.accountId;
	}

	/**
	 * Gets the category id.
	 *
	 * @return the category id
	 */
	public int getCategoryId() {
		return this.categoryId;
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
	 * Gets the portfolio id.
	 *
	 * @return the portfolio id
	 */
	public int getPortfolioId() {
		return this.portfolioId;
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
	 * Gets the style id.
	 *
	 * @return the style id
	 */
	public int getStyleId() {
		return this.styleId;
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
	 * Gets the type id.
	 *
	 * @return the type id
	 */
	public int getTypeId() {
		return this.typeId;
	}

	/**
	 * Sets the account id.
	 *
	 * @param accountId the new account id
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	/**
	 * Sets the category id.
	 *
	 * @param categoryId the new category id
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
	 * Sets the portfolio id.
	 *
	 * @param portfolioId the new portfolio id
	 */
	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
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
	 * Sets the style id.
	 *
	 * @param styleId the new style id
	 */
	public void setStyleId(int styleId) {
		this.styleId = styleId;
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
	 * Sets the type id.
	 *
	 * @param typeId the new type id
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

}