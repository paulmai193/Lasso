/*
 * 
 */
package com.lasso.rest.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.define.Constant;
import com.lasso.define.JobStepConstant;
import com.lasso.rest.controller.filter.AccountAllow;
import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.request.ChooseDesignerForOrderRequest;
import com.lasso.rest.model.api.request.CompleteJobRequest;
import com.lasso.rest.model.api.request.ConfirmOrderRequest;
import com.lasso.rest.model.api.request.CreateNewOrderRequest;
import com.lasso.rest.model.api.request.EditOrderRequest;
import com.lasso.rest.model.api.request.PaymentForOrderRequest;
import com.lasso.rest.model.api.request.UsePromoCodeForOrder;
import com.lasso.rest.model.api.response.BaseResponse;
import com.lasso.rest.model.api.response.BriefNewJobResponse;
import com.lasso.rest.model.api.response.GetOrderResponse;
import com.lasso.rest.model.api.response.InvoiceResponse;
import com.lasso.rest.model.api.response.JobOfUserDetailResponse;
import com.lasso.rest.model.api.response.ListDesignersResponse;
import com.lasso.rest.model.api.response.ListJobsOfUserResponse;
import com.lasso.rest.model.api.response.OrderPaymentDetailResponse;
import com.lasso.rest.model.api.response.RatingDetailResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.AccountSettings;
import com.lasso.rest.model.datasource.AccountsRating;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.PromoCode;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.model.push.PushNotification;
import com.lasso.rest.model.push.PushProjectDetailMessage;
import com.lasso.rest.model.push.SendPushRequest;
import com.lasso.rest.service.GenericManagement;
import com.lasso.rest.service.MessageManagement;
import com.lasso.rest.service.RewardSystemManagement;
import com.lasso.rest.service.UserManagement;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * The Class ManageOrderController.
 *
 * @author Paul Mai
 */
@Controller
@Lazy(false)
@Path("/manage_order")
@Produces(value = { MediaType.APPLICATION_JSON })
@AccountAuthenticate
@AccountAllow(roles = "" + Constant.ROLE_USER, status = "" + Constant.ACC_ACTIVATE)
public class ManageOrderController extends BaseController {

	/** The avatar storage path. */
	private String					avatarStoragePath;

	/** The category storage path. */
	private String					categoryStoragePath;

	/** The generic management. */
	@Autowired
	private GenericManagement		genericManagement;

	/** The http host. */
	private String					httpHost;

	/** The job storage path. */
	private String					jobStoragePath;

	/** The message management. */
	@Autowired
	private MessageManagement		messageManagement;

	/** The paypal client id. */
	private String					paypalClientId;

	/** The paypal client secret. */
	private String					paypalClientSecret;

	/** The paypal host. */
	private String					paypalHost;

	/** The portfolio storage path. */
	private String					portfolioStoragePath;

	/** The reward system management. */
	@Autowired
	private RewardSystemManagement	rewardSystemManagement;

	/** The style storage path. */
	private String					styleStoragePath;

	/** The type storage path. */
	private String					typeStoragePath;

	/** The user management. */
	@Autowired
	private UserManagement			userManagement;

	/** The validateContext. */
	@Context
	private SecurityContext			validateContext;

	/**
	 * Instantiates a new manage order controller.
	 */
	public ManageOrderController() {
	}

