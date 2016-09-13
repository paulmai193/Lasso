package com.lasso.rest.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.TypeDAO;
import com.lasso.rest.model.datasource.Type;

@Repository
public class ImplTypeDAO implements TypeDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Type getById(int __idType) {
		return (Type) this.sessionFactory.getCurrentSession().createCriteria(Type.class)
		        .add(Restrictions.eq("id.id", __idType)).uniqueResult();
	}

}
