/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class PortfolioType.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "portfolios_types")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PortfolioType implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				id;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	/** The portfolio id. */
	@Column(name = "portfolio_id")
	private Integer				portfolioId;

	/** The type id. */
	@Column(name = "type_id")
	private Integer				typeId;

	/**
	 * Instantiates a new portfolio type.
	 */
	public PortfolioType() {
	}

	/**
	 * Instantiates a new portfolio type.
	 *
	 * @param __created
	 *        the created
	 * @param __modified
	 *        the modified
	 * @param __portfolioId
	 *        the portfolio id
	 * @param __typeId
	 *        the type id
	 */
	public PortfolioType(Date __created, Date __modified, Integer __portfolioId, Integer __typeId) {
		super();
		this.created = __created;
		this.modified = __modified;
		this.portfolioId = __portfolioId;
		this.typeId = __typeId;
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
	 * Gets the modified.
	 *
	 * @return the modified
	 */
	public Date getModified() {
		return this.modified;
	}

	/**
	 * Gets the portfolio id.
	 *
	 * @return the portfolio id
	 */
	public Integer getPortfolioId() {
		return this.portfolioId;
	}

	/**
	 * Gets the type id.
	 *
	 * @return the type id
	 */
	public Integer getTypeId() {
		return this.typeId;
	}

	/**
	 * Sets the created.
	 *
	 * @param created
	 *        the new created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *        the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Sets the modified.
	 *
	 * @param modified
	 *        the new modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * Sets the portfolio id.
	 *
	 * @param portfolioId
	 *        the new portfolio id
	 */
	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	/**
	 * Sets the type id.
	 *
	 * @param typeId
	 *        the new type id
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

}