	/**
	 * Brief new job.
	 *
	 * @param __createNewJobRequest
	 *        the create new job request
	 * @return the response
	 * @throws UnirestException
	 *         the unirest exception
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	@POST
	@Path("/create/new")
	@Consumes(MediaType.APPLICATION_JSON)
	public BriefNewJobResponse briefNewJob(CreateNewOrderRequest __createNewJobRequest)
	        throws UnirestException, IOException {
		__createNewJobRequest.validate();
		Account _user = (Account) this.validateContext.getUserPrincipal();
		int _idJob = this.userManagement.createNewOrder(_user, __createNewJobRequest);

		// Update reward system
		new Thread(new Runnable() {

			@Override
			public void run() {
				ManageOrderController.this.rewardSystemManagement.updateUserReward(_user);
			}
		}).start();

		return new BriefNewJobResponse(_idJob);
	}

	/**
	 * Choose designer.
	 *
	 * @param __chooseDesignerForJobRequest
	 *        the choose designer for job request
	 * @return the response
	 */
	@POST
	@Path("/create/choose_designer")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response chooseDesigner(ChooseDesignerForOrderRequest __chooseDesignerForJobRequest) {
		__chooseDesignerForJobRequest.validate();
		Account _user = (Account) this.validateContext.getUserPrincipal();
		this.userManagement.chooseDesignerForOrder(_user, __chooseDesignerForJobRequest);
		return this.success();
	}

	/**
	 * Complete job.
	 *
	 * @param __completeJobRequest
	 *        the complete job request
	 * @return the response
	 */
	@POST
	@Path("/complete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response completeJob(CompleteJobRequest __completeJobRequest) {
		__completeJobRequest.validate();
		this.userManagement.completeJob(__completeJobRequest);
		return this.success();
	}

	/**
	 * Confirm order.
	 *
	 * @param __confirmOrderRequest
	 *        the confirm order request
	 * @return the response
	 */
	@POST
	@Path("/create/confirm")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response confirmOrder(ConfirmOrderRequest __confirmOrderRequest) {
		__confirmOrderRequest.validate();
		Account _user = (Account) this.validateContext.getUserPrincipal();
		try {
			this.userManagement.confirmOrder(_user, __confirmOrderRequest);
			return this.success();
		}
		catch (NullPointerException | IllegalArgumentException _ex) {
			throw new BadRequestException(_ex.getMessage(), _ex);
		}
	}

	/**
	 * Edits the job.
	 *
	 * @param __editJobRequest
	 *        the edit job request
	 * @return the response
	 * @throws UnirestException
	 *         the unirest exception
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	@POST
	@Path("/create/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editJob(EditOrderRequest __editJobRequest)
	        throws UnirestException, IOException {
		__editJobRequest.validate();
		Account _user = (Account) this.validateContext.getUserPrincipal();
		this.userManagement.editOrder(_user, __editJobRequest);
		return this.success();
	}

	/**
	 * Gets the designers.
	 *
	 * @param __index
	 *        the index
	 * @param __idCategory
	 *        the id category
	 * @param __idsStyle
	 *        the ids style
	 * @param __idType
	 *        the id type
	 * @param __filterRelevancy
	 *        the filter relevancy.
	 *        <li>0 for default
	 *        <li>ID of style in __idsStyle parameter
	 * @param __filterBudget
	 *        the filter budget
	 *        <li>0 for default
	 *        <li>negative of budget amount for smaller than or equal the
	 *        expectation
	 *        <li>positive of budget amount for bigger than the expectation
	 * @param __filterQuality
	 *        the filter quality
	 *        <li>0 for default
	 *        <li>1 for featured
	 *        <li>2 for recommended
	 *        <li>3 for popular
	 * @return the designers
	 */
	@GET
	@Path("/list/designers")
	public ListDesignersResponse getDesigners(@QueryParam("index") int __index,
	        @QueryParam("category_id") int __idCategory, @QueryParam("style_id") String __idsStyle,
	        @QueryParam("type_id") int __idType, @QueryParam("filter_1") int __filterRelevancy,
	        @QueryParam("filter_2") double __filterBudget,
	        @QueryParam("filter_3") int __filterQuality) {
		List<Integer> _listIdsStyle = new ArrayList<>();
		String[] _s = __idsStyle.split(",");
		for (String _sId : _s) {
			try {
				int _id = Integer.parseInt(_sId);
				_listIdsStyle.add(_id);
			}
			catch (Exception _ex) {
				// Swallow this exception
			}
		}
		if (!_listIdsStyle.contains(__filterRelevancy)) {
			__filterRelevancy = 0;
		}
		Number[] _filter = { __filterRelevancy, __filterBudget, __filterQuality };

		// Get portfolios by category and style
		List<Object[]> _datas = this.userManagement.getListPortfoliosByCondition(__index,
		        Constant.PAGE_SIZE, __idCategory, _listIdsStyle, __idType, _filter);
		String _prefixPortfolioUrl = this.httpHost + this.portfolioStoragePath;
		String _prefixAvatarUrl = this.httpHost + this.avatarStoragePath;

		return new ListDesignersResponse(_prefixAvatarUrl, _prefixPortfolioUrl, _datas,
		        __index + Constant.PAGE_SIZE);
	}

