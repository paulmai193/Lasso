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
import com.lasso.rest.model.datasource.Style;

/**
 * The Class ListSubCategoriesResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = ListSubCategoriesSerializer.class)
public class ListSubCategoriesResponse extends BaseResponse {

	/** The next page. */
	private int			nextPage;

	/** The prefix url. */
	private String		prefixUrl;

	/** The styles. */
	private List<Style>	styles;

	/**
	 * Instantiates a new list sub catories response.
	 *
	 * @param __error the error
	 */
	public ListSubCategoriesResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list sub catories response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public ListSubCategoriesResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list sub catories response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public ListSubCategoriesResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list sub catories response.
	 *
	 * @param __prefixUrl the prefix url
	 * @param __styles the styles
	 * @param __nextPage the next page
	 */
	public ListSubCategoriesResponse(String __prefixUrl, List<Style> __styles, int __nextPage) {
		super();
		this.prefixUrl = __prefixUrl;
		this.styles = __styles;
		this.nextPage = __nextPage;
	}

	/**
	 * Gets the next page.
	 *
	 * @return the nextPage
	 */
	public int getNextPage() {
		return this.nextPage;
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
	 * Gets the styles.
	 *
	 * @return the styles
	 */
	public List<Style> getStyles() {
		return this.styles;
	}

}

class ListSubCategoriesSerializer extends JsonSerializer<ListSubCategoriesResponse> {

	@Override
	public void serialize(ListSubCategoriesResponse __value, JsonGenerator __gen,
			SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeNumberField("next_index", __value.getNextPage());
		__gen.writeArrayFieldStart("data");
		for (Style _style : __value.getStyles()) {
			__gen.writeStartObject();
			__gen.writeNumberField("style_id", _style.getId());
			__gen.writeStringField("title", _style.getTitle());
			__gen.writeObjectFieldStart("images");
			if (_style.getImage().isEmpty()) {
				__gen.writeStringField("original", "");
				__gen.writeStringField("small", "");
				__gen.writeStringField("icon", "");
				__gen.writeStringField("retina", "");
			}
			else {
				__gen.writeStringField("original",
						__value.getPrefixUrl() + "/Original/" + _style.getImage());
				__gen.writeStringField("small",
						__value.getPrefixUrl() + "/Small/" + _style.getImage());
				__gen.writeStringField("icon",
						__value.getPrefixUrl() + "/Icon/" + _style.getImage());
				__gen.writeStringField("retina",
						__value.getPrefixUrl() + "/Retina/" + _style.getImage());
			}
			__gen.writeEndObject();
			__gen.writeEndObject();
		}
		__gen.writeEndArray();

		__gen.writeEndObject();
	}

}