/*
 * 
 */
package com.lasso.rest.model.push;

/**
 * The Interface PushData.
 *
 * @author Paul Mai
 */
public interface PushData {

	/** The Constant SCREEN_JOB_DETAIL. */
	public static final int	SCREEN_JOB_DETAIL		= 3;

	/** The Constant SCREEN_MESSAGE_DETAIL. */
	public static final int	SCREEN_MESSAGE_DETAIL	= 1;

	/** The Constant SCREEN_OFFER_DETAIL. */
	public static final int	SCREEN_OFFER_DETAIL		= 4;

	/** The Constant SCREEN_ORDER_DETAIL. */
	public static final int	SCREEN_ORDER_DETAIL		= 2;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	int getId();

	/**
	 * Gets the screen.
	 *
	 * @return the screen
	 */
	int getScreen();
}
