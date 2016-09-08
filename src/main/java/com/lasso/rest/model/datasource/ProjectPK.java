package com.lasso.rest.model.datasource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the projects database table.
 *
 * @author Paul Mai
 */
@Embeddable
public class ProjectPK implements Serializable {

	/** The Constant serialVersionUID. */
	// default serial version id, required for serializable classes.
	private static final long	serialVersionUID	= 1L;

	/** The account id. */
	private int					accountId;

	/** The id. */
	private int					id;

	/** The portfolio id. */
	private int					portfolioId;

	/** The style id. */
	private int					styleId;

	/** The type id. */
	private int					typeId;

	/**
	 * Instantiates a new project PK.
	 */
	public ProjectPK() {
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
		if (!(other instanceof ProjectPK)) {
			return false;
		}
		ProjectPK castOther = (ProjectPK) other;
		return (this.id == castOther.id) && (this.portfolioId == castOther.portfolioId)
				&& (this.styleId == castOther.styleId) && (this.typeId == castOther.typeId)
				&& (this.accountId == castOther.accountId);
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Column(unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	/**
	 * Gets the portfolio id.
	 *
	 * @return the portfolio id
	 */
	@Column(name = "portfolio_id", unique = true, nullable = false)
	public int getPortfolioId() {
		return this.portfolioId;
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
	 * Gets the type id.
	 *
	 * @return the type id
	 */
	@Column(name = "type_id", unique = true, nullable = false)
	public int getTypeId() {
		return this.typeId;
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
		hash = hash * prime + this.portfolioId;
		hash = hash * prime + this.styleId;
		hash = hash * prime + this.typeId;
		hash = hash * prime + this.accountId;

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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
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
	 * Sets the style id.
	 *
	 * @param styleId the new style id
	 */
	public void setStyleId(int styleId) {
		this.styleId = styleId;
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