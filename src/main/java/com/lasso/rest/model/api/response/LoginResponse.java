/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class LoginResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = LoginSerializer.class)
public class LoginResponse extends BaseResponse {

	/** The account. */
	private Account account;

	/** The prefix url. */
	private String prefixUrl;

	/** The token. */
	private String token;

	/**
	 * Instantiates a new login response.
	 *
	 * @param __token
	 *            the token
	 * @param __account
	 *            the account
	 * @param __prefixUrl
	 *            the prefix url
	 */
	public LoginResponse(String __token, Account __account, String __prefixUrl) {
		super();
		this.token = __token;
		this.account = __account;
		this.prefixUrl = __prefixUrl;
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

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * Sets the account.
	 *
	 * @param __account
	 *            the account to set
	 */
	public void setAccount(Account __account) {
		this.account = __account;
	}

	/**
	 * Sets the prefix url.
	 *
	 * @param __prefixUrl
	 *            the prefixUrl to set
	 */
	public void setPrefixUrl(String __prefixUrl) {
		this.prefixUrl = __prefixUrl;
	}

	/**
	 * Sets the token.
	 *
	 * @param __token
	 *            the token to set
	 */
	public void setToken(String __token) {
		this.token = __token;
	}

}

class LoginSerializer extends JsonSerializer<LoginResponse> {

	@Override
	public void serialize(LoginResponse __value, JsonGenerator __gen, SerializerProvider __serializers)
			throws IOException, JsonProcessingException {
		__gen.writeStartObject();

		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeStringField("role", __value.getAccount().getRole() == Constant.ROLE_DESIGNER ? "designer" : "user");
		__gen.writeStringField("status",
				__value.getAccount().getStatus() == Constant.ACC_NOT_ACTIVATE ? "in_activate" : "activate");
		__gen.writeNumberField("id", __value.getAccount().getId());
		__gen.writeStringField("name", __value.getAccount().getName());
		__gen.writeNumberField("reward",
				__value.getAccount().getRewards() == 0 ? 1 : __value.getAccount().getRewards());
		__gen.writeObjectFieldStart("avatar");
		if (__value.getAccount().getImage() == null || __value.getAccount().getImage().isEmpty()) {
			__gen.writeStringField("original", "");
			__gen.writeStringField("small", "");
			__gen.writeStringField("icon", "");
			__gen.writeStringField("retina", "");
		} else {
			__gen.writeStringField("original", __value.getPrefixUrl() + "/Original/" + __value.getAccount().getImage());
			__gen.writeStringField("small", __value.getPrefixUrl() + "/Small/" + __value.getAccount().getImage());
			__gen.writeStringField("icon", __value.getPrefixUrl() + "/Icon/" + __value.getAccount().getImage());
			__gen.writeStringField("retina", __value.getPrefixUrl() + "/Retina/" + __value.getAccount().getImage());
		}
		__gen.writeEndObject();
		__gen.writeStringField("token", __value.getToken());

		__gen.writeEndObject();
	}

}
