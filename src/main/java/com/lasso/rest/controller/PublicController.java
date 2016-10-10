package com.lasso.rest.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.define.Constant;
import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.request.ContactUsRequest;
import com.lasso.rest.model.api.request.FeedbackRequest;
import com.lasso.rest.model.api.response.GetServiceFeeResponse;
import com.lasso.rest.model.api.response.ListCountriesResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.service.GenericManagement;

/**
 * The Class PublicController.
 *
 * @author Paul Mai
 */
@Controller
@Path("/")
@Lazy(false)
public class PublicController extends BaseController {

	/** The generic management. */
	@Autowired
	private GenericManagement	genericManagement;

	/** The request. */
	@Context
	private HttpServletRequest	request;

	/** The validate context. */
	@Context
	private SecurityContext		validateContext;

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
	 * @throws URISyntaxException the URI syntax exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@GET
	@Path("/public/page/{static_page}")
	@Produces(MediaType.TEXT_HTML)
	public String getFAQ(@PathParam("static_page") String __staticPage)
	        throws URISyntaxException, IOException {
		File _template = new File(
		        this.getClass().getClassLoader().getResource("staticpage.html").toURI());
		String _content = FileUtils.readFileToString(_template);
		Map<String, String> _config = this.genericManagement.loadConfig();
		switch (__staticPage) {
			case "faq":
				_content = _content.replace("${body}", _config.get("FAQ.content"));
				break;

			case "term_of_service":
				_content = _content.replace("${body}", _config.get("Page.term_of_service"));
				break;

			case "privacy":
				_content = _content.replace("${body}", _config.get("Page.privacy"));
				break;

			case "help_center":
				_content = _content.replace("${body}", _config.get("Page.help_center"));
				break;

			case "partner":
				_content = _content.replace("${body}", _config.get("Page.partner"));
				break;

			default:
				_content = "";
				break;
		}
		return _content;

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

	// /**
	// * Index.
	// *
	// * @param __request the request
	// * @param __staticResource the static resource
	// * @return the input stream
	// */
	// @GET
	// @Path("/{static_resource : .+}")
	// // @Produces(MediaType.TEXT_HTML)
	// public InputStream index(@Context HttpServletRequest __request,
	// @PathParam("static_resource") String __staticResource) {
	// if (__staticResource == null || __staticResource.isEmpty()) {
	// __staticResource = "index.jsp";
	// }
	// return __request.getServletContext().getResourceAsStream(__staticResource);
	// }

	/**
	 * Receive paypal callback.
	 *
	 * @param __multivaluedMap the multivalued map
	 * @throws UnirestException
	 */
	// @POST
	// @Path("/paypal/callback")
	// public void receivePaypalCallback(MultivaluedMap<String, String> __multivaluedMap) {
	// Logger.getLogger(this.getClass()).info("INSIDE PAYPAL CALLBACK");
	// Logger.getLogger(this.getClass())
	// .info("******* IPN RAW (name:value) pair : " + __multivaluedMap);
	// Map<String, String> configurationMap = PaypalCallbackConfiguration.getConfig();
	//
	// // Modify request body to match with IPN verify
	// Map<String, String[]> _ipnMap = new HashMap<>();
	// __multivaluedMap.forEach((_key, _value) -> {
	// String[] _values = { _value.get(0) };
	// _ipnMap.put(_key, _values);
	// });
	//
	// // Verify
	// IPNMessage ipnlistener = new IPNMessage(_ipnMap, configurationMap);
	// boolean isIpnVerified = ipnlistener.validate();
	// String transactionType = ipnlistener.getTransactionType();
	// Map<String, String> map = ipnlistener.getIpnMap();
	//
	// JsonObject _jsonObject = new JsonObject();
	// map.forEach((__t, __u) -> {
	// _jsonObject.addProperty(__t, __u);
	// });
	//
	// String _response = "******* IPN VERIFY (name:value) pair : " + _jsonObject.toString() + " "
	// + "######### TransactionType : " + transactionType + " ======== IPN verified : "
	// + isIpnVerified;
	//
	// Logger.getLogger(this.getClass()).info(_response);
	//
	//
	// }

	/**
	 * Send feed back.
	 *
	 * @param __feedbackRequest the feedback request
	 * @return the response
	 */
	@POST
	@Path("/send/feedback")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@AccountAuthenticate
	public Response sendFeedBack(FeedbackRequest __feedbackRequest) {
		__feedbackRequest.validate();
		Account _account = (Account) this.validateContext.getUserPrincipal();
		this.genericManagement.saveContact(_account.getEmail(), _account.getHandphoneNumber(),
		        __feedbackRequest.getName(), __feedbackRequest.getMessage(),
		        Constant.SEND_FEEDBACK);
		return this.success();
	}

	/**
	 * Send feed contact us.
	 *
	 * @param __contactUsRequest the contact us request
	 * @return the response
	 */
	@POST
	@Path("/send/contactus")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendFeedContactUs(ContactUsRequest __contactUsRequest) {
		__contactUsRequest.validate();
		this.genericManagement.saveContact(__contactUsRequest.getEmail().getValue(),
		        __contactUsRequest.getPhone().getValue(), __contactUsRequest.getName(),
		        __contactUsRequest.getMessage(), Constant.SEND_CONTACT);
		return this.success();
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
