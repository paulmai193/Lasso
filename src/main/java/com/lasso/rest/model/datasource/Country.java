/*
 * 
 */
package com.lasso.rest.model.datasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class Country.
 *
 * @author Paul Mai
 */
@Entity
@Table(catalog = "art_design", name = "countries")
public final class Country {

	/** The code. */
	@Column(length = 2, name = "code")
	private String	code;

	/** The id. */
	@Id
	@Column(length = 11, name = "id")
	private Integer	id;

	/** The status. */
	@Column(length = 1, name = "status")
	private Byte	status;

	/**
	 * Instantiates a new country.
	 */
	public Country() {
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return this.code;
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Byte getStatus() {
		return this.status;
	}

	/**
	 * Sets the code.
	 *
	 * @param __code the new code
	 */
	public void setCode(String __code) {
		this.code = __code;
	}

	/**
	 * Sets the id.
	 *
	 * @param __id the new id
	 */
	public void setId(Integer __id) {
		this.id = __id;
	}

	/**
	 * Sets the status.
	 *
	 * @param __status the new status
	 */
	public void setStatus(Byte __status) {
		this.status = __status;
	}

}
