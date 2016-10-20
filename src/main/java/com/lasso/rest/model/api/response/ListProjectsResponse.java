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
import com.lasso.rest.model.datasource.Portfolio;

/**
 * The Class ListProjectsResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = ListProjectsSerializer.class)
public class ListProjectsResponse extends BaseResponse {

	/** The datas. {Project, designer avatar name} */
	private List<Object[]>	datas;

	/** The next index. */
	private int				nextIndex;

	/** The prefix avatar url. */
	private String			prefixAvatarUrl;

	/** The prefix portfolio url. */
	private String			prefixPortfolioUrl;

	/** The suggests. */
	private List<Object[]>	suggests;

	/**
	 * Instantiates a new list projects response.
	 *
	 * @param __error
	 *        the error
	 */
	public ListProjectsResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list projects response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 */
	public ListProjectsResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list projects response.
	 *
	 * @param __error
	 *        the error
	 * @param __message
	 *        the message
	 * @param __detail
	 *        the detail
	 */
	public ListProjectsResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list projects response.
	 *
	 * @param __nextIndex the next index
	 * @param __prefixPortfolioUrl the prefix portfolio url
	 * @param __prefixAvatarUrl the prefix avatar url
	 * @param __datas the datas
	 * @param __suggests the suggests
	 */
	public ListProjectsResponse(int __nextIndex, String __prefixPortfolioUrl,
			String __prefixAvatarUrl, List<Object[]> __datas, List<Object[]> __suggests) {
		super();
		this.nextIndex = __nextIndex;
		this.prefixPortfolioUrl = __prefixPortfolioUrl;
		this.prefixAvatarUrl = __prefixAvatarUrl;
		this.datas = __datas;
		this.suggests = __suggests;
	}

	/**
	 * Gets the datas {Project, Desiger avatar name}.
	 *
	 * @return the datas
	 */
	public List<Object[]> getDatas() {
		return this.datas;
	}

	/**
	 * Gets the next index.
	 *
	 * @return the nextIndex
	 */
	public int getNextIndex() {
		return this.nextIndex;
	}

	/**
	 * Gets the prefix avatar url.
	 *
	 * @return the prefix avatar url
	 */
	public String getPrefixAvatarUrl() {
		return this.prefixAvatarUrl;
	}

	/**
	 * Gets the prefix portfolio url.
	 *
	 * @return the prefix portfolio url
	 */
	public String getPrefixPortfolioUrl() {
		return this.prefixPortfolioUrl;
	}

	/**
	 * Gets the suggests.
	 *
	 * @return the suggests
	 */
	public List<Object[]> getSuggests() {
		return this.suggests;
	}

}

class ListProjectsSerializer extends JsonSerializer<ListProjectsResponse> {

	@Override
	public void serialize(ListProjectsResponse __value, JsonGenerator __gen,
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
			try {
				__gen.writeStartObject();
				this.serializeData(__gen, _data, __value.getPrefixAvatarUrl(),
						__value.getPrefixPortfolioUrl());
				__gen.writeEndObject();
			}
			catch (IOException _ex) {
				Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
			}

		});
		__gen.writeEndArray();
		__gen.writeArrayFieldStart("suggest");
		__value.getSuggests().forEach(_data -> {
			try {
				__gen.writeStartObject();
				this.serializeData(__gen, _data, __value.getPrefixAvatarUrl(),
						__value.getPrefixPortfolioUrl());
				__gen.writeEndObject();
			}
			catch (IOException _ex) {
				Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
			}

		});
		__gen.writeEndArray();

		__gen.writeEndObject();
	}

	private void serializeData(JsonGenerator __gen, Object[] __data, String __prefixAvatarUrl,
			String __prefixProjectUrl) {
		try {
			Portfolio _portfolio = (Portfolio) __data[0];
			__gen.writeNumberField("project_id", _portfolio.getId());
			__gen.writeStringField("title", _portfolio.getTitle());
			String _portfolioImage = _portfolio.getImage();
			if (_portfolioImage.contains(",")) {
				_portfolioImage = _portfolioImage.substring(0, _portfolioImage.indexOf(","));
			}
			__gen.writeObjectFieldStart("images");
			if (_portfolio.getImage().isEmpty()) {
				__gen.writeStringField("original", "");
				__gen.writeStringField("small", "");
				__gen.writeStringField("icon", "");
				__gen.writeStringField("retina", "");
			}
			else {
				__gen.writeStringField("original",
						__prefixProjectUrl + "/Original/" + _portfolioImage);
				__gen.writeStringField("small", __prefixProjectUrl + "/Small/" + _portfolioImage);
				__gen.writeStringField("icon", __prefixProjectUrl + "/Icon/" + _portfolioImage);
				__gen.writeStringField("retina", __prefixProjectUrl + "/Retina/" + _portfolioImage);
			}
			__gen.writeEndObject();

			String _avatar = (String) __data[1];
			if (_avatar == null || _avatar.isEmpty()) {
				__gen.writeStringField("designer_avatar", "");
			}
			else {
				__gen.writeStringField("designer_avatar", __prefixAvatarUrl + "/Icon/" + __data[1]);
			}
		}
		catch (Exception _ex) {
			Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
		}
	}

}
