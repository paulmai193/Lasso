package com.lasso.rest.model.api.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

/**
 * The Class CreatePortfolioRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class CreatePortfolioRequest extends BaseRequest {

	/** The amount. */
	@JsonProperty("amount")
	private Double			amount;

	/** The id category. */
	@JsonProperty("category_id")
	private Integer			idCategory;

	/** The id style. */
	@JsonProperty("style_id")
	private Integer			idStyle;

	/** The id types. */
	@JsonProperty("type_id")
	private List<Integer>	idTypes;

	/** The images. */
	@JsonProperty("image")
	private List<String>	images;

	/** The info. */
	@JsonProperty("portfolio_info")
	private String			info;

	/** The title. */
	@JsonProperty("portfolio_title")
	private String			title;

	/**
	 * Instantiates a new creates the portfolio request.
	 */
	public CreatePortfolioRequest() {
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public Double getAmount() {
		return this.amount == null ? 0D : this.amount;
	}

	/**
	 * Gets the id category.
	 *
	 * @return the idCategory
	 */
	public Integer getIdCategory() {
		return this.idCategory;
	}

	/**
	 * Gets the id style.
	 *
	 * @return the idStyle
	 */
	public Integer getIdStyle() {
		return this.idStyle;
	}

	/**
	 * Gets the id types.
	 *
	 * @return the idTypes
	 */
	public List<Integer> getIdTypes() {
		return this.idTypes;
	}

	/**
	 * Gets the images.
	 *
	 * @return the images
	 */
	public List<String> getImages() {
		return this.images;
	}

	/**
	 * Gets the info.
	 *
	 * @return the info
	 */
	public String getInfo() {
		return this.info;
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
	 * Sets the amount.
	 *
	 * @param __amount the amount to set
	 */
	public void setAmount(Double __amount) {
		this.amount = __amount;
	}

	/**
	 * Sets the id category.
	 *
	 * @param __idCategory the idCategory to set
	 */
	public void setIdCategory(Integer __idCategory) {
		this.idCategory = __idCategory;
	}

	/**
	 * Sets the id style.
	 *
	 * @param __idStyle the idStyle to set
	 */
	public void setIdStyle(Integer __idStyle) {
		this.idStyle = __idStyle;
	}

	/**
	 * Sets the id types.
	 *
	 * @param __idTypes the idTypes to set
	 */
	public void setIdTypes(List<Integer> __idTypes) {
		this.idTypes = __idTypes;
	}

	/**
	 * Sets the images.
	 *
	 * @param __images the images to set
	 */
	public void setImages(List<String> __images) {
		this.images = __images;
	}

	/**
	 * Sets the info.
	 *
	 * @param __info the info to set
	 */
	public void setInfo(String __info) {
		this.info = __info;
	}

	/**
	 * Sets the title.
	 *
	 * @param __title the title to set
	 */
	public void setTitle(String __title) {
		this.title = __title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.idCategory == null) {
			throw new ObjectParamException("Invalid category");
		}
		if (this.idStyle == null) {
			throw new ObjectParamException("Invalid style");
		}
		if (this.title == null || this.title.isEmpty()) {
			throw new ObjectParamException("Invalid title");
		}
		if (this.idTypes == null || this.idTypes.isEmpty()) {
			throw new ObjectParamException("Invalid type");
		}
		if (this.images == null || this.images.isEmpty()) {
			throw new ObjectParamException("Invalid image");
		}
		if (this.info == null) {
			throw new ObjectParamException("Invalid portfolio info");
		}
	}

}
