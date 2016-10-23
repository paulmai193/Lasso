package com.lasso.template;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lasso.rest.service.GenericManagement;

/**
 * The Class BasicEmail.
 *
 * @author Paul Mai
 */
public class BasicEmail {

	/** The generic management. */
	@Autowired
	private GenericManagement genericManagement;

	/**
	 * Sets the generic management.
	 *
	 * @param __genericManagement the new generic management
	 */
	public void setGenericManagement(GenericManagement __genericManagement) {
		this.genericManagement = __genericManagement;
	}

	/**
	 * Sets the social link.
	 *
	 * @param __content the content
	 * @return the string
	 */
	protected String setSocialLink(String __content) {
		Map<String, String> _mapConfig = this.genericManagement.loadConfig();
		String _fb = _mapConfig.get("Site.facebook_url");
		String _it = _mapConfig.get("Site.instagram_url");
		String _tw = _mapConfig.get("Site.twitter_url");
		String _googlePlay = _mapConfig.get("Site.android_download_link");
		String _appStore = _mapConfig.get("Site.ios_download_link");
		String[] _searchStrings = { "${fb}", "${it}", "${tw}", "${googleplay}", "${appstore}" };
		String[] _replaceStrings = { _fb, _it, _tw, _googlePlay, _appStore };

		return StringUtils.replaceEach(__content, _searchStrings, _replaceStrings);
	}
}
