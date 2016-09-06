package com.lasso.rest.model.api.response;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.rest.model.datasource.Account;

/**
 * The Class DetailUserResponse.
 *
 * @author Paul Mai
 */
public class DetailUserResponse extends BaseResponse implements DetailAccountResponse {

	/** The account. */
	@JsonProperty("data")
	@JsonSerialize(using = UserDetailSerializer.class)
	private Account account;

	/**
	 * Instantiates a new user detail response.
	 *
	 * @param __account the account
	 */
	public DetailUserResponse(Account __account) {
		super();
		this.account = __account;
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

}

class UserDetailSerializer extends JsonSerializer<Account> {

	@Override
	public void serialize(Account __value, JsonGenerator __gen, SerializerProvider __serializers)
			throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeStringField("name", __value.getName());
		__gen.writeStringField("email", __value.getEmail());
		__gen.writeStringField("phone", __value.getHandphoneNumber());
		__gen.writeStringField("avatar", __value.getImage());
		__gen.writeStringField("country", __value.getCountry().getName());
		__gen.writeStringField("com_address", __value.getCompanyAddress());
		__gen.writeStringField("com_name", __value.getCompanyName());
		__gen.writeStringField("com_phone", __value.getCompanyTelephone());
		__gen.writeEndObject();
	}

}