package com.lasso.rest.dao;

import com.lasso.rest.model.datasource.AccountsRating;

/**
 * The Interface AccountRatingDAO.
 *
 * @author Paul Mai
 */
public interface AccountRatingDAO extends HibernateSession {

	/**
	 * Gets the by account and job.
	 *
	 * @param __idAccount the id account
	 * @param __idJob the id job
	 * @return the by account and job
	 */
	AccountsRating getByAccountAndJob(int __idAccount, int __idJob);

	/**
	 * Save rating.
	 *
	 * @param __accountsRating the accounts rating
	 */
	void saveRating(AccountsRating __accountsRating);

}
