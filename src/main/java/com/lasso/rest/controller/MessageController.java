package com.lasso.rest.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lasso.define.Constant;
import com.lasso.rest.controller.filter.AccountAllow;
import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.request.SendMessageRequest;
import com.lasso.rest.model.api.response.GetOrderResponse;
import com.lasso.rest.model.api.response.ListMessageResponse;
import com.lasso.rest.model.api.response.MessageDetailResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Message;
import com.lasso.rest.service.MessageManagement;
import com.lasso.rest.service.UserManagement;

/**
 * The Class MessageController.
 *
 * @author Paul Mai
 */
@Controller
@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@AccountAuthenticate
@AccountAllow(status = "" + Constant.ACC_ACTIVATE)
public class MessageController extends BaseController {

	/** The avatar storage path. */
	private String				avatarStoragePath;

	/** The http host. */
	private String				httpHost;

	/** The job storage path. */
	private String				jobStoragePath;

	/** The message management. */
	@Autowired
	private MessageManagement	messageManagement;

	/** The user management. */
	@Autowired
	private UserManagement		userManagement;

	/** The validate context. */
	@Context
	private SecurityContext		validateContext;

	/**
	 * Gets the list message.
	 *
	 * @return the list message
	 */
	@GET
	public ListMessageResponse getListMessage() {
		Account _account = (Account) this.validateContext.getUserPrincipal();
		List<Object[]> _messageDatas = this.messageManagement.getListMessagesOfAccount(_account);
		String _prefixAvatar = this.httpHost + this.avatarStoragePath;
		return new ListMessageResponse(_messageDatas, _prefixAvatar);
	}

	/**
	 * Gets the message detail.
	 *
	 * @param __idMessage the id message
	 * @return the message detail
	 */
	@GET
	@Path("/detail")
	public MessageDetailResponse getMessageDetail(@QueryParam("message_id") int __idMessage) {
		Account _account = (Account) this.validateContext.getUserPrincipal();
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

	/**
	 * Send message.
	 *
	 * @param __sendMessageRequest the send message request
	 * @return the response
	 */
	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendMessage(SendMessageRequest __sendMessageRequest) {
		__sendMessageRequest.validate();
		Account _sender = (Account) this.validateContext.getUserPrincipal();
		this.messageManagement.sendMessage(_sender, __sendMessageRequest);
		return this.success();
	}

	/**
	 * Sets the avatar storage path.
	 *
	 * @param __avatarStoragePath the new avatar storage path
	 */
	public void setAvatarStoragePath(String __avatarStoragePath) {
		this.avatarStoragePath = __avatarStoragePath;
	}

	/**
	 * Sets the http host.
	 *
	 * @param __httpHost the new http host
	 */
	public void setHttpHost(String __httpHost) {
		this.httpHost = __httpHost;
	}

	/**
	 * Sets the job storage path.
	 *
	 * @param __jobStoragePath the new job storage path
	 */
	public void setJobStoragePath(String __jobStoragePath) {
		this.jobStoragePath = __jobStoragePath;
	}

	/**
	 * Sets the message management.
	 *
	 * @param __messageManagement the new message management
	 */
	public void setMessageManagement(MessageManagement __messageManagement) {
		this.messageManagement = __messageManagement;
	}

	/**
	 * Sets the user management.
	 *
	 * @param __userManagement the new user management
	 */
	public void setUserManagement(UserManagement __userManagement) {
		this.userManagement = __userManagement;
	}

}
