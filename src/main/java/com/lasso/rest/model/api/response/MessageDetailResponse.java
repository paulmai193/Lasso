package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Consumer;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.Message;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;

@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = MessageDetailSerializer.class)
public class MessageDetailResponse extends BaseResponse {

	private GetOrderResponse	orderDetail;
	private List<Object[]>		messageDatas;
	private String				prefixUrl;

	public MessageDetailResponse(GetOrderResponse __orderDetail, List<Object[]> __messageDatas,
	        String __prefixUrl) {
		super();
		this.orderDetail = __orderDetail;
		this.messageDatas = __messageDatas;
		this.prefixUrl = __prefixUrl;
	}

	public MessageDetailResponse(boolean __error) {
		super(__error);
	}

	public MessageDetailResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	public MessageDetailResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * @return the orderDetail
	 */
	public GetOrderResponse getOrderDetail() {
		return this.orderDetail;
	}

	/**
	 * @return the messageDatas
	 */
	public List<Object[]> getMessageDatas() {
		return this.messageDatas;
	}

	/**
	 * @return the prefixUrl
	 */
	public String getPrefixUrl() {
		return this.prefixUrl;
	}

}

class MessageDetailSerializer extends JsonSerializer<MessageDetailResponse> {

	@SuppressWarnings("unchecked")
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

		__gen.writeObjectFieldStart("job");
		GetOrderResponse _orderDetail = __value.getOrderDetail();
		Job _job = (Job) _orderDetail.getData()[0];
		List<Type> _types = (List<Type>) _orderDetail.getData()[2];
		Style _style = (Style) _orderDetail.getData()[3];
		__gen.writeStringField("job_description", _job.getDescription());
		__gen.writeArrayFieldStart("types");
		_types.forEach(new Consumer<Type>() {

			@Override
			public void accept(Type __type) {
				try {
					__gen.writeStartObject();
					__gen.writeStringField("type_title", __type.getTitle());
					__gen.writeEndObject();
				}
				catch (IOException _ex) {
					Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
				}

			}
		});
		__gen.writeEndArray();
		__gen.writeStringField("style_title", _style.getTitle());
		DateFormat _dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		__gen.writeStringField("submission", _dateFormat.format(_job.getSubmission()));
		__gen.writeStringField("objective", _job.getObjective());
		__gen.writeStringField("asset_url", _job.getAssetsUrl());
		__gen.writeStringField("further_info", _job.getFurtherInformation());
		__gen.writeEndObject();

		__gen.writeArrayFieldStart("messages");
		_dateFormat = new SimpleDateFormat("dd MMM, hh.mma");
		__value.getMessageDatas().forEach(new Consumer<Object[]>() {

			@Override
			public void accept(Object[] __messageData) {
				try {
					Message _message = (Message) __messageData[0];
					Account _sender = (Account) __messageData[1];
					__gen.writeStartObject();
					__gen.writeNumberField("message_id", _message.getId());
					__gen.writeStringField("message_content", _message.getMessage());
					__gen.writeStringField("sender_name", _sender.getName());
					if (_sender.getImage() == null || _sender.getImage().trim().isEmpty()) {
						__gen.writeStringField("sender_avatar", "");
					}
					else {
						__gen.writeStringField("sender_avatar",
						        __value.getPrefixUrl() + "/Icon/" + _sender.getImage());
					}
					DateFormat _dateFormat = new SimpleDateFormat("dd MMM, hh.mma");
					__gen.writeStringField("message_time",
					        _dateFormat.format(_message.getCreated()));
					__gen.writeEndObject();
				}
				catch (IOException _ex) {
					Logger.getLogger(getClass()).warn("Unwanted error", _ex);
				}
			}
		});
		__gen.writeEndArray();

		__gen.writeEndObject();

		__gen.writeEndObject();
	}

}
