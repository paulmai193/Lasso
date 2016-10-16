/*
 * 
 */
package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.rest.model.datasource.AccountSettings;

// TODO: Auto-generated Javadoc
/**
 * The Class SettingsResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class SettingsResponse extends BaseResponse {

	/** The settings. */
	@JsonProperty("data")
	private AccountSettings settings;

	/**
	 * Instantiates a new settings response.
	 *
	 * @param __settings
	 *            the settings
	 */
	public SettingsResponse(AccountSettings __settings) {
		super();
		this.settings = __settings;
	}

	/**
	 * Instantiates a new settings response.
	 *
	 * @param __error
	 *            the error
	 */
	public SettingsResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new settings response.
	 *
	 * @param __error
	 *            the error
	 * @param __message
	 *            the message
	 */
	public SettingsResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new settings response.
	 *
	 * @param __error
	 *            the error
	 * @param __message
	 *            the message
	 * @param __detail
	 *            the detail
	 */
	public SettingsResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Gets the settings.
	 *
	 * @return the settings
	 */
	public AccountSettings getSettings() {
		return this.settings;
	}

}
