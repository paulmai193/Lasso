package com.lasso.rest.model.api.response;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.rest.model.datasource.Account;

/**
 * The Class DetailDesignerResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class DetailDesignerResponse extends BaseResponse implements DetailAccountResponse {

	/** The account. */
	@JsonProperty("data")
	@JsonSerialize(using = DesignerDetailSerializer.class)
	private Account account;

	/**
	 * Instantiates a new designer detail response.
	 *
	 * @param __account the account
	 */
	public DetailDesignerResponse(Account __account) {
		super();
		this.account = __account;
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

}

class DesignerDetailSerializer extends JsonSerializer<Account> {

	@Override
	public void serialize(Account __value, JsonGenerator __gen, SerializerProvider __serializers)
			throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeStringField("name", __value.getName());
		__gen.writeStringField("email", __value.getEmail());
		__gen.writeStringField("phone", __value.getHandphoneNumber());
		__gen.writeStringField("avatar", __value.getImage());
		__gen.writeStringField("country", __value.getCountry().getName());
		__gen.writeStringField("info", __value.getAccountInfo());
		__gen.writeStringField("alt_contact", __value.getAlternativeContact());
		__gen.writeNumberField("payment", __value.getPaymentMethod());
		__gen.writeEndObject();
	}

}