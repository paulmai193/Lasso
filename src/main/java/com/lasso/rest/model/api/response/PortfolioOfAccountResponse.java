package com.lasso.rest.model.api.response;

import java.io.IOException;

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
 * The Class PortfolioOfAccountResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class PortfolioOfAccountResponse extends BaseResponse {

	/** The portfolio. */
	@JsonProperty("data")
	@JsonSerialize(using = PortfolioOfAccountSerializer.class)
	private Portfolio portfolio;

	/**
	 * Instantiates a new portfolio of account response.
	 *
	 * @param __error the error
	 */
	public PortfolioOfAccountResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new portfolio of account response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public PortfolioOfAccountResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new portfolio of account response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public PortfolioOfAccountResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new portfolio of account response.
	 *
	 * @param __portfolio the portfolio
	 */
	public PortfolioOfAccountResponse(Portfolio __portfolio) {
		super();
		this.portfolio = __portfolio;
	}
}

class PortfolioOfAccountSerializer extends JsonSerializer<Portfolio> {

	@Override
	public void serialize(Portfolio __value, JsonGenerator __gen, SerializerProvider __serializers)
			throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeNumberField("id", __value.getId());
		__gen.writeStringField("title", __value.getTitle());
		__gen.writeStringField("info", __value.getInfo());
		__gen.writeEndObject();
	}
}