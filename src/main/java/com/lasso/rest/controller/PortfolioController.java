package com.lasso.rest.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.define.Constant;
import com.lasso.rest.controller.filter.AccountAllow;
import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.response.ListPortfoliosResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Country;
import com.lasso.rest.service.AccountManagement;

/**
 * The Class DesignerController.
 *
 * @author Paul Mai
 */
@Controller
@Lazy(false)
@Path("/designer")
@Produces(value = { MediaType.APPLICATION_JSON })
@AccountAuthenticate
@AccountAllow(roles = "" + Constant.ROLE_DESIGNER, status = "" + Constant.ACC_ACTIVATE)
public class DesignerController extends BaseController {

	/** The account management. */
	@Autowired
	private AccountManagement	accountManagement;

	/** The context. */
	@Context
	private SecurityContext		context;

	/**
	 * Gets the all portfolios.
	 *
	 * @return the all portfolios
	 */
	@GET
	@Path("/portfolio/all")
	public ListPortfoliosResponse getAllPortfolios() {
		Account _account = (Account) this.context.getUserPrincipal();
		return new ListPortfoliosResponse(this.accountManagement.getAllPortfolios(_account));
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	@GET
	@Path("/country")
	public Country getCountry() {
		Account _account = (Account) this.context.getUserPrincipal();
		return this.accountManagement.getCountry(_account);
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
