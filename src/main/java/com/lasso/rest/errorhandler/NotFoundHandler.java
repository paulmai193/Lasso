/*
 * 
 */
package com.lasso.rest.errorhandler;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.lasso.rest.model.api.response.BaseResponse;

/**
 * The Class NotFoundHandler.
 *
 * @author Paul Mai
 */
@Provider
public class NotFoundHandler implements ExceptionMapper<NotFoundException> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(NotFoundHandler.class);

	/**
	 * Instantiates a new data not found error handler.
	 */
	public NotFoundHandler() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(NotFoundException __exception) {
		NotFoundHandler.LOGGER.warn(__exception.getMessage(), __exception);
		BaseResponse _errorResponse = new BaseResponse(true, __exception.getMessage());
		return Response.status(Status.NOT_FOUND).entity(_errorResponse).build();
	}

}
