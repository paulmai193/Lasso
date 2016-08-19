/*
 * 
 */
package com.lasso.rest.model.variable;

import java.util.regex.Pattern;

import com.lasso.exception.ObjectParamException;

/**
 * The Class ContactParam.
 *
 * @author Paul Mai
 */
public class ContactParam extends AbstractParam<String> {

	/** The Constant CONTACT_EMAIL. */
	public static final byte		CONTACT_EMAIL	= 1;

	/** The Constant CONTACT_PHONE. */
	public static final byte		CONTACT_PHONE	= 2;

	/** The Constant EMAIL_PATTERN. */
	protected static final String	EMAIL_PATTERN	= "[^@]+@[^@]+\\.[^@]+";

	/** The Constant PHONE_PATTERN. */
	protected static final String	PHONE_PATTERN	= "^[0\\+]+\\d+";

	/** The contact type. */
	protected byte					contactType;

	/**
	 * Instantiates a new contact param.
	 *
	 * @param __param the param
	 * @throws ObjectParamException the object param exception
	 */
	public ContactParam(String __param) throws ObjectParamException {
		super(__param);
	}

	/**
	 * Gets the contact type.
	 *
	 * @return the contact type
	 */
	public byte getContactType() {
		return this.contactType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nowktv.servlet.param.AbstractParam#toString()
	 */
	@Override
	public String toString() {
		switch (this.contactType) {
			case CONTACT_EMAIL:
				return "Email: " + this.getOriginalParam();

			default:
				return "Phone: " + this.getOriginalParam();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.variable.AbstractParam#parse(java.lang.String)
	 */
	@Override
	protected String parse(String __param) throws Throwable {
		if (Pattern.compile(ContactParam.EMAIL_PATTERN).matcher(__param).matches()) {
			this.contactType = ContactParam.CONTACT_EMAIL;
			return __param;
		}
		else if (Pattern.compile(ContactParam.PHONE_PATTERN).matcher(__param).matches()) {
			this.contactType = ContactParam.CONTACT_PHONE;
			return __param;
		}
		else {
			throw new ObjectParamException("Not email or phone number format");
		}
	}
}
