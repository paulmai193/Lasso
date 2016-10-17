/*
 * 
 */
package com.lasso.rest.model.datasource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class Settings.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Settings {

	/** The Constant SETTING_OFF. */
	@JsonIgnore
	public static final String	SETTING_OFF	= "off";

	/** The Constant SETTING_ON. */
	@JsonIgnore
	public static final String	SETTING_ON	= "on";

	/** The messages. */
	@JsonProperty("messages")
	private String				messages;

	/** The news promotions. */
	@JsonProperty("news-promotions")
	private String				news_promotions;

	/** The offer. */
	@JsonProperty("offer")
	private String				offer;

	/** The status pdate. */
	@JsonProperty("status-update")
	private String				status_update;

	/**
	 * Instantiates a new settings.
	 */
	public Settings() {
	}

	/**
	 * Instantiates a new settings.
	 *
	 * @param __messages
	 *        the messages
	 * @param __offer
	 *        the offer
	 * @param __status_pdate
	 *        the status pdate
	 * @param __news_promotions
	 *        the news promotions
	 */
	public Settings(String __messages, String __offer, String __status_pdate,
			String __news_promotions) {
		super();
		this.messages = __messages;
		this.offer = __offer;
		this.status_update = __status_pdate;
		this.news_promotions = __news_promotions;
	}

	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public String getMessages() {
		return this.messages;
	}

	/**
	 * Gets the news promotions.
	 *
	 * @return the news_promotions
	 */
	public String getNews_promotions() {
		return this.news_promotions;
	}

	/**
	 * Gets the offer.
	 *
	 * @return the offer
	 */
	public String getOffer() {
		return this.offer;
	}

	/**
	 * Gets the status update.
	 *
	 * @return the status update
	 */
	public String getStatus_update() {
		return this.status_update;
	}

	/**
	 * Sets the messages.
	 *
	 * @param __messages
	 *        the messages to set
	 */
	public void setMessages(String __messages) {
		this.messages = __messages;
	}

	/**
	 * Sets the news promotions.
	 *
	 * @param __news_promotions
	 *        the news_promotions to set
	 */
	public void setNews_promotions(String __news_promotions) {
		this.news_promotions = __news_promotions;
	}

	/**
	 * Sets the offer.
	 *
	 * @param __offer
	 *        the offer to set
	 */
	public void setOffer(String __offer) {
		this.offer = __offer;
	}

	/**
	 * Sets the status update.
	 *
	 * @param __status_pdate
	 *        the new status update
	 */
	public void setStatus_update(String __status_pdate) {
		this.status_update = __status_pdate;
	}

}
