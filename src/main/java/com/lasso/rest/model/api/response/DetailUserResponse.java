package com.lasso.rest.model.api.response;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.define.Constant;
import com.lasso.rest.model.datasource.Account;

/**
 * The Class DetailUserResponse.
 *
 * @author Paul Mai
 */
@JsonSerialize(using = UserDetailSerializer.class)
public class DetailUserResponse extends BaseResponse implements DetailAccountResponse {

	/** The account. */
	private Account	account;

	/** The prefix url. */
	private String	prefixUrl;

	/**
	 * Instantiates a new user detail response.
	 *
	 * @param __account the account
	 * @param __prefixUrl the prefix url
	 */
	public DetailUserResponse(Account __account, String __prefixUrl) {
		super();
		this.account = __account;
		this.prefixUrl = __prefixUrl;
	}

	/**
	 * Instantiates a new user detail response.
	 *
	 * @param __error the error
	 */
	public DetailUserResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new user detail response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public DetailUserResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new user detail response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public DetailUserResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	public Account getAccount() {
		return this.account;
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

class UserDetailSerializer extends JsonSerializer<DetailUserResponse> {

	@Override
	public void serialize(DetailUserResponse __value, JsonGenerator __gen,
	        SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}

		__gen.writeObjectFieldStart("data");
		__gen.writeStringField("name", __value.getAccount().getName());
		__gen.writeStringField("email", __value.getAccount().getEmail());
		__gen.writeStringField("phone", __value.getAccount().getHandphoneNumber());
		__gen.writeNumberField("reward", __value.getAccount().getRewards());
		__gen.writeStringField("status",
		        __value.getAccount().getStatus() == Constant.ACC_NOT_ACTIVATE ? "in_activate"
		                : "activate");

		__gen.writeObjectFieldStart("avatar");
		if (__value.getAccount().getImage().isEmpty()) {
			__gen.writeStringField("original", "");
			__gen.writeStringField("small", "");
			__gen.writeStringField("icon", "");
		}
		else {
			__gen.writeStringField("original",
			        __value.getPrefixUrl() + "/Original/" + __value.getAccount().getImage());
			__gen.writeStringField("small",
			        __value.getPrefixUrl() + "/small/" + __value.getAccount().getImage());
			__gen.writeStringField("icon",
			        __value.getPrefixUrl() + "/icon/" + __value.getAccount().getImage());
		}
		__gen.writeEndObject();

		__gen.writeStringField("country_name", __value.getAccount().getCountry().getName());
		__gen.writeStringField("country_code", __value.getAccount().getCountry().getCode());

		__gen.writeStringField("com_address", __value.getAccount().getCompanyAddress());
		__gen.writeStringField("com_name", __value.getAccount().getCompanyName());
		__gen.writeStringField("com_phone", __value.getAccount().getCompanyTelephone());
		__gen.writeEndObject();

		__gen.writeEndObject();
	}

}