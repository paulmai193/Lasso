package com.lasso.rest.model.push;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendPushRequest {

	@JsonProperty("to")
	private String				to;

	@JsonProperty("registration_ids")
	private List<String>		pushTokens;

	@JsonProperty("notification")
	private PushNotification	notification;

	@JsonProperty("data")
	private PushData			data;

	public SendPushRequest() {
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return this.to;
	}

	/**
	 * @param __to the to to set
	 */
	public void setTo(String __to) {
		this.to = __to;
		this.pushTokens = null;
	}

	/**
	 * @return the pushTokens
	 */
	public List<String> getPushTokens() {
		return this.pushTokens;
	}

	/**
	 * @param __pushTokens the pushTokens to set
	 */
	public void setPushTokens(List<String> __pushTokens) {
		this.pushTokens = __pushTokens;
		this.to = null;
	}

	/**
	 * @return the notification
	 */
	public PushNotification getNotification() {
		return this.notification;
	}

	/**
	 * @param __notification the notification to set
	 */
	public void setNotification(PushNotification __notification) {
		this.notification = __notification;
	}

	/**
	 * @return the data
	 */
	public PushData getData() {
		return this.data;
	}

	/**
	 * @param __data the data to set
	 */
	public void setData(PushData __data) {
		this.data = __data;
	}

}
