package com.lasso.rest.dao;

import java.util.List;

public interface CountryDAO {

	public List<Integer> getCountryIdsByCode(String __code);
}
