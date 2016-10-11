package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.define.JobStageConstant;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.Message;

/**
 * The Class ListMessageResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = ListMessageSerializer.class)
public class ListMessageResponse extends BaseResponse {

	/** The message datas. */
	private List<Object[]>	messageDatas;

	/** The prefix url. */
	private String			prefixUrl;

	/**
	 * Instantiates a new list message response.
	 *
	 * @param __error the error
	 */
	public ListMessageResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list message response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public ListMessageResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list message response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public ListMessageResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list message response.
	 *
	 * @param __messageDatas the message datas
	 * @param __prefixUrl the prefix url
	 */
	public ListMessageResponse(List<Object[]> __messageDatas, String __prefixUrl) {
		super();
		this.messageDatas = __messageDatas;
		this.prefixUrl = __prefixUrl;
	}

	/**
	 * Gets the message datas.
	 *
	 * @return the messageDatas
	 */
	public List<Object[]> getMessageDatas() {
		return this.messageDatas;
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

class ListMessageSerializer extends JsonSerializer<ListMessageResponse> {

	@Override
	public void serialize(ListMessageResponse __value, JsonGenerator __gen,
			SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}

		__gen.writeArrayFieldStart("data");
		__value.getMessageDatas().forEach(__messageData -> {
			Message _message = (Message) __messageData[0];
			Account _sender = (Account) __messageData[1];
			Job _job = (Job) __messageData[2];
			try {
				__gen.writeStartObject();
				__gen.writeNumberField("message_id", _message.getId());
				__gen.writeStringField("message_title", _job.getDescription());
				__gen.writeStringField("message_content", _message.getMessage());
				__gen.writeNumberField("message_read", _message.getIsRead());
				if (_job.getPaid().equals((byte) 0)) {
					__gen.writeStringField("action_status", "job_confirm");
				}
				else if (_job.getStage().equals(JobStageConstant.JOB_STAGE_COMPLETED)) {
					__gen.writeStringField("action_status", "job_completed");
				}
				else {
					__gen.writeStringField("action_status", "job_explain");
				}
				__gen.writeStringField("sender_name", _sender.getName());
				if (_sender.getImage() == null || _sender.getImage().trim().isEmpty()) {
					__gen.writeStringField("sender_avatar", "");
				}
				else {
					__gen.writeStringField("sender_avatar",
							__value.getPrefixUrl() + "/Icon/" + _sender.getImage());
				}
				__gen.writeNumberField("job_id", _job.getId());
				__gen.writeEndObject();
			}
			catch (Exception _ex) {
				Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
			}
		});
		__gen.writeEndArray();

		__gen.writeEndObject();
	}

}