package com.lasso.rest.controller;

import javax.ws.rs.core.Response;

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
