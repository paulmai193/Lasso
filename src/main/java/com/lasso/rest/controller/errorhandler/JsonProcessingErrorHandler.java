package com.lasso.rest.controller.errorhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lasso.rest.model.api.response.BaseResponse;

/**
 * The Class JsonProcessingErrorHandler.
 *
 * @author Paul Mai
 */
@Provider
public class JsonProcessingErrorHandler implements ExceptionMapper<JsonProcessingException> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(JsonProcessingErrorHandler.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(JsonProcessingException __exception) {
		LOGGER.warn(__exception.getMessage(), __exception);
		BaseResponse _errorResponse = new BaseResponse(true, __exception.getMessage(),
		        __exception.getCause() == null ? null : __exception.getCause().getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(_errorResponse).build();
	}

}
