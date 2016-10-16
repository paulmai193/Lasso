/*
 * 
 */
package com.lasso.rest.dao;

import com.lasso.rest.model.datasource.Contact;

// TODO: Auto-generated Javadoc
/**
 * The Interface ContactDAO.
 *
 * @author Paul Mai
 */
public interface ContactDAO extends HibernateSession {

	/**
	 * Save.
	 *
	 * @param __contact
	 *            the contact
	 */
	void save(Contact __contact);

}
