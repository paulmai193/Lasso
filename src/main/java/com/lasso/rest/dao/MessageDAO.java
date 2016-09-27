package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Message;

/**
 * The Interface MessageDAO.
 *
 * @author Paul Mai
 */
public interface MessageDAO extends HibernateSession {

	/**
	 * Gets the last message of root.
	 *
	 * @param __rootMessage the root message
	 * @return the last message of root
	 */
	Message getLastMessageOfRoot(Message __rootMessage);

	/**
	 * Gets the list message by id parent.
	 *
	 * @param __idMessage the id message
	 * @return the list message by id parent
	 */
	List<Message> getListMessageByIdParent(int __idMessage);

	/**
	 * Gets the list root message by id receiver.
	 *
	 * @param __idReceiver the id receiver
	 * @return the list root message by id receiver
	 */
	List<Message> getListRootMessageByIdReceiver(Integer __idReceiver);

	/**
	 * Gets the list root message by id R sender.
	 *
	 * @param __idSender the id sender
	 * @return the list root message by id R sender
	 */
	List<Message> getListRootMessageByIdRSender(Integer __idSender);

	/**
	 * Gets the root message.
	 *
	 * @param __idMessage the id message
	 * @return the root message
	 */
	Message getRootMessage(int __idMessage);

	/**
	 * Save message.
	 *
	 * @param __message the message
	 */
	void saveMessage(Message __message);

	/**
	 * Save messages.
	 *
	 * @param __messages the messages
	 */
	void saveMessages(List<Message> __messages);

}
