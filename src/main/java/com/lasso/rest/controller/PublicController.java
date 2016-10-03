package com.lasso.rest.controller;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.define.PaypalCallbackConfiguration;
import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.response.GetServiceFeeResponse;
import com.lasso.rest.model.api.response.ListCountriesResponse;
import com.lasso.rest.service.GenericManagement;
import com.paypal.ipn.IPNMessage;

/**
 * The Class PublicController.
 *
 * @author Paul Mai
 */
@Controller
@Lazy(false)
@Path("/")
public class PublicController extends BaseController {

	/** The generic management. */
	@Autowired
	private GenericManagement	genericManagement;

	/** The request. */
	@Context
	private HttpServletRequest	request;

	/**
	 * Gets the countries.
	 *
	 * @return the countries
	 */
	@GET
	@Path("/public/countries")
	@Produces(MediaType.APPLICATION_JSON)
	public ListCountriesResponse getCountries() {
		return new ListCountriesResponse(this.genericManagement.getAllCountries());
	}

	/**
	 * Gets the faq.
	 *
	 * @param __staticPage the static page
	 * @return the faq
	 */
	@GET
	@Path("/public/page/{static_page}")
	@Produces(MediaType.TEXT_HTML)
	public String getFAQ(@PathParam("static_page") String __staticPage) {
		Map<String, String> _config = this.genericManagement.loadConfig();
		switch (__staticPage) {
			case "faq":
				return _config.get("FAQ.content");

			case "term_of_service":
				return _config.get("Page.term_of_service");

			case "privacy":
				return _config.get("Page.privacy");

			case "help_center":
				return _config.get("Page.help_center");

			case "partner":
				return _config.get("Page.partner");

			default:
				return "";
		}

	}

	/**
	 * Gets the service fee.
	 *
	 * @return the service fee
	 */
	@GET
	@Path("/service_fee")
	@Produces(MediaType.APPLICATION_JSON)
	@AccountAuthenticate
	public GetServiceFeeResponse getServiceFee() {
		float _fee = this.genericManagement.getServiceFee();
		return new GetServiceFeeResponse(_fee);
	}

	/**
	 * Index.
	 *
	 * @param __request the request
	 * @return the input stream
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public InputStream index(@Context HttpServletRequest __request) {
		return __request.getServletContext().getResourceAsStream("index.jsp");
	}

	/**
	 * Receive paypal callback.
	 */
	@POST
	@Path("/paypal/callback")
	public void receivePaypalCallback() {
		// For a full list of configuration parameters refer in wiki page.
		// (https://github.com/paypal/sdk-core-java/blob/master/README.md)
		Logger.getLogger(this.getClass()).info("INSIDE PAYPAL CALLBACK");
		Logger.getLogger(this.getClass())
		.info("******* IPN RAW (name:value) pair : " + this.request.getParameterMap());
		Map<String, String> configurationMap = PaypalCallbackConfiguration.getConfig();
		IPNMessage ipnlistener = new IPNMessage(this.request, configurationMap);
		boolean isIpnVerified = ipnlistener.validate();
		String transactionType = ipnlistener.getTransactionType();
		Map<String, String> map = ipnlistener.getIpnMap();

		Logger.getLogger(this.getClass())
		.info("******* IPN VERIFY (name:value) pair : " + map + " "
				+ "######### TransactionType : " + transactionType
				+ " ======== IPN verified : " + isIpnVerified);
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
