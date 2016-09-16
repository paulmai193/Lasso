package com.lasso.rest.model.datasource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * The persistent class for the configurations database table.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "configurations")
public class Configuration implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The description. */
	private String				description;

	/** The editable. */
	private byte				editable;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int					id;

	/** The input type. */
	@Column(name = "input_type")
	private String				inputType;

	/** The name. */
	private String				name;

	/** The params. */
	@Lob
	private String				params;

	/** The title. */
	private String				title;

	/** The value. */
	@Lob
	private String				value;

	/** The weight. */
	private int					weight;

	/**
	 * Instantiates a new configuration.
	 */
	public Configuration() {
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the editable.
	 *
	 * @return the editable
	 */
	public byte getEditable() {
		return this.editable;
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
	 * Gets the input type.
	 *
	 * @return the input type
	 */
	public String getInputType() {
		return this.inputType;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the params.
	 *
	 * @return the params
	 */
	public String getParams() {
		return this.params;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the editable.
	 *
	 * @param editable the new editable
	 */
	public void setEditable(byte editable) {
		this.editable = editable;
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
	 * Sets the input type.
	 *
	 * @param inputType the new input type
	 */
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the params.
	 *
	 * @param params the new params
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Sets the weight.
	 *
	 * @param weight the new weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

}