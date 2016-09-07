package com.lasso.rest.service;

import java.util.List;

import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Style;

/**
 * The Interface ProjectManagement.
 *
 * @author Paul Mai
 */
public interface ProjectManagement {

	/**
	 * Gets the categories by index.
	 *
	 * @param __index the index
	 * @param __size the size
	 * @return the categories start from index
	 */
	List<Category> getCategoriesByIndex(int __index, int __size);

	/**
	 * Gets the category by id.
	 *
	 * @param __idCategory the id category
	 * @return the category by id
	 */
	Category getCategoryById(int __idCategory);

	/**
	 * Gets the sub categories by index.
	 *
	 * @param __idCategory the id category
	 * @param __index the index
	 * @param __size the size
	 * @return the sub categories start from index
	 */
	List<Style> getSubCategoriesByIndex(int __idCategory, int __index, int __size);

}
