/*
 * 
 */
package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class AccountRegisterReponse.
 *
 * @author Paul Mai
 */
@JsonInclude(content = Include.NON_NULL)
public class AccountRegisterReponse extends BaseResponse {

	/** The id account. */
	private Integer idAccount;

	/**
	 * Instantiates a new account register reponse.
	 */
	public AccountRegisterReponse() {
		super(true);
	}

	/**
	 * Instantiates a new account register reponse.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public AccountRegisterReponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new account register reponse.
	 *
	 * @param __idAccount the id account
	 */
	public AccountRegisterReponse(Integer __idAccount) {
		super(false);
		this.idAccount = __idAccount;
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

}
