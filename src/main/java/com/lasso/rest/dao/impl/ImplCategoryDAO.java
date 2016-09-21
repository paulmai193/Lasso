package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.CategoryDAO;
import com.lasso.rest.model.datasource.Category;

/**
 * The Class ImplCategoryDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplCategoryDAO implements CategoryDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new impl category DAO.
	 */
	public ImplCategoryDAO() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.CategoryDAO#getCategories(int, int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories(int __offset, int __limit, String __keyword) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Category.class);
		if (__keyword != null && !__keyword.isEmpty()) {
			_criteria.add(Restrictions.like("title", __keyword, MatchMode.ANYWHERE));
		}
		_criteria.add(Restrictions.eq("status", (byte) 1)).add(Restrictions.eq("deleted", (byte) 0))
		        .addOrder(Order.asc("sort"));
		if (__offset > -1) {
			_criteria.setFirstResult(__offset).setMaxResults(__limit);
		}

		return _criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.CategoryDAO#getCategoryById(int)
	 */
	@Override
	public Category getCategoryById(int __idCategory) {
		return (Category) this.sessionFactory.getCurrentSession().createCriteria(Category.class)
		        .add(Restrictions.idEq(__idCategory)).uniqueResult();
	}

	/**
	 * Sets the session factory.
	 *
	 * @param __sessionFactory the new session factory
	 */
	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

}
