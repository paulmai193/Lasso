/*
 * 
 */
package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.define.Constant;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;

// TODO: Auto-generated Javadoc
/**
 * The Class ListDesignersResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = ListDesignerSerializer.class)
public class ListDesignersResponse extends BaseResponse {

	/** The datas. */
	private List<Object[]>	datas;

	/** The next index. */
	private int				nextIndex;

	/** The prefix avatar url. */
	private String			prefixAvatarUrl;

	/** The prefix portfolio url. */
	private String			prefixPortfolioUrl;

	/**
	 * Instantiates a new list designers response.
	 *
	 * @param __error
	 *        the error
	 */
	public ListDesignersResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list designers response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 */
	public ListDesignersResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list designers response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 * @param __detail
	 *        the detail
	 */
	public ListDesignersResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list designers response.
	 *
	 * @param __prefixAvatarUrl
	 *        the prefix avatar url
	 * @param __prefixPortfolioUrl
	 *        the prefix portfolio url
	 * @param __datas
	 *        the datas
	 * @param __nextIndex
	 *        the next index
	 */
	public ListDesignersResponse(String __prefixAvatarUrl, String __prefixPortfolioUrl,
			List<Object[]> __datas, int __nextIndex) {
		super();
		this.prefixAvatarUrl = __prefixAvatarUrl;
		this.prefixPortfolioUrl = __prefixPortfolioUrl;
		this.datas = __datas;
		this.nextIndex = __nextIndex;
	}

	/**
	 * Gets the datas.
	 *
	 * @return the datas
	 */
	public List<Object[]> getDatas() {
		return this.datas;
	}

	/**
	 * Gets the next index.
	 *
	 * @return the next index
	 */
	public int getNextIndex() {
		return this.nextIndex;
	}

	/**
	 * Gets the prefix avatar url.
	 *
	 * @return the prefixAvatarUrl
	 */
	public String getPrefixAvatarUrl() {
		return this.prefixAvatarUrl;
	}

	/**
	 * Gets the prefix portfolio url.
	 *
	 * @return the prefixPortfolioUrl
	 */
	public String getPrefixPortfolioUrl() {
		return this.prefixPortfolioUrl;
	}
}

class ListDesignerSerializer extends JsonSerializer<ListDesignersResponse> {

	@Override
	public void serialize(ListDesignersResponse __value, JsonGenerator __gen,
			SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeNumberField("next_index", __value.getNextIndex());
		__gen.writeArrayFieldStart("data");
		__value.getDatas().forEach(_data -> {
			Portfolio _portfolio = (Portfolio) _data[0];
			Account _designer = (Account) _data[1];
			if (_portfolio == null || _designer == null) {
				return;
			}
			else {
				try {
					__gen.writeStartObject();

					this.serializeDesigner(__gen, _designer, __value.getPrefixAvatarUrl());

					__gen.writeObjectFieldStart("portfolio_image");
					this.serializePortfolioImage(__gen, _portfolio,
							__value.getPrefixPortfolioUrl());
					__gen.writeEndObject();

					__gen.writeEndObject();
				}
				catch (Exception _ex) {
					Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
				}
			}
		});
		__gen.writeEndArray();
		__gen.writeEndObject();
	}

	private void serializeDesigner(JsonGenerator __gen, Account __designer,
			String __prefixAvatarUrl) {
		try {
			__gen.writeNumberField("designer_id", __designer.getId());
			__gen.writeStringField("designer_name", __designer.getName());
			__gen.writeObjectFieldStart("designer_avatar");
			if (__designer.getImage() == null || __designer.getImage().trim().isEmpty()) {
				if (__designer.getGender().shortValue() == Constant.GENDER_FEMALE) {
					__gen.writeStringField("original", __prefixAvatarUrl + "/Original/female.jpg");
					__gen.writeStringField("small", __prefixAvatarUrl + "/Small/female");
					__gen.writeStringField("icon", __prefixAvatarUrl + "/Icon/female");
					__gen.writeStringField("retina", __prefixAvatarUrl + "/Retina/female");
				}
				else {
					__gen.writeStringField("original", __prefixAvatarUrl + "/Original/male.jpg");
					__gen.writeStringField("small", __prefixAvatarUrl + "/Small/male");
					__gen.writeStringField("icon", __prefixAvatarUrl + "/Icon/male");
					__gen.writeStringField("retina", __prefixAvatarUrl + "/Retina/male");
				}
			}
			else {
				__gen.writeStringField("original",
						__prefixAvatarUrl + "/Original/" + __designer.getImage().trim());
				__gen.writeStringField("retina",
						__prefixAvatarUrl + "/Retina/" + __designer.getImage().trim());
				__gen.writeStringField("small",
						__prefixAvatarUrl + "/Small/" + __designer.getImage().trim());
				__gen.writeStringField("icon",
						__prefixAvatarUrl + "/Icon/" + __designer.getImage().trim());
			}
			__gen.writeEndObject();
		}
		catch (Exception _ex) {
			Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
		}
	}

	private void serializePortfolioImage(JsonGenerator __gen, Portfolio __portfolio,
			String __prefixPortfolioUrl) {
		try {
			if (__portfolio.getImage().trim().isEmpty()) {
				__gen.writeStringField("original", "");
				__gen.writeStringField("retina", "");
				__gen.writeStringField("small", "");
				__gen.writeStringField("icon", "");
			}
			else {
				String _firstImg = __portfolio.getImage().split(",")[0];
				if (_firstImg == null || _firstImg.isEmpty()) {
					__gen.writeStringField("original", "");
					__gen.writeStringField("retina", "");
					__gen.writeStringField("small", "");
					__gen.writeStringField("icon", "");
				}
				else {
					_firstImg = _firstImg.trim();
					__gen.writeStringField("original",
							__prefixPortfolioUrl + "/Original/" + _firstImg);
					__gen.writeStringField("retina", __prefixPortfolioUrl + "/Retina/" + _firstImg);
					__gen.writeStringField("small", __prefixPortfolioUrl + "/Small/" + _firstImg);
					__gen.writeStringField("icon", __prefixPortfolioUrl + "/Icon/" + _firstImg);
				}
			}
		}
		catch (Exception _ex) {
			Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
		}
	}

}
