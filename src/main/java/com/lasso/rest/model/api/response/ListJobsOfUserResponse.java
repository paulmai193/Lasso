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
import com.lasso.define.JobStepConstant;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;

/**
 * The Class ListJobsOfDesignerResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class ListJobsOfUserResponse extends BaseResponse {

	/** The datas. */
	@JsonProperty("data")
	@JsonSerialize(using = ListJobsOfUserSerializer.class)
	private List<Object[]> datas;

	/**
	 * Instantiates a new list jobs response.
	 *
	 * @param __error the error
	 */
	public ListJobsOfUserResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list jobs response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public ListJobsOfUserResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list jobs response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public ListJobsOfUserResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list jobs response.
	 *
	 * @param __datas the datas
	 */
	public ListJobsOfUserResponse(List<Object[]> __datas) {
		super();
		this.datas = __datas;
	}

	/**
	 * Gets the datas.
	 *
	 * @return the datas
	 */
	public List<Object[]> getDatas() {
		return this.datas;
	}

	/**
	 * Sets the datas.
	 *
	 * @param __datas the datas to set
	 */
	public void setDatas(List<Object[]> __datas) {
		this.datas = __datas;
	}

}

class ListJobsOfUserSerializer extends JsonSerializer<List<Object[]>> {

	@Override
	public void serialize(List<Object[]> __value, JsonGenerator __gen,
			SerializerProvider __serializers) throws IOException, JsonProcessingException {
		DateFormat _dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		__gen.writeStartArray();
		for (Object[] _objects : __value) {
			__gen.writeStartObject();
			Job _job = (Job) _objects[0];
			String _designer = (String) _objects[1];

			@SuppressWarnings("unchecked")
			List<Style> _styles = (List<Style>) _objects[2];
			Type _type = (Type) _objects[3];
			Category _category = (Category) _objects[4];

			__gen.writeNumberField("job_id", _job.getId());
			__gen.writeArrayFieldStart("styles");
			for (Style _style : _styles) {
				__gen.writeStartObject();
				__gen.writeNumberField("style_id", _style.getId());
				__gen.writeStringField("style_title", _style.getTitle());
				__gen.writeEndObject();
			}
			__gen.writeEndArray();
			__gen.writeStringField("designer", _designer);
			__gen.writeStringField("type_title", _type.getTitle());
			__gen.writeNumberField("type_id", _type.getId());
			__gen.writeNumberField("category_id", _category.getId());
			__gen.writeStringField("date_due", _dateFormat.format(_job.getLatestSubmission()));
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
		__gen.writeEndArray();
	}

}