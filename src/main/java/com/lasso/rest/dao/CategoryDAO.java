package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Category;

/**
 * The Interface CategoryDAO.
 *
 * @author Paul Mai
 */
public interface CategoryDAO {

	/**
	 * Gets the categories.
	 *
	 * @param __offset the offset
	 * @param __limit the limit
	 * @param __keyword the keyword
	 * @return the categories
	 */
	List<Category> getCategories(int __offset, int __limit, String __keyword);

	/**
	 * Gets the category by id.
	 *
	 * @param __idCategory the id category
	 * @return the category by id
	 */
	Category getCategoryById(int __idCategory);
}
