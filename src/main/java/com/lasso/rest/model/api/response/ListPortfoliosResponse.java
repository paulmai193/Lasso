package com.lasso.rest.model.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
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
