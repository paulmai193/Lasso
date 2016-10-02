package com.lasso.rest.model.push;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class SendPushRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendPushRequest {

	/** The to. */
	@JsonProperty("to")
	private String				to;

	/** The push tokens. */
	@JsonProperty("registration_ids")
	private List<String>		pushTokens;

	/** The notification. */
	@JsonProperty("notification")
	private PushNotification	notification;

	/** The data. */
	@JsonProperty("data")
	private PushData			data;

	/**
	 * Instantiates a new send push request.
	 */
	public SendPushRequest() {
	}

	/**
	 * Gets the to.
	 *
	 * @return the to
	 */
	public String getTo() {
		return this.to;
	}

	/**
	 * Sets the to.
	 *
	 * @param __to the to to set
	 */
	public void setTo(String __to) {
		this.to = __to;
		this.pushTokens = null;
	}

	/**
	 * Gets the push tokens.
	 *
	 * @return the pushTokens
	 */
	public List<String> getPushTokens() {
		return this.pushTokens;
	}

	/**
	 * Sets the push tokens.
	 *
	 * @param __pushTokens the pushTokens to set
	 */
	public void setPushTokens(List<String> __pushTokens) {
		this.pushTokens = __pushTokens;
		this.to = null;
	}

	/**
	 * Gets the notification.
	 *
	 * @return the notification
	 */
	public PushNotification getNotification() {
		return this.notification;
	}

	/**
	 * Sets the notification.
	 *
	 * @param __notification the notification to set
	 */
	public void setNotification(PushNotification __notification) {
		this.notification = __notification;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public PushData getData() {
		return this.data;
	}

	/**
	 * Sets the data.
	 *
	 * @param __data the data to set
	 */
	public void setData(PushData __data) {
		this.data = __data;
	}

}
