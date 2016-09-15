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

@Repository
public class ImplCategoryDAO implements CategoryDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

	public ImplCategoryDAO() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories(int __offset, int __limit, String __keyword) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Category.class);
		if (__keyword != null && !__keyword.isEmpty()) {
			_criteria.add(Restrictions.like("title", __keyword, MatchMode.ANYWHERE));
		}
		_criteria.addOrder(Order.asc("title")).setFirstResult(__offset).setMaxResults(__limit);
		return _criteria.list();
	}

	@Override
	public Category getCategoryById(int __idCategory) {
		return this.sessionFactory.getCurrentSession().get(Category.class, __idCategory);
	}

}
