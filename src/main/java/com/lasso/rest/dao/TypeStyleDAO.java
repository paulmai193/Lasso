/*
 * 
 */
package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.model.datasource.TypesStyle;

// TODO: Auto-generated Javadoc
/**
 * The Interface TypeStyleDAO.
 *
 * @author Paul Mai
 */
public interface TypeStyleDAO extends HibernateSession {

	/**
	 * Gets the types styles by styles.
	 *
	 * @param __styles
	 *            the styles
	 * @return the types styles by styles
	 */
	List<TypesStyle> getTypesStylesByStyles(List<Style> __styles);

	/**
	 * Gets the types styles by types.
	 *
	 * @param __types
	 *            the types
	 * @return the types styles by types
	 */
	List<TypesStyle> getTypesStylesByTypes(List<Type> __types);
}
