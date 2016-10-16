/*
 * 
 */
package com.lasso.rest.controller;

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
import org.springframework.stereotype.Controller;

import com.lasso.define.Constant;
import com.lasso.rest.controller.filter.AccountAllow;
import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.request.ConfirmOfferRequest;
import com.lasso.rest.model.api.request.CounterOfferRequest;
import com.lasso.rest.model.api.request.UpdateJobStageRequest;
import com.lasso.rest.model.api.response.GetOfferResponse;
import com.lasso.rest.model.api.response.JobOfDesignerDetailResponse;
import com.lasso.rest.model.api.response.ListJobsOfDesignerResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.service.DesignerManagement;

// TODO: Auto-generated Javadoc
/**
 * The Class ManageProjectController.
 *
 * @author Paul Mai
 */
@Controller
@Path("/manage_project")
@Produces(MediaType.APPLICATION_JSON)
@AccountAuthenticate
@AccountAllow(roles = "" + Constant.ROLE_DESIGNER, status = "" + Constant.ACC_ACTIVATE)
public class ManageProjectController extends BaseController {

	/** The avatar storage path. */
	private String				avatarStoragePath;

	/** The category storage path. */
	private String				categoryStoragePath;

	/** The user management. */
	@Autowired
	private DesignerManagement	designerManagement;

	/** The http host. */
	private String				httpHost;

	/** The job storage path. */
	private String				jobStoragePath;

	/** The validateContext. */
	@Context
	private SecurityContext		validateContext;

	/**
	 * Confirm offer.
	 *
	 * @param __counterOfferRequest
	 *        the counter offer request
	 * @return the response
	 */
	@POST
	@Path("/offer/confirm")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response confirmOffer(ConfirmOfferRequest __counterOfferRequest) {
		__counterOfferRequest.validate();
		Account _designer = (Account) this.validateContext.getUserPrincipal();
		this.designerManagement.confirmOffer(_designer, __counterOfferRequest);
		return this.success();
	}

	/**
	 * Counter offer.
	 *
	 * @param __counterOfferRequest
	 *        the counter offer request
	 * @return the response
	 */
	@POST
	@Path("/offer/counter")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response counterOffer(CounterOfferRequest __counterOfferRequest) {
		__counterOfferRequest.validate();
		Account _designer = (Account) this.validateContext.getUserPrincipal();
		this.designerManagement.counterOffer(_designer, __counterOfferRequest);
		return this.success();
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
	public JobOfDesignerDetailResponse getJobDetail(@QueryParam("job_id") int __idJob)
			throws javassist.NotFoundException {
		Account _designer = (Account) this.validateContext.getUserPrincipal();

		// {job, designer_account, type, style}
		Object[] _jobDetail = this.designerManagement.getJobDataOfDesignerById(_designer, __idJob);
		if (_jobDetail == null) {
			throw new NotFoundException("Data not found");
		}
		else {
			return new JobOfDesignerDetailResponse(_jobDetail);
		}
	}

	/**
	 * Gets the list jobs.
	 *
	 * @return the list jobs
	 */
	@GET
	@Path("/manage")
	public ListJobsOfDesignerResponse getListJobs() {
		Account _designer = (Account) this.validateContext.getUserPrincipal();

		// {job, user name, type, style}
		List<Object[]> _jobDatas = this.designerManagement.getListJobsDataOfDesigner(_designer);
		return new ListJobsOfDesignerResponse(_jobDatas);
	}

	/**
	 * Gets the order detail.
	 *
	 * @param __idJob
	 *        the id job
	 * @return the order detail
	 */
	@GET
	@Path("/offer/detail")
	public GetOfferResponse getOfferDetail(@QueryParam("job_id") int __idJob) {
		Account _designer = (Account) this.validateContext.getUserPrincipal();
		try {
			Object[] _orderData = this.designerManagement.getOfferDataById(_designer, __idJob);
			String _prefixAvatarUrl = this.httpHost + this.avatarStoragePath;
			String _prefixCategoryUrl = this.httpHost + this.categoryStoragePath;
			String _prefixJobUrl = this.httpHost + this.jobStoragePath;
			return new GetOfferResponse(_orderData, _prefixAvatarUrl, _prefixCategoryUrl,
					_prefixJobUrl);
		}
		catch (NullPointerException | NotFoundException _ex) {
			throw new NotFoundException("Data not found", _ex);
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
	 * Sets the designer management.
	 *
	 * @param __designerManagement
	 *        the designerManagement to set
	 */
	public void setDesignerManagement(DesignerManagement __designerManagement) {
		this.designerManagement = __designerManagement;
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
	 * Update stage.
	 *
	 * @param __updateJobStageRequest
	 *        the update job stage request
	 * @return the response
	 */
	@POST
	@Path("/update_stage")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateStage(UpdateJobStageRequest __updateJobStageRequest) {
		__updateJobStageRequest.validate();
		Account _designer = (Account) this.validateContext.getUserPrincipal();
		this.designerManagement.updateStage(_designer, __updateJobStageRequest);
		return this.success();
	}
}
