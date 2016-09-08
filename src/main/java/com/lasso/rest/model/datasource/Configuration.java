package com.lasso.rest.model.datasource;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the configurations database table.
 * 
 */
@Entity
@Table(name="configurations")
@NamedQuery(name="Configuration.findAll", query="SELECT c FROM Configuration c")
public class Configuration implements Serializable {
	private static final long serialVersionUID = 1L;
	private String description;
	private byte editable;
	private int id;
	private String inputType;
	private String name;
	private String params;
	private String title;
	private String value;
	private int weight;

	public Configuration() {
	}


	@Column(nullable=false, length=250)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(nullable=false)
	public byte getEditable() {
		return this.editable;
	}

	public void setEditable(byte editable) {
		this.editable = editable;
	}


	@Column(nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name="input_type", nullable=false, length=250)
	public String getInputType() {
		return this.inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}


	@Column(nullable=false, length=64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Lob
	@Column(nullable=false)
	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}


	@Column(nullable=false, length=250)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Lob
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}