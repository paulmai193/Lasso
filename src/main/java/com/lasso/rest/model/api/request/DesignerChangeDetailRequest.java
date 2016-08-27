package com.lasso.rest.model.api.request;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;

/**
 * The Class DesignerChangeDetailRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DesignerChangeDetailRequest extends AccountChangeDetailRequest {

	/** The alternative contact. */
	@JsonProperty("alt_contact")
	private String	alternativeContact;

	/** The account info. */
	@JsonProperty("info")
	private String	accountInfo;

	/** The payment. */
	@JsonProperty(value = "payment")
	private Byte	payment;

	/**
	 * Instantiates a new designer change detail request.
	 */
	public DesignerChangeDetailRequest() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.AccountChangeDetailRequest#checkNotNull()
	 */
	@Override
	public void checkNotNull() throws ObjectParamException {
		super.checkNotNull();
		try {
			Assert.notNull(this.payment);
			Assert.notNull(this.alternativeContact);
		}
		catch (Throwable _ex) {
			throw new ObjectParamException("Some fields invalid");
		}
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
	 * Sets the alternative contact.
	 *
	 * @param __alternativeContact the alternativeContact to set
	 */
	public void setAlternativeContact(String __alternativeContact) {
		this.alternativeContact = __alternativeContact;
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
	 * Gets the account info.
	 *
	 * @return the accountInfo
	 */
	public String getAccountInfo() {
		return this.accountInfo;
	}

	/**
	 * Sets the account info.
	 *
	 * @param __accountInfo the accountInfo to set
	 */
	public void setAccountInfo(String __accountInfo) {
		this.accountInfo = __accountInfo;
	}

	/**
	 * Sets the payment.
	 *
	 * @param __payment the payment to set
	 */
	public void setPayment(Byte __payment) {
		this.payment = __payment;
	}

}
