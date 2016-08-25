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
	 * Gets the account by email.
	 *
	 * @param __email the email
	 * @return the account by email
	 */
	public Account getAccountByEmail(String __email);

	/**
	 * Gets the account by id.
	 *
	 * @param __id the id
	 * @return the account by id
	 */
	public Account getAccountById(Integer __id);

	/**
	 * Gets the account by token.
	 *
	 * @param __token the token
	 * @return the account by token
	 */
	public Account getAccountByToken(String __token);

	/**
	 * Gets all accounts in DB.
	 *
	 * @return the all
	 */
	public List<Account> getAll();

	/**
	 * Update account.
	 *
	 * @param __account the account
	 */
	void updateAccount(Account __account);
}
