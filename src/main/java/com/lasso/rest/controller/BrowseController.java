/*
 * 
 */
package com.lasso.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.define.Constant;
import com.lasso.rest.controller.filter.AccountAllow;
import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.response.CategoryResponse;
import com.lasso.rest.model.api.response.ListBannerReponse;
import com.lasso.rest.model.api.response.ListCategoriesResponse;
import com.lasso.rest.model.api.response.ListProjectsResponse;
import com.lasso.rest.model.api.response.ListSubCategoriesResponse;
import com.lasso.rest.model.api.response.ListTypesResponse;
import com.lasso.rest.model.api.response.ProjectDetailResponse;
import com.lasso.rest.model.datasource.Banner;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.service.ProjectManagement;

// TODO: Auto-generated Javadoc
/**
 * The Class BrowseController.
 *
 * @author Paul Mai
 */
@Controller
@Lazy(false)
@Path("/browse")
@Produces(value = { MediaType.APPLICATION_JSON })
public class BrowseController extends BaseController {

	/** The avatar storage path. */
	private String avatarStoragePath;

	/** The category storage path. */
	private String bannerStoragePath;

	/** The category storage path. */
	private String categoryStoragePath;

	/** The http host. */
	private String httpHost;

	/** The portfolio storage path. */
	private String portfolioStoragePath;

	/** The project management. */
	@Autowired
	private ProjectManagement projectManagement;

	/** The project storage path. */
	private String projectStoragePath;

	/** The request. */
	@Context
	private HttpServletRequest request;

	/** The style storage path. */
	private String styleStoragePath;

	/** The type storage path. */
	private String typeStoragePath;

	/** The validateContext. */
	@Context
	private SecurityContext validateContext;

	/**
	 * Gets the banners.
	 *
	 * @param __type
	 *            the type
	 * @return the banners
	 */
	@GET
	@Path("/banner")
	public ListBannerReponse getBanners(@QueryParam("type") byte __type) {
		List<Banner> _banners = this.projectManagement.getListBanner(__type);
		String _prefixUrl = this.httpHost + this.bannerStoragePath;
		return new ListBannerReponse(_banners, _prefixUrl);
	}

	/**
	 * Gets the categories.
	 *
	 * @param __index
	 *            the index
	 * @return the categories
	 */
	@GET
	@Path("/category")
	@AccountAuthenticate
	@AccountAllow(status = "" + Constant.ACC_ACTIVATE)
	public ListCategoriesResponse getCategories(@QueryParam("index") int __index) {
		List<Category> _categories = this.projectManagement.getCategoriesByIndexAndKeyword(__index, Constant.PAGE_SIZE,
				null);
		String _prefixUrl = this.httpHost + this.categoryStoragePath;
		return new ListCategoriesResponse(_prefixUrl, _categories, __index + Constant.PAGE_SIZE);
	}

	/**
	 * Gets the category detail.
	 *
	 * @param __idCategory
	 *            the id category
	 * @return the category by id
	 */
	@GET
	@Path("/category/detail")
	@AccountAuthenticate
	@AccountAllow(status = "" + Constant.ACC_ACTIVATE)
	public CategoryResponse getCategoryDetail(@QueryParam("id") int __idCategory) {
		String _prefixUrl = this.httpHost + this.categoryStoragePath;
		Category _category = this.projectManagement.getCategoryById(__idCategory);
		if (_category == null) {
			throw new NotFoundException("Category not found");
		} else {
			return new CategoryResponse(_prefixUrl, _category);
		}
	}

	/**
	 * Gets the list projects by sub category.
	 *
	 * @param __index
	 *            the index
	 * @param __idStyle
	 *            the id style
	 * @param __keyword
	 *            the keyword
	 * @return the list projects by sub category
	 */
	@GET
	@Path("/project")
	@AccountAuthenticate
	@AccountAllow(status = "" + Constant.ACC_ACTIVATE)
	public ListProjectsResponse getListProjectsBySubCategory(@QueryParam("index") int __index,
			@QueryParam("style_id") int __idStyle, @QueryParam("keyword") String __keyword) {
		String _prefixAvatarUrl = this.httpHost + this.avatarStoragePath;
		String _prefixProjectUrl = this.httpHost + this.projectStoragePath;
		return this.projectManagement.getProjectsBySubCategoryAndKeyword(__idStyle, __index, Constant.PAGE_SIZE,
				__keyword, _prefixProjectUrl, _prefixAvatarUrl);
	}

