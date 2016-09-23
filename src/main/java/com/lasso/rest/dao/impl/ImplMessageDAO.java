package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.MessageDAO;
import com.lasso.rest.model.datasource.Message;

/**
 * The Class ImplMessageDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplMessageDAO implements MessageDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new impl message DAO.
	 */
	public ImplMessageDAO() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.MessageDAO#saveMessages(java.util.List)
	 */
	@Override
	public void saveMessages(List<Message> __messages) {
		__messages.forEach(_message -> this.sessionFactory.getCurrentSession().save(_message));
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getListMessageByIdReceiver(Integer __idReceiver) {
		return this.sessionFactory.getCurrentSession().createCriteria(Message.class)
		        .add(Restrictions.eq("toAccountId", __idReceiver))
		        .add(Restrictions.eq("status", (byte) 1)).add(Restrictions.eq("deleted", (byte) 0))
		        .addOrder(Order.desc("created")).list();
	}

}
