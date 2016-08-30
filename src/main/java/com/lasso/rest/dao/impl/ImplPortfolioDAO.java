package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.PortfolioDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;

/**
 * The Class ImplPortfolioDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplPortfolioDAO implements PortfolioDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.dao.PortfolioDAO#getAllPortfoliosOfAccount(com.lasso.rest.model.datasource.
	 * Account)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Portfolio> getAllPortfoliosOfAccount(Account __account) {
		Criteria _criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Portfolio.class);
		_criteria.add(Restrictions.eq("account", __account));
		return _criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.PortfolioDAO#getPortfolioOfAccount(com.lasso.rest.model.datasource.
	 * Account, java.lang.Integer)
	 */
	@Override
	public Portfolio getPortfolioOfAccount(Account __account, Integer __id) {
		Criteria _criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Portfolio.class);
		_criteria.add(Restrictions.idEq(__id)).add(Restrictions.eq("account", __account));
		return (Portfolio) _criteria.uniqueResult();
	}

	/**
	 * Sets the session factory.
	 *
	 * @param __sessionFactory the new session factory
	 */
	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

}
