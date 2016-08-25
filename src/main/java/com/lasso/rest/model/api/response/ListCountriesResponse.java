package com.lasso.rest.model.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lasso.rest.model.datasource.Country;

@JsonInclude(value = Include.NON_NULL)
public class ListCountriesResponse extends BaseResponse {

	@JsonProperty("countries")
	private List<Country> countries;

	public ListCountriesResponse(List<Country> __countries) {
		this.countries = __countries;
	}

	public ListCountriesResponse(boolean __error) {
		super(__error);
	}

	public ListCountriesResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	public ListCountriesResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * @return the countries
	 */
	public List<Country> getCountries() {
		return this.countries;
	}

	/**
	 * @param __countries the countries to set
	 */
	public void setCountries(List<Country> __countries) {
		this.countries = __countries;
	}

}
