package com.lasso.rest.model.datasource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the configurations database table.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "configurations")
@NamedQuery(name = "Configuration.findAll", query = "SELECT c FROM Configuration c")
public class Configuration implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The description. */
	private String				description;

	/** The editable. */
	private byte				editable;

	/** The id. */
	private int					id;

	/** The input type. */
	private String				inputType;

	/** The name. */
	private String				name;

	/** The params. */
	private String				params;

	/** The title. */
	private String				title;

	/** The value. */
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
	@Column(nullable = false, length = 250)
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the editable.
	 *
	 * @return the editable
	 */
	@Column(nullable = false)
	public byte getEditable() {
		return this.editable;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	/**
	 * Gets the input type.
	 *
	 * @return the input type
	 */
	@Column(name = "input_type", nullable = false, length = 250)
	public String getInputType() {
		return this.inputType;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Column(nullable = false, length = 64)
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the params.
	 *
	 * @return the params
	 */
	@Lob
	@Column(nullable = false)
	public String getParams() {
		return this.params;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	@Column(nullable = false, length = 250)
	public String getTitle() {
		return this.title;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Lob
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