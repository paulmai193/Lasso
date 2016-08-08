package com.lasso.rest.service;

import java.util.List;

import com.lasso.rest.model.datasource.Account;

public interface AccountManagement {

	public List<Account> getAllAccounts();

	public Integer registerUserAccount(Account __newAccount);
}
