package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.MessageDAO;
import com.lasso.rest.model.datasource.Message;

@Repository
public class ImplMessageDAO implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public ImplMessageDAO() {
	}

	@Override
	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

	@Override
	public void saveMessages(List<Message> __messages) {
		__messages.forEach(_message -> this.sessionFactory.getCurrentSession().save(_message));
	}

}
