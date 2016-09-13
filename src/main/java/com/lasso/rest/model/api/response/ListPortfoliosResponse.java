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

/**
 * The Class ListPortfoliosResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = ListPortfolioSerializer.class)
public class ListPortfoliosResponse extends BaseResponse {

	/** The datas. {portfolio, category, style} */
	private List<Object[]> datas;

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

	public ListPortfoliosResponse(List<Object[]> __datas) {
		super();
		this.datas = __datas;
	}

	/**
	 * @return the datas {portfolio, category, style}
	 */
	public List<Object[]> getDatas() {
		return this.datas;
	}

	/**
	 * @param __datas the datas to set {portfolio, category, style}
	 */
	public void setDatas(List<Object[]> __datas) {
		this.datas = __datas;
	}

}

class ListPortfolioSerializer extends JsonSerializer<ListPortfoliosResponse> {

	@Override
	public void serialize(ListPortfoliosResponse __value, JsonGenerator __gen,
	        SerializerProvider __serializers) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub

	}

}