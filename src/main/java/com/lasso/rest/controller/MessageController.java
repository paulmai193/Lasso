package com.lasso.rest.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lasso.define.Constant;
import com.lasso.rest.controller.filter.AccountAllow;
import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.response.GetOrderResponse;
import com.lasso.rest.model.api.response.ListMessageResponse;
import com.lasso.rest.model.api.response.MessageDetailResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Message;
import com.lasso.rest.service.MessageManagement;
import com.lasso.rest.service.UserManagement;

@Controller
@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@AccountAuthenticate
@AccountAllow(status = "" + Constant.ACC_ACTIVATE)
public class MessageController {

	/** The avatar storage path. */
	private String	avatarStoragePath;

	private String	jobStoragePath;

	public void setJobStoragePath(String __jobStoragePath) {
		this.jobStoragePath = __jobStoragePath;
	}

	@Autowired
	private UserManagement userManagement;

	public void setUserManagement(UserManagement __userManagement) {
		this.userManagement = __userManagement;
	}

	public void setAvatarStoragePath(String __avatarStoragePath) {
		this.avatarStoragePath = __avatarStoragePath;
	}

	/** The http host. */
	private String httpHost;

	public void setHttpHost(String __httpHost) {
		this.httpHost = __httpHost;
	}

	@Context
	private SecurityContext		validateContext;

	@Autowired
	private MessageManagement	messageManagement;

	public void setMessageManagement(MessageManagement __messageManagement) {
		this.messageManagement = __messageManagement;
	}

	@GET
	public ListMessageResponse getListMessage() {
		Account _account = (Account) validateContext.getUserPrincipal();
		List<Object[]> _messageDatas = this.messageManagement.getListMessagesOfAccount(_account);
		String _prefixAvatar = this.httpHost + this.avatarStoragePath;
		return new ListMessageResponse(_messageDatas, _prefixAvatar);
	}

	@GET
	@Path("/detail")
	public MessageDetailResponse getMessageDetail(@QueryParam("id") int __idMessage) {
		Account _account = (Account) validateContext.getUserPrincipal();
		List<Object[]> _messageDatas = this.messageManagement.getMessagesDetailOfAccount(_account,
		        __idMessage);
		Message _rootMessage = (Message) _messageDatas.get(0)[0];
		Object[] _orderData = this.userManagement.getOrderDataById(_rootMessage.getJobId());
		String _prefixAvatar = this.httpHost + this.avatarStoragePath;
		String _prefixJob = this.httpHost + this.jobStoragePath;
		GetOrderResponse _orderDetail = new GetOrderResponse(_orderData, _prefixAvatar, null, null,
		        null, _prefixJob);
		return new MessageDetailResponse(_orderDetail, _messageDatas, _prefixAvatar);
	}

}
