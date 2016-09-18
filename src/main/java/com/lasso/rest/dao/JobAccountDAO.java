package com.lasso.rest.dao;

import com.lasso.rest.model.datasource.JobsAccount;

/**
 * The Interface JobAccountDAO.
 *
 * @author Paul Mai
 */
public interface JobAccountDAO extends HibernateSession {

	/**
	 * Gets the by job id.
	 *
	 * @param __idJob the id job
	 * @return the by job id
	 */
	JobsAccount getByJobId(int __idJob);

}
