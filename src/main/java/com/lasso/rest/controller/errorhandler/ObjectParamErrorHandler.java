/*
 * 
 */
package com.lasso.rest.controller.errorhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.lasso.exception.ObjectParamException;
import com.lasso.rest.model.api.response.BaseResponse;

/**
 * The Class ObjectParamErrorHandler.
 *
 * @author Paul Mai
 */
@Provider
public class ObjectParamErrorHandler implements ExceptionMapper<ObjectParamException> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ObjectParamErrorHandler.class);

	/**
	 * Instantiates a new object param parser error handler.
	 */
	public ObjectParamErrorHandler() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(ObjectParamException __exception) {
		ObjectParamErrorHandler.LOGGER.warn(__exception.getMessage(), __exception);
		BaseResponse _errorResponse = new BaseResponse(true, __exception.getMessage(),
				__exception.getCause() == null ? null : __exception.getCause().getMessage());
		return Response.status(__exception.getResponse().getStatus()).entity(_errorResponse)
				.build();
	}

}
