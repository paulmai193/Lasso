/*
 * 
 */
package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.model.datasource.Account;

/**
 * The Class ImplAccountDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplAccountDAO implements AccountDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Gets the session factory.
	 *
	 * @return the session factory
	 */
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	/**
	 * Sets the session factory.
	 *
	 * @param __sessionFactory the new session factory
	 */
	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

	/**
	 * Instantiates a new impl account DAO.
	 */
	public ImplAccountDAO() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#createAccount(com.lasso.rest.model.datasource.Account)
	 */
	public Integer createAccount(Account __account) {
		return (Integer) this.sessionFactory.getCurrentSession().save(__account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#getAccountById(java.lang.Integer)
	 */
	public Account getAccountById(Integer __id) {
		return this.sessionFactory.getCurrentSession().load(Account.class, __id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Account> getAll() {
		return this.sessionFactory.getCurrentSession().createCriteria(Account.class).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#getAccountsByEmail(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAccountsByEmail(String __email) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Account.class);
		_criteria.add(Restrictions.eq("email", __email));
		return _criteria.list();
	}

}
