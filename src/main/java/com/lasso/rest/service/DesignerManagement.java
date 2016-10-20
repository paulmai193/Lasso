/*
 * 
 */
package com.lasso.rest.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.lasso.rest.model.api.request.ConfirmOfferRequest;
import com.lasso.rest.model.api.request.CounterOfferRequest;
import com.lasso.rest.model.api.request.CreatePortfolioRequest;
import com.lasso.rest.model.api.request.EditPortfolioRequest;
import com.lasso.rest.model.api.request.UpdateJobStageRequest;
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
	 * Confirm offer.
	 *
	 * @param __designer
	 *        the designer
	 * @param __counterOfferRequest
	 *        the counter offer request
	 */
	void confirmOffer(Account __designer, ConfirmOfferRequest __counterOfferRequest);

	/**
	 * Counter offer.
	 *
	 * @param __designer
	 *        the designer
	 * @param __counterOfferRequest
	 *        the counter offer request
	 */
	void counterOffer(Account __designer, CounterOfferRequest __counterOfferRequest);

	/**
	 * Creates the portfolio.
	 *
	 * @param __desiger the desiger
	 * @param __createPortfolioRequest the create portfolio request
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws UnirestException the unirest exception
	 * @throws URISyntaxException the URI syntax exception
	 */
	void createPortfolio(Account __desiger, CreatePortfolioRequest __createPortfolioRequest)
	        throws IOException, UnirestException, URISyntaxException;

	/**
	 * Delete portfolio.
	 *
	 * @param __portfolio
	 *        the portfolio
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
	 * @throws URISyntaxException the URI syntax exception
	 */
	void editPortfolio(Account __desiger, Portfolio __portfolio,
	        EditPortfolioRequest __editPortfolioRequest)
	        throws IOException, UnirestException, URISyntaxException;

	/**
	 * Gets the all portfolios.
	 *
	 * @param __account
	 *        the account
	 * @return the all portfolios
	 */
	List<Portfolio> getAllPortfolios(Account __account);

	/**
	 * Gets the job data of designer by id.
	 *
	 * @param __designer
	 *        the designer
	 * @param __idJob
	 *        the id job
	 * @return the job data of designer by id
	 */
	Object[] getJobDataOfDesignerById(Account __designer, int __idJob);

	/**
	 * Gets the list jobs data of designer.
	 *
	 * @param __designer
	 *        the designer
	 * @return the list jobs data of designer
	 */
	List<Object[]> getListJobsDataOfDesigner(Account __designer);

	/**
	 * Gets the offer data by id.
	 *
	 * @param __designer
	 *        the designer
	 * @param __idJob
	 *        the id job
	 * @return the offer data by id
	 */
	Object[] getOfferDataById(Account __designer, int __idJob);

	/**
	 * Gets the portfolio.
	 *
	 * @param __account
	 *        the account
	 * @param __id
	 *        the id
	 * @return the portfolio
	 */
	Portfolio getPortfolio(Account __account, Integer __id);

	/**
	 * Update stage.
	 *
	 * @param __designer
	 *        the designer
	 * @param __updateJobStageRequest
	 *        the update job stage request
	 */
	void updateStage(Account __designer, UpdateJobStageRequest __updateJobStageRequest);

	Object[] getOrderDataForMessageById(Account __designer, int __idJob);

}
