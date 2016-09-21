package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Message;

public interface MessageDAO extends HibernateSession {

	/**
	 * Save messages.
	 *
	 * @param __messages the messages
	 */
	void saveMessages(List<Message> __messages);

}
