/*
 * 
 */
package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.define.JobStageConstant;
import com.lasso.define.JobStepConstant;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.Style;

/**
 * The Class JobOfUserDetailResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class JobOfDesignerDetailResponse extends BaseResponse {

	/** The data. */
	@JsonProperty("data")
	@JsonSerialize(using = JobOfDesignerDetailSerializer.class)
	private Object[] data;

	/**
	 * Instantiates a new job detail response.
	 *
	 * @param __error
	 *        the error
	 */
	public JobOfDesignerDetailResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new job detail response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 */
	public JobOfDesignerDetailResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new job detail response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 * @param __detail
	 *        the detail
	 */
	public JobOfDesignerDetailResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new job detail response.
	 *
	 * @param __data
	 *        the data
	 */
	public JobOfDesignerDetailResponse(Object[] __data) {
		this.data = __data;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Object[] getData() {
		return this.data;
	}

	/**
	 * Sets the data.
	 *
	 * @param __data
	 *        the new data
	 */
	public void setData(Object[] __data) {
		this.data = __data;
	}
}

class JobOfDesignerDetailSerializer extends JsonSerializer<Object[]> {

	@Override
	public void serialize(Object[] __value, JsonGenerator __gen, SerializerProvider __serializers)
	        throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		Job _job = (Job) __value[0];
		String _designer = (String) __value[1];

		@SuppressWarnings("unchecked")
		List<Style> _styles = (List<Style>) __value[2];
		String _typeTitle = (String) __value[3];

		__gen.writeStringField("job_description", _job.getDescription());
		__gen.writeArrayFieldStart("styles");
		for (Style _style : _styles) {
			__gen.writeStartObject();
			__gen.writeNumberField("style_id", _style.getId());
			__gen.writeStringField("title", _style.getTitle());
			__gen.writeEndObject();
		}
		__gen.writeEndArray();
		__gen.writeStringField("user", _designer);
		__gen.writeStringField("type_title", _typeTitle);
		DateFormat _fullDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		__gen.writeStringField("date_due", _fullDateFormat.format(_job.getLatestSubmission()));
		__gen.writeStringField("date_brief", _fullDateFormat.format(_job.getSubmission()));
		if (_job.getStageDate() == null) {
			_job.setStageDate(_job.getCreated());
		}
		__gen.writeStringField("date_stage", _fullDateFormat.format(_job.getStageDate()));
		__gen.writeStringField("stage", JobStageConstant.getByCode(_job.getStage()).getName());
		__gen.writeStringField("date_complete", _fullDateFormat.format(_job.getLatestSubmission()));
		String _status;
		if (_job.getPaid().equals((byte) 0)) {
			_status = JobStepConstant.getByCode(_job.getStep()).getStepName();
		}
		else {
			if (_job.getCompleted().equals((byte) 0)) {
				_status = "In Progress";
			}
			else {
				_status = "Completed";
			}
		}
		__gen.writeStringField("status", _status);
		__gen.writeEndObject();
	}

}