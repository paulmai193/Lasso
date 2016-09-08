package com.lasso.rest.model.datasource;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the projects database table.
 * 
 */
@Embeddable
public class ProjectPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int id;
	private int portfolioId;
	private int styleId;
	private int typeId;
	private int accountId;

	public ProjectPK() {
	}

	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="portfolio_id", unique=true, nullable=false)
	public int getPortfolioId() {
		return this.portfolioId;
	}
	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	@Column(name="style_id", unique=true, nullable=false)
	public int getStyleId() {
		return this.styleId;
	}
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

	@Column(name="type_id", unique=true, nullable=false)
	public int getTypeId() {
		return this.typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Column(name="account_id", unique=true, nullable=false)
	public int getAccountId() {
		return this.accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProjectPK)) {
			return false;
		}
		ProjectPK castOther = (ProjectPK)other;
		return 
			(this.id == castOther.id)
			&& (this.portfolioId == castOther.portfolioId)
			&& (this.styleId == castOther.styleId)
			&& (this.typeId == castOther.typeId)
			&& (this.accountId == castOther.accountId);
	}

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
}