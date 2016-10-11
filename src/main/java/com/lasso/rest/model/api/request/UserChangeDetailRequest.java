package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

/**
 * The Class UserChangeDetailRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserChangeDetailRequest extends AccountChangeDetailRequest {

	/** The company address. */
	@JsonProperty("com_address")
	private String	companyAddress;

	/** The company name. */
	@JsonProperty("com_name")
	private String	companyName;

	/** The company phone. */
	@JsonProperty("com_phone")
	private String	companyPhone;

	/**
	 * Instantiates a new user change detail request.
	 */
	public UserChangeDetailRequest() {
		super();
	}

	/**
	 * Gets the company address.
	 *
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return this.companyAddress;
	}

	/**
	 * Gets the company name.
	 *
	 * @return the companyName
	 */
	public String getCompanyName() {
		return this.companyName;
	}

	/**
	 * Gets the company phone.
	 *
	 * @return the company phone
	 */
	public String getCompanyPhone() {
		return this.companyPhone;
	}

	/**
	 * Sets the company address.
	 *
	 * @param __companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(String __companyAddress) {
		this.companyAddress = __companyAddress;
	}

	/**
	 * Sets the company name.
	 *
	 * @param __companyName the companyName to set
	 */
	public void setCompanyName(String __companyName) {
		this.companyName = __companyName;
	}

	/**
	 * Sets the company phone.
	 *
	 * @param __companyPhone the companyPhone to set
	 */

	public void setCompanyPhone(String __companyPhone) {
		this.companyPhone = __companyPhone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.AccountChangeDetailRequest#checkNotNull()
	 */
	@Override
	public void validate() throws ObjectParamException {
		super.validate();
		if (this.companyAddress == null) {
			throw new ObjectParamException("Invalid company address");
		}
		if (this.companyName == null) {
			throw new ObjectParamException("Invalid company name");
		}
		if (this.companyPhone == null) {
			throw new ObjectParamException("Invalid phone number");
		}
	}

}
