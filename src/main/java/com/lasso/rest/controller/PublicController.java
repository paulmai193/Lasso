package com.lasso.rest.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.rest.model.api.response.ListCountriesResponse;
import com.lasso.rest.service.GenericManagement;

@Controller
@Lazy(false)
@Path("/public")
@Produces(MediaType.APPLICATION_JSON)
public class PublicController extends BaseController {

	@Autowired
	private GenericManagement genericManagement;

	public void setGenericManagement(GenericManagement __genericManagement) {
		this.genericManagement = __genericManagement;
	}

	@GET
	@Path("/countries")
	public ListCountriesResponse getCountries() {
		return new ListCountriesResponse(genericManagement.getAllCountries());
	}

}
