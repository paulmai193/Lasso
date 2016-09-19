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
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.Type;

/**
 * The Class ListJobsResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class ListJobsResponse extends BaseResponse {

	/** The datas. */
	@JsonProperty("data")
	@JsonSerialize(using = ListJobsSerializer.class)
	private List<Object[]> datas;

	/**
	 * Instantiates a new list jobs response.
	 *
	 * @param __error the error
	 */
	public ListJobsResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list jobs response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public ListJobsResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list jobs response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public ListJobsResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list jobs response.
	 *
	 * @param __datas the datas
	 */
	public ListJobsResponse(List<Object[]> __datas) {
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

class ListJobsSerializer extends JsonSerializer<List<Object[]>> {

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
			List<Type> _types = (List<Type>) _objects[2];
			String _style = (String) _objects[3];
			__gen.writeNumberField("job_id", _job.getId());
			__gen.writeArrayFieldStart("types");
			for (Type _type : _types) {
				__gen.writeStartObject();
				__gen.writeNumberField("type_id", _type.getId());
				__gen.writeStringField("type_title", _type.getTitle());
				__gen.writeEndObject();
			}
			__gen.writeEndArray();
			__gen.writeStringField("designer", _designer);
			__gen.writeStringField("style", _style);
			__gen.writeStringField("date_due", _dateFormat.format(_job.getLatestSubmission()));
			__gen.writeStringField("status",
					_job.getCompleted() == 0 ? "In Progress" : "Completed");
			__gen.writeEndObject();
		}
		__gen.writeEndArray();
	}

}