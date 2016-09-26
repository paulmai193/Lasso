package com.lasso.rest.service;

import java.util.List;

import com.lasso.rest.model.api.response.ListProjectsResponse;
import com.lasso.rest.model.api.response.ProjectDetailResponse;
import com.lasso.rest.model.datasource.Banner;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;

/**
 * The Interface ProjectManagement.
 *
 * @author Paul Mai
 */
public interface ProjectManagement {

	/**
	 * Removes the temporary files which were older than 2 days.
	 *
	 * @param __webContextStoragePath the web context storage path
	 */
	public void removeOldTemporaryFiles(String __webContextStoragePath);

	/**
	 * Gets the categories by index and keyword.
	 *
	 * @param __index the index
	 * @param __size the size
	 * @param __keyword the keyword
	 * @return the categories start from index
	 */
	List<Category> getCategoriesByIndexAndKeyword(int __index, int __size, String __keyword);

	/**
	 * Gets the category by id.
	 *
	 * @param __idCategory the id category
	 * @return the category by id
	 */
	Category getCategoryById(int __idCategory);

	/**
	 * Gets the job by id.
	 *
	 * @param __idJob the id job
	 * @return the job by id
	 */
	Job getJobById(int __idJob);

	/**
	 * Gets the list banner.
	 *
	 * @return the list banner
	 */
	List<Banner> getListBanner();

	/**
	 * Gets the list types by id category and style.
	 *
	 * @param __idCategory the id category
	 * @param __idStyle the id style
	 * @return the list types by id category and style
	 */
	List<Type> getListTypesByIdCategoryAndStyle(int __idCategory, Integer __idStyle);

	/**
	 * Gets the list types by id portfolio.
	 *
	 * @param __idPortfolio the id portfolio
	 * @return the list types by id portfolio
	 */
	List<Type> getListTypesByIdPortfolio(int __idPortfolio);

	/**
	 * Gets the project detail by id.
	 *
	 * @param __idProject the id project
	 * @param __prefixPortfolioUrl the prefix portfolio url
	 * @param __prefixAvatarUrl the prefix avatar url
	 * @return the project by id
	 */
	ProjectDetailResponse getProjectDetailById(int __idProject, String __prefixPortfolioUrl,
			String __prefixAvatarUrl);

	/**
	 * Gets the projects by sub category and keyword.
	 *
	 * @param __idStyle the id style
	 * @param __index the index
	 * @param __size the size
	 * @param __keyword the keyword
	 * @param __prefixProjectUrl the prefix project url
	 * @param __prefixAvatarUrl the prefix avatar url
	 * @return the projects by sub category and keyword
	 */
	ListProjectsResponse getProjectsBySubCategoryAndKeyword(int __idStyle, int __index, int __size,
			String __keyword, String __prefixProjectUrl, String __prefixAvatarUrl);

	/**
	 * Gets the style by id.
	 *
	 * @param __styleId the style id
	 * @return the style by id
	 */
	Style getStyleById(int __styleId);

	/**
	 * Gets the sub categories by index and keyword.
	 *
	 * @param __idCategory the id category
	 * @param __idType the id type
	 * @param __size the size
	 * @param __index the index
	 * @param __keyword the keyword
	 * @return the sub categories start from index
	 */
	List<Style> getSubCategoriesByIndexAndKeyword(int __idCategory, Integer __idType, int __size,
			int __index, String __keyword);
}
