package com.lasso.rest.model.api.response;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	/** The created. */
	private Date	created;

	/** The expired. */
	private Date	expired;

	/** The force change password. */
	@JsonProperty("force_change_password")
	private boolean	forceChangePassword;

	/** The id account. */
	@JsonProperty("id")
	private Integer	idAccount;

	/** The token. */
	@JsonProperty("token")
	private String	token;

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
		this.setCreated();
		this.setExpired();
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	@JsonProperty("created")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z")
	public Date getCreated() {
		return this.created;
	}

	/**
	 * Gets the expired.
	 *
	 * @return the expired
	 */
	@JsonProperty("expired")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z")
	public Date getExpired() {
		return this.expired;
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
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * Checks if is force change password.
	 *
	 * @return true, if is force change password
	 */
	public boolean isForceChangePassword() {
		return this.forceChangePassword;
	}

	/**
	 * Sets the created.
	 */
	public void setCreated() {
		this.setCreated(new Date());
	}

	/**
	 * Sets the created.
	 *
	 * @param __created the new created
	 */
	public void setCreated(Date __created) {
		this.created = __created;
	}

	/**
	 * Sets the expired.
	 */
	public void setExpired() {
		Calendar _calendar = Calendar.getInstance();
		_calendar.setTime(new Date());
		_calendar.add(Calendar.DATE, 1);
		this.setExpired(_calendar.getTime());
	}

	/**
	 * Sets the expired.
	 *
	 * @param __expired the new expired
	 */
	public void setExpired(Date __expired) {
		this.expired = __expired;
	}

	/**
	 * Sets the force change password.
	 *
	 * @param __forceChangePassword the new force change password
	 */
	public void setForceChangePassword(boolean __forceChangePassword) {
		this.forceChangePassword = __forceChangePassword;
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
	 * Sets the token.
	 *
	 * @param __token the new token
	 */
	public void setToken(String __token) {
		this.token = __token;
	}

}
