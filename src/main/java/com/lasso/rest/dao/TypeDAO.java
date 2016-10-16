/*
 * 
 */
package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Type;

// TODO: Auto-generated Javadoc
/**
 * The Interface TypeDAO.
 *
 * @author Paul Mai
 */
public interface TypeDAO extends HibernateSession {

	/**
	 * Gets the list by by list ids.
	 *
	 * @param __listIdsType
	 *        the list ids type
	 * @return the list by by list ids
	 */
	List<Type> getListByByListIds(List<Integer> __listIdsType);

	/**
	 * Gets the type by id.
	 *
	 * @param __typeId
	 *        the type id
	 * @return the by id
	 */
	Type getTypeById(int __typeId);

	/**
	 * Gets the types by category.
	 *
	 * @param __category
	 *        the category
	 * @return the types by category
	 */
	List<Type> getTypesByCategory(Category __category);

	// List<Type> getTypesByStyles(List<TypesStyle> __typesStyles);

	/**
	 * Gets the types by id types and category.
	 *
	 * @param __listIdTypes
	 *        the list id types
	 * @param __category
	 *        the category
	 * @return the types by id types and category
	 */
	List<Type> getTypesByIdTypesAndCategory(List<Integer> __listIdTypes, Category __category);
}
