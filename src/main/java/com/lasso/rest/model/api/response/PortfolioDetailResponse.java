package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;

/**
 * The Class PortfolioDetailResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = PortfolioDetailSerializer.class)
public class PortfolioDetailResponse extends BaseResponse {

	/** The category. */
	private Category	category;

	/** The portfolio. */
	private Portfolio	portfolio;

	/** The prefix portfolio url. */
	private String		prefixPortfolioUrl;

	/** The style. */
	private Style		style;

	/** The types. */
	private List<Type>	types;

	/**
	 * Instantiates a new portfolio detail response.
	 *
	 * @param __error the error
	 */
	public PortfolioDetailResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new portfolio detail response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public PortfolioDetailResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new portfolio detail response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public PortfolioDetailResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new portfolio detail response.
	 *
	 * @param __category the category
	 * @param __portfolio the portfolio
	 * @param __prefixPortfolioUrl the prefix portfolio url
	 * @param __style the style
	 * @param __types the types
	 */
	public PortfolioDetailResponse(Category __category, Portfolio __portfolio,
			String __prefixPortfolioUrl, Style __style, List<Type> __types) {
		super();
		this.category = __category;
		this.portfolio = __portfolio;
		this.prefixPortfolioUrl = __prefixPortfolioUrl;
		this.style = __style;
		this.types = __types;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public Category getCategory() {
		return this.category;
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
	 * Gets the prefix portfolio url.
	 *
	 * @return the prefixPortfolioUrl
	 */
	public String getPrefixPortfolioUrl() {
		return this.prefixPortfolioUrl;
	}

	/**
	 * Gets the style.
	 *
	 * @return the style
	 */
	public Style getStyle() {
		return this.style;
	}

	/**
	 * Gets the types.
	 *
	 * @return the types
	 */
	public List<Type> getTypes() {
		return this.types;
	}

	/**
	 * Sets the category.
	 *
	 * @param __category the category to set
	 */
	public void setCategory(Category __category) {
		this.category = __category;
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
	 * Sets the prefix portfolio url.
	 *
	 * @param __prefixPortfolioUrl the prefixPortfolioUrl to set
	 */
	public void setPrefixPortfolioUrl(String __prefixPortfolioUrl) {
		this.prefixPortfolioUrl = __prefixPortfolioUrl;
	}

	/**
	 * Sets the style.
	 *
	 * @param __style the style to set
	 */
	public void setStyle(Style __style) {
		this.style = __style;
	}

	/**
	 * Sets the types.
	 *
	 * @param __types the new types
	 */
	public void setTypes(List<Type> __types) {
		this.types = __types;
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
		__gen.writeArrayFieldStart("type_title");
		for (Type _type : __value.getTypes()) {
			__gen.writeStartObject();
			__gen.writeNumberField("type_id", _type.getId().getId());
			__gen.writeStringField("type_title", _type.getTitle());
			__gen.writeEndObject();
		}
		__gen.writeEndArray();
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