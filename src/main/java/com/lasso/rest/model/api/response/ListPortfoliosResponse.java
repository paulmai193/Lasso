package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.rest.model.datasource.Portfolio;

/**
 * The Class ListPortfoliosResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class ListPortfoliosResponse extends BaseResponse {

	/** The portfolios. */
	@JsonProperty("data")
	private List<Portfolio> portfolios;

	/**
	 * Instantiates a new list portfolios response.
	 *
	 * @param __error the error
	 */
	public ListPortfoliosResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list portfolios response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public ListPortfoliosResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list portfolios response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public ListPortfoliosResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list portfolios response.
	 *
	 * @param __portfolios the portfolios
	 */
	public ListPortfoliosResponse(List<Portfolio> __portfolios) {
		super();
		this.portfolios = __portfolios;
	}

	/**
	 * Gets the portfolios.
	 *
	 * @return the portfolios
	 */
	@JsonSerialize(using = PortfolioSerializer.class)
	@JsonProperty("data")
	public List<Portfolio> getPortfolios() {
		return this.portfolios;
	}

	/**
	 * Sets the portfolios.
	 *
	 * @param __portfolios the portfolios to set
	 */
	public void setPortfolios(List<Portfolio> __portfolios) {
		this.portfolios = __portfolios;
	}
}

class PortfolioSerializer extends JsonSerializer<List<Portfolio>> {

	@Override
	public void serialize(List<Portfolio> __value, JsonGenerator __gen,
	        SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartArray();
		for (Portfolio _portfolio : __value) {
			__gen.writeStartObject();
			__gen.writeNumberField("id", _portfolio.getId().getId());
			__gen.writeStringField("title", _portfolio.getTitle());
			__gen.writeEndObject();
		}
		__gen.writeEndArray();
	}

}