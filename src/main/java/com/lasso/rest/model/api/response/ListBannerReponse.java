package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.rest.model.datasource.Banner;

/**
 * The Class ListBannerReponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = ListBannerSerializer.class)
public class ListBannerReponse extends BaseResponse {

	/** The banners. */
	private List<Banner>	banners;

	/** The prefix url. */
	private String			prefixUrl;

	/**
	 * Instantiates a new list banner reponse.
	 *
	 * @param __error the error
	 */
	public ListBannerReponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list banner reponse.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public ListBannerReponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list banner reponse.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public ListBannerReponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list banner reponse.
	 *
	 * @param __banners the banners
	 * @param __prefixUrl the prefix url
	 */
	public ListBannerReponse(List<Banner> __banners, String __prefixUrl) {
		super();
		this.banners = __banners;
		this.prefixUrl = __prefixUrl;
	}

	/**
	 * Gets the banners.
	 *
	 * @return the banners
	 */
	public List<Banner> getBanners() {
		return this.banners;
	}

	/**
	 * Gets the prefix url.
	 *
	 * @return the prefix url
	 */
	public String getPrefixUrl() {
		return this.prefixUrl;
	}

	/**
	 * Sets the banners.
	 *
	 * @param __banners the new banners
	 */
	public void setBanners(List<Banner> __banners) {
		this.banners = __banners;
	}

	/**
	 * Sets the prefix url.
	 *
	 * @param __prefixUrl the new prefix url
	 */
	public void setPrefixUrl(String __prefixUrl) {
		this.prefixUrl = __prefixUrl;
	}

}

class ListBannerSerializer extends JsonSerializer<ListBannerReponse> {

	@Override
	public void serialize(ListBannerReponse __value, JsonGenerator __gen,
			SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeArrayFieldStart("data");
		for (Banner _banner : __value.getBanners()) {
			__gen.writeStartObject();
			__gen.writeStringField("original",
					__value.getPrefixUrl() + "/Original/" + _banner.getImage().trim());
			__gen.writeStringField("small",
					__value.getPrefixUrl() + "/Small/" + _banner.getImage().trim());
			__gen.writeStringField("icon",
					__value.getPrefixUrl() + "/Icon/" + _banner.getImage().trim());
			__gen.writeStringField("retina",
					__value.getPrefixUrl() + "/Retina/" + _banner.getImage().trim());
			__gen.writeEndObject();
		}
		__gen.writeEndArray();
		__gen.writeEndObject();
	}

}