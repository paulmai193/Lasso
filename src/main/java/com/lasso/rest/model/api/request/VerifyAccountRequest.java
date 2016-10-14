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
 * The Class VerifyAccountRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyAccountRequest extends BaseRequest {

	/** The otp. */
	private String	otp;

	/** The push token. */
	@JsonProperty("push_token")
	private String	pushToken;

	/** The type. */
	private String	type;

	/**
	 * Instantiates a new verify account request.
	 */
	public VerifyAccountRequest() {
	}

	/**
	 * Gets the otp.
	 *
	 * @return the otp
	 */
	public String getOtp() {
		return this.otp;
	}

	/**
	 * Gets the push token.
	 *
	 * @return the pushToken
	 */
	public String getPushToken() {
		return this.pushToken;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets the otp.
	 *
	 * @param __otp the otp to set
	 */
	public void setOtp(String __otp) {
		this.otp = __otp;
	}

	/**
	 * Sets the push token.
	 *
	 * @param __pushToken the pushToken to set
	 */
	public void setPushToken(String __pushToken) {
		this.pushToken = __pushToken;
	}

	/**
	 * Sets the type.
	 *
	 * @param __type the type to set
	 */
	public void setType(String __type) {
		this.type = __type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.otp == null) {
			throw new ObjectParamException("Invalid OTP");
		}
		if (this.type == null || (!this.type.equalsIgnoreCase("active")
				&& !this.type.equalsIgnoreCase("reset"))) {
			throw new ObjectParamException("Invalid activate type");
		}
		if (this.pushToken == null) {
			throw new ObjectParamException("Invalid push token");
		}
	}

}
