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
import com.lasso.rest.model.api.response.ListProjectsResponse;
import com.lasso.rest.model.api.response.ListSubCatoriesResponse;
import com.lasso.rest.model.api.response.ProjectResponse;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Project;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.service.ProjectManagement;

/**
 * The Class BrowseController.
 *
 * @author Paul Mai
 */
@Controller
@Lazy(false)
@Path("/browse")
@Produces(value = { MediaType.APPLICATION_JSON })
@AccountAuthenticate
public class BrowseController extends BaseController {

	/** The category storage path. */
	private String				categoryStoragePath;

	/** The project management. */
	@Autowired
	private ProjectManagement	projectManagement;

	/** The project storage path. */
	private String				projectStoragePath;

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
	 * @param __keyword the keyword
	 * @return the categories
	 */
	@GET
	@Path("/category")
	public ListCategoriesResponse getCategories(@QueryParam("index") int __index,
	        @QueryParam("keyword") String __keyword) {
		int _size = 8;
		List<Category> _categories = this.projectManagement.getCategoriesByIndexAndKeyword(__index,
		        _size, __keyword);
		String _prefixUrl = "http://" + this.request.getServerName() + ":"
		        + this.request.getServerPort() + this.categoryStoragePath;
		return new ListCategoriesResponse(_prefixUrl, _categories, __index + _size);
	}

	/**
	 * Gets the category detail.
	 *
	 * @param __idCategory the id category
	 * @return the category by id
	 */
	@GET
	@Path("/category/detail")
	public CategoryResponse getCategoryDetail(@QueryParam("id") int __idCategory) {
		String _prefixUrl = "http://" + this.request.getServerName() + ":"
		        + this.request.getServerPort() + this.categoryStoragePath;
		return new CategoryResponse(_prefixUrl,
		        this.projectManagement.getCategoryById(__idCategory));
	}

	/**
	 * Gets the list projects by sub category.
	 *
	 * @param __index the index
	 * @param __idCategory the id category
	 * @param __idStyle the id style
	 * @param __keyword the keyword
	 * @return the list projects by sub category
	 */
	@GET
	@Path("/project")
	public ListProjectsResponse getListProjectsBySubCategory(@QueryParam("index") int __index,
	        @QueryParam("category_id") int __idCategory, @QueryParam("style_id") int __idStyle,
	        @QueryParam("keyword") String __keyword) {
		int _size = 8;
		List<Project> _projects = this.projectManagement.getProjectsBySubCategoryAndKeyword(
		        __idCategory, __idStyle, __index, _size, __keyword);
		String _prefixUrl = "http://" + this.request.getServerName() + ":"
		        + this.request.getServerPort() + this.projectStoragePath;
		return new ListProjectsResponse(_projects, __index + _size, _prefixUrl);
	}

	/**
	 * Gets the project detail.
	 *
	 * @param __idProject the id project
	 * @return the project detail
	 */
	@GET
	@Path("/project/detail")
	public ProjectResponse getProjectDetail(@QueryParam("id") int __idProject) {
		Project _project = this.projectManagement.getProjectDetailById(__idProject);
		String _prefixUrl = "http://" + this.request.getServerName() + ":"
		        + this.request.getServerPort() + this.projectStoragePath;
		return new ProjectResponse(_prefixUrl, _project);
	}

	/**
	 * Gets the sub categories.
	 *
	 * @param __index the index
	 * @param __idCategory the id category
	 * @param __keyword the keyword
	 * @return the sub categories
	 */
	@GET
	@Path("/sub_category")
	public ListSubCatoriesResponse getSubCategories(@QueryParam("index") int __index,
	        @QueryParam("category_id") int __idCategory, @QueryParam("keyword") String __keyword) {
		int _size = 8;
		List<Style> _styles = this.projectManagement.getSubCategoriesByIndexAndKeyword(__idCategory,
		        __index, _size, __keyword);
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

	/**
	 * Sets the project storage path.
	 *
	 * @param __projectStoragePath the new project storage path
	 */
	public void setProjectStoragePath(String __projectStoragePath) {
		this.projectStoragePath = __projectStoragePath;
	}
}
