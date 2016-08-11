package com.lasso.rest.controller.errorhandler;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.lasso.rest.model.api.response.BaseResponse;

/**
 * The Class NotAuthorizedHandler.
 *
 * @author Paul Mai
 */
@Provider
public class NotAuthorizedHandler implements ExceptionMapper<NotAuthorizedException> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(NotAuthorizedHandler.class);

	/**
	 * Instantiates a new not authorized handler.
	 */
	public NotAuthorizedHandler() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(NotAuthorizedException __exception) {
		NotAuthorizedHandler.LOGGER.warn(__exception.getMessage());
		BaseResponse _errorResponse = new BaseResponse(true, __exception.getMessage());
		return Response.status(__exception.getResponse().getStatus()).entity(_errorResponse)
				.build();
	}

}
