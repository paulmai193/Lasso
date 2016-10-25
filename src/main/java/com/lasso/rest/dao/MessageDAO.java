/*
 * 
 */
package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Message;

// TODO: Auto-generated Javadoc
/**
 * The Interface MessageDAO.
 *
 * @author Paul Mai
 */
public interface MessageDAO extends HibernateSession {

	/**
	 * Gets the last message of root.
	 *
	 * @param __rootMessage
	 *        the root message
	 * @return the last message of root
	 */
	Message getLastMessageOfRoot(Message __rootMessage);

	/**
	 * Gets the list message by id job.
	 *
	 * @param __idJob
	 *        the id job
	 * @return the list message by id job
	 */
	List<Message> getListMessageByIdJob(int __idJob);

	/**
	 * Gets the list message by id parent.
	 *
	 * @param __idMessageRoot
	 *        the id root message
	 * @return the list message by id parent
	 */
	List<Message> getListMessageByIdParent(int __idMessageRoot);

	/**
	 * Gets the list root message by id receiver.
	 *
	 * @param __idReceiver
	 *        the id receiver
	 * @return the list root message by id receiver
	 */
	List<Message> getListRootMessageByIdReceiver(Integer __idReceiver);

	/**
	 * Gets the list root message by id R sender.
	 *
	 * @param __idSender
	 *        the id sender
	 * @return the list root message by id R sender
	 */
	List<Message> getListRootMessageByIdRSender(Integer __idSender);

	/**
	 * Gets the list unread message of account.
	 *
	 * @param __idAccount the id account
	 * @return the list unread message of account
	 */
	List<Message> getListUnreadMessageOfAccount(Integer __idAccount);

	/**
	 * Gets the message by id.
	 *
	 * @param __idMessage
	 *        the id message
	 * @return the list message by id
	 */
	Message getMessageById(Integer __idMessage);

	/**
	 * Gets the root message.
	 *
	 * @param __idMessage
	 *        the id message
	 * @return the root message
	 */
	Message getRootMessage(int __idMessage);

	/**
	 * Gets the root message by id job.
	 *
	 * @param __idAccount
	 *        the id account
	 * @param __idJob
	 *        the id job
	 * @return the root message by id job
	 */
	Message getRootMessageByIdJob(int __idAccount, int __idJob);

	/**
	 * Gets the root message of receiver by id job.
	 *
	 * @param __idAccount
	 *        the id account
	 * @param __idJob
	 *        the id job
	 * @return the root message of receiver by id job
	 */
	Message getRootMessageOfReceiverByIdJob(int __idAccount, int __idJob);

	/**
	 * Gets the root message of sender by id job.
	 *
	 * @param __idAccount
	 *        the id account
	 * @param __idJob
	 *        the id job
	 * @return the root message of sender by id job
	 */
	Message getRootMessageOfSenderByIdJob(int __idAccount, int __idJob);

	/**
	 * Save message.
	 *
	 * @param __message
	 *        the message
	 * @return ID message
	 */
	Integer saveMessage(Message __message);

	/**
	 * Save messages.
	 *
	 * @param __messages
	 *        the messages
	 */
	void saveMessages(List<Message> __messages);

	/**
	 * Update message.
	 *
	 * @param __message
	 *        the message
	 */
	void updateMessage(Message __message);

}
