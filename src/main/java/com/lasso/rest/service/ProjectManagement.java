package com.lasso.rest.service;

import java.util.List;

import com.lasso.rest.model.api.response.ListProjectsResponse;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Project;
import com.lasso.rest.model.datasource.Style;

/**
 * The Interface ProjectManagement.
 *
 * @author Paul Mai
 */
public interface ProjectManagement {

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
	 * Gets the project detail by id.
	 *
	 * @param __idProject the id project
	 * @return the project by id
	 */
	Project getProjectDetailById(int __idProject);

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
	 * Gets the sub categories by index and keyword.
	 *
	 * @param __idCategory the id category
	 * @param __index the index
	 * @param __size the size
	 * @param __keyword the keyword
	 * @return the sub categories start from index
	 */
	List<Style> getSubCategoriesByIndexAndKeyword(int __idCategory, int __index, int __size,
	        String __keyword);

}
