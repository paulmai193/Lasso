package com.lasso.rest.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.rest.model.api.response.ListCountriesResponse;
import com.lasso.rest.service.GenericManagement;

/**
 * The Class PublicController.
 *
 * @author Paul Mai
 */
@Controller
@Lazy(false)
@Path("/public")
@Produces(MediaType.APPLICATION_JSON)
public class PublicController extends BaseController {

	/** The generic management. */
	@Autowired
	private GenericManagement genericManagement;

	/**
	 * Gets the countries.
	 *
	 * @return the countries
	 */
	@GET
	@Path("/countries")
	public ListCountriesResponse getCountries() {
		return new ListCountriesResponse(this.genericManagement.getAllCountries());
	}

	/**
	 * Sets the generic management.
	 *
	 * @param __genericManagement the new generic management
	 */
	public void setGenericManagement(GenericManagement __genericManagement) {
		this.genericManagement = __genericManagement;
	}

	/**
	 * Test reset.
	 *
	 * @param __request the request
	 * @param __otp the otp
	 * @return the response
	 * @throws URISyntaxException the URI syntax exception
	 */
	@GET
	@Path("/reset")
	public Response testReset(@Context HttpServletRequest __request,
	        @QueryParam("otp") String __otp) throws URISyntaxException {
		String _redirectSchema = "lasso://" + __request.getServerName() + ":"
		        + __request.getServerPort() + "/verfiy?type=reset&otp=" + __otp;
		return Response.seeOther(new URI(_redirectSchema)).build();
	}

	/**
	 * Test active.
	 *
	 * @param __request the request
	 * @param __otp the otp
	 * @return the response
	 * @throws URISyntaxException the URI syntax exception
	 */
	@GET
	@Path("/active")
	public Response testActive(@Context HttpServletRequest __request,
	        @QueryParam("otp") String __otp) throws URISyntaxException {
		String _redirectSchema = "lasso://" + __request.getServerName() + ":"
		        + __request.getServerPort() + "/verfiy?type=active&otp=" + __otp;
		return Response.seeOther(new URI(_redirectSchema)).build();
	}

}
