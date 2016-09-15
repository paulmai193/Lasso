package com.lasso.rest.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.dao.PortfolioDAO;
import com.lasso.rest.model.api.request.CreatePortfolioRequest;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.PortfolioType;
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

	@Override
	public void createPortfolio(Account __desiger,
	        CreatePortfolioRequest __createPortfolioRequest) {
		String _image = Arrays.toString(__createPortfolioRequest.getImages().toArray());
		_image = _image.substring(1, _image.length() - 1);
		Portfolio _portfolio = new Portfolio(__createPortfolioRequest.getAmount(), new Date(),
		        __desiger.getId(), __createPortfolioRequest.getIdCategory(),
		        __createPortfolioRequest.getIdStyle(), _image, __createPortfolioRequest.getInfo(),
		        new Date(), (byte) 1, __createPortfolioRequest.getTitle());
		int _id = this.portfolioDAO.createPortfolio(_portfolio);
		for (int _idType : __createPortfolioRequest.getIdTypes()) {
			PortfolioType _portfolioType = new PortfolioType(new Date(), new Date(), _id, _idType);
			// this.portfolioDAO.
		}

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