	/**
	 * Gets the invoice.
	 *
	 * @param __idJob
	 *        the id job
	 * @return the invoice
	 * @throws URISyntaxException
	 *         the URI syntax exception
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	@GET
	@Path("/invoice")
	@Produces(MediaType.TEXT_HTML)
	public String getInvoice(@QueryParam("job_id") int __idJob)
	        throws URISyntaxException, IOException {
		Account _user = (Account) this.validateContext.getUserPrincipal();
		Job _job = this.userManagement.getJobById(__idJob);
		if (_job == null || _job.getDeleted().byteValue() == (byte) 1
		        || !_job.getAccountId().equals(_user.getId())) {
			throw new NotFoundException("Job not found");
		}
		if (_job.getStep().byteValue() != JobStepConstant.JOB_STEP_PAY.getStepCode()) {
			throw new BadRequestException("Job not confirm payment");
		}
		File _template = new File(
		        this.getClass().getClassLoader().getResource("invoice/invoice.html").toURI());
		String _content = FileUtils.readFileToString(_template);
		DateFormat _dateFormat = new SimpleDateFormat("dd MMMM yyyy");
		double _amount = _job.getBudget() + _job.getFee();
		if (_job.getDiscount() != null) {
			_amount -= _job.getDiscount();
		}
		String _idInvoice = ("" + LocalDate.now().getYear()).substring(2)
		        + (new DecimalFormat("0000").format(__idJob));
		return _content.replace("${job_id}", _idInvoice)
		        .replace("${date_purchase}", _dateFormat.format(_job.getModified()))
		        .replace("${date_invoice}", _dateFormat.format(new Date()))
		        .replace("${job_description}", _job.getDescription())
		        .replace("${job_amount}", "" + _amount);
	}

	/**
	 * Gets the invoice for ios.
	 *
	 * @param __idJob
	 *        the id job
	 * @return the invoice for ios
	 * @throws URISyntaxException
	 *         the URI syntax exception
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	@GET
	@Path("/invoice/ios")
	@Produces(MediaType.APPLICATION_JSON)
	public InvoiceResponse getInvoiceForIos(@QueryParam("job_id") int __idJob)
	        throws URISyntaxException, IOException {
		Account _user = (Account) this.validateContext.getUserPrincipal();
		Job _job = this.userManagement.getJobById(__idJob);
		if (_job == null || _job.getDeleted().byteValue() == (byte) 1
		        || !_job.getAccountId().equals(_user.getId())) {
			throw new NotFoundException("Job not found");
		}
		if (_job.getStep().byteValue() != JobStepConstant.JOB_STEP_PAY.getStepCode()) {
			throw new BadRequestException("Job not confirm payment");
		}
		File _template = new File(
		        this.getClass().getClassLoader().getResource("invoice/invoice.html").toURI());
		String _content = FileUtils.readFileToString(_template);
		DateFormat _dateFormat = new SimpleDateFormat("dd MMMM yyyy");
		double _amount = _job.getBudget() + _job.getFee();
		if (_job.getDiscount() != null) {
			_amount -= _job.getDiscount();
		}
		String _idInvoice = ("" + LocalDate.now().getYear()).substring(2)
		        + (new DecimalFormat("0000").format(__idJob));
		_content = _content.replace("${job_id}", _idInvoice)
		        .replace("${date_purchase}", _dateFormat.format(_job.getModified()))
		        .replace("${date_invoice}", _dateFormat.format(new Date()))
		        .replace("${job_description}", _job.getDescription())
		        .replace("${job_amount}", "" + _amount);
		return new InvoiceResponse(_content);
	}

	/**
	 * Gets the job detail.
	 *
	 * @param __idJob
	 *        the id job
	 * @return the job detail
	 * @throws NotFoundException
	 *         the not found exception
	 */
	@GET
	@Path("/manage/detail")
	public JobOfUserDetailResponse getJobDetail(@QueryParam("job_id") int __idJob)
	        throws javassist.NotFoundException {
		Account _user = (Account) this.validateContext.getUserPrincipal();

		// {job, designer_account, type, style}
		Object[] _jobDetail = this.userManagement.getJobDataOfUserById(_user, __idJob);
		if (_jobDetail == null) {
			throw new NotFoundException("Data not found");
		}
		else {
			return new JobOfUserDetailResponse(_jobDetail);
		}
	}

