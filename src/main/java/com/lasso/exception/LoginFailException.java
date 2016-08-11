package com.lasso.exception;

import javax.ws.rs.core.Response.Status;

public class LoginFailException extends AuthenticateException {

	private static final long serialVersionUID = 195366392720070809L;

	public LoginFailException() {
		super("Email or password not correct", Status.FORBIDDEN);
	}

}
