package com.lasso.rest.model.api.response;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.define.Constant;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Country;

/**
 * The Class DetailDesignerResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = DesignerDetailSerializer.class)
public class DetailDesignerResponse extends BaseResponse implements DetailAccountResponse {

	/** The account. */
	private Account	account;

	/** The country. */
	private Country	country;

	/** The prefix url. */
	private String	prefixUrl;

	/**
	 * Instantiates a new detail designer response.
	 *
	 * @param __account the account
	 * @param __country the country
	 * @param __prefixUrl the prefix url
	 */
	public DetailDesignerResponse(Account __account, Country __country, String __prefixUrl) {
		super();
		this.account = __account;
		this.country = __country;
		this.prefixUrl = __prefixUrl;
	}

	/**
	 * Instantiates a new designer detail response.
	 *
	 * @param __error the error
	 */
	public DetailDesignerResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new designer detail response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public DetailDesignerResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new designer detail response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public DetailDesignerResponse(boolean __error, String __message, String __detail) {
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
	 * @return the prefix url
	 */
	public String getPrefixUrl() {
		return this.prefixUrl;
	}

}

class DesignerDetailSerializer extends JsonSerializer<DetailDesignerResponse> {

	@Override
	public void serialize(DetailDesignerResponse __value, JsonGenerator __gen,
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
			__gen.writeStringField("original", "");
			__gen.writeStringField("small", "");
			__gen.writeStringField("icon", "");
			__gen.writeStringField("retina", "");
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

		__gen.writeStringField("info", __value.getAccount().getAccountInfo() == null ? ""
		        : __value.getAccount().getAccountInfo());
		__gen.writeStringField("alt_contact", __value.getAccount().getAlternativeContact());
		__gen.writeNumberField("payment", __value.getAccount().getPaymentMethod());
		__gen.writeNumberField("gender", __value.getAccount().getGender());
		__gen.writeEndObject();

		__gen.writeEndObject();
	}

}