	/**
	 * Gets the list jobs.
	 *
	 * @return the list jobs
	 */
	@GET
	@Path("/manage")
	public ListJobsOfUserResponse getListJobs() {
		Account _user = (Account) this.validateContext.getUserPrincipal();

		// {job, designer_account, type, style}
		List<Object[]> _jobDatas = this.userManagement.getListJobsDataOfUser(_user);
		return new ListJobsOfUserResponse(_jobDatas);
	}

	/**
	 * Gets the order detail.
	 *
	 * @param __idJob
	 *        the id job
	 * @return the order detail
	 */
	@GET
	@Path("/detail/order")
	public GetOrderResponse getOrderDetail(@QueryParam("id") int __idJob) {
		try {
			Object[] _orderData = this.userManagement.getOrderDataById(__idJob);
			String _prefixAvatarUrl = this.httpHost + this.avatarStoragePath;
			String _prefixStyleUrl = this.httpHost + this.styleStoragePath;
			String _prefixTypeUrl = this.httpHost + this.typeStoragePath;
			String _prefixCategoryUrl = this.httpHost + this.categoryStoragePath;
			String _prefixJobUrl = this.httpHost + this.jobStoragePath;
			String _prefixPortfolioUrl = this.httpHost + this.portfolioStoragePath;
			return new GetOrderResponse(_orderData, _prefixAvatarUrl, _prefixStyleUrl,
			        _prefixTypeUrl, _prefixCategoryUrl, _prefixJobUrl, _prefixPortfolioUrl);
		}
		catch (NullPointerException _ex) {
			throw new NotFoundException("Data not found", _ex);
		}
	}

	/**
	 * Gets the payment detail.
	 *
	 * @param __idJob
	 *        the id job
	 * @return the payment detail
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Path("/detail/payment")
	public OrderPaymentDetailResponse getPaymentDetail(@QueryParam("id") int __idJob) {
		Account _user = (Account) this.validateContext.getUserPrincipal();
		try {
			Object[] _paymentDetail = this.userManagement.getPaymentDetailOfOrder(_user, __idJob);
			return new OrderPaymentDetailResponse((Job) _paymentDetail[0],
			        (PromoCode) _paymentDetail[1], (List<Style>) _paymentDetail[2],
			        (Type) _paymentDetail[3], (Category) _paymentDetail[4]);
		}
		catch (NullPointerException _ex) {
			throw new NotFoundException("Data not found", _ex);
		}
	}

	/**
	 * Gets the rating detail.
	 *
	 * @param __idJob
	 *        the id job
	 * @return the rating detail
	 */
	@GET
	@Path("/rating/detail")
	public RatingDetailResponse getRatingDetail(@QueryParam("job_id") int __idJob) {
		Object[] _datas = this.userManagement.getJobRatingDetail(__idJob);
		String _prefixAvatar = this.httpHost + this.avatarStoragePath;
		return new RatingDetailResponse((Account) _datas[0], (AccountsRating) _datas[1],
		        _prefixAvatar);
	}

