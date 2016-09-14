package com.lasso.rest.dao;

import com.lasso.rest.model.datasource.PortfolioType;

/**
 * The Interface PortfolioTypeDAO.
 *
 * @author Paul Mai
 */
public interface PortfolioTypeDAO {

	/**
	 * Gets the by id portfolio.
	 *
	 * @param __idPortfolio the id portfolio
	 * @return the by id portfolio
	 */
	public PortfolioType getByIdPortfolio(int __idPortfolio);
}
