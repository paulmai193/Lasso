package com.lasso.rest.model.datasource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the accounts database table.
 *
 * @author Paul Mai
 */
@Embeddable
public class AccountPK implements Serializable {

	/** The Constant serialVersionUID. */
	// default serial version id, required for serializable classes.
	private static final long	serialVersionUID	= 1L;

	/** The country id. */
	private int					countryId;

	/** The id. */
	private int					id;

	/**
	 * Instantiates a new account PK.
	 */
	public AccountPK() {
	}

	/**
	 * Instantiates a new account PK.
	 *
	 * @param __countryId the country id
	 */
	public AccountPK(int __countryId) {
		super();
		this.countryId = __countryId;
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
		if (!(other instanceof AccountPK)) {
			return false;
		}
		AccountPK castOther = (AccountPK) other;
		return (this.id == castOther.id) && (this.countryId == castOther.countryId);
	}

	/**
	 * Gets the country id.
	 *
	 * @return the country id
	 */
	@Column(name = "country_id", unique = true, nullable = false)
	public int getCountryId() {
		return this.countryId;
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
		hash = hash * prime + this.countryId;

		return hash;
	}

	/**
	 * Sets the country id.
	 *
	 * @param countryId the new country id
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
}