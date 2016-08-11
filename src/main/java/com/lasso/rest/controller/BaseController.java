package com.lasso.rest.controller;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lasso.rest.model.api.response.BaseResponse;

/**
 * The Class BaseController.
 *
 * @author Paul Mai
 */
public class BaseController {

	/**
	 * Instantiates a new base controller.
	 */
	public BaseController() {
	}

	/**
	 * Fail.
	 *
	 * @param __response the response
	 * @param __status the status
	 * @return the response
	 */
	public Response fail(BaseResponse __response, Status __status) {
		return Response.status(__status).entity(__response).build();
	}

	/**
	 * Success.
	 *
	 * @return the response
	 */
	public Response success() {
		return this.success(new BaseResponse());
	}

	/**
	 * Success.
	 *
	 * @param __response the response
	 * @return the response
	 */
	public Response success(BaseResponse __response) {
		return Response.ok().entity(__response).build();
	}

}
