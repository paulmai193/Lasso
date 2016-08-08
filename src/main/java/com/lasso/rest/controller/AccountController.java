package com.lasso.rest.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lasso.rest.model.api.response.AccountRegisterReponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.service.AccountManagement;
import com.lasso.rest.service.GenericManagement;

@Component
@Path("/account")
@Produces(value = { MediaType.APPLICATION_JSON })
public class AccountController {

	@Autowired
	private AccountManagement	accountManagement;

	@Autowired
	private GenericManagement	genericManagement;

	@GET
	public List<Account> getAllAccounts() {
		return this.accountManagement.getAllAccounts();
	}

	@POST
	@Path("/register/{role}")
	@Consumes(MediaType.APPLICATION_JSON)
	public AccountRegisterReponse registerNewAccount(@PathParam("role") int __role,
	        Account __newAccount) {
		return new AccountRegisterReponse(this.accountManagement.registerUserAccount(__newAccount));
	}

}
