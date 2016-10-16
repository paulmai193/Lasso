/*
 * 
 */
package com.lasso.rest.controller.errorhandler;

import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.lasso.rest.model.api.response.BaseResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class NotSupportErrorHandler.
 *
 * @author Paul Mai
 */
@Provider
public class NotSupportErrorHandler implements ExceptionMapper<NotSupportedException> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(NotSupportErrorHandler.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(NotSupportedException __exception) {
		NotSupportErrorHandler.LOGGER.warn(__exception.getMessage(), __exception);
		BaseResponse _errorResponse = new BaseResponse(true, __exception.getMessage(),
				__exception.getCause() == null ? null : __exception.getCause().getMessage());
		return Response.status(__exception.getResponse().getStatus()).entity(_errorResponse).build();
	}

}
