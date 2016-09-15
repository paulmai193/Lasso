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
		if (__account.getId() != null) {
			this.sessionFactory.getCurrentSession().update(__account);
			return __account.getId();
		}
		else {
			return (Integer) this.sessionFactory.getCurrentSession().save(__account);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#getAccountsByEmail(java.lang.String)
	 */
	@Override
	public Account getAccountByEmail(String __email) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Account.class);
		_criteria.add(Restrictions.eq("email", __email));
		return (Account) _criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#getAccountById(java.lang.Integer)
	 */
	public Account getAccountById(Integer __id) {
		return this.sessionFactory.getCurrentSession().get(Account.class, __id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#getAccountByOtp(java.lang.String)
	 */
	@Override
	public Account getAccountByOtp(String __otp) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Account.class);
		_criteria.add(Restrictions.eq("otp", __otp));
		return (Account) _criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#getAccountByToken(java.lang.String)
	 */
	@Override
	public Account getAccountByToken(String __token) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Account.class);
		_criteria.add(Restrictions.eq("appSession", __token));
		return (Account) _criteria.uniqueResult();
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
	 * @see com.lasso.rest.dao.AccountDAO#updateAccount(com.lasso.rest.model.datasource.Account)
	 */
	@Override
	public void updateAccount(Account __account) {
		this.sessionFactory.getCurrentSession().update(__account);
	}

}
