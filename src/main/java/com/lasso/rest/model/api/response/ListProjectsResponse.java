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

	/** The next index. */
	private int				nextIndex;

	/** The prefix url. */
	private String			prefixUrl;

	/** The categories. */
	private List<Project>	projects;

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
	 * @param __projects the projects
	 * @param __nextIndex the next index
	 * @param __prefixUrl the prefix url
	 */
	public ListProjectsResponse(List<Project> __projects, int __nextIndex, String __prefixUrl) {
		super();
		this.projects = __projects;
		this.nextIndex = __nextIndex;
		this.prefixUrl = __prefixUrl;
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
	 * Gets the prefix url.
	 *
	 * @return the prefixUrl
	 */
	public String getPrefixUrl() {
		return this.prefixUrl;
	}

	/**
	 * Gets the projects.
	 *
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return this.projects;
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
		for (Project _project : __value.getProjects()) {
			__gen.writeStartObject();
			__gen.writeNumberField("style_id", _project.getId().getId());
			__gen.writeStringField("title", _project.getTitle());
			__gen.writeObjectFieldStart("images");
			if (_project.getImage().isEmpty()) {
				__gen.writeStringField("original", "");
				__gen.writeStringField("small", "");
				__gen.writeStringField("icon", "");
			}
			else {
				__gen.writeStringField("original",
				        __value.getPrefixUrl() + "/Original/" + _project.getImage());
				__gen.writeStringField("small",
				        __value.getPrefixUrl() + "/small/" + _project.getImage());
				__gen.writeStringField("icon",
				        __value.getPrefixUrl() + "/icon/" + _project.getImage());
			}
			__gen.writeEndObject();
			__gen.writeEndObject();
		}
		__gen.writeEndArray();

		__gen.writeEndObject();
	}

}
