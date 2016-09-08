package com.lasso.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.dao.ProjectDAO;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Project;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.model.datasource.TypesStyle;
import com.lasso.rest.service.ProjectManagement;

/**
 * The Class ImplProjectManagement.
 *
 * @author Paul Mai
 */
@Service
@Transactional
public class ImplProjectManagement implements ProjectManagement {

	/** The project DAO. */
	@Autowired
	private ProjectDAO projectDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getCategoriesByIndexAndKeyword(int, int,
	 * java.lang.String)
	 */
	@Override
	public List<Category> getCategoriesByIndexAndKeyword(int __index, int __size,
			String __keyword) {
		return this.projectDAO.getCategories(__index, __size, __keyword);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getCategoryById(int)
	 */
	@Override
	public Category getCategoryById(int __idCategory) {
		return this.projectDAO.getCategoryById(__idCategory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getProjectsBySubCategoryAndKeyword(int, int,
	 * int, int, java.lang.String)
	 */
	@Override
	public List<Project> getProjectsBySubCategoryAndKeyword(int __idCategory, int __idStyle,
			int __index, int __size, String __keyword) {
		return this.projectDAO.searchProjects(__idCategory, __idStyle, __keyword, __index, __size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getSubCategoriesByIndexAndKeyword(int, int,
	 * int, java.lang.String)
	 */
	@Override
	public List<Style> getSubCategoriesByIndexAndKeyword(int __idCategory, int __index, int __size,
			String __keyword) {
		// Get Category from id
		Category _category = this.projectDAO.getCategoryById(__idCategory);
		if (_category == null) {
			return new ArrayList<>();
		}

		// Get Type from Category
		List<Type> _types = this.projectDAO.getTypesByCategory(_category);
		if (_types.size() == 0) {
			return new ArrayList<>();
		}

		// Get TypesStyle from Types
		List<TypesStyle> _typesStyles = this.projectDAO.getTypesStylesByTypes(_types);
		if (_typesStyles.size() == 0) {
			return new ArrayList<>();
		}

		// Get Style from TypesStyles
		return this.projectDAO.getStylesByTypesAndKeyword(_typesStyles, __index, __size, __keyword);
	}

	/**
	 * Sets the project DAO.
	 *
	 * @param __projectDAO the new project DAO
	 */
	public void setProjectDAO(ProjectDAO __projectDAO) {
		this.projectDAO = __projectDAO;
	}

}
