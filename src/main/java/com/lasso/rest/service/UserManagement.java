package com.lasso.rest.service;

import java.util.List;

import com.lasso.rest.model.datasource.Account;

import javassist.NotFoundException;

/**
 * The Interface UserManagement.
 *
 * @author Paul Mai
 */
public interface UserManagement extends ProjectManagement {

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

}
