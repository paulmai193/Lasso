package com.lasso.rest.model.push;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class PushProjectDetailMessage.
 *
 * @author Paul Mai
 */
public class PushProjectDetailMessage implements PushData {

	/** The id job. */
	private int idJob;

	/**
	 * Instantiates a new push project detail message.
	 *
	 * @param __idJob the id job
	 */
	public PushProjectDetailMessage(int __idJob) {
		super();
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
		return PushData.SCREEN_PROJECT_DETAIL;
	}

}
