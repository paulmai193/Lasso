package com.lasso.rest.model.datasource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the portfolios database table.
 *
 * @author Paul Mai
 */
@Embeddable
public class PortfolioPK implements Serializable {

	/** The Constant serialVersionUID. */
	// default serial version id, required for serializable classes.
	private static final long	serialVersionUID	= 1L;

	/** The account id. */
	private int					accountId;

	/** The category id. */
	private int					categoryId;

	/** The id. */
	private int					id;

	/** The style id. */
	private int					styleId;

	/**
	 * Instantiates a new portfolio PK.
	 */
	public PortfolioPK() {
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
		if (!(other instanceof PortfolioPK)) {
			return false;
		}
		PortfolioPK castOther = (PortfolioPK) other;
		return (this.id == castOther.id) && (this.accountId == castOther.accountId)
				&& (this.categoryId == castOther.categoryId) && (this.styleId == castOther.styleId);
	}

	/**
	 * Gets the account id.
	 *
	 * @return the account id
	 */
	@Column(name = "account_id", nullable = false)
	public int getAccountId() {
		return this.accountId;
	}

	/**
	 * Gets the category id.
	 *
	 * @return the category id
	 */
	@Column(name = "category_id", nullable = false)
	public int getCategoryId() {
		return this.categoryId;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Column(nullable = false, unique = true)
	public int getId() {
		return this.id;
	}

	/**
	 * Gets the style id.
	 *
	 * @return the style id
	 */
	@Column(name = "style_id", nullable = false)
	public int getStyleId() {
		return this.styleId;
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
		hash = hash * prime + this.accountId;
		hash = hash * prime + this.categoryId;
		hash = hash * prime + this.styleId;

		return hash;
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
}