package com.lasso.rest.controller.filter;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lasso.exception.AuthenticateException;
import com.lasso.rest.model.api.response.BaseResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.service.AccountManagement;

/**
 * The Class AccountAuthenticateFilter.
 *
 * @author Paul Mai
 */
@AccountAuthenticate
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AccountAuthenticateFilter implements ContainerRequestFilter {

	/** The Constant LOGGER. */
	private static final Logger	LOGGER	= Logger.getLogger(AccountAuthenticateFilter.class);

	/** The account management. */
	@Autowired
	private AccountManagement	accountManagement;

	/**
	 * Instantiates a new account authenticate filter.
	 */
	public AccountAuthenticateFilter() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.
	 * ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext __requestContext) throws IOException {
		// Get the HTTP Authorization header from the request
		String _authorizationHeader = __requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Check if the HTTP Authorization header is present and formatted correctly
		if (_authorizationHeader == null || !_authorizationHeader.startsWith("Lasso-Token ")) {
			throw new AuthenticateException("Authorization header must be provided",
			        Status.UNAUTHORIZED);
		}

		String _token = _authorizationHeader.substring("Lasso-Token".length()).trim();
		try {
			// Validate the token
			final Account _verifiedAccount = this.accountManagement.validateAccountToken(_token);
			__requestContext.setSecurityContext(new SecurityContext() {

				@Override
				public String getAuthenticationScheme() {
					return null;
				}

				@Override
				public Principal getUserPrincipal() {
					return _verifiedAccount;
				}

				@Override
				public boolean isSecure() {
					return false;
				}

				@Override
				public boolean isUserInRole(String __role) {
					if (_verifiedAccount.getRole() == Byte.parseByte(__role)) {
						return true;
					}
					else {
						return false;
					}
				}
			});
		}
		catch (AuthenticateException _e) {
			AccountAuthenticateFilter.LOGGER.warn(_e.getMessage());
			BaseResponse _errorResponse = new BaseResponse(true, _e.getMessage());
			__requestContext.abortWith(
			        Response.status(_e.getResponse().getStatus()).entity(_errorResponse).build());
		}
	}

	/**
	 * Sets the account management.
	 *
	 * @param __accountManagement the new account management
	 */
	public void setAccountManagement(AccountManagement __accountManagement) {
		this.accountManagement = __accountManagement;
	}

}
