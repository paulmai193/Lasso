/*
 * 
 */
package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;
import com.lasso.rest.model.variable.EmailParam;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactUsRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactUsRequest extends FeedbackRequest {

	/** The email. */
	private EmailParam email;

	/** The email string. */
	private String emailString;

	/** The phone. */
	@JsonProperty(value = "phone")
	private String phone;

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public EmailParam getEmail() {
		return this.email;
	}

	/**
	 * Gets the email string.
	 *
	 * @return the emailString
	 */
	public String getEmailString() {
		return this.emailString;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * Sets the email.
	 *
	 * @param __email
	 *            the email to set
	 */
	public void setEmail(EmailParam __email) {
		this.email = __email;
	}

	/**
	 * Sets the email string.
	 *
	 * @param __emailString
	 *            the emailString to set
	 */
	@JsonProperty(value = "email")
	public void setEmailString(String __emailString) {
		this.emailString = __emailString;
	}

	/**
	 * Sets the phone.
	 *
	 * @param __phone
	 *            the phone to set
	 */
	public void setPhone(String __phone) {
		this.phone = __phone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.FeedbackRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		super.validate();
		if (this.emailString == null) {
			throw new ObjectParamException("Invalid email");
		} else {
			this.email = new EmailParam(this.emailString);
		}
		if (this.phone == null) {
			throw new ObjectParamException("Invalid phone");
		}
	}

}
