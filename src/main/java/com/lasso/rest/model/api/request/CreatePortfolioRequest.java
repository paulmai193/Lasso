package com.lasso.rest.model.api.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

@JsonInclude(value = Include.NON_NULL)
public class CreatePortfolioRequest extends BaseRequest {

	@JsonProperty("category_id")
	private Integer			idCategory;

	@JsonProperty("portfolio_title")
	private String			title;

	@JsonProperty("style_id")
	private Integer			idStyle;

	@JsonProperty("type_id")
	private List<Integer>	idTypes;

	@JsonProperty("image")
	private List<String>	images;

	@JsonProperty("portfolio_info")
	private String			info;

	@JsonProperty("amount")
	private Double			amount	= 0D;

	public CreatePortfolioRequest() {
	}

	@Override
	public void validate() throws ObjectParamException {
		if (idCategory == null) {
			throw new ObjectParamException("Invalid category");
		}
		if (idStyle == null) {
			throw new ObjectParamException("Invalid style");
		}
		if (title == null || title.isEmpty()) {
			throw new ObjectParamException("Invalid title");
		}
		if (idTypes == null || idTypes.isEmpty()) {
			throw new ObjectParamException("Invalid type");
		}
		if (images == null) {
			throw new ObjectParamException("Invalid image");
		}
		if (info == null) {
			throw new ObjectParamException("Invalid portfolio info");
		}
	}

	/**
	 * @return the idCategory
	 */
	public Integer getIdCategory() {
		return this.idCategory;
	}

	/**
	 * @param __idCategory the idCategory to set
	 */
	public void setIdCategory(Integer __idCategory) {
		this.idCategory = __idCategory;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param __title the title to set
	 */
	public void setTitle(String __title) {
		this.title = __title;
	}

	/**
	 * @return the idStyle
	 */
	public Integer getIdStyle() {
		return this.idStyle;
	}

	/**
	 * @param __idStyle the idStyle to set
	 */
	public void setIdStyle(Integer __idStyle) {
		this.idStyle = __idStyle;
	}

	/**
	 * @return the idTypes
	 */
	public List<Integer> getIdTypes() {
		return this.idTypes;
	}

	/**
	 * @param __idTypes the idTypes to set
	 */
	public void setIdTypes(List<Integer> __idTypes) {
		this.idTypes = __idTypes;
	}

	/**
	 * @return the images
	 */
	public List<String> getImages() {
		return this.images;
	}

	/**
	 * @param __images the images to set
	 */
	public void setImages(List<String> __images) {
		this.images = __images;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return this.info;
	}

	/**
	 * @param __info the info to set
	 */
	public void setInfo(String __info) {
		this.info = __info;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return this.amount;
	}

	/**
	 * @param __amount the amount to set
	 */
	public void setAmount(Double __amount) {
		this.amount = __amount;
	}

}
