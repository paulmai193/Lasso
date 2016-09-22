package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Job;

/**
 * The Interface JobDAO.
 *
 * @author Paul Mai
 */
public interface JobDAO extends HibernateSession {

	/**
	 * Gets the job by id.
	 *
	 * @param __idJob the id job
	 * @return the job by id
	 */
	Job getJobById(int __idJob);

	/**
	 * Gets the job of user by id.
	 *
	 * @param __idUser the id user
	 * @param __idJob the id job
	 * @return the of user by id
	 */
	Job getJobOfUserById(int __idUser, int __idJob);

	/**
	 * Gets the list jobs of user.
	 *
	 * @param __idUser the id user
	 * @return the of user
	 */
	List<Job> getListJobsOfUser(Integer __idUser);

	/**
	 * Save job.
	 *
	 * @param __job the job
	 * @return the job Id
	 */
	Integer saveJob(Job __job);

	/**
	 * Update job.
	 *
	 * @param __job the job
	 */
	void updateJob(Job __job);

}
