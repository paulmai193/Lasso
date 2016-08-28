package com.lasso.rest.model.datasource;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

/**
 * The Class Portfolio.
 *
 * @author Paul Mai
 */
@Entity
@Table(catalog = "art_design", name = "portfolios")
@DynamicInsert(true)
@DynamicUpdate(true)
public final class Portfolio {

	/** The account. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	private Account		account;

	/** The amount. */
	@Column(name = "amount")
	private Double		amount;

	/** The category. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category	category;

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

	/** The info. */
	@Column(name = "info")
	@Type(type = "text")
	private String		info;

	/** The modified. */
	@Column(length = 19, name = "modified")
	private Date		modified;

	/** The sort. */
	@Column(length = 11, name = "sort")
	private Integer		sort;

	/** The status. */
	@Column(length = 1, name = "status")
	private Byte		status;

	/** The style. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "style_id")
	private Style		style;

	/** The title. */
	@Column(length = 45, name = "title")
	private String		title;

	/**
	 * Instantiates a new portfolio.
	 */
	public Portfolio() {
	}

	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	public Account getAccount() {
		return this.account;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public Double getAmount() {
		return this.amount;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public Category getCategory() {
		return this.category;
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
	 * Gets the info.
	 *
	 * @return the info
	 */
	public String getInfo() {
		return this.info;
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
	 * Gets the style.
	 *
	 * @return the style
	 */
	public Style getStyle() {
		return this.style;
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
	 * Sets the account.
	 *
	 * @param __account the account to set
	 */
	public void setAccount(Account __account) {
		this.account = __account;
	}

	/**
	 * Sets the amount.
	 *
	 * @param __amount the amount to set
	 */
	public void setAmount(Double __amount) {
		this.amount = __amount;
	}

	/**
	 * Sets the category.
	 *
	 * @param __category the category to set
	 */
	public void setCategory(Category __category) {
		this.category = __category;
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
	 * Sets the info.
	 *
	 * @param __info the info to set
	 */
	public void setInfo(String __info) {
		this.info = __info;
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
	 * Sets the style.
	 *
	 * @param __style the style to set
	 */
	public void setStyle(Style __style) {
		this.style = __style;
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
