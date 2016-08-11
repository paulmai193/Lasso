package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class LoginResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class LoginResponse extends BaseResponse {

	/** The id account. */
	@JsonProperty("id")
	private Integer	idAccount;

	/** The token. */
	@JsonProperty("token")
	private String	token;

	/** The force change password. */
	@JsonProperty("force_change_password")
	private boolean	forceChangePassword;

	/**
	 * Instantiates a new login response.
	 *
	 * @param __idAccount the id account
	 * @param __token the token
	 */
	public LoginResponse(Integer __idAccount, String __token) {
		this(__idAccount, __token, false);
	}

	/**
	 * Instantiates a new login response.
	 *
	 * @param __idAccount the id account
	 * @param __token the token
	 * @param __forceChangePassword the force change password
	 */
	public LoginResponse(Integer __idAccount, String __token, boolean __forceChangePassword) {
		super();
		this.idAccount = __idAccount;
		this.token = __token;
		this.forceChangePassword = __forceChangePassword;
	}

	/**
	 * Gets the id account.
	 *
	 * @return the id account
	 */
	public Integer getIdAccount() {
		return this.idAccount;
	}

	/**
	 * Sets the id account.
	 *
	 * @param __idAccount the new id account
	 */
	public void setIdAccount(Integer __idAccount) {
		this.idAccount = __idAccount;
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
	 * Sets the token.
	 *
	 * @param __token the new token
	 */
	public void setToken(String __token) {
		this.token = __token;
	}

	public boolean isForceChangePassword() {
		return this.forceChangePassword;
	}

	public void setForceChangePassword(boolean __forceChangePassword) {
		this.forceChangePassword = __forceChangePassword;
	}

}
