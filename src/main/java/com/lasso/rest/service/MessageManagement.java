package com.lasso.rest.service;

import java.util.List;

import com.lasso.rest.model.datasource.Account;

/**
 * The Interface MessageManagement.
 *
 * @author Paul Mai
 */
public interface MessageManagement {

	/**
	 * Gets the list messages of account.
	 *
	 * @param __account the account
	 * @return the list messages of account
	 */
	List<Object[]> getListMessagesOfAccount(Account __account);

	/**
	 * Gets the messages detail of account.
	 *
	 * @param __account the account
	 * @param __idMessage the id message
	 * @return the messages detail of account
	 */
	List<Object[]> getMessagesDetailOfAccount(Account __account, int __idMessage);

}
