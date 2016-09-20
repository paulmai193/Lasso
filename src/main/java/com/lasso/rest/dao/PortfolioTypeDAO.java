package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.PortfolioType;

/**
 * The Interface PortfolioTypeDAO.
 *
 * @author Paul Mai
 */
public interface PortfolioTypeDAO extends HibernateSession {

	/**
	 * Creates the portfolio type.
	 *
	 * @param __portfolioType the portfolio type
	 */
	void createPortfolioType(PortfolioType __portfolioType);

	/**
	 * Gets the list by id portfolio.
	 *
	 * @param __idPortfolio the id portfolio
	 * @return the list by id portfolio
	 */
	List<PortfolioType> getListByIdPortfolio(int __idPortfolio);

	/**
	 * Removes the by portfolio id.
	 *
	 * @param __idPortfolio the id portfolio
	 */
	void removeByPortfolioId(int __idPortfolio);

	/**
	 * Gets the list by id types.
	 *
	 * @param __idsType the ids type
	 * @return the list by id types
	 */
	List<PortfolioType> getListByIdTypes(List<Integer> __idsType);
}
