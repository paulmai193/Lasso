package com.lasso.rest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.define.Constant;
import com.lasso.rest.controller.filter.AccountAllow;
import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.request.ChooseDesignerForOrderRequest;
import com.lasso.rest.model.api.request.ConfirmOrderRequest;
import com.lasso.rest.model.api.request.CreateNewOrderRequest;
import com.lasso.rest.model.api.request.EditOrderRequest;
import com.lasso.rest.model.api.request.UsePromoCodeForOrder;
import com.lasso.rest.model.api.response.GetOrderResponse;
import com.lasso.rest.model.api.response.JobDetailResponse;
import com.lasso.rest.model.api.response.ListDesignersResponse;
import com.lasso.rest.model.api.response.ListJobsResponse;
import com.lasso.rest.model.api.response.OrderPaymentDetailResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.PromoCode;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.service.UserManagement;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * The Class ManageOrderController.
 *
 * @author Paul Mai
 */
@Controller
@Lazy(false)
@Path("/order")
@Produces(value = { MediaType.APPLICATION_JSON })
@AccountAuthenticate
@AccountAllow(roles = "" + Constant.ROLE_USER, status = "" + Constant.ACC_ACTIVATE)
public class ManageOrderController extends BaseController {

	/** The avatar storage path. */
	private String			avatarStoragePath;

	/** The category storage path. */
	private String			categoryStoragePath;

	/** The http host. */
	private String			httpHost;

	/** The job storage path. */
	private String			jobStoragePath;

	/** The portfolio storage path. */
	private String			portfolioStoragePath;

	/** The style storage path. */
	private String			styleStoragePath;

	/** The type storage path. */
	private String			typeStoragePath;

	/** The user management. */
	@Autowired
	private UserManagement	userManagement;

	/** The validateContext. */
	@Context
	private SecurityContext	validateContext;

	/**
	 * Instantiates a new manage order controller.
	 */
	public ManageOrderController() {
	}

	/**
	 * Brief new job.
	 *
	 * @param __createNewJobRequest the create new job request
	 * @return the response
	 * @throws UnirestException the unirest exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@POST
	@Path("/create/new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response briefNewJob(CreateNewOrderRequest __createNewJobRequest)
			throws UnirestException, IOException {
		__createNewJobRequest.validate();
		Account _user = (Account) this.validateContext.getUserPrincipal();
		this.userManagement.createNewOrder(_user, __createNewJobRequest);
		return this.success();
	}

	/**
	 * Choose designer.
	 *
	 * @param __chooseDesignerForJobRequest the choose designer for job request
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
	 * Confirm order.
	 *
	 * @param __confirmOrderRequest the confirm order request
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
	 * @param __editJobRequest the edit job request
	 * @return the response
	 * @throws UnirestException the unirest exception
	 * @throws IOException Signals that an I/O exception has occurred.
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
	 * @param __index the index
	 * @param __idCategory the id category
	 * @param __idStyle the id style
	 * @param __idsType the ids type
	 * @return the designers
	 */
	@GET
	@Path("/list/designers")
	public ListDesignersResponse getDesigners(@QueryParam("index") int __index,
			@QueryParam("category_id") int __idCategory, @QueryParam("style_id") int __idStyle,
			@QueryParam("type_ids") String __idsType) {
		int _size = 8;
		List<Integer> _listIdsType = new ArrayList<>();
		String[] _s = __idsType.split(",");
		for (String _sId : _s) {
			try {
				int _id = Integer.parseInt(_sId);
				_listIdsType.add(_id);
			}
			catch (Exception _ex) {
				// Swallow this exception
			}
		}

		// Get portfolios by category and style
		List<Object[]> _datas = this.userManagement.getListPortfoliosByCondition(__index, _size,
				__idCategory, __idStyle, _listIdsType);
		String _prefixPortfolioUrl = this.httpHost + this.portfolioStoragePath;
		String _prefixAvatarUrl = this.httpHost + this.avatarStoragePath;

		return new ListDesignersResponse(_prefixAvatarUrl, _prefixPortfolioUrl, _datas,
				__index + _size);
	}

