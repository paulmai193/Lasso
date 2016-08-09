/*
 * 
 */
package com.lasso.rest.model.datasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "art_design", name = "countries")
public final class Country {

	@Id
	@Column(length = 11, name = "id")
	private Integer	id;

	@Column(length = 2, name = "code")
	private String	code;

	@Column(length = 1, name = "status")
	private Byte	status;

	public Country() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer __id) {
		this.id = __id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String __code) {
		this.code = __code;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte __status) {
		this.status = __status;
	}

}
