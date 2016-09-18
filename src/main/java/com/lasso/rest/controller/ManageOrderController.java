package com.lasso.rest.controller;

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
import com.lasso.rest.model.api.response.JobDetailResponse;
import com.lasso.rest.model.api.response.ListJobsResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.service.UserManagement;

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
@AccountAllow(status = "" + Constant.ACC_ACTIVATE)
public class ManageOrderController extends BaseController {

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
	 * Gets the job detail.
	 *
	 * @param __idJob the id job
	 * @return the job detail
	 * @throws NotFoundException the not found exception
	 */
	@GET
	@Path("/detail")
	@AccountAllow(roles = "" + Constant.ROLE_USER)
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
	@AccountAllow(roles = "" + Constant.ROLE_USER)
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
	 * Sets the user management.
	 *
	 * @param __userManagement the new user management
	 */
	public void setUserManagement(UserManagement __userManagement) {
		this.userManagement = __userManagement;
	}

}