	/**
	 * Gets the job detail.
	 *
	 * @param __idJob the id job
	 * @return the job detail
	 * @throws NotFoundException the not found exception
	 */
	@GET
	@Path("/manage/detail")
	public JobDetailResponse getJobDetail(@QueryParam("job_id") int __idJob)
			throws javassist.NotFoundException {
		Account _user = (Account) this.validateContext.getUserPrincipal();

		// {job, designer_account, type, style}
		Object[] _jobDetail = this.userManagement.getJobDataOfUserById(_user, __idJob);
		if (_jobDetail == null) {
			throw new NotFoundException("Data not found");
		}
		else {
			return new JobDetailResponse(_jobDetail);
		}
	}

	/**
	 * Gets the list jobs.
	 *
	 * @return the list jobs
	 */
	@GET
	@Path("/manage")
	public ListJobsResponse getListJobs() {
		Account _user = (Account) this.validateContext.getUserPrincipal();

		// {job, designer_account, type, style}
		List<Object[]> _jobDatas = this.userManagement.getListJobsDataOfUser(_user);
		if (_jobDatas.isEmpty()) {
			throw new NotFoundException("Data not found");
		}
		else {
			return new ListJobsResponse(_jobDatas);
		}
	}

	/**
	 * Gets the order detail.
	 *
	 * @param __idJob the id job
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
			return new GetOrderResponse(_orderData, _prefixAvatarUrl, _prefixStyleUrl,
					_prefixTypeUrl, _prefixCategoryUrl, _prefixJobUrl);
		}
		catch (NullPointerException | NotFoundException _ex) {
			throw new NotFoundException("Data not found", _ex);
		}
	}

	/**
	 * Gets the payment detail.
	 *
	 * @param __idJob the id job
	 * @return the payment detail
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Path("/payment/detail")
	public OrderPaymentDetailResponse getPaymentDetail(@QueryParam("id") int __idJob) {
		Account _user = (Account) this.validateContext.getUserPrincipal();
		try {
			Object[] _paymentDetail = this.userManagement.getPaymentDetailOfOrder(_user, __idJob);
			return new OrderPaymentDetailResponse((Job) _paymentDetail[0],
					(PromoCode) _paymentDetail[1], (List<Type>) _paymentDetail[2],
					(Style) _paymentDetail[3]);
		}
		catch (NullPointerException | NotFoundException _ex) {
			throw new NotFoundException("Data not found", _ex);
		}
	}

	/**
	 * Sets the avatar storage path.
	 *
	 * @param __avatarStoragePath the avatarStoragePath to set
	 */
	public void setAvatarStoragePath(String __avatarStoragePath) {
		this.avatarStoragePath = __avatarStoragePath;
	}

	/**
	 * Sets the category storage path.
	 *
	 * @param __categoryStoragePath the categoryStoragePath to set
	 */
	public void setCategoryStoragePath(String __categoryStoragePath) {
		this.categoryStoragePath = __categoryStoragePath;
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
	 * Sets the job storage path.
	 *
	 * @param __jobStoragePath the jobStoragePath to set
	 */
	public void setJobStoragePath(String __jobStoragePath) {
		this.jobStoragePath = __jobStoragePath;
	}

	/**
	 * Sets the portfolio storage path.
	 *
	 * @param __portfolioStoragePath the portfolioStoragePath to set
	 */
	public void setPortfolioStoragePath(String __portfolioStoragePath) {
		this.portfolioStoragePath = __portfolioStoragePath;
	}

	/**
	 * Sets the style storage path.
	 *
	 * @param __styleStoragePath the styleStoragePath to set
	 */
	public void setStyleStoragePath(String __styleStoragePath) {
		this.styleStoragePath = __styleStoragePath;
	}

	/**
	 * Sets the type storage path.
	 *
	 * @param __typeStoragePath the typeStoragePath to set
	 */
	public void setTypeStoragePath(String __typeStoragePath) {
		this.typeStoragePath = __typeStoragePath;
	}

	/**
	 * Sets the user management.
	 *
	 * @param __userManagement the new user management
	 */
	public void setUserManagement(UserManagement __userManagement) {
		this.userManagement = __userManagement;
	}

	/**
	 * Sets the validate context.
	 *
	 * @param __validateContext the validateContext to set
	 */
	public void setValidateContext(SecurityContext __validateContext) {
		this.validateContext = __validateContext;
	}

	/**
	 * Use promocode for job.
	 *
	 * @param __usePromoCodeForOrder the use promo code for order
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
