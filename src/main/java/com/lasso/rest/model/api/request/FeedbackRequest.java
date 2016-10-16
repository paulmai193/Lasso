/*
 * 
 */
package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.lasso.exception.ObjectParamException;

// TODO: Auto-generated Javadoc
/**
 * The Class FeedbackRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedbackRequest extends BaseRequest {

	/** The message. */
	protected String	message;

	/** The name. */
	protected String	name;

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the message.
	 *
	 * @param __message
	 *        the message to set
	 */
	public void setMessage(String __message) {
		this.message = __message;
	}

	/**
	 * Sets the name.
	 *
	 * @param __name
	 *        the name to set
	 */
	public void setName(String __name) {
		this.name = __name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.message == null) {
			throw new ObjectParamException("Invalid message");
		}
		if (this.name == null) {
			throw new ObjectParamException("Invalid name");
		}
	}

}
