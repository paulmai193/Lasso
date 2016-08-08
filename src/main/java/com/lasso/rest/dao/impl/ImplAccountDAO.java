package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.model.datasource.Account;

@Repository
public class ImplAccountDAO implements AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public ImplAccountDAO() {
	}

	public Integer createAccount(Account __account) {
		return (Integer) this.sessionFactory.getCurrentSession().save(__account);
	}

	public Account getAccountById(Integer __id) {
		return this.sessionFactory.getCurrentSession().load(Account.class, __id);
	}

	@SuppressWarnings("unchecked")
	public List<Account> getAll() {
		return this.sessionFactory.getCurrentSession().createCriteria(Account.class).list();
	}

}
