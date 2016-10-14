/*
 * 
 */
package com.lasso.rest.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.ContactDAO;
import com.lasso.rest.model.datasource.Contact;

/**
 * The Class ImplContactDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplContactDAO implements ContactDAO {

	/** The session factory. */
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.ContactDAO#save(com.lasso.rest.model.datasource.Contact)
	 */
	@Override
	public void save(Contact __contact) {
		this.sessionFactory.getCurrentSession().save(__contact);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.HibernateSession#setSessionFactory(org.hibernate.SessionFactory)
	 */
	@Override
	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

}
