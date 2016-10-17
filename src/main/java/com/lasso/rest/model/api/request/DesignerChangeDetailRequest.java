/*
 * 
 */
package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

/**
 * The Class DesignerChangeDetailRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DesignerChangeDetailRequest extends AccountChangeDetailRequest {

	/** The account info. */
	@JsonProperty("info")
	private String	accountInfo;

	/** The alternative contact. */
	@JsonProperty("alt_contact")
	private String	alternativeContact;

	/** The payment. */
	@JsonProperty(value = "payment")
	private Byte	payment;

	/**
	 * Instantiates a new designer change detail request.
	 */
	public DesignerChangeDetailRequest() {
		super();
	}

	/**
	 * Gets the account info.
	 *
	 * @return the accountInfo
	 */
	public String getAccountInfo() {
		return this.accountInfo;
	}

	/**
	 * Gets the alternative contact.
	 *
	 * @return the alternativeContact
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
	 * Sets the account info.
	 *
	 * @param __accountInfo
	 *        the accountInfo to set
	 */
	public void setAccountInfo(String __accountInfo) {
		this.accountInfo = __accountInfo;
	}

	/**
	 * Sets the alternative contact.
	 *
	 * @param __alternativeContact
	 *        the alternativeContact to set
	 */
	public void setAlternativeContact(String __alternativeContact) {
		this.alternativeContact = __alternativeContact;
	}

	/**
	 * Sets the payment.
	 *
	 * @param __payment
	 *        the payment to set
	 */
	public void setPayment(Byte __payment) {
		this.payment = __payment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.model.api.request.AccountChangeDetailRequest#checkNotNull(
	 * )
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
