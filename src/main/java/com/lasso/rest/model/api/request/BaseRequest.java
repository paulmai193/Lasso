/*
 * 
 */
package com.lasso.rest.model.api.request;

import com.lasso.exception.ObjectParamException;

/**
 * The Class BaseRequest.
 *
 * @author Paul Mai
 */
public abstract class BaseRequest {

	/**
	 * Instantiates a new base request.
	 */
	public BaseRequest() {
	}

	/**
	 * Validate request object.
	 *
	 * @throws ObjectParamException
	 *         the object param exception
	 */
	public abstract void validate() throws ObjectParamException;
}
