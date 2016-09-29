package com.lasso.rest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.PortfolioDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.PortfolioType;
import com.lasso.rest.model.datasource.Project;

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
	 * com.lasso.rest.dao.PortfolioDAO#createPortfolio(com.lasso.rest.model.datasource.Portfolio)
	 */
	@Override
	public Integer createPortfolio(Portfolio __portfolio) {
		return (Integer) this.sessionFactory.getCurrentSession().save(__portfolio);
	}

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
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Portfolio.class)
				.add(Restrictions.eq("accountId", __account.getId()))
				.add(Restrictions.eq("status", (byte) 1)).add(Restrictions.eq("deleted", (byte) 0))
				.addOrder(Order.asc("title"));
		return _criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.PortfolioDAO#getPortfolioById(java.lang.Integer)
	 */
	@Override
	public Portfolio getPortfolioById(Integer __id) {
		return (Portfolio) this.sessionFactory.getCurrentSession().createCriteria(Portfolio.class)
				.add(Restrictions.idEq(__id)).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.PortfolioDAO#getPortfolioByProject(com.lasso.rest.model.datasource.
	 * Project)
	 */
	@Override
	public Portfolio getPortfolioByProject(Project __project) {
		return (Portfolio) this.sessionFactory.getCurrentSession().createCriteria(Portfolio.class)
				.add(Restrictions.eq("id", __project.getPortfolioId()))
				.add(Restrictions.eq("deleted", (byte) 0)).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.PortfolioDAO#getPortfolioOfAccount(com.lasso.rest.model.datasource.
	 * Account, java.lang.Integer)
	 */
	@Override
	public Portfolio getPortfolioOfAccount(Account __account, Integer __idPortfolio) {
		return (Portfolio) this.sessionFactory.getCurrentSession().createCriteria(Portfolio.class)
				.add(Restrictions.eq("id", __idPortfolio))
				.add(Restrictions.eq("accountId", __account.getId()))
				.add(Restrictions.eq("deleted", (byte) 0)).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.PortfolioDAO#searchPortfolios(int, int, int, java.util.List,
	 * java.util.List, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Portfolio> searchPortfolios(int __offset, int __limit, int __idCategory,
			List<Integer> __idsStyle, List<PortfolioType> __portfolioTypes,
			double __budgetCompare) {
		List<Integer> _portfolioIds = new ArrayList<>();
		__portfolioTypes.forEach(_pt -> _portfolioIds.add(_pt.getPortfolioId()));

		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Portfolio.class)
				.add(Restrictions.eq("categoryId", __idCategory))
				.add(Restrictions.in("styleId", __idsStyle))
				.add(Restrictions.in("id", _portfolioIds)).add(Restrictions.eq("status", (byte) 1))
				.add(Restrictions.eq("deleted", (byte) 0));
		if (__budgetCompare > 0) {
			_criteria.add(Restrictions.gt("amount", __budgetCompare))
			.addOrder(Order.desc("amount"));
		}
		else if (__budgetCompare < 0) {
			_criteria.add(Restrictions.le("amount", __budgetCompare * -1D))
			.addOrder(Order.asc("amount"));
		}
		else {
			_criteria.addOrder(Order.asc("amount"));
		}

		if (__offset > -1) {
			_criteria.setFirstResult(__offset).setMaxResults(__limit);
		}

		return _criteria.list();
	}

	/**
	 * Sets the session factory.
	 *
	 * @param __sessionFactory the new session factory
	 */
	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.dao.PortfolioDAO#updatePortfolio(com.lasso.rest.model.datasource.Portfolio)
	 */
	@Override
	public void updatePortfolio(Portfolio __portfolio) {
		this.sessionFactory.getCurrentSession().update(__portfolio);
	}

}
