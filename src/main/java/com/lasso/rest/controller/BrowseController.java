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
import com.lasso.rest.model.api.response.CategoryResponse;
import com.lasso.rest.model.api.response.ListCategoriesResponse;
import com.lasso.rest.model.api.response.ListSubCatoriesResponse;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.service.ProjectManagement;

/**
 * The Class ProjectController.
 *
 * @author Paul Mai
 */
@Controller
@Lazy(false)
@Path("/project")
@Produces(value = { MediaType.APPLICATION_JSON })
@AccountAuthenticate
public class ProjectController extends BaseController {

	/** The category storage path. */
	private String				categoryStoragePath;

	/** The project management. */
	@Autowired
	private ProjectManagement	projectManagement;

	/** The request. */
	@Context
	private HttpServletRequest	request;

	/** The validateContext. */
	@Context
	private SecurityContext		validateContext;

	/**
	 * Gets the categories.
	 *
	 * @param __index the index
	 * @return the categories
	 */
	@GET
	@Path("/category/all")
	public ListCategoriesResponse getCategories(@QueryParam("index") int __index) {
		int _size = 8;
		List<Category> _categories = this.projectManagement.getCategoriesByIndex(__index, _size);
		String _prefixUrl = "http://" + this.request.getServerName() + ":"
		        + this.request.getServerPort() + this.categoryStoragePath;
		return new ListCategoriesResponse(_prefixUrl, _categories, __index + _size);
	}

	/**
	 * Gets the category by id.
	 *
	 * @param __idCategory the id category
	 * @return the category by id
	 */
	@GET
	@Path("/category")
	public CategoryResponse getCategoryById(@QueryParam("id") int __idCategory) {
		String _prefixUrl = "http://" + this.request.getServerName() + ":"
		        + this.request.getServerPort() + this.categoryStoragePath;
		return new CategoryResponse(_prefixUrl,
		        this.projectManagement.getCategoryById(__idCategory));
	}

	/**
	 * Gets the sub categories.
	 *
	 * @param __index the index
	 * @param __idCategory the id category
	 * @return the sub categories
	 */
	@GET
	@Path("/sub_category")
	public ListSubCatoriesResponse getSubCategories(@QueryParam("index") int __index,
	        @QueryParam("category_id") int __idCategory) {
		int _size = 8;
		List<Style> _styles = this.projectManagement.getSubCategoriesByIndex(__idCategory, __index,
		        _size);
		String _prefixUrl = "http://" + this.request.getServerName() + ":"
		        + this.request.getServerPort() + this.categoryStoragePath;
		return new ListSubCatoriesResponse(_prefixUrl, _styles, __index + _size);
	}

	/**
	 * Sets the category storage path.
	 *
	 * @param __categoryStoragePath the new category storage path
	 */
	public void setCategoryStoragePath(String __categoryStoragePath) {
		this.categoryStoragePath = __categoryStoragePath;
	}

	/**
	 * Sets the project management.
	 *
	 * @param __projectManagement the new project management
	 */
	public void setProjectManagement(ProjectManagement __projectManagement) {
		this.projectManagement = __projectManagement;
	}
}
