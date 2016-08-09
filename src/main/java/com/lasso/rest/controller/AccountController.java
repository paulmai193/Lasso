/*
 * 
 */
package com.lasso.rest.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lasso.rest.model.api.request.AccountRegisterRequest;
import com.lasso.rest.model.api.response.BaseResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Country;
import com.lasso.rest.service.AccountManagement;
import com.lasso.rest.service.GenericManagement;

/**
 * The Class AccountController.
 *
 * @author Paul Mai
 */
@Controller
@Path("/account")
@Produces(value = { MediaType.APPLICATION_JSON })
public class AccountController {

	/** The account management. */
	@Autowired
	private AccountManagement	accountManagement;

	/** The generic management. */
	@Autowired
	private GenericManagement	genericManagement;

	/**
	 * Gets the account management.
	 *
	 * @return the account management
	 */
	public AccountManagement getAccountManagement() {
		return this.accountManagement;
	}

	/**
	 * Sets the account management.
	 *
	 * @param __accountManagement the new account management
	 */
	public void setAccountManagement(AccountManagement __accountManagement) {
		this.accountManagement = __accountManagement;
	}

	/**
	 * Gets the generic management.
	 *
	 * @return the generic management
	 */
	public GenericManagement getGenericManagement() {
		return this.genericManagement;
	}

	/**
	 * Sets the generic management.
	 *
	 * @param __genericManagement the new generic management
	 */
	public void setGenericManagement(GenericManagement __genericManagement) {
		this.genericManagement = __genericManagement;
	}

	/**
	 * Gets the all accounts.
	 *
	 * @return the all accounts
	 */
	@GET
	public List<Account> getAllAccounts() {
		return this.accountManagement.getAllAccounts();
	}

	/**
	 * Register new account.
	 *
	 * @param __request the request
	 * @param __registerAccount the register account
	 * @return the base response
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public BaseResponse registerNewAccount(@Context HttpServletRequest __request,
	        AccountRegisterRequest __registerAccount) throws AddressException, MessagingException {
		Country _country = genericManagement.getCountryIdByCode(__registerAccount.getCountryCode());
		__registerAccount.setCountry(_country);
		String _refcode = this.accountManagement
		        .registerUserAccount(new Account(__registerAccount));
		String _refLink = "http://" + __request.getServerName() + ":" + __request.getServerPort()
		        + __request.getContextPath() + "/activate?ref=" + _refcode;
		accountManagement.sendActivationEmail(__registerAccount.getEmail(), _refLink);
		return new BaseResponse();
	}

}
