/*
 * 
 */
package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.JobsStyle;

// TODO: Auto-generated Javadoc
/**
 * The Interface JobStyleDAO.
 *
 * @author Paul Mai
 */
public interface JobStyleDAO extends HibernateSession {

	/**
	 * Gets the list job styles by job id.
	 *
	 * @param __idJob
	 *            the id job
	 * @return the list job styles by job id
	 */
	List<JobsStyle> getListJobStylesByJobId(int __idJob);

	/**
	 * Removes the job style by job id.
	 *
	 * @param __idJob
	 *            the id job
	 */
	void removeJobStyleByJobId(Integer __idJob);

	/**
	 * Save list job styles.
	 *
	 * @param __jobStyles
	 *            the job styles
	 */
	void saveListJobStyles(List<JobsStyle> __jobStyles);

}
