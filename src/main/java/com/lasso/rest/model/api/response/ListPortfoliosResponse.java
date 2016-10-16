/*
 * 
 */
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

class ListPortfolioSerializer extends JsonSerializer<ListPortfoliosResponse> {

	@SuppressWarnings("unchecked")
	@Override
	public void serialize(ListPortfoliosResponse __value, JsonGenerator __gen, SerializerProvider __serializers)
			throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeArrayFieldStart("data");
		for (Object[] _data : __value.getDatas()) {
			__gen.writeStartObject();
			__gen.writeNumberField("portfolio_id", ((Portfolio) _data[0]).getId());
			__gen.writeStringField("portfolio_title", ((Portfolio) _data[0]).getTitle());
			__gen.writeStringField("category_title", ((Category) _data[1]).getTitle());
			__gen.writeStringField("style_title", ((Style) _data[2]).getTitle());
			__gen.writeArrayFieldStart("types");
			for (Type _type : (List<Type>) _data[3]) {
				__gen.writeStartObject();
				__gen.writeNumberField("type_id", _type.getId());
				__gen.writeStringField("type_title", _type.getTitle());
				__gen.writeEndObject();
			}
			__gen.writeEndArray();

			__gen.writeArrayFieldStart("images");
			if (!((Portfolio) _data[0]).getImage().isEmpty()) {
				for (String _portfolioImage : ((Portfolio) _data[0]).getImage().split(",")) {
					if (!_portfolioImage.trim().isEmpty()) {
						__gen.writeStartObject();
						__gen.writeStringField("original",
								__value.getPrefixUrl() + "/Original/" + _portfolioImage.trim());
						__gen.writeStringField("small", __value.getPrefixUrl() + "/Small/" + _portfolioImage.trim());
						__gen.writeStringField("icon", __value.getPrefixUrl() + "/Icon/" + _portfolioImage.trim());
						__gen.writeStringField("retina", __value.getPrefixUrl() + "/Retina/" + _portfolioImage.trim());
						__gen.writeEndObject();
					} else {
						__gen.writeStartObject();
						__gen.writeStringField("original", "");
						__gen.writeStringField("small", "");
						__gen.writeStringField("icon", "");
						__gen.writeStringField("retina", "");
						__gen.writeEndObject();
					}
				}
			}
			__gen.writeEndArray();

			__gen.writeEndObject();
		}
		__gen.writeEndArray();

		__gen.writeEndObject();
	}

}

// TODO: Auto-generated Javadoc
/**
 * The Class ListPortfoliosResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = ListPortfolioSerializer.class)
public class ListPortfoliosResponse extends BaseResponse {

	/** The datas. {portfolio, category, style} */
	private List<Object[]> datas;

	/** The prefix url. */
	private String prefixUrl;

	/**
	 * Instantiates a new list portfolios response.
	 *
	 * @param __error
	 *            the error
	 */
	public ListPortfoliosResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list portfolios response.
	 *
	 * @param __error
	 *            the error
	 * @param __message
	 *            the message
	 */
	public ListPortfoliosResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list portfolios response.
	 *
	 * @param __error
	 *            the error
	 * @param __message
	 *            the message
	 * @param __detail
	 *            the detail
	 */
	public ListPortfoliosResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list portfolios response.
	 *
	 * @param __datas
	 *            the datas
	 * @param __prefixUrl
	 *            the prefix url
	 */
	public ListPortfoliosResponse(List<Object[]> __datas, String __prefixUrl) {
		super();
		this.datas = __datas;
		this.prefixUrl = __prefixUrl;
	}

	/**
	 * Gets the datas.
	 *
	 * @return the datas {portfolio, category, style}
	 */
	public List<Object[]> getDatas() {
		return this.datas;
	}

	/**
	 * Gets the prefix url.
	 *
	 * @return the prefixUrl
	 */
	public String getPrefixUrl() {
		return this.prefixUrl;
	}

	/**
	 * Sets the datas.
	 *
	 * @param __datas
	 *            the datas to set {portfolio, category, style}
	 */
	public void setDatas(List<Object[]> __datas) {
		this.datas = __datas;
	}

	/**
	 * Sets the prefix url.
	 *
	 * @param __prefixUrl
	 *            the prefixUrl to set
	 */
	public void setPrefixUrl(String __prefixUrl) {
		this.prefixUrl = __prefixUrl;
	}

}