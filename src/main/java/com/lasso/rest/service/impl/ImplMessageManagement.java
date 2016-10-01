package com.lasso.rest.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.ws.rs.NotFoundException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lasso.define.Constant;
import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.dao.JobDAO;
import com.lasso.rest.dao.MessageDAO;
import com.lasso.rest.model.api.request.SendMessageRequest;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.Message;
import com.lasso.rest.model.push.SendPushRequest;
import com.lasso.rest.model.push.SendPushResponse;
import com.lasso.rest.service.MessageManagement;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * The Class ImplMessageManagement.
 *
 * @author Paul Mai
 */
@Service
@Transactional
public class ImplMessageManagement implements MessageManagement {

	/** The account DAO. */
	@Autowired
	private AccountDAO	accountDAO;

	/** The job DAO. */
	@Autowired
	private JobDAO		jobDAO;

	/** The message DAO. */
	@Autowired
	private MessageDAO	messageDAO;

	private String		firebaseApiKey;

	public void setFirebaseApiKey(String __firebaseApiKey) {
		this.firebaseApiKey = __firebaseApiKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.MessageManagement#getListMessagesOfAccount(com.lasso.rest.model.
	 * datasource.Account)
	 */
	@Override
	public List<Object[]> getListMessagesOfAccount(Account __account) {
		List<Message> _messages;
		if (__account.getRole().equals(Constant.ROLE_DESIGNER)) {
			_messages = this.messageDAO.getListRootMessageByIdReceiver(__account.getId());
		}
		else {
			_messages = this.messageDAO.getListRootMessageByIdRSender(__account.getId());
		}

		List<Object[]> _messageDatas = new ArrayList<>();
		_messages.forEach(new Consumer<Message>() {

			@Override
			public void accept(Message __rootMessage) {
				Object[] _data = { null, null, null };// {message, sender, job}
				Message _lastMessage = ImplMessageManagement.this.messageDAO
				        .getLastMessageOfRoot(__rootMessage);
				_data[0] = _lastMessage == null ? __rootMessage : _lastMessage;
				Account _sender = ImplMessageManagement.this.accountDAO
				        .getAccountById(__rootMessage.getFromAccountId());
				if (_sender != null) {
					_data[1] = _sender;
				}
				Job _job = ImplMessageManagement.this.jobDAO.getJobById(__rootMessage.getJobId());
				if (_job != null) {
					_data[2] = _job;
				}
				_messageDatas.add(_data);
			}
		});
		return _messageDatas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.MessageManagement#getMessagesDetailOfAccount(com.lasso.rest.model.
	 * datasource.Account, int)
	 */
	@Override
	public List<Object[]> getMessagesDetailOfAccount(Account __account, int __idMessage) {
		Message _rootMessage = this.messageDAO.getRootMessage(__idMessage);
		if (_rootMessage == null) {
			throw new NotFoundException("Message not found");
		}
		List<Message> _messages = this.messageDAO.getListMessageByIdParent(_rootMessage.getId());
		_messages.add(0, _rootMessage);

		List<Object[]> _messageDatas = new ArrayList<>();
		_messages.forEach(new Consumer<Message>() {

			@Override
			public void accept(Message __message) {
				Account _sender = ImplMessageManagement.this.accountDAO
				        .getAccountById(__message.getFromAccountId());
				if (_sender != null) {
					Object[] _data = { __message, _sender };
					_messageDatas.add(_data);
				}
			}
		});
		return _messageDatas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.MessageManagement#sendMessage(com.lasso.rest.model.datasource.Account,
	 * com.lasso.rest.model.api.request.SendMessageRequest)
	 */
	@Override
	public void sendMessage(Account __sender, SendMessageRequest __sendMessageRequest) {
		Account _receiver = this.accountDAO.getAccountById(__sendMessageRequest.getIdReceiver());
		if (_receiver == null) {
			throw new NotFoundException("Receiver not found");
		}
		Message _rootMessage = this.messageDAO.getRootMessage(__sendMessageRequest.getIdRoot());
		if (_rootMessage == null) {
			throw new NotFoundException("Root message not found");
		}
		Message _message = new Message(__sender.getId(), _rootMessage.getJobId(),
		        __sendMessageRequest.getMessage(), _rootMessage.getTitle(), _receiver.getId());
		this.messageDAO.saveMessage(_message);
	}

	@Override
	public void sendPush(SendPushRequest __pushRequest) throws UnirestException, IOException {
		final String _firebaseHost = "https://fcm.googleapis.com/fcm/send";
		ObjectMapper _mapper = new ObjectMapper();
		HttpResponse<String> _response = Unirest.post(_firebaseHost)
		        .header("Content-Type", "application/json")
		        .header("Authorization", "key=" + this.firebaseApiKey)
		        .body(_mapper.writeValueAsString(__pushRequest)).asString();
		Logger _logger = Logger.getLogger(getClass());
		_logger.info("Send push status: " + _response.getStatus());
		SendPushResponse _pushResponse = _mapper.readValue(_response.getBody(),
		        SendPushResponse.class);
		_logger.info("Result: Success - " + _pushResponse.getSuccess() + ", Failure - "
		        + _pushResponse.getFailure());
	}

	/**
	 * Sets the account DAO.
	 *
	 * @param __accountDAO the new account DAO
	 */
	public void setAccountDAO(AccountDAO __accountDAO) {
		this.accountDAO = __accountDAO;
	}

	/**
	 * Sets the job DAO.
	 *
	 * @param __jobDAO the new job DAO
	 */
	public void setJobDAO(JobDAO __jobDAO) {
		this.jobDAO = __jobDAO;
	}

	/**
	 * Sets the message DAO.
	 *
	 * @param __messageDAO the new message DAO
	 */
	public void setMessageDAO(MessageDAO __messageDAO) {
		this.messageDAO = __messageDAO;
	}

}
