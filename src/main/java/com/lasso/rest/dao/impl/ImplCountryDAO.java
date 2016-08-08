package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.CountryDAO;
import com.lasso.rest.model.datasource.Country;

@Repository
public class ImplCountryDAO implements CountryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public ImplCountryDAO() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getCountryIdsByCode(String __code) {
		Criteria _criteria = sessionFactory.getCurrentSession().createCriteria(Country.class);
		_criteria.add(Restrictions.eq("code", __code));
		_criteria.setProjection(Projections.id());
		return _criteria.list();
	}

}
