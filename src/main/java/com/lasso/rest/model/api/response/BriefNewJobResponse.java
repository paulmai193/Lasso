/*
 * 
 */
package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class BriefNewJobResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class BriefNewJobResponse extends BaseResponse {

	/** The id job. */
	@JsonProperty("job_id")
	private int idJob;

	/**
	 * Instantiates a new brief new job response.
	 *
	 * @param __error
	 *        the error
	 */
	public BriefNewJobResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new brief new job response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 */
	public BriefNewJobResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new brief new job response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 * @param __detail
	 *        the detail
	 */
	public BriefNewJobResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new brief new job response.
	 *
	 * @param __idJob
	 *        the id job
	 */
	public BriefNewJobResponse(int __idJob) {
		super();
		this.idJob = __idJob;
	}

}
