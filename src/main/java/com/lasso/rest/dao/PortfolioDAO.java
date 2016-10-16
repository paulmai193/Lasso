/*
 * 
 */
package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.PortfolioType;
import com.lasso.rest.model.datasource.Project;

// TODO: Auto-generated Javadoc
/**
 * The Interface PortfolioDAO.
 *
 * @author Paul Mai
 */
public interface PortfolioDAO extends HibernateSession {

	/**
	 * Creates the portfolio.
	 *
	 * @param __portfolio
	 *            the portfolio
	 * @return the portfolio ID
	 */
	Integer createPortfolio(Portfolio __portfolio);

	/**
	 * Gets the all portfolios of account.
	 *
	 * @param __account
	 *            the account
	 * @return the all portfolios of account
	 */
	List<Portfolio> getAllPortfoliosOfAccount(Account __account);

	/**
	 * Gets the portfolio by portfolio ID.
	 *
	 * @param __id
	 *            the id
	 * @return the portfolio by id
	 */
	Portfolio getPortfolioById(Integer __id);

	/**
	 * Gets the portfolio by project.
	 *
	 * @param __project
	 *            the project
	 * @return the portfolio by project
	 */
	Portfolio getPortfolioByProject(Project __project);

	/**
	 * Gets the portfolio of account.
	 *
	 * @param __account
	 *            the account
	 * @param __idPortfolio
	 *            the id portfolio
	 * @return the portfolio of account
	 */
	Portfolio getPortfolioOfAccount(Account __account, Integer __idPortfolio);

	/**
	 * Search portfolios.
	 *
	 * @param __offset
	 *            the offset
	 * @param __limit
	 *            the limit
	 * @param __idCategory
	 *            the id category
	 * @param __idsStyle
	 *            the id style
	 * @param __portfolioTypes
	 *            the list portfolio types
	 * @param __budgetCompare
	 *            the budget compare. negative value for smaller or equal,
	 *            positive value for bigger
	 * @return the list portfolios by condition
	 */
	List<Portfolio> searchPortfolios(int __offset, int __limit, int __idCategory, List<Integer> __idsStyle,
			List<PortfolioType> __portfolioTypes, double __budgetCompare);

	/**
	 * Update portfolio.
	 *
	 * @param __portfolio
	 *            the portfolio
	 */
	void updatePortfolio(Portfolio __portfolio);

}
