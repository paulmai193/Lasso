/*
 * 
 */
package com.lasso.rest.model.push;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

class Result {

	@JsonProperty("error")
	private String error;

	@JsonProperty("message_id")
	private String message_id;

	@JsonProperty("registration_id")
	private String registration_id;

	public Result() {
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return this.error;
	}

	/**
	 * @return the message_id
	 */
	public String getMessage_id() {
		return this.message_id;
	}

	/**
	 * @return the registration_id
	 */
	public String getRegistration_id() {
		return this.registration_id;
	}

	/**
	 * @param __error
	 *            the error to set
	 */
	public void setError(String __error) {
		this.error = __error;
	}

	/**
	 * @param __message_id
	 *            the message_id to set
	 */
	public void setMessage_id(String __message_id) {
		this.message_id = __message_id;
	}

	/**
	 * @param __registration_id
	 *            the registration_id to set
	 */
	public void setRegistration_id(String __registration_id) {
		this.registration_id = __registration_id;
	}

}

// TODO: Auto-generated Javadoc
/**
 * The Class SendPushResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendPushResponse {

	/** The failure. */
	@JsonProperty("failure")
	private int failure;

	/** The results. */
	@JsonProperty("results")
	private List<Result> results;

	/** The success. */
	@JsonProperty("success")
	private int success;

	/**
	 * Instantiates a new send push response.
	 */
	public SendPushResponse() {
	}

	/**
	 * Gets the failure.
	 *
	 * @return the failure
	 */
	public int getFailure() {
		return this.failure;
	}

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<Result> getResults() {
		return this.results;
	}

	/**
	 * Gets the success.
	 *
	 * @return the success
	 */
	public int getSuccess() {
		return this.success;
	}

	/**
	 * Sets the failure.
	 *
	 * @param __failure
	 *            the failure to set
	 */
	public void setFailure(int __failure) {
		this.failure = __failure;
	}

	/**
	 * Sets the results.
	 *
	 * @param __results
	 *            the results to set
	 */
	public void setResults(List<Result> __results) {
		this.results = __results;
	}

	/**
	 * Sets the success.
	 *
	 * @param __success
	 *            the success to set
	 */
	public void setSuccess(int __success) {
		this.success = __success;
	}

}
