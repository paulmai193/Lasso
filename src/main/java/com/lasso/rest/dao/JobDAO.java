package com.lasso.rest.dao;

import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Job;

/**
 * The Interface JobDAO.
 *
 * @author Paul Mai
 */
public interface JobDAO {

	/**
	 * Gets the of user by id.
	 *
	 * @param __account the account
	 * @param __id the id
	 * @return the of user by id
	 */
	Job getOfUserById(Account __account, int __id);

}
