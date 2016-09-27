package com.lasso.rest.dao;

import com.lasso.rest.model.datasource.AccountsRating;

/**
 * The Interface AccountRatingDAO.
 *
 * @author Paul Mai
 */
public interface AccountRatingDAO extends HibernateSession {

	/**
	 * Save rating.
	 *
	 * @param __accountsRating the accounts rating
	 */
	void saveRating(AccountsRating __accountsRating);

}
