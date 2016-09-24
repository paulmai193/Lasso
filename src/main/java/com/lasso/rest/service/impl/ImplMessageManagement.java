package com.lasso.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.dao.JobDAO;
import com.lasso.rest.dao.MessageDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.Message;
import com.lasso.rest.service.MessageManagement;

@Service
public class ImplMessageManagement implements MessageManagement {

	@Autowired
	private MessageDAO	messageDAO;

	@Autowired
	private AccountDAO	accountDAO;

	@Autowired
	private JobDAO		jobDAO;

	public void setJobDAO(JobDAO __jobDAO) {
		this.jobDAO = __jobDAO;
	}

	public void setAccountDAO(AccountDAO __accountDAO) {
		this.accountDAO = __accountDAO;
	}

	public void setMessageDAO(MessageDAO __messageDAO) {
		this.messageDAO = __messageDAO;
	}

	@Override
	public List<Object[]> getListMessagesOfAccount(Account __account) {
		List<Message> _messages = this.messageDAO.getListRootMessageByIdReceiver(__account.getId());
		List<Object[]> _messageDatas = new ArrayList<>();
		_messages.forEach(new Consumer<Message>() {

			@Override
			public void accept(Message __rootMessage) {
				Object[] _data = { null, null, null };// {message, sender, job title}
				Message _lastMessage = messageDAO.getLastMessageOfRoot(__rootMessage);
				_data[0] = _lastMessage == null ? __rootMessage : _lastMessage;
				Account _sender = accountDAO.getAccountById(__rootMessage.getFromAccountId());
				if (_sender != null) {
					_data[1] = _sender;
				}
				Job _job = jobDAO.getJobById(__rootMessage.getJobId());
				if (_job != null) {
					_data[2] = _job;
				}
				_messageDatas.add(_data);
			}
		});
		return _messageDatas;
	}

	@Override
	public List<Object[]> getMessagesDetailOfAccount(Account __account, int __idMessage) {
		Message _rootMessage = this.messageDAO.getRootMessage(__idMessage);
		if (_rootMessage == null) {
			throw new NotFoundException("Message not found");
		}
		List<Message> _messages = this.messageDAO.getListMessageByIdParent(__idMessage);
		_messages.add(0, _rootMessage);

		List<Object[]> _messageDatas = new ArrayList<>();
		_messages.forEach(new Consumer<Message>() {

			@Override
			public void accept(Message __message) {
				Account _sender = accountDAO.getAccountById(__message.getFromAccountId());
				if (_sender != null) {
					Object[] _data = { __message, _sender };
					_messageDatas.add(_data);
				}
			}
		});
		return _messageDatas;
	}

}
