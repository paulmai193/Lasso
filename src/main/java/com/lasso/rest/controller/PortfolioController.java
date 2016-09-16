package com.lasso.rest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.define.Constant;
import com.lasso.rest.controller.filter.AccountAllow;
import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.request.CreatePortfolioRequest;
import com.lasso.rest.model.api.request.EditPortfolioRequest;
import com.lasso.rest.model.api.response.ListPortfoliosResponse;
import com.lasso.rest.model.api.response.PortfolioDetailResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.service.DesignerManagement;

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

	/** The designer management. */
	@Autowired
	private DesignerManagement	designerManagement;

	/** The http host. */
	private String				httpHost;

	/** The portfolio storage path. */
	private String				portfolioStoragePath;

	/** The validateContext. */
	@Context
	private SecurityContext		validateContext;

	/**
	 * Creates the portfolio.
	 *
	 * @param __createPortfolioRequest the create portfolio request
	 * @return the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPortfolio(CreatePortfolioRequest __createPortfolioRequest)
	        throws IOException {
		__createPortfolioRequest.validate();
		Account _desiger = (Account) this.validateContext.getUserPrincipal();
		this.designerManagement.createPortfolio(_desiger, __createPortfolioRequest);
		return this.success();
	}

	/**
	 * Edits the portfolio.
	 *
	 * @param __editPortfolioRequest the edit portfolio request
	 * @return the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@POST
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editPortfolio(EditPortfolioRequest __editPortfolioRequest) throws IOException {
		__editPortfolioRequest.validate();
		try {
			Account _desiger = (Account) this.validateContext.getUserPrincipal();
			Portfolio _portfolio = this.designerManagement.getPortfolio(_desiger,
			        __editPortfolioRequest.getId());
			if (_portfolio == null) {
				throw new NotFoundException("Portfolio not found");
			}
			else {
				this.designerManagement.editPortfolio(_portfolio, __editPortfolioRequest);
				return this.success();
			}
		}
		catch (NullPointerException _ex) {
			throw new NotFoundException("Portfolio not found", _ex);
		}
	}

	/**
	 * Edits the portfolio.
	 *
	 * @param __idPortfolio the id portfolio
	 * @return the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editPortfolio(@QueryParam("id") int __idPortfolio) throws IOException {
		try {
			Account _desiger = (Account) this.validateContext.getUserPrincipal();
			Portfolio _portfolio = this.designerManagement.getPortfolio(_desiger, __idPortfolio);
			if (_portfolio == null) {
				throw new NotFoundException("Portfolio not found");
			}
			else {
				this.designerManagement.deletePortfolio(_portfolio);
				return this.success();
			}
		}
		catch (NullPointerException _ex) {
			throw new NotFoundException("Portfolio not found", _ex);
		}
	}

	/**
	 * Gets the all portfolios of account.
	 *
	 * @return the all portfolios of account
	 */
	@GET
	public ListPortfoliosResponse getAllPortfoliosOfAccount() {
		Account _account = (Account) this.validateContext.getUserPrincipal();
		List<Portfolio> _portfolios = this.designerManagement.getAllPortfolios(_account);
		if (_portfolios.isEmpty()) {
			throw new NotFoundException("Data not found");
		}
		else {
			List<Object[]> _datas = new ArrayList<>(); // {portfolio, category, style}
			for (Portfolio _portfolio : _portfolios) {
				Category _category = this.designerManagement
				        .getCategoryById(_portfolio.getCategoryId());
				Style _style = this.designerManagement.getStyleById(_portfolio.getStyleId());
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
	 * @return the portfolio of account
	 */
	@GET
	@Path("/detail")
	public PortfolioDetailResponse getPortfolioOfAccount(@QueryParam("id") Integer __id) {
		if (__id == null) {
			throw new NotFoundException("Portfolio not found");
		}
		else {
			Account _account = (Account) this.validateContext.getUserPrincipal();
			Portfolio _portfolio = this.designerManagement.getPortfolio(_account, __id);
			if (_portfolio == null) {
				throw new NotFoundException("Portfolio not found");
			}
			else {
				try {
					Category _category = this.designerManagement
					        .getCategoryById(_portfolio.getCategoryId());
					Style _style = this.designerManagement.getStyleById(_portfolio.getStyleId());
					List<Type> _types = this.designerManagement
					        .getListTypesByIdPortfolio(_portfolio.getId());
					if (_types.isEmpty()) {
						throw new NotFoundException("Portfolio detail not found");
					}
					String _prefixUrl = this.httpHost + this.portfolioStoragePath;
					return new PortfolioDetailResponse(_category, _portfolio, _prefixUrl, _style,
					        _types);
				}
				catch (NullPointerException _ex) {
					throw new NotFoundException("Portfolio detail not found", _ex);
				}
			}
		}
	}

	/**
	 * Sets the designer management.
	 *
	 * @param __designerManagement the new designer management
	 */
	public void setDesignerManagement(DesignerManagement __designerManagement) {
		this.designerManagement = __designerManagement;
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
	 * Sets the portfolio storage path.
	 *
	 * @param __portfolioStoragePath the new portfolio storage path
	 */
	public void setPortfolioStoragePath(String __portfolioStoragePath) {
		this.portfolioStoragePath = __portfolioStoragePath;
	}

}
