package com.lasso.rest.controller.errorhandler;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.lasso.rest.model.api.response.BaseResponse;

/**
 * The Class BadRequestErrorHandler.
 *
 * @author Paul Mai
 */
@Provider
public class BadRequestErrorHandler implements ExceptionMapper<BadRequestException> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(BadRequestErrorHandler.class);

	/**
	 * Instantiates a new bad request error handler.
	 */
	public BadRequestErrorHandler() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(BadRequestException __exception) {
		BadRequestErrorHandler.LOGGER.warn(__exception.getMessage(), __exception);
		BaseResponse _errorResponse = new BaseResponse(true, __exception.getMessage(),
				__exception.getCause() == null ? null : __exception.getCause().getMessage());
		return Response.status(__exception.getResponse().getStatus()).entity(_errorResponse)
				.build();
	}

}
