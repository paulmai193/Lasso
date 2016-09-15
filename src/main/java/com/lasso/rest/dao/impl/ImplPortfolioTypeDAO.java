package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.PortfolioTypeDAO;
import com.lasso.rest.model.datasource.PortfolioType;

/**
 * The Class ImplPortfolioTypeDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplPortfolioTypeDAO implements PortfolioTypeDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.PortfolioTypeDAO#getByIdPortfolio(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PortfolioType> getListByIdPortfolio(int __idPortfolio) {
		return this.sessionFactory.getCurrentSession().createCriteria(PortfolioType.class)
		        .add(Restrictions.eq("portfolioId", __idPortfolio)).list();
	}

}
