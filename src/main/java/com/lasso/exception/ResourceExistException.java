/*
 * 
 */
package com.lasso.exception;

/**
 * The Class ResourceExistException.
 *
 * @author Paul Mai
 */
public class ResourceExistException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6534731105219767339L;

	/**
	 * Instantiates a new resource exist exception.
	 */
	public ResourceExistException() {
		this("Resource was exist");
	}

	/**
	 * Instantiates a new resource exist exception.
	 *
	 * @param __message the message
	 */
	public ResourceExistException(String __message) {
		super(__message);
	}

}
