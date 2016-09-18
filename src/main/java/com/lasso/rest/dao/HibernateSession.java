package com.lasso.rest.dao;

import org.hibernate.SessionFactory;

/**
 * The Interface HibernateSession.
 *
 * @author Paul Mai
 */
public interface HibernateSession {

	/**
	 * Sets the session factory.
	 *
	 * @param __sessionFactory the new session factory
	 */
	void setSessionFactory(SessionFactory __sessionFactory);
}
