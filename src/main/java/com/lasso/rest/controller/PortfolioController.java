package com.lasso.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
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
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.service.PortfolioManagement;
import com.lasso.rest.service.ProjectManagement;

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
@AccountAllow(roles = "" + Constant.ROLE_DESIGNER, status = "" + Constant.ACC_ACTIVATE)
public class PortfolioController extends BaseController {

	/** The account management. */
	@Autowired
	private PortfolioManagement	portfolioManagement;

	@Autowired
	private ProjectManagement	projectManagement;

	/** The validateContext. */
	@Context
	private SecurityContext		validateContext;

	private String				httpHost;
	private String				portforlioStoragePath;

	/**
	 * @param __httpHost the httpHost to set
	 */
	public void setHttpHost(String __httpHost) {
		this.httpHost = __httpHost;
	}

	/**
	 * @param __portforlioStoragePath the portforlioStoragePath to set
	 */
	public void setPortforlioStoragePath(String __portforlioStoragePath) {
		this.portforlioStoragePath = __portforlioStoragePath;
	}

	public void setProjectManagement(ProjectManagement __projectManagement) {
		this.projectManagement = __projectManagement;
	}

	/**
	 * Gets the all portfolios of account.
	 *
	 * @return the all portfolios of account
	 */
	@GET
	@Path("/me/getall")
	public ListPortfoliosResponse getAllPortfoliosOfAccount() {
		Account _account = (Account) this.validateContext.getUserPrincipal();
		List<Portfolio> _portfolios = this.portfolioManagement.getAllPortfolios(_account);
		if (_portfolios.isEmpty()) {
			throw new NotFoundException("Data not found");
		}
		else {
			List<Object[]> _datas = new ArrayList<>(); // {portfolio, category, style}
			for (Portfolio _portfolio : _portfolios) {
				Category _category = this.projectManagement
				        .getCategoryById(_portfolio.getId().getCategoryId());
				Style _style = this.projectManagement.getStyleById(_portfolio.getId().getStyleId());
				Object[] _data = { _portfolio, _category, _style };
				_datas.add(_data);
			}
			return new ListPortfoliosResponse(_datas);
		}
	}

	/**
	 * Gets the portfolio of account.
	 *
	 * @param __id the id
	 * @return the portfolio of account
	 */
	@GET
	@Path("/me/get")
	public PortfolioOfAccountResponse getPortfolioOfAccount(@QueryParam("id") Integer __id) {
		if (__id == null) {
			throw new NotFoundException("Portfolio not found");
		}
		else {
			Account _account = (Account) this.validateContext.getUserPrincipal();
			return new PortfolioOfAccountResponse(
			        this.portfolioManagement.getPortfolio(_account, __id));
		}
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
