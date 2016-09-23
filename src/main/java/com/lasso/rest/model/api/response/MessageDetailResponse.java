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
import com.lasso.rest.model.datasource.Message;

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

	@Override
	public void serialize(MessageDetailResponse __value, JsonGenerator __gen,
	        SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}

		__gen.writeArrayFieldStart("data");
		DateFormat _dateFormat = new SimpleDateFormat("dd MMM, hh.mma");
		__value.getMessageDatas().forEach(new Consumer<Object[]>() {

			@Override
			public void accept(Object[] __messageData) {
				try {
					Message _message = (Message) __messageData[0];
					Account _sender = (Account) __messageData[1];
					__gen.writeStartObject();
					__gen.writeNumberField("message_id", _message.getId());
					__gen.writeStringField("message_title", _message.getTitle());
					__gen.writeStringField("message_content", _message.getMessage());
					__gen.writeStringField("sender_name", _sender.getName());
					if (_sender.getImage() == null || _sender.getImage().trim().isEmpty()) {
						__gen.writeStringField("sender_avatar", "");
					}
					else {
						__gen.writeStringField("sender_avatar",
						        __value.getPrefixUrl() + "/Icon/" + _sender.getImage());
					}
					__gen.writeStringField("message_time",
					        _dateFormat.format(_message.getCreated()));

					if (_message.getParentId() == 0) {
						// Root message, serialize order detail

					}
					__gen.writeEndObject();
				}
				catch (IOException _ex) {
					Logger.getLogger(getClass()).warn("Unwanted error", _ex);
				}
			}
		});
		__gen.writeEndArray();

		__gen.writeEndObject();
	}

}
