/*
 * 
 */
package com.lasso.rest.controller.errorhandler;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.lasso.rest.model.api.response.BaseResponse;

/**
 * The Class NotAllowErrorHandler.
 *
 * @author Paul Mai
 */
@Provider
public class NotAllowErrorHandler implements ExceptionMapper<NotAllowedException> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(NotAllowErrorHandler.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(NotAllowedException __exception) {
		NotAllowErrorHandler.LOGGER.warn(__exception.getMessage(), __exception);
		BaseResponse _errorResponse = new BaseResponse(true, __exception.getMessage(),
				__exception.getCause() == null ? null : __exception.getCause().getMessage());
		return Response.status(__exception.getResponse().getStatus()).entity(_errorResponse)
				.build();
	}

}
