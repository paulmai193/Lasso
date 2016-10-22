package com.lasso.rest.model.push;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class PushMessageData.
 *
 * @author Paul Mai
 */
public class PushMessageData implements PushData {

	/** The id job. */
	private int idJob;

	/**
	 * Instantiates a new push message data.
	 *
	 * @param __idJob the id job
	 */
	public PushMessageData(int __idJob) {
		this.idJob = __idJob;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.push.PushData#getId()
	 */
	@Override
	@JsonProperty("id")
	public int getId() {
		return this.idJob;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.push.PushData#getScreen()
	 */
	@Override
	@JsonProperty("screen")
	public int getScreen() {
		return PushData.SCREEN_MESSAGE_DETAIL;
	}

}
