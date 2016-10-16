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
public interface AccountDAO extends HibernateSession {

	/**
	 * Creates the account.
	 *
	 * @param __account
	 *        the account
	 * @return the integer
	 */
	Integer createAccount(Account __account);

	/**
	 * Gets the account by device id.
	 *
	 * @param __pushToken
	 *        the push token
	 * @return the account by device id
	 */
	List<Account> getAccountByDeviceId(String __pushToken);

	/**
	 * Gets the account by email.
	 *
	 * @param __email
	 *        the email
	 * @return the account by email
	 */
	Account getAccountByEmail(String __email);

	/**
	 * Gets the account by id.
	 *
	 * @param __id
	 *        the id
	 * @return the account by id
	 */
	Account getAccountById(Integer __id);

	/**
	 * Gets the account by otp.
	 *
	 * @param __otp
	 *        the otp
	 * @return the account by otp
	 */
	Account getAccountByOtp(String __otp);

	/**
	 * Gets the account by token.
	 *
	 * @param __token
	 *        the token
	 * @return the account by token
	 */
	Account getAccountByToken(String __token);

	/**
	 * Gets all accounts in DB.
	 *
	 * @return the all
	 */
	List<Account> getAll();

	/**
	 * Update account.
	 *
	 * @param __account
	 *        the account
	 */
	void updateAccount(Account __account);
}
