package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.model.datasource.TypesStyle;

/**
 * The Interface ProjectDAO.
 *
 * @author Paul Mai
 */
public interface ProjectDAO {

	/**
	 * Gets the categories.
	 *
	 * @param __offset the offset
	 * @param __limit the limit
	 * @return the categories
	 */
	List<Category> getCategories(int __offset, int __limit);

	/**
	 * Gets the category by id.
	 *
	 * @param __idCategory the id category
	 * @return the category by id
	 */
	Category getCategoryById(int __idCategory);

	/**
	 * Gets the styles by types.
	 *
	 * @param __typesStyles the types styles
	 * @param __offset the offset
	 * @param __limit the limit
	 * @return the styles by types
	 */
	List<Style> getStylesByTypes(List<TypesStyle> __typesStyles, int __offset, int __limit);

	/**
	 * Gets the types by category.
	 *
	 * @param __category the category
	 * @return the types by category
	 */
	List<Type> getTypesByCategory(Category __category);

	/**
	 * Gets the types styles by types.
	 *
	 * @param __types the types
	 * @return the types styles by types
	 */
	List<TypesStyle> getTypesStylesByTypes(List<Type> __types);

}
