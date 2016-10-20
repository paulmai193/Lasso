/*
 * 
 */
package com.lasso.define;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The Class Constant.
 *
 * @author Paul Mai
 */
public class Constant {

	/** The Constant ACC_ACTIVATE. */
	public static final byte								ACC_ACTIVATE				= 1;

	/** The Constant ACC_NOT_ACTIVATE. */
	public static final byte								ACC_NOT_ACTIVATE			= 0;

	/**
	 * The Constant BROWSE_CATEGORY_STATISTIC.
	 * [Account ID - number categories browsed]
	 */
	public static final ConcurrentHashMap<Integer, Integer>	BROWSE_CATEGORY_STATISTIC	= new ConcurrentHashMap<>();

	/** The Constant GENDER_FEMALE. */
	public static final byte								GENDER_FEMALE				= 1;

	/** The Constant GENDER_MALE. */
	public static final byte								GENDER_MALE					= 0;

	/** The Constant PAGE_SIZE. */
	public static final byte								PAGE_SIZE					= 12;

	/** The Constant ROLE_DESIGNER. */
	public static final byte								ROLE_DESIGNER				= 1;

	/** The Constant ROLE_USER. */
	public static final byte								ROLE_USER					= 0;

	/** The Constant SEND_CONTACT. */
	public static final byte								SEND_CONTACT				= 0;

	/** The Constant SEND_FEEDBACK. */
	public static final byte								SEND_FEEDBACK				= 1;

}
