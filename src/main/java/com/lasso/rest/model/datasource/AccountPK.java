package com.lasso.rest.model.datasource;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the accounts database table.
 * 
 */
@Embeddable
public class AccountPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int id;
	private int countrieId;

	public AccountPK() {
	}

	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="countrie_id", unique=true, nullable=false)
	public int getCountrieId() {
		return this.countrieId;
	}
	public void setCountrieId(int countrieId) {
		this.countrieId = countrieId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AccountPK)) {
			return false;
		}
		AccountPK castOther = (AccountPK)other;
		return 
			(this.id == castOther.id)
			&& (this.countrieId == castOther.countrieId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.countrieId;
		
		return hash;
	}
}