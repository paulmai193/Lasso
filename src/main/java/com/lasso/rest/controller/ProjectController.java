package com.lasso.rest.controller;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

// @Controller
// @Lazy(false)
// @Path("/project")
// @Produces(value = { MediaType.APPLICATION_JSON })
// @AccountAuthenticate
public class ProjectController extends BaseController {

	/** The validateContext. */
	@Context
	private SecurityContext validateContext;

}
