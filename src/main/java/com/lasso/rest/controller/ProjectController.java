package com.lasso.rest.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.service.ProjectManagement;

@Controller
@Lazy(false)
@Path("/project")
@Produces(value = { MediaType.APPLICATION_JSON })
@AccountAuthenticate
public class ProjectController extends BaseController {

	/** The validateContext. */
	@Context
	private SecurityContext		validateContext;

	@Autowired
	private ProjectManagement	projectManagement;

	public void setProjectManagement(ProjectManagement __projectManagement) {
		this.projectManagement = __projectManagement;
	}

	@GET
	@Path("/category/all")
	private Response getCategories() {
		return this.success();
	}

}
