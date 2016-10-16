/*
 * 
 */
package com.lasso.rest.controller.errorhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.lasso.exception.ResourceExistException;
import com.lasso.rest.model.api.response.BaseResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class ResourceExistHandler.
 *
 * @author Paul Mai
 */
@Provider
public class ResourceExistHandler implements ExceptionMapper<ResourceExistException> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ResourceExistHandler.class);

	/**
	 * Instantiates a new resource exist error handler.
	 */
	public ResourceExistHandler() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(ResourceExistException __exception) {
		ResourceExistHandler.LOGGER.warn(__exception.getMessage(), __exception);
		BaseResponse _errorResponse = new BaseResponse(true, __exception.getMessage());
		return Response.status(Status.CONFLICT).entity(_errorResponse).build();
	}

}
