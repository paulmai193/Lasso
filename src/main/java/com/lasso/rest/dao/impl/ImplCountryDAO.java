/*
 * 
 */
package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.CountryDAO;
import com.lasso.rest.model.datasource.Country;

/**
 * The Class ImplCountryDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplCountryDAO implements CountryDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new impl country DAO.
	 */
	public ImplCountryDAO() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.CountryDAO#getCountryIdsByCode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Country> getCountryIdsByCode(String __code) {

		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Country.class);
		if (!__code.equalsIgnoreCase("all")) {
			_criteria.add(Restrictions.eq("code", __code));
		}
		_criteria.add(Restrictions.eq("status", (byte) 1));
		_criteria.addOrder(Order.asc("name"));

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

}
