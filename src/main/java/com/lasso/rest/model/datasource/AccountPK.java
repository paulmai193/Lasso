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

	/** The id. */
	private int					id;

	/** The countrie id. */
	private int					countrieId;

	/**
	 * Instantiates a new account PK.
	 */
	public AccountPK() {
	}

	/**
	 * Instantiates a new account PK.
	 *
	 * @param __countrieId the countrie id
	 */
	public AccountPK(int __countrieId) {
		super();
		this.countrieId = __countrieId;
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
	 * Gets the countrie id.
	 *
	 * @return the countrie id
	 */
	@Column(name = "countrie_id", unique = true, nullable = false)
	public int getCountrieId() {
		return this.countrieId;
	}

	/**
	 * Sets the countrie id.
	 *
	 * @param countrieId the new countrie id
	 */
	public void setCountrieId(int countrieId) {
		this.countrieId = countrieId;
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
		if (!(other instanceof AccountPK)) {
			return false;
		}
		AccountPK castOther = (AccountPK) other;
		return (this.id == castOther.id) && (this.countrieId == castOther.countrieId);
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
		hash = hash * prime + this.countrieId;

		return hash;
	}
}