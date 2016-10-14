/*
 * 
 */
package com.lasso.rest.model.api.response;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.AccountsRating;

/**
 * The Class EditOrderRequest.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = RatingDetailSerializer.class)
public class RatingDetailResponse extends BaseResponse {

	/** The id designer. */
	private Account			designer;

	/** The prefix avatar. */
	private String			prefixAvatar;

	/** The rating. */
	private AccountsRating	rating;

	/**
	 * Instantiates a new edits the job request.
	 */
	public RatingDetailResponse() {
	}

	/**
	 * Instantiates a new rating detail response.
	 *
	 * @param __designer the designer
	 * @param __rating the rating
	 * @param __prefixAvatar the prefix avatar
	 */
	public RatingDetailResponse(Account __designer, AccountsRating __rating,
			String __prefixAvatar) {
		super();
		this.designer = __designer;
		this.rating = __rating;
		this.prefixAvatar = __prefixAvatar;
	}

	/**
	 * Gets the designer.
	 *
	 * @return the designer
	 */
	public Account getDesigner() {
		return this.designer;
	}

	/**
	 * Gets the prefix avatar.
	 *
	 * @return the prefix avatar
	 */
	public String getPrefixAvatar() {
		return this.prefixAvatar;
	}

	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public AccountsRating getRating() {
		return this.rating;
	}

}

class RatingDetailSerializer extends JsonSerializer<RatingDetailResponse> {

	@Override
	public void serialize(RatingDetailResponse __value, JsonGenerator __gen,
			SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}

		__gen.writeObjectFieldStart("data");
		this.serializeAccount(__gen, __value.getDesigner(), __value.getPrefixAvatar());
		this.serializeRating(__gen, __value.getRating());
		__gen.writeEndObject();

		__gen.writeEndObject();
	}

	private void serializeAccount(JsonGenerator __gen, Account __designer, String __prefixAvatar) {
		try {
			__gen.writeNumberField("designer_id", __designer.getId());
			__gen.writeStringField("designer_name", __designer.getName());
			__gen.writeNumberField("designer_reward",
					__designer.getRewards() == 0 ? 1 : __designer.getRewards());
			__gen.writeObjectFieldStart("designer_avatar");
			this.serializeImage(__gen, __prefixAvatar, __designer.getImage());
			__gen.writeEndObject();
		}
		catch (Exception _ex) {
			Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
		}
	}

	private void serializeImage(JsonGenerator __gen, String __prefixUrl, String imageName) {
		try {
			if (imageName == null || imageName.trim().isEmpty()) {
				__gen.writeStringField("original", "");
				__gen.writeStringField("small", "");
				__gen.writeStringField("icon", "");
				__gen.writeStringField("retina", "");
			}
			else {
				__gen.writeStringField("original", __prefixUrl + "/Original/" + imageName.trim());
				__gen.writeStringField("small", __prefixUrl + "/Small/" + imageName.trim());
				__gen.writeStringField("icon", __prefixUrl + "/Icon/" + imageName.trim());
				__gen.writeStringField("retina", __prefixUrl + "/Retina/" + imageName.trim());
			}
		}
		catch (Exception _ex) {
			Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
		}

	}

	private void serializeRating(JsonGenerator __gen, AccountsRating __rating) {
		try {
			if (__rating == null) {
				__gen.writeNumberField("rating_communication", 0);
				__gen.writeNumberField("rating_experience", 0);
				__gen.writeNumberField("rating_quality", 0);
			}
			else {
				__gen.writeNumberField("rating_communication", __rating.getCommunication());
				__gen.writeNumberField("rating_experience", __rating.getExperience());
				__gen.writeNumberField("rating_quality", __rating.getQuality());
			}
		}
		catch (IOException _ex) {
			Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
		}
	}
}
