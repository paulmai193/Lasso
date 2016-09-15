package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
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

	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.TypeDAO#getListByByListIds(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getListByByListIds(List<Integer> __listIdsType) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Type.class);
		_criteria.add(Restrictions.in("id.id", __listIdsType));
		return _criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getTypesByCategory(Category __category) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Type.class)
		        .add(Restrictions.eq("category", __category));
		return _criteria.list();
	}
}
