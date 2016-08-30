package com.lasso.rest.model.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.rest.model.datasource.Country;

/**
 * The Class ListCountriesResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
public class ListCountriesResponse extends BaseResponse {

	/** The countries. */
	@JsonProperty("data")
	private List<Country> countries;

	/**
	 * Instantiates a new list countries response.
	 *
	 * @param __error the error
	 */
	public ListCountriesResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new list countries response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public ListCountriesResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new list countries response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public ListCountriesResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new list countries response.
	 *
	 * @param __countries the countries
	 */
	public ListCountriesResponse(List<Country> __countries) {
		this.countries = __countries;
	}

	/**
	 * Gets the countries.
	 *
	 * @return the countries
	 */
	public List<Country> getCountries() {
		return this.countries;
	}

	/**
	 * Sets the countries.
	 *
	 * @param __countries the countries to set
	 */
	public void setCountries(List<Country> __countries) {
		this.countries = __countries;
	}

}
