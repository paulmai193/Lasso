package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Type;

/**
 * The Interface TypeDAO.
 *
 * @author Paul Mai
 */
public interface TypeDAO {

	/**
	 * Gets the list by by list ids.
	 *
	 * @param __listIdsType the list ids type
	 * @return the list by by list ids
	 */
	public List<Type> getListByByListIds(List<Integer> __listIdsType);
}
