package com.lasso.rest.service;

import java.io.IOException;
import java.util.List;

import com.lasso.rest.model.api.request.SendMessageRequest;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.push.SendPushRequest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * The Interface MessageManagement.
 *
 * @author Paul Mai
 */
public interface MessageManagement {

	/**
	 * Gets the list messages of account.
	 *
	 * @param __account the account
	 * @return the list messages of account
	 */
	List<Object[]> getListMessagesOfAccount(Account __account);

	/**
	 * Gets the messages detail of account.
	 *
	 * @param __account the account
	 * @param __idMessage the id message
	 * @return the messages detail of account
	 */
	List<Object[]> getMessagesDetailOfAccount(Account __account, int __idMessage);

	/**
	 * Send message.
	 *
	 * @param __sender the sender
	 * @param __sendMessageRequest the send message request
	 */
	void sendMessage(Account __sender, SendMessageRequest __sendMessageRequest);

	void sendPush(SendPushRequest __pushRequest) throws UnirestException, IOException;

}
