package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.PortfolioType;

/**
 * The Interface PortfolioTypeDAO.
 *
 * @author Paul Mai
 */
public interface PortfolioTypeDAO {

	/**
	 * Gets the list by id portfolio.
	 *
	 * @param __idPortfolio the id portfolio
	 * @return the list by id portfolio
	 */
	public List<PortfolioType> getListByIdPortfolio(int __idPortfolio);
}
