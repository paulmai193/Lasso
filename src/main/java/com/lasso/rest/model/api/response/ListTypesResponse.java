package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.rest.model.datasource.Type;

/**
 * The Class ListTypesResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class ListTypesResponse extends BaseResponse {

	/** The datas. */
	@JsonProperty("data")
	@JsonSerialize(using = ListTypeSerializer.class)
	private List<Type> datas;

	/**
	 * Instantiates a new list types response.
	 *
	 * @param __error the error
	 */
	public ListTypesResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list types response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public ListTypesResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list types response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public ListTypesResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list types response.
	 *
	 * @param __datas the datas
	 */
	public ListTypesResponse(List<Type> __datas) {
		super();
		this.datas = __datas;
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
	 * Sets the datas.
	 *
	 * @param __datas the new datas
	 */
	public void setDatas(List<Type> __datas) {
		this.datas = __datas;
	}

}

class ListTypeSerializer extends JsonSerializer<List<Type>> {

	@Override
	public void serialize(List<Type> __value, JsonGenerator __gen, SerializerProvider __serializers)
	        throws IOException, JsonProcessingException {
		__gen.writeStartArray();
		for (Type _type : __value) {
			__gen.writeStartObject();
			__gen.writeNumberField("id", _type.getId());
			__gen.writeStringField("title", _type.getTitle());
			__gen.writeEndObject();
		}
		__gen.writeEndArray();
	}

}
