package com.lasso.rest.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.PortfolioTypeDAO;
import com.lasso.rest.model.datasource.PortfolioType;

@Repository
public class ImplPortfolioTypeDAO implements PortfolioTypeDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PortfolioType getByIdPortfolio(int __idPortfolio) {
		return (PortfolioType) this.sessionFactory.getCurrentSession()
		        .createCriteria(PortfolioType.class)
		        .add(Restrictions.eq("portfolioId", __idPortfolio)).uniqueResult();
	}

}
