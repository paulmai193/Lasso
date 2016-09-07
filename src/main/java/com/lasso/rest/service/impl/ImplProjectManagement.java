package com.lasso.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.dao.ProjectDAO;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.service.ProjectManagement;

@Service
@Transactional
public class ImplProjectManagement implements ProjectManagement {

	@Autowired
	private ProjectDAO projectDAO;

	public void setProjectDAO(ProjectDAO __projectDAO) {
		this.projectDAO = __projectDAO;
	}

	@Override
	public List<Category> getCategoriesByPage(int __page, int __size) {
		return this.projectDAO.getCategories(__page, __size);
	}

}
