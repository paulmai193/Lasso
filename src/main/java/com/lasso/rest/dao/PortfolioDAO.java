package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.Project;

/**
 * The Interface PortfolioDAO.
 *
 * @author Paul Mai
 */
public interface PortfolioDAO {

	/**
	 * Creates the portfolio.
	 *
	 * @param __portfolio the portfolio
	 * @return the portfolio ID
	 */
	Integer createPortfolio(Portfolio __portfolio);

	/**
	 * Gets the all portfolios of account.
	 *
	 * @param __account the account
	 * @return the all portfolios of account
	 */
	List<Portfolio> getAllPortfoliosOfAccount(Account __account);

	/**
	 * Gets the portfolio by portfolio ID.
	 *
	 * @param __id the id
	 * @return the portfolio by id
	 */
	Portfolio getPortfolioById(Integer __id);

	/**
	 * Gets the portfolio of account.
	 *
	 * @param __account the account
	 * @param __id the id
	 * @return the portfolio of account
	 */
	Portfolio getPortfolioOfAccount(Account __account, Integer __id);

	/**
	 * Update portfolio.
	 *
	 * @param __portfolio the portfolio
	 */
	void updatePortfolio(Portfolio __portfolio);

	/**
	 * Gets the portfolio by project.
	 *
	 * @param __project the project
	 * @return the portfolio by project
	 */
	Portfolio getPortfolioByProject(Project __project);

}
