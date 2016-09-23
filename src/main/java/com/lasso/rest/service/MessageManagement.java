package com.lasso.rest.service;

import java.util.List;

import com.lasso.rest.model.datasource.Account;

public interface MessageManagement {

	List<Object[]> getListMessagesOfAccount(Account __account);

	List<Object[]> getMessagesDetailOfAccount(Account __account, int __idMessage);

}
