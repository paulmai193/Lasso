package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.TypesStyle;

/**
 * The Interface StyleDAO.
 *
 * @author Paul Mai
 */
public interface StyleDAO {

	/**
	 * Gets the style by id.
	 *
	 * @param __styleId the style id
	 * @return the style by id
	 */
	Style getStyleById(int __styleId);

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
