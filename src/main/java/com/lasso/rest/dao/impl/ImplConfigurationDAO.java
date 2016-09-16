package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.ConfigurationDAO;
import com.lasso.rest.model.datasource.Configuration;

@Repository
public class ImplConfigurationDAO implements ConfigurationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

	public ImplConfigurationDAO() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Configuration> loadConfig() {
		return this.sessionFactory.getCurrentSession().createCriteria(Configuration.class).list();
	}

}
