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
 * The Class Type_Style.
 *
 * @author Paul Mai
 */
@Entity
@Table(catalog = "art_design", name = "types_styles")
@DynamicInsert(true)
@DynamicUpdate(true)
public final class Type_Style {

	/** The created. */
	@Column(length = 19, name = "created")
	private Date	created;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(length = 11, name = "id")
	private Integer	id;

	/** The modified. */
	@Column(length = 19, name = "modified")
	private Date	modified;

	/** The style. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "style_id")
	private Style	style;

	/** The type. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	private Type	type;

	/**
	 * Instantiates a new type style.
	 */
	public Type_Style() {
		// TODO Auto-generated constructor stub
	}

}
