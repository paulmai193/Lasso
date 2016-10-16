/*
 * 
 */
package com.lasso.rest.model.variable;

import java.util.regex.Pattern;

import com.lasso.exception.ObjectParamException;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailParam.
 *
 * @author Paul Mai
 */
public class EmailParam extends ContactParam {

	/**
	 * Instantiates a new email param.
	 *
	 * @param __param
	 *            the param
	 * @throws ObjectParamException
	 *             the object param exception
	 */
	public EmailParam(String __param) throws ObjectParamException {
		super(__param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.variable.ContactParam#parse(java.lang.String)
	 */
	@Override
	protected String parse(String __param) throws Throwable {
		if (__param.isEmpty()) {
			this.contactType = ContactParam.CONTACT_EMPTY;
			return __param;
		} else if (Pattern.compile(ContactParam.EMAIL_PATTERN).matcher(__param).matches()) {
			this.contactType = ContactParam.CONTACT_EMAIL;
			return __param;
		} else {
			throw new ObjectParamException("Not email format");
		}
	}

}