	/**
	 * Pay job.
	 *
	 * @param __paymentForJobRequest
	 *        the payment for job request
	 * @return the response
	 */
	@POST
	@Path("/create/payment")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response payJob(PaymentForOrderRequest __paymentForJobRequest) {
		__paymentForJobRequest.validate();
		Account _user = (Account) this.validateContext.getUserPrincipal();
		this.userManagement.applyPayment(_user, __paymentForJobRequest);
		return this.success();
	}

	/**
	 * Receive paypal callback.
	 *
	 * @param __paypalCallback
	 *        the paypal callback
	 * @return the response
	 * @throws Exception
	 *         the exception
	 */
	@POST
	@Path("/paypal/callback")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response receivePaypalCallback(String __paypalCallback) throws Exception {
		Logger.getLogger(this.getClass()).debug(__paypalCallback);
		// Pre: convert callback body to json object and get payment ID
		JSONObject _jsonCallback = new JSONObject(__paypalCallback);
		String _paymentId = _jsonCallback.getJSONObject("response").getString("id");
		int _idJob = _jsonCallback.getInt("job_id");

		// Step 1: Get access-token
		HttpResponse<JsonNode> _authResponse = Unirest.post(this.paypalHost + "/oauth2/token")
		        .basicAuth(this.paypalClientId, this.paypalClientSecret)
		        .header("content-type", "application/x-www-form-urlencoded")
		        .body("grant_type=client_credentials").asJson();
		Logger.getLogger(this.getClass()).debug(_authResponse.getBody().toString());
		if (_authResponse.getStatus() != 200) {
			throw new Exception("Paypal callback error: Cannot authorize");
		}
		String _tokenType = _authResponse.getBody().getObject().getString("token_type");
		String _accessToken = _authResponse.getBody().getObject().getString("access_token");

		// Step 2: Validate payment id
		String _validateHttpRequest = this.paypalHost + "/payments/payment/" + _paymentId;
		Logger.getLogger(this.getClass()).debug(_validateHttpRequest);

		HttpResponse<JsonNode> _validateResponse = Unirest.get(_validateHttpRequest)
		        .header("content-type", "application/json")
		        .header("authorization", _tokenType + " " + _accessToken).asJson();
		Logger.getLogger(this.getClass()).debug(_validateResponse.getBody().toString());
		if (_validateResponse.getStatus() != 200) {
			throw new Exception("Paypal callback error: Cannot validate");
		}
		// JSONObject _transaction = _validateResponse.getBody().getObject()
		// .getJSONArray("transactions").getJSONObject(0);
		String _state = _validateResponse.getBody().getObject().getString("state");
		if (_state.equals("approved")) {
			Account _user = (Account) this.validateContext.getUserPrincipal();
			this.userManagement.applyPaypal(_user.getId(), _idJob);

			// Send push
			Map<String, String> _mapConfig = this.genericManagement.loadConfig();
			new Thread(new Runnable() {

				@Override
				public void run() {
					AccountSettings _accountSettings;
					try {
						_accountSettings = _user.getSettings();

						// Send push in-app
						if (_accountSettings.getAppSettings().getStatus_update() != null
						        && _accountSettings.getAppSettings().getStatus_update()
						                .equals("on")) {
							SendPushRequest _pushRequest = new SendPushRequest();
							_pushRequest.setNotification(new PushNotification(
							        _mapConfig.get("EmailTemplate.designer_received_project_title"),
							        "EmailTemplate.designer_received_project_desc"));
							_pushRequest.setData(new PushProjectDetailMessage(_idJob));
							_pushRequest.setTo(_user.getDeviceId());
							ManageOrderController.this.messageManagement.sendPush(_pushRequest);
						}
					}
					catch (Exception _ex) {
						Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
					}
				}
			}).start();

			return this.success();
		}
		else {
			return this.fail(new BaseResponse(true, "Payment not approve"), Status.FORBIDDEN);
		}
	}

