package com.lasso.rest.model.api.request;

import org.springframework.util.Assert;

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
public class DesignerRegisterRequest extends AccountRegisterRequest {

	/** The payment. */
	@JsonProperty(value = "payment")
	private Byte payment;

	/**
	 * Instantiates a new designer register request.
	 */
	public DesignerRegisterRequest() {
		super(Constant.ROLE_DESIGNER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.AccountRegisterRequest#checkNotNull()
	 */
	@Override
	public void checkNotNull() throws ObjectParamException {
		super.checkNotNull();
		try {
			Assert.notNull(this.payment);
		}
		catch (Throwable _ex) {
			throw new ObjectParamException("Some fields invalid");
		}
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
	 * Sets the payment.
	 *
	 * @param __payment the new payment
	 */
	public void setPayment(Byte __payment) {
		this.payment = __payment;
	}
}
