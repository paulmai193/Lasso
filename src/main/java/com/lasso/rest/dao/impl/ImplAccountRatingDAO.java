package com.lasso.rest.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.AccountRatingDAO;
import com.lasso.rest.model.datasource.AccountsRating;

/**
 * The Class ImplAccountRatingDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplAccountRatingDAO implements AccountRatingDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.AccountRatingDAO#saveRating(com.lasso.rest.model.datasource.
	 * AccountsRating)
	 */
	@Override
	public void saveRating(AccountsRating __accountsRating) {
		this.sessionFactory.getCurrentSession().save(__accountsRating);
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
