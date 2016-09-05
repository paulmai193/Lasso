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

	/** The id. */
	private int					id;

	/** The account id. */
	private int					accountId;

	/** The categorie id. */
	private int					categorieId;

	/** The style id. */
	private int					styleId;

	/**
	 * Instantiates a new portfolio PK.
	 */
	public PortfolioPK() {
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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the account id.
	 *
	 * @return the account id
	 */
	@Column(name = "account_id", unique = true, nullable = false)
	public int getAccountId() {
		return this.accountId;
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
	 * Gets the categorie id.
	 *
	 * @return the categorie id
	 */
	@Column(name = "categorie_id", unique = true, nullable = false)
	public int getCategorieId() {
		return this.categorieId;
	}

	/**
	 * Sets the categorie id.
	 *
	 * @param categorieId the new categorie id
	 */
	public void setCategorieId(int categorieId) {
		this.categorieId = categorieId;
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
	 * Sets the style id.
	 *
	 * @param styleId the new style id
	 */
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PortfolioPK)) {
			return false;
		}
		PortfolioPK castOther = (PortfolioPK) other;
		return (this.id == castOther.id) && (this.accountId == castOther.accountId)
		        && (this.categorieId == castOther.categorieId)
		        && (this.styleId == castOther.styleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.accountId;
		hash = hash * prime + this.categorieId;
		hash = hash * prime + this.styleId;

		return hash;
	}
}