package com.lasso.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.dao.MessageDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Message;
import com.lasso.rest.service.MessageManagement;

@Service
public class ImplMessageManagement implements MessageManagement {

	@Autowired
	private MessageDAO	messageDAO;

	@Autowired
	private AccountDAO	accountDAO;

	public void setAccountDAO(AccountDAO __accountDAO) {
		this.accountDAO = __accountDAO;
	}

	public void setMessageDAO(MessageDAO __messageDAO) {
		this.messageDAO = __messageDAO;
	}

	@Override
	public List<Object[]> getListMessagesOfAccount(Account __account) {
		List<Message> _messages = this.messageDAO.getListMessageByIdReceiver(__account.getId());
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

	@Override
	public List<Object[]> getMessagesDetailOfAccount(Account __account, int __idMessage) {
		// TODO Auto-generated method stub
		return null;
	}

}
