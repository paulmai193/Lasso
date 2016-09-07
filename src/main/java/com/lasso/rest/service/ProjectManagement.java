package com.lasso.rest.service;

import java.util.List;

import com.lasso.rest.model.datasource.Category;

public interface ProjectManagement {

	List<Category> getCategoriesByPage(int __page, int __size);

}
