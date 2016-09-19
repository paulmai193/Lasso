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
	@JsonSerialize(using = CountriesSerializer.class)
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

class CountriesSerializer extends JsonSerializer<List<Country>> {

	@Override
	public void serialize(List<Country> __value, JsonGenerator __gen,
			SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartArray();
		for (Country _country : __value) {
			__gen.writeStartObject();
			__gen.writeNumberField("country_id", _country.getId());
			__gen.writeStringField("country_code", _country.getCode());
			__gen.writeStringField("country_name", _country.getName());
			__gen.writeNumberField("phone_code", _country.getPhoneCode());
			__gen.writeEndObject();
		}
		__gen.writeEndArray();
	}

}