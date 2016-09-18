package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.TypeDAO;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Type;

/**
 * The Class ImplTypeDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplTypeDAO implements TypeDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.TypeDAO#getById(int)
	 */
	@Override
	public Type getById(int __typeId) {
		return (Type) this.sessionFactory.getCurrentSession().createCriteria(Type.class)
				.add(Restrictions.idEq(__typeId)).add(Restrictions.eq("deleted", (byte) 0))
				.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.TypeDAO#getListByByListIds(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getListByByListIds(List<Integer> __listIdsType) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Type.class)
				.add(Restrictions.in("id", __listIdsType)).add(Restrictions.eq("status", (byte) 1))
				.add(Restrictions.eq("deleted", (byte) 0)).addOrder(Order.asc("sort"));
		return _criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.TypeDAO#getTypesByCategory(com.lasso.rest.model.datasource.Category)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getTypesByCategory(Category __category) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Type.class)
				.add(Restrictions.eq("category", __category))
				.add(Restrictions.eq("status", (byte) 1)).add(Restrictions.eq("deleted", (byte) 0))
				.addOrder(Order.asc("sort"));
		return _criteria.list();
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
