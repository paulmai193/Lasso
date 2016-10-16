/*
 * 
 */
package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.PortfolioTypeDAO;
import com.lasso.rest.model.datasource.PortfolioType;

// TODO: Auto-generated Javadoc
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.dao.PortfolioTypeDAO#createPortfolioType(com.lasso.rest.
	 * model.datasource. PortfolioType)
	 */
	@Override
	public void createPortfolioType(PortfolioType __portfolioType) {
		this.sessionFactory.getCurrentSession().save(__portfolioType);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.PortfolioTypeDAO#getListByIdTypes(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PortfolioType> getListByIdTypes(List<Integer> __idsType) {
		return this.sessionFactory.getCurrentSession().createCriteria(PortfolioType.class)
				.add(Restrictions.in("typeId", __idsType)).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.PortfolioTypeDAO#removeByPortfolioId(int)
	 */
	@Override
	public void removeByPortfolioId(int __idPortfolio) {
		this.sessionFactory.getCurrentSession().createQuery("delete PortfolioType where portfolioId = :id")
				.setInteger("id", __idPortfolio).executeUpdate();
	}

	/**
	 * Sets the session factory.
	 *
	 * @param __sessionFactory
	 *            the new session factory
	 */
	@Override
	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}
}
