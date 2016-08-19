/*
 * 
 */
package com.lasso.rest.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.NotFoundException;

import com.lasso.rest.model.api.request.AccountRegisterRequest;
import com.lasso.rest.model.api.response.LoginResponse;
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
	 * Change password.
	 *
	 * @param __oldPassword the old password, required match with current password
	 * @param __newPassword the new password wanna change
	 * @param __account the account
	 * @return true, if successful
	 */
	public boolean changePassword(String __oldPassword, String __newPassword, Account __account);

	/**
	 * Gets the all accounts.
	 *
	 * @return the all accounts in DB
	 */
	public List<Account> getAllAccounts();

	/**
	 * User login by email and password.
	 *
	 * @param __email the email
	 * @param __password the password
	 * @return the login response
	 */
	public LoginResponse login(String __email, String __password);

	/**
	 * User Logout.
	 *
	 * @param __idAccount the id account
	 */
	public void logout(Integer __idAccount);

	/**
	 * Register user account.
	 *
	 * @param __registerAccount the register account
	 * @return the query string to activate registed account
	 */
	public String registerUserAccount(AccountRegisterRequest __registerAccount);

	/**
	 * Request to reset password given by email.
	 *
	 * @param __email the email
	 * @return the query string to verify reset password request
	 * @throws NotFoundException the not found exception
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public String resetPassword(String __email)
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

	/**
	 * Send reset password email.
	 *
	 * @param __email the email
	 * @param __refLink the reference link
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public void sendResetPasswordEmail(String __email, String __refLink)
	        throws AddressException, MessagingException;

	/**
	 * Validate the token to verify secured account.
	 *
	 * @param __token the token
	 * @return the verified account
	 */
	public Account validateAccountToken(String __token);

	/**
	 * Change avatar.
	 *
	 * @param __account the account
	 * @param __fileStream the file stream
	 * @param __destinationFile the destination location on disk
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void changeAvatar(Account __account, InputStream __fileStream, File __destinationFile)
	        throws IOException, IllegalArgumentException;
}
