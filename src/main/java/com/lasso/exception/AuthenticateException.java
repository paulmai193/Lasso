package com.lasso.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

public class AuthenticateException extends WebApplicationException {

	private static final long serialVersionUID = 8320813886291256537L;

	public AuthenticateException(String __message, Status __status) {
		super(__message, __status);
	}

}
