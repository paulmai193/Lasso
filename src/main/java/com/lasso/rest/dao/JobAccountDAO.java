package com.lasso.rest.dao;

import java.util.List;

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

	/**
	 * Save job accounts.
	 *
	 * @param __jobsAccounts the jobs accounts
	 */
	void saveJobAccounts(List<JobsAccount> __jobsAccounts);

	List<JobsAccount> getByOfferId(int __idJob);

}
