/*
 * 
 */
package com.lasso.rest.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.variable.EmailParam;

/**
 * The Interface AccountManagement.
 *
 * @author Paul Mai
 */
public interface AccountManagement {

	/**
	 * Check exist email.
	 *
	 * @param __email the email
	 * @return true, if this email exist
	 */
	public boolean checkExistEmail(EmailParam __email);

	/**
	 * Gets the all accounts.
	 *
	 * @return the all accounts in DB
	 */
	public List<Account> getAllAccounts();

	/**
	 * Register user account.
	 *
	 * @param __newAccount the new account
	 * @return the reference code to activate
	 */
	public String registerUserAccount(Account __newAccount);

	/**
	 * Send activation email.
	 *
	 * @param __email the email
	 * @param __refLink the reference link
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public void sendActivationEmail(String __email, String __refLink)
	        throws AddressException, MessagingException;
}
