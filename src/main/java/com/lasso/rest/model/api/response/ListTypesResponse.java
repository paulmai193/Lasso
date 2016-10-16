/*
 * 
 */
package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.rest.model.datasource.Type;

// TODO: Auto-generated Javadoc
/**
 * The Class ListTypesResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = ListTypeSerializer.class)
public class ListTypesResponse extends BaseResponse {

	/** The datas. */
	private List<Type>	datas;

	/** The prefix url. */
	private String		prefixUrl;

	/**
	 * Instantiates a new list types response.
	 *
	 * @param __error
	 *        the error
	 */
	public ListTypesResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list types response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 */
	public ListTypesResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list types response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 * @param __detail
	 *        the detail
	 */
	public ListTypesResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list types response.
	 *
	 * @param __datas
	 *        the datas
	 * @param __prefixUrl
	 *        the prefix url
	 */
	public ListTypesResponse(List<Type> __datas, String __prefixUrl) {
		super();
		this.datas = __datas;
		this.prefixUrl = __prefixUrl;
	}

	/**
	 * Gets the datas.
	 *
	 * @return the datas
	 */
	public List<Type> getDatas() {
		return this.datas;
	}

	/**
	 * Gets the prefix url.
	 *
	 * @return the prefix url
	 */
	public String getPrefixUrl() {
		return this.prefixUrl;
	}

}

class ListTypeSerializer extends JsonSerializer<ListTypesResponse> {

	@Override
	public void serialize(ListTypesResponse __value, JsonGenerator __gen,
			SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeArrayFieldStart("data");
		__value.getDatas().forEach(new Consumer<Type>() {

			@Override
			public void accept(Type __type) {
				try {
					__gen.writeStartObject();
					__gen.writeNumberField("type_id", __type.getId());
					__gen.writeStringField("type_title", __type.getTitle());
					__gen.writeObjectFieldStart("image");
					ListTypeSerializer.this.serializeImage(__gen, __value.getPrefixUrl(),
							__type.getImage());
					__gen.writeEndObject();
					__gen.writeEndObject();
				}
				catch (IOException _ex) {
					Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
				}

			}
		});
		__gen.writeEndArray();
		__gen.writeEndObject();
	}

	private void serializeImage(JsonGenerator __gen, String __prefixUrl, String imageName) {
		try {
			if (imageName == null || imageName.trim().isEmpty()) {
				__gen.writeStringField("original", "");
				__gen.writeStringField("small", "");
				__gen.writeStringField("icon", "");
				__gen.writeStringField("retina", "");
			}
			else {
				__gen.writeStringField("original", __prefixUrl + "/Original/" + imageName.trim());
				__gen.writeStringField("small", __prefixUrl + "/Small/" + imageName.trim());
				__gen.writeStringField("icon", __prefixUrl + "/Icon/" + imageName.trim());
				__gen.writeStringField("retina", __prefixUrl + "/Retina/" + imageName.trim());
			}
		}
		catch (Exception _ex) {
			Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
		}

	}
}
