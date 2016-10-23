/*
 * 
 */
package com.lasso.rest.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.PromoDAO;
import com.lasso.rest.model.datasource.PromoCode;
import com.lasso.rest.model.datasource.PromoHistory;

/**
 * The Class ImplPromoDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplPromoDAO implements PromoDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.PromoDAO#getPromoCodeByCode(java.lang.String)
	 */
	@Override
	public PromoCode getPromoCodeByCode(String __promoCode) {
		Date _date = new Date();
		return (PromoCode) this.sessionFactory.getCurrentSession().createCriteria(PromoCode.class)
		        .add(Restrictions.like("code", __promoCode))
		        .add(Restrictions.le("startDate", _date)).add(Restrictions.ge("endDate", _date))
		        .add(Restrictions.eq("status", (byte) 1)).add(Restrictions.eq("deleted", (byte) 0))
		        .uniqueResult();
	}

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
		        .createCriteria(PromoHistory.class).add(Restrictions.eq("jobId", __idJob))
		        .add(Restrictions.eq("deleted", (byte) 0)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PromoHistory> getPromoHistroyOfAccountByPromoCodeId(int __idAccount,
	        int __idPromoCode) {
		return this.sessionFactory.getCurrentSession().createCriteria(PromoHistory.class)
		        .add(Restrictions.eq("promoCodeId", __idPromoCode))
		        .add(Restrictions.eq("accountId", __idAccount))
		        .add(Restrictions.eq("deleted", (byte) 0)).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.PromoDAO#savePromoHistory(com.lasso.rest.model.
	 * datasource.PromoHistory)
	 */
	@Override
	public void savePromoHistory(PromoHistory __promoHistory) {
		this.sessionFactory.getCurrentSession().save(__promoHistory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.HibernateSession#setSessionFactory(org.hibernate.
	 * SessionFactory)
	 */
	@Override
	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

}