	/**
	 * Gets the sub categories.
	 *
	 * @param __index
	 *            the index
	 * @param __idCategory
	 *            the id category
	 * @param __idTypes
	 *            the id types
	 * @return the sub categories
	 */
	@GET
	@Path("/sub_category")
	@AccountAuthenticate
	@AccountAllow(status = "" + Constant.ACC_ACTIVATE)
	public ListSubCategoriesResponse getListStyles(@QueryParam("index") int __index,
			@QueryParam("category_id") int __idCategory, @QueryParam("type_id") String __idTypes) {
		List<Integer> _idTypes = new ArrayList<>();
		String[] _strings = __idTypes == null ? "".split("") : __idTypes.split(",");
		for (String _string : _strings) {
			try {
				_idTypes.add(Integer.parseInt(_string));
			} catch (Exception _ex) {
				// Swallow this exception
			}
		}
		List<Style> _styles = this.projectManagement.getSubCategoriesByIndexAndKeyword(__idCategory, _idTypes, __index,
				Constant.PAGE_SIZE, null);
		String _prefixUrl = this.httpHost + this.styleStoragePath;
		return new ListSubCategoriesResponse(_prefixUrl, _styles, __index + Constant.PAGE_SIZE);
	}

	/**
	 * Gets the list types by catogory.
	 *
	 * @param __idCategory
	 *            the id category
	 * @param __idStyle
	 *            the id style
	 * @return the list types by catogory
	 */
	@GET
	@Path("/type")
	@AccountAuthenticate
	@AccountAllow(status = "" + Constant.ACC_ACTIVATE)
	public ListTypesResponse getListTypes(@QueryParam("category_id") int __idCategory,
			@QueryParam("style_id") Integer __idStyle) {
		List<Type> _types = this.projectManagement.getListTypesByIdCategoryAndStyle(__idCategory, __idStyle);
		String _prefixTypetUrl = this.httpHost + this.typeStoragePath;
		return new ListTypesResponse(_types, _prefixTypetUrl);
	}

	/**
	 * Gets the project detail.
	 *
	 * @param __idProject
	 *            the id project
	 * @return the project detail
	 */
	@GET
	@Path("/project/detail")
	@AccountAuthenticate
	@AccountAllow(status = "" + Constant.ACC_ACTIVATE)
	public ProjectDetailResponse getProjectDetail(@QueryParam("id") int __idProject) {
		String _prefixPortforlioUrl = this.httpHost + this.portfolioStoragePath;
		String _prefixAvatarUrl = this.httpHost + this.avatarStoragePath;
		return this.projectManagement.getProjectDetailById(__idProject, _prefixPortforlioUrl, _prefixAvatarUrl);
	}

	/**
	 * Sets the avatar storage path.
	 *
	 * @param __avatarStoragePath
	 *            the new avatar storage path
	 */
	public void setAvatarStoragePath(String __avatarStoragePath) {
		this.avatarStoragePath = __avatarStoragePath;
	}

	/**
	 * Sets the banner storage path.
	 *
	 * @param __bannerStoragePath
	 *            the new banner storage path
	 */
	public void setBannerStoragePath(String __bannerStoragePath) {
		this.bannerStoragePath = __bannerStoragePath;
	}

	/**
	 * Sets the category storage path.
	 *
	 * @param __categoryStoragePath
	 *            the new category storage path
	 */
	public void setCategoryStoragePath(String __categoryStoragePath) {
		this.categoryStoragePath = __categoryStoragePath;
	}

	/**
	 * Sets the http host.
	 *
	 * @param __httpHost
	 *            the new http host
	 */
	public void setHttpHost(String __httpHost) {
		this.httpHost = __httpHost;
	}

	/**
	 * Sets the portfolio storage path.
	 *
	 * @param __portfolioStoragePath
	 *            the new portfolio storage path
	 */
	public void setPortfolioStoragePath(String __portfolioStoragePath) {
		this.portfolioStoragePath = __portfolioStoragePath;
	}

	/**
	 * Sets the project management.
	 *
	 * @param __projectManagement
	 *            the new project management
	 */
	public void setProjectManagement(ProjectManagement __projectManagement) {
		this.projectManagement = __projectManagement;
	}

	/**
	 * Sets the project storage path.
	 *
	 * @param __projectStoragePath
	 *            the new project storage path
	 */
	public void setProjectStoragePath(String __projectStoragePath) {
		this.projectStoragePath = __projectStoragePath;
	}

	/**
	 * Sets the style storage path.
	 *
	 * @param __styleStoragePath
	 *            the new style storage path
	 */
	public void setStyleStoragePath(String __styleStoragePath) {
		this.styleStoragePath = __styleStoragePath;
	}

	/**
	 * Sets the type storage path.
	 *
	 * @param __typeStoragePath
	 *            the new type storage path
	 */
	public void setTypeStoragePath(String __typeStoragePath) {
		this.typeStoragePath = __typeStoragePath;
	}
}
