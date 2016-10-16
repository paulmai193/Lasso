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

// TODO: Auto-generated Javadoc
/**
 * The Class ListCategoriesResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = ListCategoriesSerializer.class)
public class ListCategoriesResponse extends BaseResponse {

	/** The categories. */
	private List<Category>	categories;

	/** The next index. */
	private int				nextIndex;

	/** The prefix url. */
	private String			prefixUrl;

	/**
	 * Instantiates a new list categories response.
	 *
	 * @param __error
	 *        the error
	 */
	public ListCategoriesResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list categories response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 */
	public ListCategoriesResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list categories response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 * @param __detail
	 *        the detail
	 */
	public ListCategoriesResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list categories response.
	 *
	 * @param __prefixUrl
	 *        the prefix url
	 * @param __categories
	 *        the categories
	 * @param __nextIndex
	 *        the next index
	 */
	public ListCategoriesResponse(String __prefixUrl, List<Category> __categories,
			int __nextIndex) {
		super();
		this.prefixUrl = __prefixUrl;
		this.categories = __categories;
		this.nextIndex = __nextIndex;
	}

	/**
	 * Gets the categories.
	 *
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return this.categories;
	}

	/**
	 * Gets the next page.
	 *
	 * @return the next page
	 */
	public int getNextPage() {
		return this.nextIndex;
	}

	/**
	 * Gets the prefix url.
	 *
	 * @return the prefixUrl
	 */
	public String getPrefixUrl() {
		return this.prefixUrl;
	}
}

class ListCategoriesSerializer extends JsonSerializer<ListCategoriesResponse> {

	@Override
	public void serialize(ListCategoriesResponse __value, JsonGenerator __gen,
			SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeNumberField("next_index", __value.getNextPage());
		__gen.writeArrayFieldStart("data");
		for (Category _category : __value.getCategories()) {
			__gen.writeStartObject();
			__gen.writeNumberField("category_id", _category.getId());
			__gen.writeStringField("title", _category.getTitle());

			__gen.writeObjectFieldStart("images");
			if (_category.getImage().isEmpty()) {
				__gen.writeStringField("original", "");
				__gen.writeStringField("small", "");
				__gen.writeStringField("icon", "");
				__gen.writeStringField("retina", "");
			}
			else {
				__gen.writeStringField("original",
						__value.getPrefixUrl() + "/Original/" + _category.getImage());
				__gen.writeStringField("small",
						__value.getPrefixUrl() + "/Small/" + _category.getImage());
				__gen.writeStringField("icon",
						__value.getPrefixUrl() + "/Icon/" + _category.getImage());
				__gen.writeStringField("retina",
						__value.getPrefixUrl() + "/Retina/" + _category.getImage());
			}
			__gen.writeEndObject();

			__gen.writeEndObject();
		}
		__gen.writeEndArray();

		__gen.writeEndObject();
	}

}