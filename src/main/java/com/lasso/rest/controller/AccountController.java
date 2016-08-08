package com.lasso.rest.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lasso.rest.model.api.response.AccountRegisterReponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.service.AccountManagement;

@Component
@Path("/account")
@Produces(value = { MediaType.APPLICATION_JSON })
public class AccountController {

	@Autowired
	private AccountManagement accountManagement;

	@GET
	public List<Account> getAllAccounts() {
		return accountManagement.getAllAccounts();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public AccountRegisterReponse registerNewAccount(Account __newAccount) {
		return new AccountRegisterReponse(accountManagement.registerUserAccount(__newAccount));
	}

}
