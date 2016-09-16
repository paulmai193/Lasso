package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.lasso.rest.model.api.request.EditPortfolioRequest;

/**
 * The Class Portfolio.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "portfolios")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Portfolio implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The account id. */
	@Column(name = "account_id")
	private int					accountId;

	/** The amount. */
	private double				amount;

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
	@Lob
	private String				image;

	/** The info. */
	@Lob
	private String				info;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	/** The status. */
	private byte				status;

	/** The style id. */
	@Column(name = "style_id")
	private int					styleId;

	/** The title. */
	private String				title;

	/**
	 * Instantiates a new portfolio.
	 */
	public Portfolio() {
	}

	/**
	 * Instantiates a new portfolio.
	 *
	 * @param __amount the amount
	 * @param __created the created
	 * @param __accountId the account id
	 * @param __categoryId the category id
	 * @param __styleId the style id
	 * @param __image the image
	 * @param __info the info
	 * @param __modified the modified
	 * @param __status the status
	 * @param __title the title
	 */
	public Portfolio(double __amount, Date __created, int __accountId, int __categoryId,
	        int __styleId, String __image, String __info, Date __modified, byte __status,
	        String __title) {
		super();
		this.amount = __amount;
		this.created = __created;
		this.accountId = __accountId;
		this.categoryId = __categoryId;
		this.styleId = __styleId;
		this.image = __image;
		this.info = __info;
		this.modified = __modified;
		this.status = __status;
		this.title = __title;
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
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return this.amount;
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
	 * Sets the account id.
	 *
	 * @param accountId the new account id
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
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
	 * Sets the info.
	 *
	 * @param info the new info
	 */
	public void setInfo(String info) {
		this.info = info;
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
	 * Update.
	 *
	 * @param __editPortfolioRequest the edit portfolio request
	 */
	public void update(EditPortfolioRequest __editPortfolioRequest) {
		this.setAmount(__editPortfolioRequest.getAmount());
		this.setCategoryId(__editPortfolioRequest.getIdCategory());
		String _image = Arrays.toString(__editPortfolioRequest.getImages().toArray());
		_image = _image.substring(1, _image.length() - 1);
		this.setImage(_image);
		this.setInfo(__editPortfolioRequest.getInfo());
		this.setModified(new Date());
		this.setStyleId(__editPortfolioRequest.getIdStyle());
		this.setTitle(__editPortfolioRequest.getTitle());
	}

}
