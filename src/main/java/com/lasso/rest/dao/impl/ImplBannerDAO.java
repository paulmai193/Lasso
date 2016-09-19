package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.BannerDAO;
import com.lasso.rest.model.datasource.Banner;

/**
 * The Class ImplBannerDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplBannerDAO implements BannerDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new impl banner DAO.
	 */
	public ImplBannerDAO() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.BannerDAO#getListBanner()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Banner> getListBanner() {
		return this.sessionFactory.getCurrentSession().createCriteria(Banner.class)
				.add(Restrictions.eq("status", (byte) 1)).add(Restrictions.eq("deleted", (byte) 0))
				.list();
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
