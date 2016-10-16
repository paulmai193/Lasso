/*
 * 
 */
package com.lasso.rest.model.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.exception.ObjectParamException;
import com.lasso.rest.model.datasource.Settings;

// TODO: Auto-generated Javadoc
/**
 * The Class SettingsRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SettingsRequest extends BaseRequest {

	/** The app settings. */
	@JsonProperty("app")
	private Settings	appSettings;

	/** The email settings. */
	@JsonProperty("email")
	private Settings	emailSettings;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.model.api.request.BaseRequest#validate()
	 */
	@Override
	public void validate() throws ObjectParamException {
		if (this.appSettings == null) {
			throw new ObjectParamException("Invalid settings app");
		}
		else if (this.appSettings.getMessages() == null
				|| this.appSettings.getNews_promotions() == null
				|| this.appSettings.getOffer() == null
				|| this.appSettings.getStatus_update() == null) {
			throw new ObjectParamException("Invalid settings app");
		}

		if (this.emailSettings == null) {
			throw new ObjectParamException("Invalid settings email");
		}
		else if (this.emailSettings.getMessages() == null
				|| this.emailSettings.getNews_promotions() == null
				|| this.emailSettings.getOffer() == null
				|| this.emailSettings.getStatus_update() == null) {
			throw new ObjectParamException("Invalid settings email");
		}
	}

}
