/*
 * 
 */
package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.TypesStyle;

/**
 * The Interface StyleDAO.
 *
 * @author Paul Mai
 */
public interface StyleDAO extends HibernateSession {

	/**
	 * Gets the by id.
	 *
	 * @param __styleId the style id
	 * @return the style by id
	 */
	Style getById(int __styleId);

	/**
	 * Gets the list by by list ids.
	 *
	 * @param __styleIds the style ids
	 * @return the list by by list ids
	 */
	List<Style> getListByByListIds(List<Integer> __styleIds);

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

}
