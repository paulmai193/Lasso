package com.lasso.rest.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class ChangeAvatarResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class ChangeAvatarResponse extends BaseResponse {

	/** The link avatar. */
	@JsonProperty("link")
	private String linkAvatar;

	/**
	 * Instantiates a new change avatar response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public ChangeAvatarResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new change avatar response.
	 *
	 * @param __linkAvatar the link avatar
	 */
	public ChangeAvatarResponse(String __linkAvatar) {
		super();
		this.linkAvatar = __linkAvatar;
	}

	/**
	 * Gets the link avatar.
	 *
	 * @return the link avatar
	 */
	public String getLinkAvatar() {
		return this.linkAvatar;
	}

	/**
	 * Sets the link avatar.
	 *
	 * @param __linkAvatar the new link avatar
	 */
	public void setLinkAvatar(String __linkAvatar) {
		this.linkAvatar = __linkAvatar;
	}

}
