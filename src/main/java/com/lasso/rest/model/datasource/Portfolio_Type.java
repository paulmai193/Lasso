package com.lasso.rest.model.datasource;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * The Class Portfolio_Type.
 *
 * @author Paul Mai
 */
@Entity
@Table(catalog = "art_design", name = "portfolios_types")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Portfolio_Type {

	/** The created. */
	@Column(length = 19, name = "created")
	private Date		created;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(length = 11, name = "id")
	private Integer		id;

	/** The modified. */
	@Column(length = 19, name = "modified")
	private Date		modified;

	/** The portfolio. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "portfolio_id")
	private Portfolio	portfolio;

	/** The type. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	private Type		type;

	/**
	 * Instantiates a new portfolio type.
	 */
	public Portfolio_Type() {
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
	 * Gets the portfolio.
	 *
	 * @return the portfolio
	 */
	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Type getType() {
		return this.type;
	}

	/**
	 * Sets the created.
	 *
	 * @param __created the created to set
	 */
	public void setCreated(Date __created) {
		this.created = __created;
	}

	/**
	 * Sets the id.
	 *
	 * @param __id the id to set
	 */
	public void setId(Integer __id) {
		this.id = __id;
	}

	/**
	 * Sets the modified.
	 *
	 * @param __modified the modified to set
	 */
	public void setModified(Date __modified) {
		this.modified = __modified;
	}

	/**
	 * Sets the portfolio.
	 *
	 * @param __portfolio the portfolio to set
	 */
	public void setPortfolio(Portfolio __portfolio) {
		this.portfolio = __portfolio;
	}

	/**
	 * Sets the type.
	 *
	 * @param __type the type to set
	 */
	public void setType(Type __type) {
		this.type = __type;
	}

}
