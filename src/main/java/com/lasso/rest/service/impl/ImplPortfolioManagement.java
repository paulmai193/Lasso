package com.lasso.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.dao.PortfolioDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.service.PortfolioManagement;

/**
 * The Class ImplPortfolioManagement.
 *
 * @author Paul Mai
 */
@Service
@Transactional
public class ImplPortfolioManagement implements PortfolioManagement {

	/** The portfolio DAO. */
	@Autowired
	private PortfolioDAO portfolioDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.PortfolioManagement#getAllPortfolios(com.lasso.rest.model.datasource.
	 * Account)
	 */
	@Override
	public List<Portfolio> getAllPortfolios(Account __account) {
		return this.portfolioDAO.getAllPortfoliosOfAccount(__account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.PortfolioManagement#getPortfolio(com.lasso.rest.model.datasource.
	 * Account, java.lang.Integer)
	 */
	@Override
	public Portfolio getPortfolio(Account __account, Integer __id) {
		return this.portfolioDAO.getPortfolioOfAccount(__account, __id);
	}

	/**
	 * Sets the portfolio DAO.
	 *
	 * @param __portfolioDAO the new portfolio DAO
	 */
	public void setPortfolioDAO(PortfolioDAO __portfolioDAO) {
		this.portfolioDAO = __portfolioDAO;
	}
}
