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
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;

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
	 * @param __error the error
	 */
	public ListDesignersResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list designers response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public ListDesignersResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list designers response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public ListDesignersResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list designers response.
	 *
	 * @param __prefixAvatarUrl the prefix avatar url
	 * @param __prefixPortfolioUrl the prefix portfolio url
	 * @param __datas the datas
	 * @param __nextIndex the next index
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
		for (Object[] _data : __value.getDatas()) {
			Portfolio _portfolio = (Portfolio) _data[0];
			Account _designer = (Account) _data[1];
			if (_portfolio == null || _designer == null) {
				break;
			}
			else {
				__gen.writeStartObject();
				__gen.writeNumberField("designer_id", _designer.getId());
				__gen.writeStringField("designer_name", _designer.getName());
				__gen.writeObjectFieldStart("designer_avatar");
				if (_designer.getImage().trim().isEmpty()) {
					__gen.writeStringField("original", "");
					__gen.writeStringField("retina", "");
					__gen.writeStringField("small", "");
					__gen.writeStringField("icon", "");
				}
				else {
					__gen.writeStringField("original", __value.getPrefixAvatarUrl() + "/Original/"
					        + _designer.getImage().trim());
					__gen.writeStringField("retina", __value.getPrefixAvatarUrl() + "/Retina/"
					        + _designer.getImage().trim());
					__gen.writeStringField("small",
					        __value.getPrefixAvatarUrl() + "/Small/" + _designer.getImage().trim());
					__gen.writeStringField("icon",
					        __value.getPrefixAvatarUrl() + "/Icon/" + _designer.getImage().trim());
				}
				__gen.writeEndObject();
				__gen.writeObjectFieldStart("portfolio_image");
				if (_portfolio.getImage().trim().isEmpty()) {
					__gen.writeStringField("original", "");
					__gen.writeStringField("retina", "");
					__gen.writeStringField("small", "");
					__gen.writeStringField("icon", "");
				}
				else {
					String _firstImg = _portfolio.getImage();
					_firstImg = _firstImg.substring(0, _firstImg.indexOf(",")).trim();
					__gen.writeStringField("original",
					        __value.getPrefixPortfolioUrl() + "/Original/" + _firstImg);
					__gen.writeStringField("retina",
					        __value.getPrefixPortfolioUrl() + "/Retina/" + _firstImg);
					__gen.writeStringField("small",
					        __value.getPrefixPortfolioUrl() + "/Small/" + _firstImg);
					__gen.writeStringField("icon",
					        __value.getPrefixPortfolioUrl() + "/Icon/" + _firstImg);
				}
				__gen.writeEndObject();
				__gen.writeEndObject();
			}
		}
		__gen.writeEndArray();
		__gen.writeEndObject();
	}

}
