package com.lasso.rest.model.variable;

import java.util.regex.Pattern;

import com.lasso.exception.ObjectParamException;

/**
 * The Class PhoneParam.
 *
 * @author Paul Mai
 */
public class PhoneParam extends ContactParam {

	/**
	 * Instantiates a new phone param.
	 *
	 * @param __param the param
	 * @throws ObjectParamException the object param exception
	 */
	public PhoneParam(String __param) throws ObjectParamException {
		super(__param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.variable.ContactParam#parse(java.lang.String)
	 */
	@Override
	protected String parse(String __param) throws Throwable {
		if (Pattern.compile(ContactParam.PHONE_PATTERN).matcher(__param).matches()) {
			this.contactType = ContactParam.CONTACT_PHONE;
			return __param;
		}
		else {
			throw new ObjectParamException("Not phone number format");
		}
	}
}
