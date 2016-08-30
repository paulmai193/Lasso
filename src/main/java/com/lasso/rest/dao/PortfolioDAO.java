package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;

/**
 * The Interface PortfolioDAO.
 *
 * @author Paul Mai
 */
public interface PortfolioDAO {

	/**
	 * Gets the all portfolios of account.
	 *
	 * @param __account the account
	 * @return the all portfolios of account
	 */
	public List<Portfolio> getAllPortfoliosOfAccount(Account __account);
}
