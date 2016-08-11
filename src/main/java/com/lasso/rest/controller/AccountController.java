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
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lasso.rest.model.api.request.AccountRegisterRequest;
import com.lasso.rest.model.api.request.DesignerRegisterRequest;
import com.lasso.rest.model.api.request.ForgotPasswordRequest;
import com.lasso.rest.model.api.request.LoginRequest;
import com.lasso.rest.model.api.request.UserRegisterRequest;
import com.lasso.rest.model.api.response.BaseResponse;
import com.lasso.rest.model.api.response.LoginResponse;
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
public class AccountController extends BaseController {

	/** The account management. */
	@Autowired
	private AccountManagement	accountManagement;

	/** The generic management. */
	@Autowired
	private GenericManagement	genericManagement;

	/**
	 * Activate account.
	 *
	 * @param __accountId the account id
	 * @param __refCode the ref code
	 * @return the response
	 */
	@GET
	@Path("/activate")
	public Response activateAccount(@QueryParam("id") int __accountId,
	        @QueryParam("ref") int __refCode) {
		if (this.accountManagement.activateAccount(__accountId, __refCode)) {
			return this.success();
		}
		else {
			return Response.status(Status.BAD_REQUEST)
			        .entity(new BaseResponse(true, "Cannot activate this account")).build();
		}
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

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse login(LoginRequest __loginRequest) {
		__loginRequest.checkNotNull();
		return accountManagement.login(__loginRequest.getEmailParam().getValue(),
		        __loginRequest.getPassword());
	}

	@GET
	@Path("/test")
	public void testAccount(@QueryParam("id") Integer __id, @QueryParam("token") String __token) {
		accountManagement.verifyAccountToken(__id, __token);
	}

	/**
	 * Register designer account.
	 *
	 * @param __request the request
	 * @param __registerAccount the register account
	 * @return the response
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	@POST
	@Path("/register/designer")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerDesignerAccount(@Context HttpServletRequest __request,
	        DesignerRegisterRequest __registerAccount) throws AddressException, MessagingException {
		return this.registerNewAccount(__request, __registerAccount);
	}

	/**
	 * Register designer account.
	 *
	 * @param __request the request
	 * @param __registerAccount the register account
	 * @return the response
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	@POST
	@Path("/register/user")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerDesignerAccount(@Context HttpServletRequest __request,
	        UserRegisterRequest __registerAccount) throws AddressException, MessagingException {
		return this.registerNewAccount(__request, __registerAccount);
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
	private Response registerNewAccount(HttpServletRequest __request,
	        AccountRegisterRequest __registerAccount) throws AddressException, MessagingException {
		__registerAccount.checkNotNull();
		Country _country = this.genericManagement
		        .getCountryIdByCode(__registerAccount.getCountryCode());
		__registerAccount.setCountry(_country);
		__registerAccount.checkCountryValid();
		String _refcode = this.accountManagement.registerUserAccount(__registerAccount);
		String _refLink = "http://" + __request.getServerName() + ":" + __request.getServerPort()
		        + __request.getContextPath() + _refcode;
		this.accountManagement.sendActivationEmail(__registerAccount.getEmail().getValue(),
		        _refLink);
		return this.success();
	}

	/**
	 * Forgot password.
	 *
	 * @param __email the email
	 * @return the response
	 * @throws NotFoundException the not found exception
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	@POST
	@Path("/reset_password")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response resetPassword(ForgotPasswordRequest __forgotPasswordRequest)
	        throws NotFoundException, AddressException, MessagingException {
		__forgotPasswordRequest.checkNotNull();
		this.accountManagement.resetPassword(__forgotPasswordRequest.getEmail().getValue());
		return this.success();
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
	 * Sets the generic management.
	 *
	 * @param __genericManagement the new generic management
	 */
	public void setGenericManagement(GenericManagement __genericManagement) {
		this.genericManagement = __genericManagement;
	}

}
