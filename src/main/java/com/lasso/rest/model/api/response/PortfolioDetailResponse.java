package com.lasso.rest.model.api.response;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;

@JsonInclude(value = Include.NON_NULL)
public class PortfolioDetailResponse extends BaseResponse {

	private Portfolio	portfolio;
	private Category	category;
	private Style		style;
	private Type		type;
	private String		prefixPortfolioUrl;

	public PortfolioDetailResponse(Portfolio __portfolio, Category __category, Style __style,
	        Type __type, String __prefixPortfolioUrl) {
		super();
		this.portfolio = __portfolio;
		this.category = __category;
		this.style = __style;
		this.type = __type;
		this.prefixPortfolioUrl = __prefixPortfolioUrl;
	}

	public PortfolioDetailResponse(boolean __error) {
		super(__error);
	}

	public PortfolioDetailResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	public PortfolioDetailResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * @return the portfolio
	 */
	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	/**
	 * @param __portfolio the portfolio to set
	 */
	public void setPortfolio(Portfolio __portfolio) {
		this.portfolio = __portfolio;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return this.category;
	}

	/**
	 * @param __category the category to set
	 */
	public void setCategory(Category __category) {
		this.category = __category;
	}

	/**
	 * @return the style
	 */
	public Style getStyle() {
		return this.style;
	}

	/**
	 * @param __style the style to set
	 */
	public void setStyle(Style __style) {
		this.style = __style;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return this.type;
	}

	/**
	 * @param __type the type to set
	 */
	public void setType(Type __type) {
		this.type = __type;
	}

	/**
	 * @return the prefixPortfolioUrl
	 */
	public String getPrefixPortfolioUrl() {
		return this.prefixPortfolioUrl;
	}

	/**
	 * @param __prefixPortfolioUrl the prefixPortfolioUrl to set
	 */
	public void setPrefixPortfolioUrl(String __prefixPortfolioUrl) {
		this.prefixPortfolioUrl = __prefixPortfolioUrl;
	}

}

class PortfolioDetailSerializer extends JsonSerializer<PortfolioDetailResponse> {

	@Override
	public void serialize(PortfolioDetailResponse __value, JsonGenerator __gen,
	        SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeObjectFieldStart("data");
		__gen.writeStringField("portfolio_title", __value.getPortfolio().getTitle());
		__gen.writeStringField("category_title", __value.getCategory().getTitle());
		__gen.writeStringField("type_title", __value.getType().getTitle());
		__gen.writeStringField("style_title", __value.getStyle().getTitle());
		__gen.writeNumberField("amount", __value.getPortfolio().getAmount());
		__gen.writeStringField("info", __value.getPortfolio().getInfo());

		__gen.writeArrayFieldStart("images");
		if (!__value.getPortfolio().getImage().isEmpty()) {
			for (String _portfolioImage : __value.getPortfolio().getImage().split(",")) {
				if (!_portfolioImage.trim().isEmpty()) {
					__gen.writeString(
					        __value.getPrefixPortfolioUrl() + "/small/" + _portfolioImage);
				}
			}
		}
		__gen.writeEndArray();

		__gen.writeEndObject();

		__gen.writeEndObject();
	}

}