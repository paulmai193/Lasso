package com.lasso.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.service.AccountManagement;

@Service
@Transactional
public class ImplAccountManagement implements AccountManagement {

	@Autowired
	private AccountDAO accountDAO;

	public ImplAccountManagement() {
	}

	@Override
	public List<Account> getAllAccounts() {
		return this.accountDAO.getAll();
	}

	@Override
	public Integer registerUserAccount(Account __newAccount) {
		return this.accountDAO.createAccount(__newAccount);
	}
}
