package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.rest.model.datasource.Job;

/**
 * The Class JobDetailResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class JobDetailResponse extends BaseResponse {

	/** The data. */
	@JsonProperty("data")
	@JsonSerialize(using = JobDetailSerializer.class)
	private Object[] data;

	/**
	 * Instantiates a new job detail response.
	 *
	 * @param __error the error
	 */
	public JobDetailResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new job detail response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public JobDetailResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new job detail response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public JobDetailResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new job detail response.
	 *
	 * @param __data the data
	 */
	public JobDetailResponse(Object[] __data) {
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
	 * @param __data the new data
	 */
	public void setData(Object[] __data) {
		this.data = __data;
	}
}

class JobDetailSerializer extends JsonSerializer<Object[]> {

	@Override
	public void serialize(Object[] __value, JsonGenerator __gen, SerializerProvider __serializers)
			throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		Job _job = (Job) __value[0];
		String _designer = (String) __value[1];
		String _type = (String) __value[2];
		String _style = (String) __value[3];
		__gen.writeStringField("type", _type);
		__gen.writeStringField("designer", _designer);
		__gen.writeStringField("style", _style);
		DateFormat _fullDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat _shortDateFormat = new SimpleDateFormat("dd.MM");
		__gen.writeStringField("date_due", _fullDateFormat.format(_job.getLatestSubmission()));
		__gen.writeStringField("date_brief", _shortDateFormat.format(_job.getSubmission()));
		__gen.writeStringField("date_stage", _shortDateFormat.format(_job.getStageDate()));
		switch (_job.getStage()) {
			case 1:
				__gen.writeStringField("stage", "1st Draft");
				break;

			case 2:
				__gen.writeStringField("stage", "Revised");
				break;
			case 3:
				__gen.writeStringField("stage", "Final Artwork");
				break;
			case 4:
				__gen.writeStringField("stage", "Completed");
				break;
		}
		__gen.writeStringField("date_complete",
				_shortDateFormat.format(_job.getLatestSubmission()));
		__gen.writeStringField("status", _job.getCompleted() == 0 ? "In Progress" : "Completed");
		__gen.writeEndObject();
	}

}