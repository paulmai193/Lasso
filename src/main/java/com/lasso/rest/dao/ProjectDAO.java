package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Category;

public interface ProjectDAO {

	List<Category> getCategories(int __offset, int __limit);

}
