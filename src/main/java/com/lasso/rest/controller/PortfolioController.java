package com.lasso.rest.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
import com.lasso.rest.model.api.response.PortfolioOfAccountResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.service.PortfolioManagement;

/**
 * The Class PortfolioController.
 *
 * @author Paul Mai
 */
@Controller
@Lazy(false)
@Path("/portfolio")
@Produces(value = { MediaType.APPLICATION_JSON })
@AccountAuthenticate
public class PortfolioController extends BaseController {

	/** The context. */
	@Context
	private SecurityContext		context;

	/** The account management. */
	@Autowired
	private PortfolioManagement	portfolioManagement;

	/**
	 * Gets the all portfolios of account.
	 *
	 * @return the all portfolios of account
	 */
	@GET
	@Path("/me/getall")
	@AccountAllow(roles = "" + Constant.ROLE_DESIGNER, status = "" + Constant.ACC_ACTIVATE)
	public ListPortfoliosResponse getAllPortfoliosOfAccount() {
		Account _account = (Account) this.context.getUserPrincipal();
		return new ListPortfoliosResponse(this.portfolioManagement.getAllPortfolios(_account));
	}

	/**
	 * Gets the portfolio of account.
	 *
	 * @param __id the id
	 * @return the portfolio of account
	 */
	@GET
	@Path("/me/get")
	@AccountAllow(roles = "" + Constant.ROLE_DESIGNER, status = "" + Constant.ACC_ACTIVATE)
	public PortfolioOfAccountResponse getPortfolioOfAccount(@QueryParam("id") Integer __id) {
		if (__id == null) {

		}
		Account _account = (Account) this.context.getUserPrincipal();
		return new PortfolioOfAccountResponse(
				this.portfolioManagement.getPortfolio(_account, __id));
	}

	/**
	 * Sets the portfolio management.
	 *
	 * @param __portfolioManagement the new portfolio management
	 */
	public void setPortfolioManagement(PortfolioManagement __portfolioManagement) {
		this.portfolioManagement = __portfolioManagement;
	}
}
