package com.lasso.rest.model.api.request;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

/**
 * The Class ChangePasswordRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangePasswordRequest extends BaseRequest {

	/** The new password. */
	@JsonProperty("new_password")
	private String	newPassword;

	/** The old password. */
	@JsonProperty("old_password")
	private String	oldPassword;

	/**
	 * Instantiates a new change password request.
	 */
	public ChangePasswordRequest() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#checkNotNull()
	 */
	@Override
	public void checkNotNull() throws ObjectParamException {
		try {
			Assert.notNull(this.newPassword);
			Assert.notNull(this.oldPassword);
		}
		catch (Throwable _ex) {
			throw new ObjectParamException("Some fields invalid");
		}
	}

	/**
	 * Gets the new password.
	 *
	 * @return the new password
	 */
	public String getNewPassword() {
		return this.newPassword;
	}

	/**
	 * Gets the old password.
	 *
	 * @return the old password
	 */
	public String getOldPassword() {
		return this.oldPassword;
	}

	/**
	 * Sets the new password.
	 *
	 * @param __newPassword the new new password
	 */
	public void setNewPassword(String __newPassword) {
		this.newPassword = __newPassword;
	}

	/**
	 * Sets the old password.
	 *
	 * @param __oldPassword the new old password
	 */
	public void setOldPassword(String __oldPassword) {
		this.oldPassword = __oldPassword;
	}

}
