/*
 * 
 */
package com.lasso.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticateException.
 *
 * @author Paul Mai
 */
public class AuthenticateException extends WebApplicationException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8320813886291256537L;

	/**
	 * Instantiates a new authenticate exception.
	 *
	 * @param __message
	 *            the message
	 * @param __status
	 *            the status
	 */
	public AuthenticateException(String __message, Status __status) {
		super(__message, __status);
	}

}
