package com.lasso.exception;

import javax.ws.rs.core.Response.Status;

public class InActivateException extends AuthenticateException {

	private static final long serialVersionUID = 4036779365347254512L;

	public InActivateException() {
		super("Account not activated", Status.FORBIDDEN);
	}

}
