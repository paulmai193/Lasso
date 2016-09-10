package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.define.Constant;
import com.lasso.exception.ObjectParamException;

/**
 * The Class DesignerRegisterRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DesignerRegisterRequest extends AccountRegisterRequest {

	/** The alternative contact. */
	@JsonProperty("alt_contact")
	private String	alternativeContact;

	/** The payment. */
	@JsonProperty(value = "payment")
	private Byte	payment;

	/**
	 * Instantiates a new designer register request.
	 */
	public DesignerRegisterRequest() {
		super(Constant.ROLE_DESIGNER);
	}

	/**
	 * Gets the alternative contact.
	 *
	 * @return the alternative contact
	 */
	public String getAlternativeContact() {
		return this.alternativeContact;
	}

	/**
	 * Gets the payment.
	 *
	 * @return the payment
	 */
	public Byte getPayment() {
		return this.payment;
	}

	/**
	 * Sets the alternative contact.
	 *
	 * @param __alternativeContact the new alternative contact
	 */
	public void setAlternativeContact(String __alternativeContact) {
		this.alternativeContact = __alternativeContact;
	}

	/**
	 * Sets the payment.
	 *
	 * @param __payment the new payment
	 */
	public void setPayment(Byte __payment) {
		this.payment = __payment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.AccountRegisterRequest#checkNotNull()
	 */
	@Override
	public void validate() throws ObjectParamException {
		super.validate();
		if (this.alternativeContact == null) {
			throw new ObjectParamException("Invalid alternative contact");
		}
		if (this.payment == null) {
			throw new ObjectParamException("Invalid payment value");
		}
	}
}
