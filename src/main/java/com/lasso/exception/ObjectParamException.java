/*
 * 
 */
package com.lasso.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

// TODO: Auto-generated Javadoc
/**
 * The Class ObjectParamException.
 *
 * @author Paul Mai
 */
public class ObjectParamException extends WebApplicationException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 899411299322975919L;

	/**
	 * Instantiates a new object param exception.
	 */
	public ObjectParamException() {
		super(Status.BAD_REQUEST);
	}

	/**
	 * Instantiates a new object param exception.
	 *
	 * @param __message
	 *        the message
	 */
	public ObjectParamException(String __message) {
		super(__message, Status.BAD_REQUEST);
	}

	/**
	 * Instantiates a new object param exception.
	 *
	 * @param __message
	 *        the message
	 * @param __cause
	 *        the cause
	 */
	public ObjectParamException(String __message, Throwable __cause) {
		super(__message, __cause, Status.BAD_REQUEST);
	}

	/**
	 * Instantiates a new object param exception.
	 *
	 * @param __cause
	 *        the cause
	 */
	public ObjectParamException(Throwable __cause) {
		super(__cause, Status.BAD_REQUEST);
	}

}
