/*
 * 
 */
package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Account;

/**
 * The Interface AccountDAO.
 *
 * @author Paul Mai
 */
public interface AccountDAO {

	/**
	 * Creates the account.
	 *
	 * @param __account the account
	 * @return the integer
	 */
	public Integer createAccount(Account __account);

	/**
	 * Gets the account by id.
	 *
	 * @param __id the id
	 * @return the account by id
	 */
	public Account getAccountById(Integer __id);

	/**
	 * Gets the accounts by email.
	 *
	 * @param __email the email
	 * @return the accounts by email
	 */
	public List<Account> getAccountsByEmail(String __email);

	/**
	 * Gets all accounts in DB.
	 *
	 * @return the all
	 */
	public List<Account> getAll();
}
