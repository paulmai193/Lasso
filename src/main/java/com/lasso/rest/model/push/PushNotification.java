package com.lasso.rest.model.push;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushNotification {

	private String	title;
	private String	body;

	public PushNotification(String __title, String __body) {
		super();
		this.title = __title;
		this.body = __body;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return this.body;
	}

}
