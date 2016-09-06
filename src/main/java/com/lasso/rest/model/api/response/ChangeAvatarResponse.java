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

	/** The original avatar. */
	@JsonProperty("avatar")
	private AvatarLink avatarLink;

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
	 * @param __original the original avatar
	 * @param __small the small avatar
	 * @param __icon the icon avatar
	 */
	public ChangeAvatarResponse(String __original, String __small, String __icon) {
		super();
		this.avatarLink = new AvatarLink(__original, __icon, __small);
	}

	/**
	 * Gets the avatar link.
	 *
	 * @return the avatarLink
	 */
	public AvatarLink getAvatarLink() {
		return this.avatarLink;
	}

	/**
	 * Sets the avatar link.
	 *
	 * @param __avatarLink the avatarLink to set
	 */
	public void setAvatarLink(AvatarLink __avatarLink) {
		this.avatarLink = __avatarLink;
	}

}

@JsonInclude(value = Include.NON_NULL)
class AvatarLink {

	/** The icon avatar. */
	@JsonProperty("icon")
	private String	iconAvatar;

	/** The original avatar. */
	@JsonProperty("original")
	private String	originalAvatar;

	/** The smal avatar. */
	@JsonProperty("small")
	private String	smalAvatar;

	public AvatarLink(String __avatarName) {

	}

	public AvatarLink(String __originalAvatar, String __iconAvatar, String __smalAvatar) {
		super();
		this.originalAvatar = __originalAvatar;
		this.iconAvatar = __iconAvatar;
		this.smalAvatar = __smalAvatar;
	}

	/**
	 * @return the iconAvatar
	 */
	public String getIconAvatar() {
		return this.iconAvatar;
	}

	/**
	 * @return the originalAvatar
	 */
	public String getOriginalAvatar() {
		return this.originalAvatar;
	}

	/**
	 * @return the smalAvatar
	 */
	public String getSmalAvatar() {
		return this.smalAvatar;
	}

	/**
	 * @param __iconAvatar the iconAvatar to set
	 */
	public void setIconAvatar(String __iconAvatar) {
		this.iconAvatar = __iconAvatar;
	}

	/**
	 * @param __originalAvatar the originalAvatar to set
	 */
	public void setOriginalAvatar(String __originalAvatar) {
		this.originalAvatar = __originalAvatar;
	}

	/**
	 * @param __smalAvatar the smalAvatar to set
	 */
	public void setSmalAvatar(String __smalAvatar) {
		this.smalAvatar = __smalAvatar;
	}

}
