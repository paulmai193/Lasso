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
	 * @see
	 * com.lasso.rest.dao.MessageDAO#getLastMessageOfRoot(com.lasso.rest.model.datasource.Message)
	 */
	@Override
	public Message getLastMessageOfRoot(Message __rootMessage) {
		List<Message> _messages = this.getListMessageByIdParent(__rootMessage.getId());
		return _messages.size() == 0 ? null : _messages.get((_messages.size() - 1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.MessageDAO#getListMessageById(java.lang.Integer)
	 */
	@Override
	public Message getListMessageById(Integer __idMessage) {
		return this.sessionFactory.getCurrentSession().get(Message.class, __idMessage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.MessageDAO#getListMessageByIdParent(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getListMessageByIdParent(int __idMessageRoot) {
		return this.sessionFactory.getCurrentSession().createCriteria(Message.class)
				.add(Restrictions.eq("parentId", __idMessageRoot))
				.add(Restrictions.eq("status", (byte) 1)).add(Restrictions.eq("deleted", (byte) 0))
				.addOrder(Order.asc("created")).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.MessageDAO#getListRootMessageByIdReceiver(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getListRootMessageByIdReceiver(Integer __idReceiver) {
		return this.sessionFactory.getCurrentSession().createCriteria(Message.class)
				.add(Restrictions.eq("toAccountId", __idReceiver))
				.add(Restrictions.eq("parentId", 0)).add(Restrictions.eq("status", (byte) 1))
				.add(Restrictions.eq("deleted", (byte) 0)).addOrder(Order.desc("created")).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.MessageDAO#getListRootMessageByIdRSender(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getListRootMessageByIdRSender(Integer __idSender) {
		return this.sessionFactory.getCurrentSession().createCriteria(Message.class)
				.add(Restrictions.eq("fromAccountId", __idSender))
				.add(Restrictions.eq("parentId", 0)).add(Restrictions.eq("status", (byte) 1))
				.add(Restrictions.eq("deleted", (byte) 0)).addOrder(Order.desc("created")).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.MessageDAO#getRootMessage(int)
	 */
	@Override
	public Message getRootMessage(int __idMessage) {
		Message _rootMessage = this.sessionFactory.getCurrentSession().get(Message.class,
				__idMessage);
		if (_rootMessage.getParentId() > 0) {
			_rootMessage = this.getRootMessage(_rootMessage.getParentId());
		}
		return _rootMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.MessageDAO#saveMessage(com.lasso.rest.model.datasource.Message)
	 */
	@Override
	public void saveMessage(Message __message) {
		this.sessionFactory.getCurrentSession().save(__message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.MessageDAO#saveMessages(java.util.List)
	 */
	@Override
	public void saveMessages(List<Message> __messages) {
		__messages.forEach(_message -> this.saveMessage(_message));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.MessageDAO#updateMessage(com.lasso.rest.model.datasource.Message)
	 */
	@Override
	public void updateMessage(Message __message) {
		this.sessionFactory.getCurrentSession().update(__message);
	}

}
