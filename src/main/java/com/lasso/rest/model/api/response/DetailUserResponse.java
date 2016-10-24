/*
 * 
 */
package com.lasso.rest.model.api.response;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.define.Constant;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Country;

/**
 * The Class DetailUserResponse.
 *
 * @author Paul Mai
 */
@JsonSerialize(using = UserDetailSerializer.class)
public class DetailUserResponse extends BaseResponse implements DetailAccountResponse {

	/** The account. */
	private Account	account;

	/** The country. */
	private Country	country;

	/** The prefix url. */
	private String	prefixUrl;

	/**
	 * Instantiates a new detail user response.
	 *
	 * @param __account
	 *        the account
	 * @param __country
	 *        the country
	 * @param __prefixUrl
	 *        the prefix url
	 */
	public DetailUserResponse(Account __account, Country __country, String __prefixUrl) {
		super();
		this.account = __account;
		this.country = __country;
		this.prefixUrl = __prefixUrl;
	}

	/**
	 * Instantiates a new user detail response.
	 *
	 * @param __error
	 *        the error
	 */
	public DetailUserResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new user detail response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 */
	public DetailUserResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new user detail response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 * @param __detail
	 *        the detail
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
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry() {
		return this.country;
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
		if (__value.getAccount().getImage() == null || __value.getAccount().getImage().isEmpty()) {
			if (__value.getAccount().getGender().shortValue() == Constant.GENDER_FEMALE) {
				__gen.writeStringField("original", __value.getPrefixUrl() + "/Original/female.jpg");
				__gen.writeStringField("small", __value.getPrefixUrl() + "/Small/female.jpg");
				__gen.writeStringField("icon", __value.getPrefixUrl() + "/Icon/female.jpg");
				__gen.writeStringField("retina", __value.getPrefixUrl() + "/Retina/female.jpg");
			}
			else {
				__gen.writeStringField("original", __value.getPrefixUrl() + "/Original/male.jpg");
				__gen.writeStringField("small", __value.getPrefixUrl() + "/Small/male");
				__gen.writeStringField("icon", __value.getPrefixUrl() + "/Icon/male");
				__gen.writeStringField("retina", __value.getPrefixUrl() + "/Retina/male");
			}
		}
		else {
			__gen.writeStringField("original",
					__value.getPrefixUrl() + "/Original/" + __value.getAccount().getImage());
			__gen.writeStringField("small",
					__value.getPrefixUrl() + "/Small/" + __value.getAccount().getImage());
			__gen.writeStringField("icon",
					__value.getPrefixUrl() + "/Icon/" + __value.getAccount().getImage());
			__gen.writeStringField("retina",
					__value.getPrefixUrl() + "/Retina/" + __value.getAccount().getImage());
		}
		__gen.writeEndObject();

		__gen.writeStringField("country_name", __value.getCountry().getName());
		__gen.writeStringField("country_code", __value.getCountry().getCode());

		__gen.writeStringField("com_address", __value.getAccount().getCompanyAddress());
		__gen.writeStringField("com_name", __value.getAccount().getCompanyName());
		__gen.writeStringField("com_phone", __value.getAccount().getCompanyTelephone());
		__gen.writeNumberField("gender", __value.getAccount().getGender());
		__gen.writeStringField("password", __value.getAccount().getPassword());
		__gen.writeEndObject();

		__gen.writeEndObject();
	}

}