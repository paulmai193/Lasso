/*
 * 
 */
package com.lasso.rest.model.api.response;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.rest.model.datasource.Category;

/**
 * The Class CategoryResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = CategorySerializer.class)
public class CategoryResponse extends BaseResponse {

	/** The category. */
	private Category	category;

	/** The prefix url. */
	@JsonIgnore
	private String		prefixUrl;

	/**
	 * Instantiates a new category response.
	 *
	 * @param __error
	 *        the error
	 */
	public CategoryResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new category response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 */
	public CategoryResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new category response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 * @param __detail
	 *        the detail
	 */
	public CategoryResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new category response.
	 *
	 * @param __prefixUrl
	 *        the prefix url
	 * @param __category
	 *        the category
	 */
	public CategoryResponse(String __prefixUrl, Category __category) {
		super();
		this.prefixUrl = __prefixUrl;
		this.category = __category;
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
	 * Gets the prefix url.
	 *
	 * @return the prefixUrl
	 */
	public String getPrefixUrl() {
		return this.prefixUrl;
	}

}

class CategorySerializer extends JsonSerializer<CategoryResponse> {

	@Override
	public void serialize(CategoryResponse __value, JsonGenerator __gen,
			SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();

		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeObjectFieldStart("data");
		__gen.writeNumberField("id", __value.getCategory().getId());
		__gen.writeStringField("title", __value.getCategory().getTitle());
		__gen.writeObjectFieldStart("images");
		if (__value.getCategory().getImage().isEmpty()) {
			__gen.writeStringField("original", "");
			__gen.writeStringField("small", "");
			__gen.writeStringField("icon", "");
			__gen.writeStringField("retina", "");
		}
		else {
			__gen.writeStringField("original",
					__value.getPrefixUrl() + "/Original/" + __value.getCategory().getImage());
			__gen.writeStringField("small",
					__value.getPrefixUrl() + "/Small/" + __value.getCategory().getImage());
			__gen.writeStringField("icon",
					__value.getPrefixUrl() + "/Icon/" + __value.getCategory().getImage());
			__gen.writeStringField("retina",
					__value.getPrefixUrl() + "/Retina/" + __value.getCategory().getImage());
		}
		__gen.writeEndObject();

		__gen.writeEndObject();
	}

}