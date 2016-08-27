/*
 * 
 */
package com.lasso.rest.controller;

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
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.define.Constant;
import com.lasso.rest.controller.filter.AccountAllow;
import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.request.AccountChangeDetailRequest;
import com.lasso.rest.model.api.request.AccountRegisterRequest;
import com.lasso.rest.model.api.request.ChangePasswordRequest;
import com.lasso.rest.model.api.request.DesignerChangeDetailRequest;
import com.lasso.rest.model.api.request.DesignerRegisterRequest;
import com.lasso.rest.model.api.request.LoginRequest;
import com.lasso.rest.model.api.request.ResetPasswordRequest;
import com.lasso.rest.model.api.request.UserChangeDetailRequest;
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
@Lazy(false)
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
	 * Change designer detail.
	 *
	 * @param __context the context
	 * @param __designerChangeDetailRequest the designer change detail request
	 * @return the response
	 */
	@POST
	@Path("/change_detail/designer")
	@Consumes(MediaType.APPLICATION_JSON)
	@AccountAuthenticate
	public Response changeDesignerDetail(@Context SecurityContext __context,
			DesignerChangeDetailRequest __designerChangeDetailRequest) {
		return this.changeAccountDetail((Account) __context.getUserPrincipal(),
				__designerChangeDetailRequest);
	}

	/**
	 * Change password.
	 *
	 * @param __context the context
	 * @param __changePasswordRequest the change password request
	 * @return the response
	 */
	@POST
	@Path("/change_password")
	@Consumes(MediaType.APPLICATION_JSON)
	@AccountAuthenticate
	public Response changePassword(@Context SecurityContext __context,
			ChangePasswordRequest __changePasswordRequest) {
		__changePasswordRequest.checkNotNull();
		Account _account = (Account) __context.getUserPrincipal();
		if (this.accountManagement.changePassword(__changePasswordRequest.getOldPassword(),
				__changePasswordRequest.getNewPassword(), _account)) {
			return this.success();
		}
		else {
			return this.fail(new BaseResponse(true, "Current password not match."),
					Status.FORBIDDEN);
		}
	}

	/**
	 * Change user detail.
	 *
	 * @param __context the context
	 * @param __userChangeDetailRequest the user change detail request
	 * @return the response
	 */
	@POST
	@Path("/change_detail/user")
	@Consumes(MediaType.APPLICATION_JSON)
	@AccountAuthenticate
	public Response changeUserDetail(@Context SecurityContext __context,
			UserChangeDetailRequest __userChangeDetailRequest) {
		return this.changeAccountDetail((Account) __context.getUserPrincipal(),
				__userChangeDetailRequest);
	}

	/**
	 * Login.
	 *
	 * @param __loginRequest the login request
	 * @return the login response
	 */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse login(LoginRequest __loginRequest) {
		__loginRequest.checkNotNull();
		return this.accountManagement.login(__loginRequest.getEmailParam().getValue(),
				__loginRequest.getPassword());
	}

	/**
	 * Logout.
	 *
	 * @param __context the context
	 */
	@GET
	@Path("/logout")
	@AccountAuthenticate
	public void logout(@Context SecurityContext __context) {
		this.accountManagement.logout(((Account) __context.getUserPrincipal()).getId());
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
	 * Forgot password.
	 *
	 * @param __request the request
	 * @param __resetPasswordRequest the reset password request
	 * @return the response
	 * @throws NotFoundException the not found exception
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	@POST
	@Path("/reset_password")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response resetPassword(@Context HttpServletRequest __request,
			ResetPasswordRequest __resetPasswordRequest)
					throws NotFoundException, AddressException, MessagingException {
		__resetPasswordRequest.checkNotNull();
		String _refQuery = this.accountManagement
				.resetPassword(__resetPasswordRequest.getEmail().getValue());
		String _refLink = "http://" + __request.getServerName() + ":" + __request.getServerPort()
		+ __request.getContextPath() + "/public" + _refQuery;
		this.accountManagement.sendResetPasswordEmail(__resetPasswordRequest.getEmail().getValue(),
				_refLink);
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

	/**
	 * Test account.
	 *
	 * @param __context the context
	 * @return the response
	 */
	@GET
	@Path("/test/role")
	@AccountAuthenticate
	@AccountAllow(roles = "" + Constant.ROLE_USER)
	public Account testAccountRole(@Context SecurityContext __context) {
		return (Account) __context.getUserPrincipal();
	}

	/**
	 * Test account status.
	 *
	 * @param __context the context
	 * @return the account
	 */
	@GET
	@Path("/test/status")
	@AccountAuthenticate
	@AccountAllow(status = "" + Constant.ACC_NOT_ACTIVATE)
	public Account testAccountStatus(@Context SecurityContext __context) {
		return (Account) __context.getUserPrincipal();
	}

	/**
	 * Verify.
	 *
	 * @param __type the type
	 * @param __otp the otp
	 * @return the response
	 */
	@GET
	@Path("/verify")
	public Response verify(@QueryParam("type") String __type, @QueryParam("otp") String __otp) {
		if (!__type.equalsIgnoreCase("active") && !__type.equalsIgnoreCase("reset")) {
			return Response.status(Status.BAD_REQUEST)
					.entity(new BaseResponse(true, "Invalid verify type")).build();
		}
		if (this.accountManagement.verifyAccount(__otp)) {
			return this.success();
		}
		else {
			return Response.status(Status.BAD_REQUEST).entity(new BaseResponse(true, "Invalid otp"))
					.build();
		}
	}

	/**
	 * Change account detail.
	 *
	 * @param __account the account
	 * @param __accountChangeDetailRequest the account change detail request
	 * @return the response
	 */
	private Response changeAccountDetail(Account __account,
			AccountChangeDetailRequest __accountChangeDetailRequest) {
		__accountChangeDetailRequest.checkNotNull();
		Country _country = this.genericManagement
				.getCountryIdByCode(__accountChangeDetailRequest.getCountryCode());
		__accountChangeDetailRequest.setCountry(_country);
		__accountChangeDetailRequest.checkCountryValid();
		this.accountManagement.changeAccountDetail(__account, __accountChangeDetailRequest);
		return this.success();
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
		String _refQuery = this.accountManagement.registerUserAccount(__registerAccount);
		String _refLink = "http://" + __request.getServerName() + ":" + __request.getServerPort()
		+ __request.getContextPath() + "/public" + _refQuery;
		this.accountManagement.sendActivationEmail(__registerAccount.getEmail().getValue(),
				_refLink);
		return this.success();
	}
}
