package com.lasso.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lasso.rest.dao.ProjectDAO;
import com.lasso.rest.service.ProjectManagement;

@Service
public class ImplProjectManagement implements ProjectManagement {

	@Autowired
	private ProjectDAO projectDAO;

	public void setProjectDAO(ProjectDAO __projectDAO) {
		this.projectDAO = __projectDAO;
	}

}
