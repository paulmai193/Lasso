package com.lasso.rest.model.api.response;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.rest.model.datasource.Project;

/**
 * The Class ProjectResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = ProjectSerializer.class)
public class ProjectResponse extends BaseResponse {

	/** The prefix url. */
	private String	prefixUrl;

	/** The categories. */
	private Project	project;

	/**
	 * Instantiates a new project response.
	 *
	 * @param __error the error
	 */
	public ProjectResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new project response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public ProjectResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new project response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public ProjectResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new project response.
	 *
	 * @param __prefixUrl the prefix url
	 * @param __project the project
	 */
	public ProjectResponse(String __prefixUrl, Project __project) {
		super();
		this.prefixUrl = __prefixUrl;
		this.project = __project;
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
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return this.project;
	}

}

class ProjectSerializer extends JsonSerializer<ProjectResponse> {

	@Override
	public void serialize(ProjectResponse __value, JsonGenerator __gen,
	        SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();

		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeObjectFieldStart("data");
		__gen.writeNumberField("id", __value.getProject().getId().getId());
		__gen.writeStringField("title", __value.getProject().getTitle());
		__gen.writeObjectFieldStart("images");
		if (__value.getProject().getImage().isEmpty()) {
			__gen.writeStringField("original", "");
			__gen.writeStringField("small", "");
			__gen.writeStringField("icon", "");
		}
		else {
			__gen.writeStringField("original",
			        __value.getPrefixUrl() + "/Original/" + __value.getProject().getImage());
			__gen.writeStringField("small",
			        __value.getPrefixUrl() + "/small/" + __value.getProject().getImage());
			__gen.writeStringField("icon",
			        __value.getPrefixUrl() + "/icon/" + __value.getProject().getImage());
		}
		__gen.writeEndObject();

		__gen.writeEndObject();
	}

}
