package com.lasso.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.response.ListCategoryResponse;
import com.lasso.rest.model.datasource.Category;
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

	@Context
	private HttpServletRequest	request;

	@Autowired
	private ProjectManagement	projectManagement;

	private String				categoryStoragePath;

	public void setProjectManagement(ProjectManagement __projectManagement) {
		this.projectManagement = __projectManagement;
	}

	public void setCategoryStoragePath(String __categoryStoragePath) {
		this.categoryStoragePath = __categoryStoragePath;
	}

	@GET
	@Path("/category/all")
	private ListCategoryResponse getCategories(@QueryParam("page") int __page) {
		int _size = 8;
		List<Category> _categories = this.projectManagement.getCategoriesByPage(__page, _size);
		String _prefixUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
		        + this.categoryStoragePath;
		return new ListCategoryResponse(_prefixUrl, _categories, __page + _size - 1);
	}

}
