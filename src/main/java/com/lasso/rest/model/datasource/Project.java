/*
 * 
 */
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
	private Integer				accountId;

	/** The category id. */
	@Column(name = "category_id")
	private Integer				categoryId;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	/** The deleted. */
	private Byte				deleted;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				id;

	/** The image. */
	private String				image;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	/** The portfolio id. */
	@Column(name = "portfolio_id")
	private Integer				portfolioId;

	/** The status. */
	private Byte				status;

	/** The style id. */
	@Column(name = "style_id")
	private Integer				styleId;

	/** The title. */
	private String				title;

	/** The type id. */
	@Column(name = "type_id")
	private Integer				typeId;

	/**
	 * Instantiates a new project.
	 */
	public Project() {
	}

	/**
	 * Instantiates a new project.
	 *
	 * @param __portfolio the portfolio
	 */
	public Project(Portfolio __portfolio) {
		this.accountId = __portfolio.getAccountId();
		this.categoryId = __portfolio.getCategoryId();
		this.created = this.modified = new Date();
		this.deleted = 0;
		String _portfolioImage = __portfolio.getImage();
		if (_portfolioImage.contains(",")) {
			this.image = _portfolioImage.substring(0, _portfolioImage.indexOf(","));
		}
		else {
			this.image = _portfolioImage;
		}
		this.portfolioId = __portfolio.getId();
		this.status = 1;
		this.styleId = __portfolio.getStyleId();
		this.title = __portfolio.getTitle();
		this.typeId = 0;
	}

	/**
	 * Gets the account id.
	 *
	 * @return the account id
	 */
	public Integer getAccountId() {
		return this.accountId;
	}

	/**
	 * Gets the category id.
	 *
	 * @return the category id
	 */
	public Integer getCategoryId() {
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
	public Byte getDeleted() {
		return this.deleted;
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
	 * Gets the portfolio id.
	 *
	 * @return the portfolio id
	 */
	public Integer getPortfolioId() {
		return this.portfolioId;
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
	 * Gets the style id.
	 *
	 * @return the style id
	 */
	public Integer getStyleId() {
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
	public Integer getTypeId() {
		return this.typeId;
	}

	/**
	 * Sets the account id.
	 *
	 * @param accountId
	 *        the new account id
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * Sets the category id.
	 *
	 * @param categoryId
	 *        the new category id
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Sets the created.
	 *
	 * @param created
	 *        the new created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param deleted
	 *        the new deleted
	 */
	public void setDeleted(Byte deleted) {
		this.deleted = deleted;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *        the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Sets the image.
	 *
	 * @param image
	 *        the new image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Sets the modified.
	 *
	 * @param modified
	 *        the new modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * Sets the portfolio id.
	 *
	 * @param portfolioId
	 *        the new portfolio id
	 */
	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *        the new status
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * Sets the style id.
	 *
	 * @param styleId
	 *        the new style id
	 */
	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *        the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the type id.
	 *
	 * @param typeId
	 *        the new type id
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

}