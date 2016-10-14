/*
 * 
 */
package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

/**
 * The Class ReadMessageRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReadMessageRequest extends BaseRequest {

	/** The id root. */
	@JsonProperty("message_id")
	private Integer idMessage;

	/**
	 * Instantiates a new send message request.
	 */
	public ReadMessageRequest() {
	}

	/**
	 * Gets the id message.
	 *
	 * @return the idMessage
	 */
	public Integer getIdMessage() {
		return this.idMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.idMessage == null) {
			throw new ObjectParamException("Invalid ID message");
		}
	}

}
