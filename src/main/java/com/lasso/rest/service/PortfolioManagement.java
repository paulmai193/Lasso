package com.lasso.rest.service;

import java.util.List;

import com.lasso.rest.model.api.request.CreatePortfolioRequest;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;

/**
 * The Interface PortfolioManagement.
 *
 * @author Paul Mai
 */
public interface PortfolioManagement {

	/**
	 * Gets the all portfolios.
	 *
	 * @param __account the account
	 * @return the all portfolios
	 */
	List<Portfolio> getAllPortfolios(Account __account);

	/**
	 * Gets the portfolio.
	 *
	 * @param __account the account
	 * @param __id the id
	 * @return the portfolio
	 */
	Portfolio getPortfolio(Account __account, Integer __id);

	/**
	 * Creates the portfolio.
	 *
	 * @param __desiger the desiger
	 * @param __createPortfolioRequest the create portfolio request
	 */
	void createPortfolio(Account __desiger, CreatePortfolioRequest __createPortfolioRequest);

}
