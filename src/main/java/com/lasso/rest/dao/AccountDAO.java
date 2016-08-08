package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Account;

public interface AccountDAO {

	public Integer createAccount(Account __account);

	public Account getAccountById(Integer __id);

	public List<Account> getAll();
}
