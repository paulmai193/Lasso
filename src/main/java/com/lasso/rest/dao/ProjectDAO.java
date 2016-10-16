/*
 * 
 */
package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Project;

// TODO: Auto-generated Javadoc
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
	 * Gets the ramdom project.
	 *
	 * @param __limit
	 *        the limit
	 * @return the ramdom
	 */
	List<Project> getRamdom(int __limit);

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

}
