/*
 * 
 */
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
import com.lasso.define.JobConfirmationConstant;
import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.dao.JobAccountDAO;
import com.lasso.rest.dao.JobDAO;
import com.lasso.rest.dao.MessageDAO;
import com.lasso.rest.model.api.request.ReadMessageRequest;
import com.lasso.rest.model.api.request.SendMessageRequest;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.AccountSettings;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.JobsAccount;
import com.lasso.rest.model.datasource.Message;
import com.lasso.rest.model.push.PushNotification;
import com.lasso.rest.model.push.SendPushRequest;
import com.lasso.rest.service.MessageManagement;
import com.lasso.template.DesignerNewMessageEmail;
import com.lasso.template.EmailTemplate;
import com.lasso.template.UserNewMessageEmail;
import com.lasso.util.EmailUtil;
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
	private AccountDAO		accountDAO;

	/** The email util. */
	private EmailUtil		emailUtil;

	/** The firebase api key. */
	private String			firebaseApiKey;

	/** The http host. */
	private String			httpHost;

	/** The job account DAO. */
	@Autowired
	private JobAccountDAO	jobAccountDAO;

	/** The job DAO. */
	@Autowired
	private JobDAO			jobDAO;

	/** The message DAO. */
	@Autowired
	private MessageDAO		messageDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.MessageManagement#getListMessagesOfAccount(com.
	 * lasso.rest.model. datasource.Account)
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
			try {
				Object[] _data = { null, null, null, null };// {message, sender, job, jobaccount}
				Message _lastMessage = ImplMessageManagement.this.messageDAO
						.getLastMessageOfRoot(_rootMessage);
				_data[0] = _lastMessage == null ? _rootMessage : _lastMessage;
				Account _sender = ImplMessageManagement.this.accountDAO
						.getAccountById(_rootMessage.getFromAccountId());
				_data[1] = _sender;
				Job _job = ImplMessageManagement.this.jobDAO.getJobById(_rootMessage.getJobId());
				Account _designer;
				if (_sender.getRole().byteValue() == Constant.ROLE_DESIGNER) {
					_designer = _sender;
				}
				else {
					_designer = ImplMessageManagement.this.accountDAO
							.getAccountById(_rootMessage.getToAccountId());
				}
				JobsAccount _jobsAccount = ImplMessageManagement.this.jobAccountDAO
						.getByJobAndDesignerId(_job.getId(), _designer.getId());
				if (_job != null && _jobsAccount != null) {
					if (__account.getRole().equals(Constant.ROLE_DESIGNER)) {
						_data[2] = _job;
						_data[3] = _jobsAccount;
					}
					else if (_job.getPaid().byteValue() == (byte) 1
							&& __account.getRole().equals(Constant.ROLE_USER)
							&& _jobsAccount.getConfirm()
							.byteValue() == JobConfirmationConstant.JOB_ACCEPT.getCode()) {
						_data[2] = _job;
						_data[3] = _jobsAccount;
					}
					else {
						throw new RuntimeException();
					}
					_messageDatas.add(_data);
				}
			}
			catch (RuntimeException _ex) {
				// Swallow this exception
			}
		});

		return _messageDatas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.MessageManagement#getMessagesDetailOfAccount(com.
	 * lasso.rest.model. datasource.Account, int)
	 */
	@Override
	public List<Object[]> getMessagesDetailOfAccount(Account __account, int __idJob) {
		Message _rootMessage;
		if (__account.getRole().byteValue() == Constant.ROLE_DESIGNER) {
			_rootMessage = this.messageDAO.getRootMessageOfReceiverByIdJob(__account.getId(),
					__idJob);
		}
		else {
			JobsAccount _jobsAccount = this.jobAccountDAO.getAcceptByJobId(__idJob);
			Account _designer = this.accountDAO.getAccountById(_jobsAccount.getAccountId());
			_rootMessage = this.messageDAO.getRootMessageOfReceiverByIdJob(_designer.getId(),
					__idJob);
		}
		Job _job = this.jobDAO.getJobById(__idJob);
		if (_job == null) {
			throw new NotFoundException("Job not found");
		}
		List<Message> _messages = this.messageDAO.getListMessageByIdParent(_rootMessage.getId());
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
	 * com.lasso.rest.service.MessageManagement#readMessage(com.lasso.rest.model
	 * .datasource.Account, com.lasso.rest.model.api.request.ReadMessageRequest)
	 */
	@Override
	public void readMessage(Account __sender, ReadMessageRequest __readMessageRequest) {
		Message _message = this.messageDAO.getMessageById(__readMessageRequest.getIdMessage());
		_message.setIsRead((byte) 1);
		this.messageDAO.updateMessage(_message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.MessageManagement#sendMessage(com.lasso.rest.model
	 * .datasource.Account, com.lasso.rest.model.api.request.SendMessageRequest)
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
				int _idMessage = this.messageDAO.saveMessage(_message);

				// Send in seperate thread
				Account _receiver = ImplMessageManagement.this.accountDAO.getAccountById(_idReceiver);
				new Thread(new Runnable() {

					@Override
					public void run() {
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
							else {
								Logger.getLogger(ImplMessageManagement.this.getClass())
								.debug("Get message setting: "
										+ _accountSettings.getAppSettings().getMessages() == null
										? "null"
												: _accountSettings.getAppSettings().getMessages());
							}

							// Send email
							if (_accountSettings.getEmailSettings().getMessages() != null
									&& _accountSettings.getEmailSettings().getMessages().equals("on")) {
								// TODO Notify email
								EmailTemplate _emailTemplate;
								String _link = ImplMessageManagement.this.httpHost + "/message-detail-"
										+ _idMessage + ".html?device_id=" + _receiver.getDeviceId();
								if (_receiver.getRole().byteValue() == Constant.ROLE_DESIGNER) {
									_emailTemplate = new DesignerNewMessageEmail(_receiver.getName(),
											_link);
								}
								else {
									_emailTemplate = new UserNewMessageEmail(_receiver.getName(), _link);
								}
								ImplMessageManagement.this.emailUtil.sendEmailByTemplate(
										_receiver.getEmail(), "New Message", _emailTemplate.getContent(),
										javax.mail.Message.RecipientType.TO, _emailTemplate.getTemplate());
							}
						}
						catch (Exception _ex) {
							Logger.getLogger(ImplMessageManagement.this.getClass()).warn("Unwanted error",
									_ex);
						}
					}
				}).start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.MessageManagement#sendPush(com.lasso.rest.model.
	 * push.SendPushRequest)
	 */
	@Override
	public void sendPush(SendPushRequest __pushRequest) throws UnirestException, IOException {
		if ((__pushRequest.getTo() != null && !__pushRequest.getTo().isEmpty())
				|| (__pushRequest.getPushTokens() != null
				&& !__pushRequest.getPushTokens().isEmpty())) {
			final String _firebaseHost = "https://fcm.googleapis.com/fcm/send";
			ObjectMapper _mapper = new ObjectMapper();
			HttpResponse<String> _response = Unirest.post(_firebaseHost)
					.header("Content-Type", "application/json")
					.header("Authorization", "key=" + this.firebaseApiKey)
					.body(_mapper.writeValueAsString(__pushRequest)).asString();
			Logger _logger = Logger.getLogger(this.getClass());
			_logger.info("Send push status: " + _response.getStatus());
			_logger.info("Send push response: " + _response.getBody());
			// SendPushResponse _pushResponse =
			// _mapper.readValue(_response.getBody(),
			// SendPushResponse.class);
			// _logger.info("Result: Success - " + _pushResponse.getSuccess() +
			// ", Failure - "
			// + _pushResponse.getFailure());
			System.out.println("Send push status: " + _response.getStatus());
			System.out.println("Send push response: " + _response.getBody());
		}
	}

	/**
	 * Sets the account DAO.
	 *
	 * @param __accountDAO
	 *        the new account DAO
	 */
	public void setAccountDAO(AccountDAO __accountDAO) {
		this.accountDAO = __accountDAO;
	}

	/**
	 * Sets the email util.
	 *
	 * @param __emailUtil
	 *        the new email util
	 */
	public void setEmailUtil(EmailUtil __emailUtil) {
		this.emailUtil = __emailUtil;
	}

	/**
	 * Sets the firebase api key.
	 *
	 * @param __firebaseApiKey
	 *        the new firebase api key
	 */
	public void setFirebaseApiKey(String __firebaseApiKey) {
		this.firebaseApiKey = __firebaseApiKey;
	}

	/**
	 * Sets the http host.
	 *
	 * @param __httpHost
	 *        the new http host
	 */
	public void setHttpHost(String __httpHost) {
		this.httpHost = __httpHost;
	}

	/**
	 * Sets the job account DAO.
	 *
	 * @param __jobAccountDAO
	 *        the new job account DAO
	 */
	public void setJobAccountDAO(JobAccountDAO __jobAccountDAO) {
		this.jobAccountDAO = __jobAccountDAO;
	}

	/**
	 * Sets the job DAO.
	 *
	 * @param __jobDAO
	 *        the new job DAO
	 */
	public void setJobDAO(JobDAO __jobDAO) {
		this.jobDAO = __jobDAO;
	}

	/**
	 * Sets the message DAO.
	 *
	 * @param __messageDAO
	 *        the new message DAO
	 */
	public void setMessageDAO(MessageDAO __messageDAO) {
		this.messageDAO = __messageDAO;
	}

}
