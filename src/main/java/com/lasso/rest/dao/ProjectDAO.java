package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.Project;
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

	/**
	 * Gets the project by id.
	 *
	 * @param __idProject the id project
	 * @return the project by id
	 */
	Project getProjectById(int __idProject);

	/**
	 * Gets the styles by types and keyword.
	 *
	 * @param __typesStyles the types styles
	 * @param __offset the offset
	 * @param __limit the limit
	 * @param __keyword the keyword
	 * @return the styles by types
	 */
	List<Style> getStylesByTypesAndKeyword(List<TypesStyle> __typesStyles, int __offset,
	        int __limit, String __keyword);

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

	/**
	 * Search projects.
	 *
	 * @param __idCategory the id category
	 * @param __idStyle the id style
	 * @param __keyword the keyword
	 * @param __offset the offset
	 * @param __limit the limit
	 * @return the list
	 */
	List<Project> searchProjects(Integer __idCategory, Integer __idStyle, String __keyword,
	        int __offset, int __limit);

	/**
	 * Gets the portfolio by project.
	 *
	 * @param __project the project
	 * @return the portfolio by project
	 */
	Portfolio getPortfolioByProject(Project __project);

}
