/*
 * 
 */
package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.Message;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;

/**
 * The Class MessageDetailResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = MessageDetailSerializer.class)
public class MessageDetailResponse extends BaseResponse {

	/** The message datas. */
	private List<Object[]>		messageDatas;

	/** The order detail. */
	private GetOrderResponse	orderDetail;

	/**
	 * Instantiates a new message detail response.
	 *
	 * @param __error
	 *        the error
	 */
	public MessageDetailResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new message detail response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 */
	public MessageDetailResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new message detail response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 * @param __detail
	 *        the detail
	 */
	public MessageDetailResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new message detail response.
	 *
	 * @param __orderDetail
	 *        the order detail
	 * @param __messageDatas
	 *        the message datas
	 */
	public MessageDetailResponse(GetOrderResponse __orderDetail, List<Object[]> __messageDatas) {
		super();
		this.orderDetail = __orderDetail;
		this.messageDatas = __messageDatas;
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
	 * Gets the order detail.
	 *
	 * @return the orderDetail
	 */
	public GetOrderResponse getOrderDetail() {
		return this.orderDetail;
	}

}

class MessageDetailSerializer extends JsonSerializer<MessageDetailResponse> {

	@Override
	public void serialize(MessageDetailResponse __value, JsonGenerator __gen,
	        SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}

		__gen.writeObjectFieldStart("data");
		Job _job = (Job) __value.getOrderDetail().getData()[0];
		if (_job.getPaid().equals((byte) 0)) {
			__gen.writeStringField("action_status", "job_confirm");
		}
		else if (_job.getStage().equals(JobStageConstant.JOB_STAGE_COMPLETED)) {
			__gen.writeStringField("action_status", "job_completed");
		}
		else {
			__gen.writeStringField("action_status", "job_explain");
		}

		__gen.writeObjectFieldStart("job");
		this.serializeJob(__gen, __value);
		__gen.writeEndObject();

		__gen.writeArrayFieldStart("messages");
		__value.getMessageDatas().forEach(_data -> this.serializeMessages(__gen, _data,
		        __value.getOrderDetail().getPrefixAvatar()));
		__gen.writeEndArray();

		__gen.writeEndObject();
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

	private void serializeJob(JsonGenerator __gen, MessageDetailResponse __value) {
		GetOrderResponse _orderDetail = __value.getOrderDetail();
		Job _job = (Job) _orderDetail.getData()[0];
		Account _user = (Account) __value.getMessageDatas().get(0)[1];

		@SuppressWarnings("unchecked")
		List<Style> _styles = (List<Style>) _orderDetail.getData()[2];
		Type _type = (Type) _orderDetail.getData()[3];
		Category _category = (Category) _orderDetail.getData()[4];

		try {
			__gen.writeNumberField("job_id", _job.getId());
			__gen.writeStringField("job_description", _job.getDescription());
			__gen.writeArrayFieldStart("styles");
			_styles.forEach(_style -> {
				try {
					__gen.writeStartObject();
					__gen.writeStringField("title", _style.getTitle());
					__gen.writeEndObject();
				}
				catch (IOException _ex) {
					Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
				}

			});
			__gen.writeEndArray();
			__gen.writeStringField("type_title", _type.getTitle());
			__gen.writeStringField("category_title", _category.getTitle());
			DateFormat _dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			__gen.writeStringField("submission", _dateFormat.format(_job.getSubmission()));
			__gen.writeStringField("objective", _job.getObjective());
			__gen.writeStringField("asset_url", _job.getAssetsUrl());
			__gen.writeStringField("further_information",
			        _job.getFurtherInformation() == null ? "" : _job.getFurtherInformation());
			__gen.writeArrayFieldStart("images");
			if (_job.getReference() != null && !_job.getReference().trim().isEmpty()) {
				for (String _referenceImage : _job.getReference().trim().split(",")) {
					__gen.writeStartObject();
					this.serializeImage(__gen, _orderDetail.getPrefixJob(), _referenceImage);
					__gen.writeEndObject();
				}
			}
			__gen.writeEndArray();
			if (_user.getImage().trim().isEmpty()) {
				__gen.writeStringField("avatar", "");
			}
			else {
				__gen.writeStringField("avatar", __value.getOrderDetail().getPrefixAvatar()
				        + "/Icon/" + _user.getImage().trim());
			}
		}
		catch (IOException _ex) {
			Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
		}
	}

	private void serializeMessages(JsonGenerator __gen, Object[] __messageData,
	        String __prefixUrl) {
		try {
			Message _message = (Message) __messageData[0];
			Account _sender = (Account) __messageData[1];
			__gen.writeStartObject();
			__gen.writeNumberField("message_id", _message.getId());
			__gen.writeStringField("message_content", _message.getMessage());
			__gen.writeNumberField("sender_id", _sender.getId());
			__gen.writeStringField("sender_name", _sender.getName());
			if (_sender.getImage() == null || _sender.getImage().trim().isEmpty()) {
				__gen.writeStringField("sender_avatar", "");
			}
			else {
				__gen.writeStringField("sender_avatar",
				        __prefixUrl + "/Icon/" + _sender.getImage().trim());
			}
			DateFormat _dateFormat = new SimpleDateFormat("dd MMM, hh.mma");
			__gen.writeStringField("message_time", _dateFormat.format(_message.getCreated()));
			__gen.writeEndObject();
		}
		catch (IOException _ex) {
			Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
		}
	}

}
