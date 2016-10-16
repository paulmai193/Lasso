/*
 * 
 */
package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

// TODO: Auto-generated Javadoc
/**
 * The Class SendMessageRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendMessageRequest extends BaseRequest {

	/** The id root. */
	@JsonProperty("root_id")
	private Integer idRoot;

	/** The message. */
	@JsonProperty("message")
	private String message;

	/**
	 * Instantiates a new send message request.
	 */
	public SendMessageRequest() {
	}

	/**
	 * Gets the id root.
	 *
	 * @return the idRoot
	 */
	public Integer getIdRoot() {
		return this.idRoot;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.idRoot == null) {
			throw new ObjectParamException("Invalid root message");
		}
		if (this.message == null) {
			throw new ObjectParamException("Invalid message");
		}
	}

}
