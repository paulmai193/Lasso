package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.define.Constant;
import com.lasso.exception.ObjectParamException;
import com.lasso.rest.model.variable.PhoneParam;

/**
 * The Class UserRegisterRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterRequest extends AccountRegisterRequest {

	/** The company address. */
	@JsonProperty("com_address")
	private String		companyAddress;

	/** The company name. */
	@JsonProperty("com_name")
	private String		companyName;

	/** The company phone. */
	private PhoneParam	companyPhone;

	/** The company phone string. */
	@JsonProperty("com_phone")
	private String		companyPhoneString;

	/**
	 * Instantiates a new user register request.
	 */
	public UserRegisterRequest() {
		super(Constant.ROLE_USER);
	}

	/**
	 * Gets the company address.
	 *
	 * @return the company address
	 */
	public String getCompanyAddress() {
		return this.companyAddress;
	}

	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return this.companyName;
	}

	/**
	 * Gets the company phone.
	 *
	 * @return the company phone
	 */
	public PhoneParam getCompanyPhone() {
		return this.companyPhone;
	}

	/**
	 * Sets the company address.
	 *
	 * @param __companyAddress the new company address
	 */
	public void setCompanyAddress(String __companyAddress) {
		this.companyAddress = __companyAddress;
	}

	/**
	 * Sets the company name.
	 *
	 * @param __companyName the new company name
	 */
	public void setCompanyName(String __companyName) {
		this.companyName = __companyName;
	}

	/**
	 * Sets the company phone.
	 *
	 * @param __companyPhone the new company phone
	 */
	public void setCompanyPhone(PhoneParam __companyPhone) {
		this.companyPhone = __companyPhone;
	}

	/**
	 * Sets the company phone string.
	 *
	 * @param __companyPhoneString the new company phone string
	 */
	public void setCompanyPhoneString(String __companyPhoneString) {
		this.companyPhoneString = __companyPhoneString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.AccountRegisterRequest#checkNotNull()
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
		if (this.companyPhoneString == null) {
			throw new ObjectParamException("Invalid company phone number");
		}
		else {
			this.companyPhone = new PhoneParam(this.companyPhoneString);
		}
	}
}
