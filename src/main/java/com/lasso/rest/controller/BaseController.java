package com.lasso.rest.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.rest.model.api.response.BaseResponse;

/**
 * The Class BaseController.
 *
 * @author Paul Mai
 */
@Controller
@Lazy(false)
@Path("/")
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

	/**
	 * Index.
	 *
	 * @param __request the request
	 * @return the input stream
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public InputStream index(@Context HttpServletRequest __request) {
		return __request.getServletContext().getResourceAsStream("index.jsp");
	}

}
