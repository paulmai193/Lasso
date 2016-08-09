/*
 * 
 */
package com.lasso.rest.model.variable;

import java.util.regex.Pattern;

import com.lasso.exception.ObjectParamException;

public class EmailParam extends ContactParam {

	public EmailParam(String __param) throws ObjectParamException {
		super(__param);
	}

	@Override
	protected String parse(String __param) throws Throwable {
		if (Pattern.compile(ContactParam.EMAIL_PATTERN).matcher(__param).matches()) {
			this.contactType = ContactParam.CONTACT_EMAIL;
			return __param;
		}
		else {
			throw new ObjectParamException("Not email or phone number format");
		}
	}

}
