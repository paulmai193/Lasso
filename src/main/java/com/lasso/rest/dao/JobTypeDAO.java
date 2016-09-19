package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.JobsType;

/**
 * The Interface JobTypeDAO.
 *
 * @author Paul Mai
 */
public interface JobTypeDAO extends HibernateSession {

	/**
	 * Gets the list jobs types by job id.
	 *
	 * @param __idJob the id job
	 * @return the jobs types by job id
	 */
	List<JobsType> getListJobsTypesByJobId(int __idJob);

	/**
	 * Save list jobs types.
	 *
	 * @param __jobsTypes the jobs types
	 */
	void saveListJobsTypes(List<JobsType> __jobsTypes);

}
