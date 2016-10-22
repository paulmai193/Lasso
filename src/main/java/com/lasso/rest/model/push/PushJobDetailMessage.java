package com.lasso.rest.model.push;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class PushJobDetailMessage.
 *
 * @author Paul Mai
 */
public class PushJobDetailMessage implements PushData {

	/** The id job. */
	private int idJob;

	/**
	 * Instantiates a new push job detail message.
	 *
	 * @param __idJob the id job
	 */
	public PushJobDetailMessage(int __idJob) {
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
		return PushData.SCREEN_JOB_DETAIL;
	}

}
