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
 * The Class EditPortfolioRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditPortfolioRequest extends CreatePortfolioRequest {

	/** The id. */
	@JsonProperty("portfolio_id")
	private Integer id;

	/**
	 * Instantiates a new edits the portfolio request.
	 */
	public EditPortfolioRequest() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param __id
	 *            the id to set
	 */
	public void setId(Integer __id) {
		this.id = __id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.CreatePortfolioRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.id == null) {
			throw new ObjectParamException("Invalid portfolio ID");
		}
		super.validate();
	}

}
