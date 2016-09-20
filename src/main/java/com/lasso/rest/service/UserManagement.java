package com.lasso.rest.service;

import java.io.IOException;
import java.util.List;

import com.lasso.rest.model.api.request.CreateNewJobRequest;
import com.lasso.rest.model.api.request.EditJobRequest;
import com.lasso.rest.model.datasource.Account;
import com.mashape.unirest.http.exceptions.UnirestException;

import javassist.NotFoundException;

/**
 * The Interface UserManagement.
 *
 * @author Paul Mai
 */
public interface UserManagement extends ProjectManagement {

	/**
	 * Creates the new job.
	 *
	 * @param __user the user
	 * @param __createNewJobRequest the create new job request
	 * @throws UnirestException the unirest exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void createNewJob(Account __user, CreateNewJobRequest __createNewJobRequest)
	        throws UnirestException, IOException;

	/**
	 * Gets the job data of user by id.
	 *
	 * @param __user the user
	 * @param __idJob the id job
	 * @return the job data of user by id
	 * @throws NotFoundException the not found exception
	 */
	Object[] getJobDataOfUserById(Account __user, int __idJob) throws NotFoundException;

	/**
	 * Gets the list jobs data of user.
	 *
	 * @param __user the user
	 * @return the list jobs data of user
	 */
	List<Object[]> getListJobsDataOfUser(Account __user);

	/**
	 * Edits the job.
	 *
	 * @param __user the user
	 * @param __editJobRequest the edit job request
	 * @throws UnirestException the unirest exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void editJob(Account __user, EditJobRequest __editJobRequest)
	        throws UnirestException, IOException;

	/**
	 * Gets the list portfolios by condition.
	 *
	 * @param __index the index
	 * @param __size the size
	 * @param __idCategory the id category
	 * @param __idStyle the id style
	 * @param __idsType the list IDs type
	 * @return the list portfolios data by condition {portoflio, designer}
	 */
	List<Object[]> getListPortfoliosByCondition(int __index, int __size, int __idCategory,
	        int __idStyle, List<Integer> __idsType);

}
