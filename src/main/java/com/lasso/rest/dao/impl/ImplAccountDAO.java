/*
 * 
 */
package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.model.datasource.Account;

// TODO: Auto-generated Javadoc
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
	 * @see com.lasso.rest.dao.AccountDAO#createAccount(com.lasso.rest.model.
	 * datasource.Account)
	 */
	@Override
	public Integer createAccount(Account __account) {
		if (__account.getId() != null) {
			this.sessionFactory.getCurrentSession().update(__account);
			return __account.getId();
		} else {
			return (Integer) this.sessionFactory.getCurrentSession().save(__account);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#getAccountByDeviceId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAccountByDeviceId(String __pushToken) {
		return this.sessionFactory.getCurrentSession().createCriteria(Account.class)
				.add(Restrictions.eq("deviceId", __pushToken)).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#getAccountsByEmail(java.lang.String)
	 */
	@Override
	public Account getAccountByEmail(String __email) {
		return (Account) this.sessionFactory.getCurrentSession().createCriteria(Account.class)
				.add(Restrictions.eq("email", __email)).add(Restrictions.eq("deleted", (byte) 0)).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#getAccountById(java.lang.Integer)
	 */
	@Override
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
		return (Account) this.sessionFactory.getCurrentSession().createCriteria(Account.class)
				.add(Restrictions.eq("otp", __otp)).add(Restrictions.eq("deleted", (byte) 0)).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#getAccountByToken(java.lang.String)
	 */
	@Override
	public Account getAccountByToken(String __token) {
		return (Account) this.sessionFactory.getCurrentSession().createCriteria(Account.class)
				.add(Restrictions.eq("appSession", __token)).add(Restrictions.eq("deleted", (byte) 0)).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#getAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Account> getAll() {
		return this.sessionFactory.getCurrentSession().createCriteria(Account.class)
				.add(Restrictions.eq("deleted", (byte) 0)).list();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountDAO#updateAccount(com.lasso.rest.model.
	 * datasource.Account)
	 */
	@Override
	public void updateAccount(Account __account) {
		this.sessionFactory.getCurrentSession().update(__account);
	}

}
