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
	 * Gets the accept by job and designer id.
	 *
	 * @param __idJob the id job
	 * @param __idDesigner the id designer
	 * @return the accept by job and designer id
	 */
	JobsAccount getAcceptByJobAndDesignerId(Integer __idJob, Integer __idDesigner);

	/**
	 * Gets the by job id.
	 *
	 * @param __idJob the id job
	 * @return the by job id
	 */
	JobsAccount getByJobId(int __idJob);

	/**
	 * Gets the by offer id.
	 *
	 * @param __idJob the id job
	 * @return the by offer id
	 */
	List<JobsAccount> getByOfferId(int __idJob);

	/**
	 * Gets the by job and designer id.
	 *
	 * @param __idJob the id job
	 * @param __idDesigner the id designer
	 * @return the by job and designer id
	 */
	JobsAccount getConfirmByJobAndDesignerId(Integer __idJob, Integer __idDesigner);

	/**
	 * Gets the list jobs account of designer.
	 *
	 * @param __idDesigner the id designer
	 * @return the list jobs account of designer
	 */
	List<JobsAccount> getListJobsAccountOfDesigner(Integer __idDesigner);

	/**
	 * Gets the un confirm by job and designer id.
	 *
	 * @param __idJob the id job
	 * @param __idDesigner the id designer
	 * @return the un confirm by job and designer id
	 */
	JobsAccount getUnConfirmByJobAndDesignerId(Integer __idJob, Integer __idDesigner);

	/**
	 * Save job account.
	 *
	 * @param __jobsAccount the jobs account
	 */
	void saveJobAccount(JobsAccount __jobsAccount);

	/**
	 * Save job accounts.
	 *
	 * @param __jobsAccounts the jobs accounts
	 */
	void saveJobAccounts(List<JobsAccount> __jobsAccounts);

	/**
	 * Update.
	 *
	 * @param __jobsAccount the jobs account
	 */
	void update(JobsAccount __jobsAccount);

	JobsAccount getByJobAndDesignerId(Integer __idJob, Integer __idDesigner);

}
