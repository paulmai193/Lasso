/*
 * 
 */
package com.lasso.rest.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
import com.lasso.rest.model.api.request.ForgetPasswordRequest;
import com.lasso.rest.model.api.request.LoginRequest;
import com.lasso.rest.model.api.request.ResetPasswordRequest;
import com.lasso.rest.model.api.request.UserChangeDetailRequest;
import com.lasso.rest.model.api.request.UserRegisterRequest;
import com.lasso.rest.model.api.request.VerifyAccountRequest;
import com.lasso.rest.model.api.response.BaseResponse;
import com.lasso.rest.model.api.response.DetailAccountResponse;
import com.lasso.rest.model.api.response.DetailDesignerResponse;
import com.lasso.rest.model.api.response.DetailUserResponse;
import com.lasso.rest.model.api.response.LoginResponse;
import com.lasso.rest.model.api.response.UserStatusResponse;
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

	/** The avatar storage path. */
	private String				avatarStoragePath;

	/** The generic management. */
	@Autowired
	private GenericManagement	genericManagement;

	/** The http host. */
	private String				httpHost;

	/** The request. */
	@Context
	private HttpServletRequest	request;

	/** The validate context. */
	@Context
	private SecurityContext		validateContext;

	/**
	 * Change designer detail.
	 *
	 * @param __designerChangeDetailRequest the designer change detail request
	 * @return the response
	 */
	@POST
	@Path("/change_detail/designer")
	@Consumes(MediaType.APPLICATION_JSON)
	@AccountAuthenticate
	@AccountAllow(status = "" + Constant.ACC_ACTIVATE, roles = "" + Constant.ROLE_DESIGNER)
	public Response changeDetailDesigner(
	        DesignerChangeDetailRequest __designerChangeDetailRequest) {
		return this.changeAccountDetail((Account) this.validateContext.getUserPrincipal(),
		        __designerChangeDetailRequest);
	}

	/**
	 * Change user detail.
	 *
	 * @param __userChangeDetailRequest the user change detail request
	 * @return the response
	 */
	@POST
	@Path("/change_detail/user")
	@Consumes(MediaType.APPLICATION_JSON)
	@AccountAuthenticate
	@AccountAllow(status = "" + Constant.ACC_ACTIVATE, roles = "" + Constant.ROLE_USER)
	public Response changeDetailUser(UserChangeDetailRequest __userChangeDetailRequest) {
		return this.changeAccountDetail((Account) this.validateContext.getUserPrincipal(),
		        __userChangeDetailRequest);
	}

	/**
	 * Change password.
	 *
	 * @param __changePasswordRequest the change password request
	 * @return the response
	 */
	@POST
	@Path("/change_password")
	@Consumes(MediaType.APPLICATION_JSON)
	@AccountAuthenticate
	@AccountAllow(status = "" + Constant.ACC_ACTIVATE)
	public Response changePassword(ChangePasswordRequest __changePasswordRequest) {
		__changePasswordRequest.validate();
		Account _account = (Account) this.validateContext.getUserPrincipal();
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
	 * Forget password.
	 *
	 * @param __forgetPasswordRequest the forget password request
	 * @return the response
	 * @throws NotFoundException the not found exception
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	@POST
	@Path("/forget_password")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response forgetPassword(ForgetPasswordRequest __forgetPasswordRequest)
	        throws NotFoundException, AddressException, MessagingException {
		__forgetPasswordRequest.validate();
		String _refQuery = this.accountManagement
		        .forgotPassword(__forgetPasswordRequest.getEmail().getValue());
		String _refLink = "http://"
		        + this.request.getServerName() /*
		                                        * + ":"
		                                        * + this.request.getServerPort() +
		                                        * this.request.getContextPath() + "/public"
		                                        */
		        + _refQuery;
		this.accountManagement.sendResetPasswordEmail(__forgetPasswordRequest.getEmail().getValue(),
		        _refLink);
		return this.success();
	}

	/**
	 * Gets the detail.
	 *
	 * @return the response
	 */
	@GET
	@Path("/detail/me")
	@AccountAuthenticate
	public DetailAccountResponse getDetail() {
		Account _account = (Account) this.validateContext.getUserPrincipal();
		Country _country = this.genericManagement.getCountryById(_account.getCountryId());
		String _prefixUrl = this.httpHost + this.avatarStoragePath;
		if (_account.getRole() == Constant.ROLE_DESIGNER) {
			return new DetailDesignerResponse(_account, _country, _prefixUrl);
		}
		else if (_account.getRole() == Constant.ROLE_USER) {
			return new DetailUserResponse(_account, _country, _prefixUrl);
		}
		else {
			throw new BadRequestException("This account's role not have any detail");
		}
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	@GET
	@Path("/detail/me/status")
	@AccountAuthenticate
	public UserStatusResponse getStatus() {
		Account _account = (Account) this.validateContext.getUserPrincipal();
		return new UserStatusResponse(_account.getStatus());
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
		__loginRequest.validate();
		String _prefixUrl = this.httpHost + this.avatarStoragePath;
		return this.accountManagement.login(__loginRequest.getEmailParam().getValue(),
		        __loginRequest.getPassword(), _prefixUrl);
	}

	/**
	 * Logout.
	 *
	 * @return the response
	 */
	@GET
	@Path("/logout")
	@AccountAuthenticate
	public Response logout() {
		this.accountManagement.logout((Account) this.validateContext.getUserPrincipal());
		return this.success();
	}

	/**
	 * Register designer account.
	 *
	 * @param __registerAccount the register account
	 * @return the login response
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@POST
	@Path("/register/designer")
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse registerDesignerAccount(DesignerRegisterRequest __registerAccount)
	        throws AddressException, MessagingException, URISyntaxException, IOException {
		return this.registerNewAccount(this.request, __registerAccount, "designer");
	}

	/**
	 * Register user account.
	 *
	 * @param __registerAccount the register account
	 * @return the login response
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@POST
	@Path("/register/user")
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse registerUserAccount(UserRegisterRequest __registerAccount)
	        throws AddressException, MessagingException, URISyntaxException, IOException {
		return this.registerNewAccount(this.request, __registerAccount, "user");
	}

	/**
	 * Resend activate email.
	 *
	 * @return the response
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@POST
	@Path("/resend_activate")
	@AccountAuthenticate
	public Response resendActivateEmail()
	        throws AddressException, MessagingException, URISyntaxException, IOException {
		Account _account = (Account) this.validateContext.getUserPrincipal();
		String _refQuery = this.accountManagement.resendActivate(_account);
		String _refLink = "http://" + this.request.getServerName() + _refQuery;

		String _requestType;
		if (_account.getRole() == Constant.ROLE_DESIGNER) {
			_requestType = "designer";
		}
		else {
			_requestType = "user";
		}
		this.accountManagement.sendActivationEmail(_account.getEmail(), _refLink, _requestType);
		return this.success();
	}

	/**
	 * Reset password.
	 *
	 * @param __resetPasswordRequest the reset password request
	 * @return the response
	 * @throws NotFoundException the not found exception
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	@POST
	@Path("/reset_password")
	@Consumes(MediaType.APPLICATION_JSON)
	@AccountAuthenticate
	public Response resetPassword(ResetPasswordRequest __resetPasswordRequest)
	        throws NotFoundException, AddressException, MessagingException {
		__resetPasswordRequest.validate();
		Account _account = (Account) this.validateContext.getUserPrincipal();
		this.accountManagement.resetPassword(_account, __resetPasswordRequest.getPassword());
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
	 * Sets the avatar storage path.
	 *
	 * @param __avatarStoragePath the new avatar storage path
	 */
	public void setAvatarStoragePath(String __avatarStoragePath) {
		this.avatarStoragePath = __avatarStoragePath;
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
	 * Sets the http host.
	 *
	 * @param __httpHost the new http host
	 */
	public void setHttpHost(String __httpHost) {
		this.httpHost = __httpHost;
	}

	/**
	 * Verify.
	 *
	 * @param __verifyAccountRequest the verify account request
	 * @return the login response
	 */
	@POST
	@Path("/verify")
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse verify(VerifyAccountRequest __verifyAccountRequest) {
		__verifyAccountRequest.validate();
		String _prefixUrl = this.httpHost + this.avatarStoragePath;
		return this.accountManagement.verifyAccount(__verifyAccountRequest.getOtp(), _prefixUrl);
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
		__accountChangeDetailRequest.validate();
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
	 * @param __registerType the register type: "designer" or "user"
	 * @return the base response
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 * @throws URISyntaxException the URI syntax exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private LoginResponse registerNewAccount(HttpServletRequest __request,
	        AccountRegisterRequest __registerAccount, String __registerType)
	        throws AddressException, MessagingException, URISyntaxException, IOException {
		__registerAccount.validate();
		Country _country = this.genericManagement
		        .getCountryIdByCode(__registerAccount.getCountryCode());
		if (_country == null) {
			throw new NotFoundException("Country not found");
		}
		String _refQuery = this.accountManagement.registerUserAccount(__registerAccount, _country);
		String _refLink = "http://" + __request.getServerName() + _refQuery;
		this.accountManagement.sendActivationEmail(__registerAccount.getEmail().getValue(),
		        _refLink, __registerType);
		String _prefixUrl = this.httpHost + this.avatarStoragePath;
		return this.accountManagement.login(__registerAccount.getEmail().getValue(),
		        __registerAccount.getPassword(), _prefixUrl);
	}
}
