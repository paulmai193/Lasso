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
	 * Check all fields of this instance not null.
	 *
	 * @throws ObjectParamException the object param exception
	 */
	public abstract void checkNotNull() throws ObjectParamException;
}
