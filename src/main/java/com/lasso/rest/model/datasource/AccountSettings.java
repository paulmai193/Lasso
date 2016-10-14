/*
 * 
 */
package com.lasso.rest.model.datasource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class AccountSettings.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountSettings {

	/**
	 * Creates the default.
	 *
	 * @return the account settings
	 */
	public static AccountSettings createDefault() {
		Settings _emailSettings = new Settings(Settings.SETTING_ON, Settings.SETTING_ON,
				Settings.SETTING_ON, Settings.SETTING_ON);
		Settings _appSettings = new Settings(Settings.SETTING_ON, Settings.SETTING_ON,
				Settings.SETTING_ON, Settings.SETTING_ON);
		return new AccountSettings(_emailSettings, _appSettings);
	}

	/** The app settings. */
	@JsonProperty("app")
	private Settings	appSettings;

	/** The email settings. */
	@JsonProperty("email")
	private Settings	emailSettings;

	/**
	 * Instantiates a new account settings.
	 */
	public AccountSettings() {
	}

	/**
	 * Instantiates a new account settings.
	 *
	 * @param __emailSettings the email settings
	 * @param __appSettings the app settings
	 */
	public AccountSettings(Settings __emailSettings, Settings __appSettings) {
		super();
		this.emailSettings = __emailSettings;
		this.appSettings = __appSettings;
	}

	/**
	 * Gets the app settings.
	 *
	 * @return the appSettings
	 */
	public Settings getAppSettings() {
		return this.appSettings;
	}

	/**
	 * Gets the email settings.
	 *
	 * @return the emailSettings
	 */
	public Settings getEmailSettings() {
		return this.emailSettings;
	}

	/**
	 * Sets the app settings.
	 *
	 * @param __appSettings the appSettings to set
	 */
	public void setAppSettings(Settings __appSettings) {
		this.appSettings = __appSettings;
	}

	/**
	 * Sets the email settings.
	 *
	 * @param __emailSettings the emailSettings to set
	 */
	public void setEmailSettings(Settings __emailSettings) {
		this.emailSettings = __emailSettings;
	}

}
