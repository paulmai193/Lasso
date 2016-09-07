package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lasso.rest.model.datasource.Category;

@JsonInclude(value = Include.NON_NULL)
public class ListCategoryResponse extends BaseResponse {

	private String			prefixUrl;

	private List<Category>	categories;

	private int				nextPage;

	public ListCategoryResponse(String __prefixUrl, List<Category> __categories, int __nextPage) {
		super();
		this.prefixUrl = __prefixUrl;
		this.categories = __categories;
		this.nextPage = __nextPage;
	}

	public ListCategoryResponse(boolean __error) {
		super(__error);
	}

	public ListCategoryResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	public ListCategoryResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * @return the prefixUrl
	 */
	public String getPrefixUrl() {
		return this.prefixUrl;
	}

	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return this.categories;
	}

	public int getNextPage() {
		return this.nextPage;
	}
}

class ListCategorySerializer extends JsonSerializer<ListCategoryResponse> {

	@Override
	public void serialize(ListCategoryResponse __value, JsonGenerator __gen,
	        SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeNumberField("next_page", __value.getNextPage());
		__gen.writeArrayFieldStart("data");
		for (Category _category : __value.getCategories()) {
			__gen.writeObjectFieldStart("images");
			if (_category.getImage().isEmpty()) {
				__gen.writeStringField("original", "");
				__gen.writeStringField("small", "");
				__gen.writeStringField("icon", "");
			}
			else {
				__gen.writeStringField("original",
				        __value.getPrefixUrl() + "/Original/" + _category.getImage());
				__gen.writeStringField("small",
				        __value.getPrefixUrl() + "/small/" + _category.getImage());
				__gen.writeStringField("icon",
				        __value.getPrefixUrl() + "/icon/" + _category.getImage());
			}
			__gen.writeEndObject();
		}
		__gen.writeEndArray();

		__gen.writeEndObject();
	}

}