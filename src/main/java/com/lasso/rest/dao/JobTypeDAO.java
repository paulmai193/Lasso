package com.lasso.rest.dao;

import com.lasso.rest.model.datasource.JobsType;

/**
 * The Interface JobTypeDAO.
 *
 * @author Paul Mai
 */
public interface JobTypeDAO extends HibernateSession {

	/**
	 * Gets the by job id.
	 *
	 * @param __idJob the id job
	 * @return the by job id
	 */
	JobsType getByJobId(int __idJob);

}
