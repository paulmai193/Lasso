package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.ProjectDAO;
import com.lasso.rest.model.datasource.Category;

@Repository
public class ImplProjectDAO implements ProjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories(int __offset, int __limit) {
		Session _session = this.sessionFactory.getCurrentSession();
		Criteria _criteria = _session.createCriteria(Category.class).addOrder(Order.asc("title"))
		        .setFirstResult(__offset).setMaxResults(__limit);
		return _criteria.list();
	}
}
