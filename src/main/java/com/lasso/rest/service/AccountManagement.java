/*
 * 
 */
package com.lasso.rest.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.NotFoundException;

import com.lasso.rest.model.api.request.AccountChangeDetailRequest;
import com.lasso.rest.model.api.request.AccountRegisterRequest;
import com.lasso.rest.model.api.response.LoginResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Country;

/**
 * The Interface AccountManagement.
 *
 * @author Paul Mai
 */
public interface AccountManagement {

	/**
	 * Change account detail.
	 *
	 * @param __account the account
	 * @param __accountChangeDetailRequest the account change detail request
	 */
	public void changeAccountDetail(Account __account,
			AccountChangeDetailRequest __accountChangeDetailRequest);

	/**
	 * Change avatar.
	 *
	 * @param __account the account
	 * @param __avatarName the avatar name
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void changeAvatar(Account __account, String __avatarName);

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
	 * Forgot password.
	 *
	 * @param __email the email
	 * @return the query string to verify reset password request
	 * @throws NotFoundException the not found exception
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public String forgotPassword(String __email)
			throws NotFoundException, AddressException, MessagingException;

	/**
	 * Gets the all accounts.
	 *
	 * @return the all accounts in DB
	 */
	public List<Account> getAllAccounts();

	/**
	 * Gets the country.
	 *
	 * @param __account the account
	 * @return the country
	 */
	public Country getCountry(Account __account);

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
	 * @param __account the account
	 */
	public void logout(Account __account);

	/**
	 * Register user account.
	 *
	 * @param __registerAccount the register account
	 * @return the query string to activate registed account
	 */
	public String registerUserAccount(AccountRegisterRequest __registerAccount);

	/**
	 * Resend activate.
	 *
	 * @param __account the account
	 * @return the string
	 */
	public String resendActivate(Account __account);

	/**
	 * Reset password.
	 *
	 * @param __account the account will reset password
	 * @param __password the new password
	 */
	public void resetPassword(Account __account, String __password);

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
	 * Verify account.
	 *
	 * @param __otp the otp
	 * @return the login response
	 */
	public LoginResponse verifyAccount(String __otp);

}
