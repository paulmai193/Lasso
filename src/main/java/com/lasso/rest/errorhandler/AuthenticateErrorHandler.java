package com.lasso.rest.errorhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.lasso.exception.AuthenticateException;
import com.lasso.rest.model.api.response.BaseResponse;

@Provider
public class AuthenticateErrorHandler implements ExceptionMapper<AuthenticateException> {

	private static final Logger LOGGER = Logger.getLogger(AuthenticateErrorHandler.class);

	public AuthenticateErrorHandler() {
	}

	@Override
	public Response toResponse(AuthenticateException __exception) {
		LOGGER.warn(__exception.getMessage());
		BaseResponse _errorResponse = new BaseResponse(true, __exception.getMessage());
		return Response.status(__exception.getResponse().getStatus()).entity(_errorResponse)
		        .build();
	}

}
