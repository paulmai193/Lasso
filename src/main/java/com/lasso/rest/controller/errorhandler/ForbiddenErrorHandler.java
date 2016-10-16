/*
 * 
 */
package com.lasso.rest.controller.errorhandler;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.lasso.rest.model.api.response.BaseResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class ForbiddenErrorHandler.
 *
 * @author Paul Mai
 */
@Provider
public class ForbiddenErrorHandler implements ExceptionMapper<ForbiddenException> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ForbiddenErrorHandler.class);

	/**
	 * Instantiates a new forbidden error handler.
	 */
	public ForbiddenErrorHandler() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(ForbiddenException __exception) {
		ForbiddenErrorHandler.LOGGER.warn(__exception.getMessage(), __exception);
		BaseResponse _errorResponse = new BaseResponse(true, __exception.getMessage(),
				__exception.getCause() == null ? null : __exception.getCause().getMessage());
		return Response.status(__exception.getResponse().getStatus()).entity(_errorResponse)
				.build();
	}

}
