package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.define.Constant;

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

	/** The name. */
	@JsonProperty("name")
	private String	name;

	/** The role. */
	private byte	role;

	/** The account status. */
	private byte	status;

	/** The token. */
	@JsonProperty("token")
	private String	token;

	/**
	 * Instantiates a new login response.
	 *
	 * @param __idAccount the id account
	 * @param __name the name
	 * @param __token the token
	 * @param __status the status
	 * @param __role the value
	 */
	public LoginResponse(Integer __idAccount, String __name, String __token, byte __status,
	        byte __role) {
		super();
		this.idAccount = __idAccount;
		this.name = __name;
		this.token = __token;
		this.status = __status;
		this.role = __role;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public byte getRole() {
		return this.role;
	}

	/**
	 * Gets the value name.
	 *
	 * @return the value name
	 */
	@JsonProperty("role")
	public String getRoleName() {
		switch (this.role) {
			case Constant.ROLE_DESIGNER:
				return "designer";

			default:
				return "user";
		}
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public byte getStatus() {
		return this.status;
	}

	/**
	 * Gets the status name.
	 *
	 * @return the status name
	 */
	@JsonProperty("status")
	public String getStatusName() {
		switch (this.status) {
			case Constant.ACC_NOT_ACTIVATE:
				return "in_activate";

			default:
				return "activate";
		}
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
	 * Sets the id account.
	 *
	 * @param __idAccount the new id account
	 */
	public void setIdAccount(Integer __idAccount) {
		this.idAccount = __idAccount;
	}

	/**
	 * Sets the name.
	 *
	 * @param __name the new name
	 */
	public void setName(String __name) {
		this.name = __name;
	}

	/**
	 * Sets the value.
	 *
	 * @param __role the new value
	 */
	public void setRole(byte __role) {
		this.role = __role;
	}

	/**
	 * Sets the status.
	 *
	 * @param __status the new status
	 */
	public void setStatus(byte __status) {
		this.status = __status;
	}

	/**
	 * Sets the token.
	 *
	 * @param __token the new token
	 */
	public void setToken(String __token) {
		this.token = __token;
	}

}
