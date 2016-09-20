package com.lasso.rest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.lasso.rest.model.api.request.CreateNewJobRequest;
import com.lasso.rest.model.api.request.EditJobRequest;
import com.lasso.rest.model.api.response.JobDetailResponse;
import com.lasso.rest.model.api.response.ListDesignersResponse;
import com.lasso.rest.model.api.response.ListJobsResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.service.UserManagement;
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
	private String			avatarStoragePath;

	/** The http host. */
	private String			httpHost;

	/** The portfolio storage path. */
	private String			portfolioStoragePath;

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
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response briefNewJob(CreateNewJobRequest __createNewJobRequest)
	        throws UnirestException, IOException {
		Account _user = (Account) this.validateContext.getUserPrincipal();
		this.userManagement.createNewJob(_user, __createNewJobRequest);
		return this.success();
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
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editJob(EditJobRequest __editJobRequest) throws UnirestException, IOException {
		Account _user = (Account) this.validateContext.getUserPrincipal();
		this.userManagement.editJob(_user, __editJobRequest);
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
	@Path("/detail")
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
	@Path("/list")
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
	 * Sets the avatar storage path.
	 *
	 * @param __avatarStoragePath the avatarStoragePath to set
	 */
	public void setAvatarStoragePath(String __avatarStoragePath) {
		this.avatarStoragePath = __avatarStoragePath;
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
	 * @param __portfolioStoragePath the portfolioStoragePath to set
	 */
	public void setPortfolioStoragePath(String __portfolioStoragePath) {
		this.portfolioStoragePath = __portfolioStoragePath;
	}

	/**
	 * Sets the user management.
	 *
	 * @param __userManagement the new user management
	 */
	public void setUserManagement(UserManagement __userManagement) {
		this.userManagement = __userManagement;
	}

}
