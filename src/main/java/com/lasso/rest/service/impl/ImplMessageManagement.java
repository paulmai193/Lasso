package com.lasso.rest.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.lasso.rest.model.datasource.AccountSettings;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.Message;
import com.lasso.rest.model.push.PushNotification;
import com.lasso.rest.model.push.SendPushRequest;
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

	/** The firebase api key. */
	private String		firebaseApiKey	= "AIzaSyC_wC6A14jCGLuA1ARbwXHSuIoMJSsbo8g";

	/** The job DAO. */
	@Autowired
	private JobDAO		jobDAO;

	/** The message DAO. */
	@Autowired
	private MessageDAO	messageDAO;

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
		_messages.forEach(_rootMessage -> {
			Object[] _data = { null, null, null };// {message, sender, job}
			Message _lastMessage = ImplMessageManagement.this.messageDAO
			        .getLastMessageOfRoot(_rootMessage);
			_data[0] = _lastMessage == null ? _rootMessage : _lastMessage;
			Account _sender = ImplMessageManagement.this.accountDAO
			        .getAccountById(_rootMessage.getFromAccountId());
			if (_sender != null) {
				_data[1] = _sender;
			}
			Job _job = ImplMessageManagement.this.jobDAO.getJobById(_rootMessage.getJobId());
			if (_job != null) {
				if (__account.getRole().equals(Constant.ROLE_DESIGNER)) {
					_data[2] = _job;
				}
				else if (_job.getPaid().equals((byte) 1)
				        && __account.getRole().equals(Constant.ROLE_USER)) {
					_data[2] = _job;
				}
				else {
					return;
				}
			}
			_messageDatas.add(_data);
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
	public List<Object[]> getMessagesDetailOfAccount(Account __account, int __idJob) {
		Message _rootMessage = this.messageDAO.getRootMessage(__idJob);
		// if (_rootMessage == null) {
		// throw new NotFoundException("Message not found");
		// }
		// List<Message> _messages = this.messageDAO.getListMessageByIdParent(_rootMessage.getId());
		Job _job = this.jobDAO.getJobById(__idJob);
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		List<Message> _messages = this.messageDAO.getListMessageByIdJob(__idJob);
		_messages.add(0, _rootMessage);

		List<Object[]> _messageDatas = new ArrayList<>();
		_messages.forEach(_message -> {
			// Update read status for these messages
			_message.setIsRead((byte) 1);
			this.messageDAO.updateMessage(_message);

			// Get return data
			Account _sender = ImplMessageManagement.this.accountDAO
			        .getAccountById(_message.getFromAccountId());
			if (_sender != null) {
				Object[] _data = { _message, _sender };
				_messageDatas.add(_data);
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
		Message _rootMessage = this.messageDAO.getRootMessage(__sendMessageRequest.getIdRoot());
		if (_rootMessage == null) {
			throw new NotFoundException("Root message not found");
		}
		int _idReceiver = _rootMessage.getFromAccountId().equals(__sender.getId())
		        ? _rootMessage.getToAccountId() : _rootMessage.getFromAccountId();
		Message _message = new Message(__sender.getId(), _rootMessage.getJobId(),
		        __sendMessageRequest.getMessage(), __sendMessageRequest.getIdRoot(),
		        _rootMessage.getTitle(), _idReceiver);
		this.messageDAO.saveMessage(_message);
		new Thread(new Runnable() {

			@Override
			public void run() {
				Account _receiver = ImplMessageManagement.this.accountDAO
				        .getAccountById(_idReceiver);
				try {
					AccountSettings _accountSettings = _receiver.getSettings();

					// Send push in-app
					if (_accountSettings.getAppSettings().getMessages() != null
					        && _accountSettings.getAppSettings().getMessages().equals("on")) {
						SendPushRequest _pushRequest = new SendPushRequest();
						_pushRequest.setNotification(
						        new PushNotification(_message.getTitle(), _message.getMessage()));
						_pushRequest.setTo(_receiver.getDeviceId());
						ImplMessageManagement.this.sendPush(_pushRequest);
					}

					// Send email
					if (_accountSettings.getEmailSettings().getMessages() != null
					        && _accountSettings.getEmailSettings().getMessages().equals("on")) {
						// TODO Notify email
						// EmailTemplate _emailTemplate = new DesignerActivateEmail(
						// _designer.getName(), "#");
						// ImplUserManagement.this.emailUtil.sendEmailByTemplate(
						// _designer.getEmail(), "New offer",
						// _emailTemplate.getContent(), RecipientType.TO,
						// _emailTemplate.getTemplate());
					}
				}
				catch (Exception _ex) {
					Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
				}
			}
		}).start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.MessageManagement#sendPush(com.lasso.rest.model.push.SendPushRequest)
	 */
	@Override
	public void sendPush(SendPushRequest __pushRequest) throws UnirestException, IOException {
		final String _firebaseHost = "https://fcm.googleapis.com/fcm/send";
		ObjectMapper _mapper = new ObjectMapper();
		HttpResponse<String> _response = Unirest.post(_firebaseHost)
		        .header("Content-Type", "application/json")
		        .header("Authorization", "key=" + this.firebaseApiKey)
		        .body(_mapper.writeValueAsString(__pushRequest)).asString();
		Logger _logger = Logger.getLogger(this.getClass());
		_logger.info("Send push status: " + _response.getStatus());
		_logger.info("Send push response: " + _response.getBody());
		// SendPushResponse _pushResponse = _mapper.readValue(_response.getBody(),
		// SendPushResponse.class);
		// _logger.info("Result: Success - " + _pushResponse.getSuccess() + ", Failure - "
		// + _pushResponse.getFailure());
		System.out.println("Send push status: " + _response.getStatus());
		System.out.println("Send push response: " + _response.getBody());
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
	 * Sets the firebase api key.
	 *
	 * @param __firebaseApiKey the new firebase api key
	 */
	public void setFirebaseApiKey(String __firebaseApiKey) {
		this.firebaseApiKey = __firebaseApiKey;
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
