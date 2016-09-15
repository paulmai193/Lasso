package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.model.datasource.TypesStyle;

public interface TypeStyleDAO {

	/**
	 * Gets the types styles by types.
	 *
	 * @param __types the types
	 * @return the types styles by types
	 */
	List<TypesStyle> getTypesStylesByTypes(List<Type> __types);
}
