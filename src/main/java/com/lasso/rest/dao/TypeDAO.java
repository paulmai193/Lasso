package com.lasso.rest.dao;

import com.lasso.rest.model.datasource.Type;

/**
 * The Interface TypeDAO.
 *
 * @author Paul Mai
 */
public interface TypeDAO {

	/**
	 * Gets the by id.
	 *
	 * @param __idType the id type
	 * @return the by id
	 */
	public Type getById(int __idType);
}