	/**
	 * Sets the avatar storage path.
	 *
	 * @param __avatarStoragePath
	 *        the avatarStoragePath to set
	 */
	public void setAvatarStoragePath(String __avatarStoragePath) {
		this.avatarStoragePath = __avatarStoragePath;
	}

	/**
	 * Sets the category storage path.
	 *
	 * @param __categoryStoragePath
	 *        the categoryStoragePath to set
	 */
	public void setCategoryStoragePath(String __categoryStoragePath) {
		this.categoryStoragePath = __categoryStoragePath;
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
	 * @param __httpHost
	 *        the httpHost to set
	 */
	public void setHttpHost(String __httpHost) {
		this.httpHost = __httpHost;
	}

	/**
	 * Sets the job storage path.
	 *
	 * @param __jobStoragePath
	 *        the jobStoragePath to set
	 */
	public void setJobStoragePath(String __jobStoragePath) {
		this.jobStoragePath = __jobStoragePath;
	}

	/**
	 * Sets the message management.
	 *
	 * @param __messageManagement the new message management
	 */
	public void setMessageManagement(MessageManagement __messageManagement) {
		this.messageManagement = __messageManagement;
	}

	/**
	 * Sets the paypal client id.
	 *
	 * @param __paypalClientId
	 *        the paypalClientId to set
	 */
	public void setPaypalClientId(String __paypalClientId) {
		this.paypalClientId = __paypalClientId;
	}

	/**
	 * Sets the paypal client secret.
	 *
	 * @param __paypalClientSecret
	 *        the paypalClientSecret to set
	 */
	public void setPaypalClientSecret(String __paypalClientSecret) {
		this.paypalClientSecret = __paypalClientSecret;
	}

	/**
	 * Sets the paypal host.
	 *
	 * @param __paypalHost
	 *        the new paypal host
	 */
	public void setPaypalHost(String __paypalHost) {
		this.paypalHost = __paypalHost;
	}

	/**
	 * Sets the portfolio storage path.
	 *
	 * @param __portfolioStoragePath
	 *        the portfolioStoragePath to set
	 */
	public void setPortfolioStoragePath(String __portfolioStoragePath) {
		this.portfolioStoragePath = __portfolioStoragePath;
	}

	/**
	 * Sets the reward system management.
	 *
	 * @param __rewardSystemManagement the new reward system management
	 */
	public void setRewardSystemManagement(RewardSystemManagement __rewardSystemManagement) {
		this.rewardSystemManagement = __rewardSystemManagement;
	}

	/**
	 * Sets the style storage path.
	 *
	 * @param __styleStoragePath
	 *        the styleStoragePath to set
	 */
	public void setStyleStoragePath(String __styleStoragePath) {
		this.styleStoragePath = __styleStoragePath;
	}

	/**
	 * Sets the type storage path.
	 *
	 * @param __typeStoragePath
	 *        the typeStoragePath to set
	 */
	public void setTypeStoragePath(String __typeStoragePath) {
		this.typeStoragePath = __typeStoragePath;
	}

	/**
	 * Sets the user management.
	 *
	 * @param __userManagement
	 *        the new user management
	 */
	public void setUserManagement(UserManagement __userManagement) {
		this.userManagement = __userManagement;
	}

	/**
	 * Sets the validate context.
	 *
	 * @param __validateContext
	 *        the validateContext to set
	 */
	public void setValidateContext(SecurityContext __validateContext) {
		this.validateContext = __validateContext;
	}

	/**
	 * Use promocode for job.
	 *
	 * @param __usePromoCodeForOrder
	 *        the use promo code for order
	 * @return the response
	 */
	@POST
	@Path("/create/use_promo")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response usePromocodeForJob(UsePromoCodeForOrder __usePromoCodeForOrder) {
		__usePromoCodeForOrder.validate();
		Account _user = (Account) this.validateContext.getUserPrincipal();
		this.userManagement.applyPromoCodeForOrder(_user, __usePromoCodeForOrder);
		return this.success();
	}

}
