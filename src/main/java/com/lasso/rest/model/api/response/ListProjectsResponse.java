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
import com.lasso.rest.model.datasource.Project;

/**
 * The Class ListProjectsResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = ListProjectsSerializer.class)
public class ListProjectsResponse extends BaseResponse {

	/** The datas. {Project, designer avatar name} */
	private List<Object[]>	datas;

	/** The next index. */
	private int				nextIndex;

	/** The prefix avatar url. */
	private String			prefixAvatarUrl;

	/** The prefix project url. */
	private String			prefixProjectUrl;

	/**
	 * Instantiates a new list projects response.
	 *
	 * @param __error the error
	 */
	public ListProjectsResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list projects response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public ListProjectsResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list projects response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public ListProjectsResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list projects response.
	 *
	 * @param __nextIndex the next index
	 * @param __prefixProjectUrl the prefix project url
	 * @param __prefixAvatarUrl the prefix avatar url
	 * @param __datas the datas
	 */
	public ListProjectsResponse(int __nextIndex, String __prefixProjectUrl,
	        String __prefixAvatarUrl, List<Object[]> __datas) {
		super();
		this.nextIndex = __nextIndex;
		this.prefixProjectUrl = __prefixProjectUrl;
		this.prefixAvatarUrl = __prefixAvatarUrl;
		this.datas = __datas;
	}

	/**
	 * Gets the datas {Project, Desiger avatar name}.
	 *
	 * @return the datas
	 */
	public List<Object[]> getDatas() {
		return this.datas;
	}

	/**
	 * Gets the next index.
	 *
	 * @return the nextIndex
	 */
	public int getNextIndex() {
		return this.nextIndex;
	}

	/**
	 * Gets the prefix avatar url.
	 *
	 * @return the prefix avatar url
	 */
	public String getPrefixAvatarUrl() {
		return this.prefixAvatarUrl;
	}

	/**
	 * Gets the prefix project url.
	 *
	 * @return the prefix project url
	 */
	public String getPrefixProjectUrl() {
		return this.prefixProjectUrl;
	}

}

class ListProjectsSerializer extends JsonSerializer<ListProjectsResponse> {

	@Override
	public void serialize(ListProjectsResponse __value, JsonGenerator __gen,
	        SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeNumberField("next_index", __value.getNextIndex());
		__gen.writeArrayFieldStart("data");
		for (Object[] _data : __value.getDatas()) {
			__gen.writeStartObject();
			__gen.writeNumberField("project_id", ((Project) _data[0]).getId());
			__gen.writeStringField("title", ((Project) _data[0]).getTitle());
			__gen.writeObjectFieldStart("images");
			if (((Project) _data[0]).getImage().isEmpty()) {
				__gen.writeStringField("original", "");
				__gen.writeStringField("small", "");
				__gen.writeStringField("icon", "");
				__gen.writeStringField("retina", "");
			}
			else {
				__gen.writeStringField("original", __value.getPrefixProjectUrl() + "/Original/"
				        + ((Project) _data[0]).getImage());
				__gen.writeStringField("small", __value.getPrefixProjectUrl() + "/Small/"
				        + ((Project) _data[0]).getImage());
				__gen.writeStringField("icon",
				        __value.getPrefixProjectUrl() + "/Icon/" + ((Project) _data[0]).getImage());
				__gen.writeStringField("retina", __value.getPrefixProjectUrl() + "/Retina/"
				        + ((Project) _data[0]).getImage());
			}
			__gen.writeEndObject();

			if (((String) _data[1]).isEmpty()) {
				__gen.writeStringField("designer_avatar", "");
			}
			else {
				__gen.writeStringField("designer_avatar",
				        __value.getPrefixAvatarUrl() + "/icon/" + _data[1]);
			}
			__gen.writeEndObject();
		}
		__gen.writeEndArray();

		__gen.writeEndObject();
	}

}
