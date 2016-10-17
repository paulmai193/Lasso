/*
 * 
 */
package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Project;

/**
 * The Interface ProjectDAO.
 *
 * @author Paul Mai
 */
public interface ProjectDAO extends HibernateSession {

	/**
	 * Gets the project by id.
	 *
	 * @param __idProject
	 *        the id project
	 * @return the project by id
	 */
	Project getProjectById(int __idProject);

	/**
	 * Gets the project by id portfolio.
	 *
	 * @param __idPortfolio the id portfolio
	 * @return the project by id portfolio
	 */
	Project getProjectByIdPortfolio(Integer __idPortfolio);

	/**
	 * Gets the ramdom project.
	 *
	 * @param __limit
	 *        the limit
	 * @return the ramdom
	 */
	List<Project> getRamdom(int __limit);

	/**
	 * Save project.
	 *
	 * @param __project the project
	 */
	void saveProject(Project __project);

	/**
	 * Search projects.
	 *
	 * @param __idStyle
	 *        the id style
	 * @param __keyword
	 *        the keyword
	 * @param __offset
	 *        the offset
	 * @param __limit
	 *        the limit
	 * @return the list
	 */
	List<Project> searchProjects(Integer __idStyle, String __keyword, int __offset, int __limit);

	/**
	 * Update project.
	 *
	 * @param __project the project
	 */
	void updateProject(Project __project);

}
