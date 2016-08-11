/*
 * 
 */
package com.lasso.rest.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.NotFoundException;

import com.lasso.rest.model.api.request.AccountRegisterRequest;
import com.lasso.rest.model.datasource.Account;

/**
 * The Interface AccountManagement.
 *
 * @author Paul Mai
 */
public interface AccountManagement {

	/**
	 * Activate account.
	 *
	 * @param __accountId the account id
	 * @param __code the code
	 * @return true, if successful
	 */
	public boolean activateAccount(Integer __accountId, int __code);

	/**
	 * Gets the all accounts.
	 *
	 * @return the all accounts in DB
	 */
	public List<Account> getAllAccounts();

	/**
	 * Register user account.
	 *
	 * @param __registerAccount the register account
	 * @return the reference code to activate
	 */
	public String registerUserAccount(AccountRegisterRequest __registerAccount);

	/**
	 * Request to reset password given by email.
	 *
	 * @param __email the email
	 * @throws NotFoundException the not found exception
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public void resetPassword(String __email)
			throws NotFoundException, AddressException, MessagingException;

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
