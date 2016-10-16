/*
 * 
 */
package com.lasso.rest.controller.errorhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.lasso.exception.AuthenticateException;
import com.lasso.rest.model.api.response.BaseResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticateErrorHandler.
 *
 * @author Paul Mai
 */
@Provider
public class AuthenticateErrorHandler implements ExceptionMapper<AuthenticateException> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(AuthenticateErrorHandler.class);

	/**
	 * Instantiates a new authenticate error handler.
	 */
	public AuthenticateErrorHandler() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(AuthenticateException __exception) {
		AuthenticateErrorHandler.LOGGER.warn(__exception.getMessage());
		BaseResponse _errorResponse = new BaseResponse(true, __exception.getMessage());
		return Response.status(__exception.getResponse().getStatus()).entity(_errorResponse)
				.build();
	}

}
