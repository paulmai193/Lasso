package com.lasso.rest.service;

import java.util.List;

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
	 * Gets the project by id.
	 *
	 * @param __idProject the id project
	 * @return the project by id
	 */
	Project getProjectById(int __idProject);

	/**
	 * Gets the projects by sub category and keyword.
	 *
	 * @param __idCategory the id category
	 * @param __idStyle the id style
	 * @param __index the index
	 * @param __size the size
	 * @param __keyword the keyword
	 * @return the projects by sub category and keyword
	 */
	List<Project> getProjectsBySubCategoryAndKeyword(int __idCategory, int __idStyle, int __index,
	        int __size, String __keyword);

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
