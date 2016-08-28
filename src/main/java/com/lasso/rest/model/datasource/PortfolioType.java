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
 * The Class PortfolioType.
 *
 * @author Paul Mai
 */
@Entity
@Table(catalog = "art_design", name = "portfolios_types")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PortfolioType implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int					id;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	/** The portfolio id. */
	@Column(name = "portfolio_id")
	private int					portfolioId;

	/** The type id. */
	@Column(name = "type_id")
	private int					typeId;

	/**
	 * Instantiates a new portfolio type.
	 */
	public PortfolioType() {
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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
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
	 * Sets the created.
	 *
	 * @param created the new created
	 */
	public void setCreated(Date created) {
		this.created = created;
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
	 * Sets the modified.
	 *
	 * @param modified the new modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * Gets the portfolio id.
	 *
	 * @return the portfolio id
	 */
	public int getPortfolioId() {
		return this.portfolioId;
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
	 * Gets the type id.
	 *
	 * @return the type id
	 */
	public int getTypeId() {
		return this.typeId;
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