package com.lasso.rest.service;

import java.io.IOException;
import java.util.List;

import com.lasso.rest.model.api.request.ConfirmOfferRequest;
import com.lasso.rest.model.api.request.CounterOfferRequest;
import com.lasso.rest.model.api.request.CreatePortfolioRequest;
import com.lasso.rest.model.api.request.EditPortfolioRequest;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;
import com.mashape.unirest.http.exceptions.UnirestException;

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
	 * @throws UnirestException the unirest exception
	 */
	void createPortfolio(Account __desiger, CreatePortfolioRequest __createPortfolioRequest)
	        throws IOException, UnirestException;

	/**
	 * Delete portfolio.
	 *
	 * @param __portfolio the portfolio
	 */
	void deletePortfolio(Portfolio __portfolio);

	/**
	 * Edits the portfolio.
	 *
	 * @param __desiger the desiger
	 * @param __portfolio the portfolio
	 * @param __editPortfolioRequest the edit portfolio request
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws UnirestException the unirest exception
	 */
	void editPortfolio(Account __desiger, Portfolio __portfolio,
	        EditPortfolioRequest __editPortfolioRequest) throws IOException, UnirestException;

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

	Object[] getOfferDataById(int __idJob);

	void counterOffer(Account __designer, CounterOfferRequest __counterOfferRequest);

	void confirmOffer(Account __designer, ConfirmOfferRequest __counterOfferRequest);

}
