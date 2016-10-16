/*
 * 
 */
package com.lasso.rest.model.push;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class SendPushRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendPushRequest {

	/** The data. */
	@JsonProperty("data")
	private PushData data;

	/** The notification. */
	@JsonProperty("notification")
	private PushNotification notification;

	/** The push tokens. */
	@JsonProperty("registration_ids")
	private List<String> pushTokens;

	/** The to. */
	@JsonProperty("to")
	private String to;

	/**
	 * Instantiates a new send push request.
	 */
	public SendPushRequest() {
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
	 * Gets the notification.
	 *
	 * @return the notification
	 */
	public PushNotification getNotification() {
		return this.notification;
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
	 * Gets the to.
	 *
	 * @return the to
	 */
	public String getTo() {
		return this.to;
	}

	/**
	 * Sets the data.
	 *
	 * @param __data
	 *            the data to set
	 */
	public void setData(PushData __data) {
		this.data = __data;
	}

	/**
	 * Sets the notification.
	 *
	 * @param __notification
	 *            the notification to set
	 */
	public void setNotification(PushNotification __notification) {
		this.notification = __notification;
	}

	/**
	 * Sets the push tokens.
	 *
	 * @param __pushTokens
	 *            the pushTokens to set
	 */
	public void setPushTokens(List<String> __pushTokens) {
		this.pushTokens = __pushTokens;
		this.to = null;
	}

	/**
	 * Sets the to.
	 *
	 * @param __to
	 *            the to to set
	 */
	public void setTo(String __to) {
		this.to = __to;
		this.pushTokens = null;
	}

}
