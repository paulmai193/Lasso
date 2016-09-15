package com.lasso.rest.service;

import java.io.IOException;
import java.util.List;

import com.lasso.rest.model.api.request.CreatePortfolioRequest;
import com.lasso.rest.model.api.request.EditPortfolioRequest;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;

/**
 * The Interface DesignerManagement.
 *
 * @author Paul Mai
 */
public interface DesignerManagement extends ProjectManagement {

	/**
	 * Creates the portfolio.
	 *
	 * @param __desiger the desiger
	 * @param __createPortfolioRequest the create portfolio request
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void createPortfolio(Account __desiger, CreatePortfolioRequest __createPortfolioRequest)
			throws IOException;

	/**
	 * Edits the portfolio.
	 *
	 * @param __desiger the desiger
	 * @param __editPortfolioRequest the edit portfolio request
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void editPortfolio(Account __desiger, EditPortfolioRequest __editPortfolioRequest)
			throws IOException;

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

}
