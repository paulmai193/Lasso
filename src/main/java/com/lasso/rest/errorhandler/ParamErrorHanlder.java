/*
 * 
 */
package com.lasso.rest.errorhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ParamException;

import com.lasso.rest.model.api.response.BaseResponse;

/**
 * The Class ParamErrorHanlder.
 *
 * @author Paul Mai
 */
@Provider
public class ParamErrorHanlder implements ExceptionMapper<ParamException> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ParamErrorHanlder.class);

	/**
	 * Instantiates a new param error hanlder.
	 */
	public ParamErrorHanlder() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(ParamException __exception) {
		ParamErrorHanlder.LOGGER.warn(__exception.getMessage(), __exception);
		BaseResponse _errorResponse = new BaseResponse(true, "Bad request",
		        __exception.getCause().getMessage());
		return Response.status(__exception.getResponse().getStatus()).entity(_errorResponse)
		        .build();
	}

}
