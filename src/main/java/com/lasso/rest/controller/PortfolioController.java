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
import com.lasso.rest.model.api.response.PortfolioDetailResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
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

	/** The http host. */
	private String				httpHost;

	/** The portfolio management. */
	@Autowired
	private PortfolioManagement	portfolioManagement;

	/** The portfolio storage path. */
	private String				portfolioStoragePath;

	/** The project management. */
	@Autowired
	private ProjectManagement	projectManagement;

	/** The validateContext. */
	@Context
	private SecurityContext		validateContext;

	/**
	 * Gets the all portfolios of account.
	 *
	 * @return the all portfolios of account
	 */
	@GET
	@Path("/me")
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
			String _prefixUrl = this.httpHost + this.portfolioStoragePath;
			return new ListPortfoliosResponse(_datas, _prefixUrl);
		}
	}

	/**
	 * Gets the portfolio of account.
	 *
	 * @param __id the id
	 */
	@GET
	@Path("/me/detail")
	public PortfolioDetailResponse getPortfolioOfAccount(@QueryParam("id") Integer __id) {
		if (__id == null) {
			throw new NotFoundException("Portfolio not found");
		}
		else {
			Account _account = (Account) this.validateContext.getUserPrincipal();
			Portfolio _portfolio = this.portfolioManagement.getPortfolio(_account, __id);
			if (_portfolio == null) {
				throw new NotFoundException("Portfolio not found");
			}
			else {
				Category _category = this.projectManagement
				        .getCategoryById(_portfolio.getId().getCategoryId());
				Style _style = this.projectManagement.getStyleById(_portfolio.getId().getStyleId());
				Type _type = this.projectManagement
				        .getTypeByIdPortfolio(_portfolio.getId().getId());
				String __prefixUrl = this.httpHost + this.portfolioStoragePath;
				return new PortfolioDetailResponse(_portfolio, _category, _style, _type,
				        __prefixUrl);
			}
		}
	}

	/**
	 * Sets the http host.
	 *
	 * @param __httpHost the httpHost to set
	 */
	public void setHttpHost(String __httpHost) {
		this.httpHost = __httpHost;
	}

	/**
	 * Sets the portfolio management.
	 *
	 * @param __portfolioManagement the new portfolio management
	 */
	public void setPortfolioManagement(PortfolioManagement __portfolioManagement) {
		this.portfolioManagement = __portfolioManagement;
	}

	/**
	 * Sets the portfolio storage path.
	 *
	 * @param __portfolioStoragePath the new portfolio storage path
	 */
	public void setPortfolioStoragePath(String __portfolioStoragePath) {
		this.portfolioStoragePath = __portfolioStoragePath;
	}

	/**
	 * Sets the project management.
	 *
	 * @param __projectManagement the new project management
	 */
	public void setProjectManagement(ProjectManagement __projectManagement) {
		this.projectManagement = __projectManagement;
	}
}
