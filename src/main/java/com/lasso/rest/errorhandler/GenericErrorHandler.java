package com.lasso.rest.errorhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.lasso.rest.model.api.response.BaseResponse;

@Provider
public class GenericErrorHandler implements ExceptionMapper<Exception> {

	private static final Logger LOGGER = Logger.getLogger(GenericErrorHandler.class);

	public GenericErrorHandler() {
	}

	@Override
	public Response toResponse(Exception __exception) {
		GenericErrorHandler.LOGGER.error(__exception.getMessage(), __exception);
		BaseResponse _errorResponse = new BaseResponse(true, __exception.getMessage(),
		        __exception.getCause() == null ? null : __exception.getCause().getMessage());
		return Response.serverError().entity(_errorResponse).build();
	}

}
