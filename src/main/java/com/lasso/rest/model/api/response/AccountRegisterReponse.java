package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(content = Include.NON_NULL)
public class AccountRegisterReponse extends BaseResponse {

	private Integer idAccount;

	public AccountRegisterReponse() {
		super(true);
	}

	public AccountRegisterReponse(boolean __error, String __message) {
		super(__error, __message);
	}

	public AccountRegisterReponse(Integer __idAccount) {
		super(false);
		this.idAccount = __idAccount;
	}

}
