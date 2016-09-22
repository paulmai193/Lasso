package com.lasso.rest.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.PromoDAO;
import com.lasso.rest.model.datasource.PromoCode;
import com.lasso.rest.model.datasource.PromoHistory;

/**
 * The Class ImplPomoDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplPomoDAO implements PromoDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.PromoDAO#getPromoCodeById(int)
	 */
	@Override
	public PromoCode getPromoCodeById(int __promoCodeId) {
		return this.sessionFactory.getCurrentSession().get(PromoCode.class, __promoCodeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.PromoDAO#getPromoHistroyByJobId(int)
	 */
	@Override
	public PromoHistory getPromoHistroyByJobId(int __idJob) {
		return (PromoHistory) this.sessionFactory.getCurrentSession()
				.createCriteria(PromoHistory.class).add(Restrictions.eq("job_id", __idJob))
				.add(Restrictions.eq("deleted", (byte) 0)).uniqueResult();
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
