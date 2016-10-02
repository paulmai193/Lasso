package com.lasso.rest.model.push;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class PushNotification.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushNotification {

	/** The title. */
	private String	title;
	
	/** The body. */
	private String	body;

	/**
	 * Instantiates a new push notification.
	 *
	 * @param __title the title
	 * @param __body the body
	 */
	public PushNotification(String __title, String __body) {
		super();
		this.title = __title;
		this.body = __body;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public String getBody() {
		return this.body;
	}

}
