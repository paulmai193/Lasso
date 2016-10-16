/*
 * 
 */
package com.lasso.rest.controller.filter;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.lasso.exception.AuthenticateException;
import com.lasso.rest.model.api.response.BaseResponse;
import com.lasso.rest.model.datasource.Account;

/**
 * The Class AccountAllowFilter.
 *
 * @author Paul Mai
 */
@AccountAllow
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AccountAllowFilter implements ContainerRequestFilter {

	/** The Constant LOGGER. */
	private static final Logger	LOGGER	= Logger.getLogger(AccountAllowFilter.class);

	/** The resource info. */
	@Context
	private ResourceInfo		resourceInfo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.
	 * container. ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext __requestContext) throws IOException {
		Class<?> resourceClass = this.resourceInfo.getResourceClass();
		List<String> classStatus = this.extractAllowStatus(resourceClass);
		List<String> classRoles = this.extractAllowRoles(resourceClass);

		Method resourceMethod = this.resourceInfo.getResourceMethod();
		List<String> methodStatus = this.extractAllowStatus(resourceMethod);
		List<String> methodRoles = this.extractAllowRoles(resourceMethod);

		try {
			Account _authenticatedAccount = (Account) __requestContext.getSecurityContext()
			        .getUserPrincipal();
			if (methodStatus.isEmpty()) {
				this.checkPermissions(classStatus, _authenticatedAccount.getStatus(), classRoles,
				        _authenticatedAccount.getRole());
			}
			else {
				this.checkPermissions(methodStatus, _authenticatedAccount.getStatus(), methodRoles,
				        _authenticatedAccount.getRole());
			}
		}
		catch (AuthenticateException _e) {
			AccountAllowFilter.LOGGER.warn(_e.getMessage());
			BaseResponse _errorResponse = new BaseResponse(true, _e.getMessage());
			__requestContext.abortWith(
			        Response.status(_e.getResponse().getStatus()).entity(_errorResponse).build());
		}

	}

	/**
	 * Check permissions.
	 *
	 * @param __allowedStatus
	 *        the allowed status
	 * @param __statusMustCheck
	 *        the status must check
	 * @param __allowedRoles
	 *        the allowed roles
	 * @param __roleMustCheck
	 *        the role must check
	 * @throws AuthenticateException
	 *         the authenticate exception
	 */
	private void checkPermissions(List<String> __allowedStatus, Byte __statusMustCheck,
	        List<String> __allowedRoles, Byte __roleMustCheck) throws AuthenticateException {
		if (__allowedStatus.contains(__statusMustCheck.toString())) {
			if (__allowedRoles.contains(__roleMustCheck.toString())) {
				return;
			}
			else {
				throw new AuthenticateException("User's role not allow to access",
				        Status.FORBIDDEN);
			}
		}
		else {
			throw new AuthenticateException("User's current status not allow to access",
			        Status.FORBIDDEN);
		}
	}

	/**
	 * Extract allow roles.
	 *
	 * @param __annotatedElement
	 *        the annotated element
	 * @return the list
	 */
	private List<String> extractAllowRoles(AnnotatedElement __annotatedElement) {
		if (__annotatedElement == null) {
			return new ArrayList<>();
		}
		else {
			AccountAllow _secured = __annotatedElement.getAnnotation(AccountAllow.class);
			if (_secured == null) {
				return new ArrayList<>();
			}
			else {
				String[] _allowedRoles = _secured.roles();
				return Arrays.asList(_allowedRoles);
			}
		}
	}

	/**
	 * Extract allow status.
	 *
	 * @param __annotatedElement
	 *        the annotated element
	 * @return the list allow status
	 */
	private List<String> extractAllowStatus(AnnotatedElement __annotatedElement) {
		if (__annotatedElement == null) {
			return new ArrayList<>();
		}
		else {
			AccountAllow _secured = __annotatedElement.getAnnotation(AccountAllow.class);
			if (_secured == null) {
				return new ArrayList<>();
			}
			else {
				String[] _allowedStatus = _secured.status();
				return Arrays.asList(_allowedStatus);
			}
		}
	}

}
