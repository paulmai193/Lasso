package com.lasso.template;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class BasicEmail.
 *
 * @author Paul Mai
 */
public class BasicEmail {

	/** The map config. */
	private final Map<String, String> MAP_CONFIG;

	/**
	 * Instantiates a new basic email.
	 *
	 * @param ___mapConfig the map config
	 */
	public BasicEmail(Map<String, String> ___mapConfig) {
		super();
		this.MAP_CONFIG = ___mapConfig;
	}

	/**
	 * Sets the social link.
	 *
	 * @param __content the content
	 * @return the string
	 */
	protected String setSocialLink(String __content) {

		String _fb = this.MAP_CONFIG.get("Site.facebook_url");
		String _it = this.MAP_CONFIG.get("Site.instagram_url");
		String _tw = this.MAP_CONFIG.get("Site.twitter_url");
		String _googlePlay = this.MAP_CONFIG.get("Site.android_download_link");
		String _appStore = this.MAP_CONFIG.get("Site.ios_download_link");
		String[] _searchStrings = { "${fb}", "${it}", "${tw}", "${googleplay}", "${appstore}" };
		String[] _replaceStrings = { _fb, _it, _tw, _googlePlay, _appStore };

		return StringUtils.replaceEach(__content, _searchStrings, _replaceStrings);
	}
}
