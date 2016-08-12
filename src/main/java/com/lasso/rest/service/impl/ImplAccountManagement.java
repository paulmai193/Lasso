/*
 * 
 */
package com.lasso.rest.service.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.define.Constant;
import com.lasso.exception.AuthenticateException;
import com.lasso.exception.ResourceExistException;
import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.model.api.request.AccountRegisterRequest;
import com.lasso.rest.model.api.response.LoginResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.service.AccountManagement;
import com.lasso.util.EmailUtil;

/**
 * The Class ImplAccountManagement.
 *
 * @author Paul Mai
 */
@Service
@Transactional
public class ImplAccountManagement implements AccountManagement {

	/** The account DAO. */
	@Autowired
	private AccountDAO					accountDAO;

	/** The map Credentials of token. */
	private Map<String, LoginResponse>	mapTokenOfUser;

	/** The map user current Credentials. */
	private Map<Integer, LoginResponse>	mapUserCurrentToken;

	/**
	 * Instantiates a new impl account management.
	 */
	public ImplAccountManagement() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#activateAccount(java.lang.Integer, int)
	 */
	@Override
	public boolean activateAccount(Integer __accountId, int __code) {
		Account _account = this.accountDAO.getAccountById(__accountId);
		if (_account.getStatus().equals(Constant.ACC_ACTIVATE)) {
			return true;
		}
		else if (_account.getActivationCode().equals(__code)) {
			_account.setActivationCode(null);
			_account.setStatus(Constant.ACC_ACTIVATE);
			_account.setModified();
			this.accountDAO.updateAccount(_account);
			return true;
		}
		else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#changePassword(java.lang.String,
	 * java.lang.String, com.lasso.rest.model.datasource.Account)
	 */
	@Override
	public boolean changePassword(String __oldPassword, String __newPassword, Account __account) {
		if (__oldPassword.equals(__account.getPassword())) {
			__account.setPassword(__newPassword);
			this.accountDAO.updateAccount(__account);
			return true;
		}
		else {
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#getAllAccounts()
	 */
	@Override
	public List<Account> getAllAccounts() {
		return this.accountDAO.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#login(java.lang.String, java.lang.String)
	 */
	@Override
	public LoginResponse login(String __email, String __password) {
		Account _account = this.accountDAO.getAccountByEmail(__email);
		LoginResponse _response;
		if (_account == null) {
			throw new AuthenticateException("Email or password not valid", Status.UNAUTHORIZED);
		}
		else if (!_account.getPassword().equals(__password)) {
			if (_account.getActivationCode().equals(__password)) {
				// In reset password case
				_response = new LoginResponse(_account.getId(),
				        RandomStringUtils.randomAlphanumeric(200), true);
			}
			else {
				throw new AuthenticateException("Email or password not valid", Status.UNAUTHORIZED);
			}
		}
		else if (_account.getStatus().equals(Constant.ACC_NOT_ACTIVATE)) {
			throw new AuthenticateException("Email not activated", Status.FORBIDDEN);
		}
		else {
			_response = new LoginResponse(_account.getId(),
			        RandomStringUtils.randomAlphanumeric(200));
		}

		this.mapTokenOfUser.put(_response.getToken(), _response);
		this.mapUserCurrentToken.put(_response.getIdAccount(), _response);

		return _response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#logout(java.lang.Integer)
	 */
	@Override
	public void logout(Integer __idAccount) {
		LoginResponse __credentials = this.mapUserCurrentToken.remove(__idAccount);
		if (__credentials != null) {
			this.mapTokenOfUser.remove(__credentials.getToken());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.AccountManagement#registerUserAccount(com.lasso.rest.model.datasource.
	 * Account)
	 */
	@Override
	public String registerUserAccount(AccountRegisterRequest __registerAccount) {
		// Check email account exist
		String _email = __registerAccount.getEmail().getValue();
		Account _account = this.accountDAO.getAccountByEmail(_email);
		if (_account == null) {
			_account = new Account(__registerAccount);
		}
		else if (_account.getStatus().equals(Constant.ACC_NOT_ACTIVATE)) {
			_account.set(__registerAccount);
		}
		else if (_account.getStatus().equals(Constant.ACC_ACTIVATE)) {
			// Check status of exist account
			throw new ResourceExistException(
			        "Email " + __registerAccount.getEmail() + " was exist");
		}

		// Request email available, create new account
		Integer _uniqueCode = RandomUtils.nextInt(100000, 999999);
		_account.setActivationCode(_uniqueCode);
		_account.setModified();
		Integer _id = this.accountDAO.createAccount(_account);
		String _query = MessageFormat.format("/account/activate?id={0, number,#}&ref={1, number,#}",
		        _id, _uniqueCode);
		return _query;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#resetPassword(java.lang.String)
	 */
	public void resetPassword(String __email)
	        throws NotFoundException, AddressException, MessagingException {
		Account _account = this.accountDAO.getAccountByEmail(__email);
		if (_account == null) {
			throw new NotFoundException("Email not exist");
		}
		else {
			int _randomPassword = RandomUtils.nextInt(100000, 999999);

			// Temporate save in activation code field
			_account.setActivationCode(_randomPassword);
			_account.setModified();
			this.accountDAO.updateAccount(_account);

			// Send random password to email
			EmailUtil.getInstance().sendEmail(__email,
			        _randomPassword + " là mật khẩu ứng dụng mới của bạn",
			        "Hãy dùng mật khẩu <strong>" + _randomPassword
			                + "</strong> để đăng nhập vào ứng dụng",
			        RecipientType.TO);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#sendActivationEmail(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void sendActivationEmail(String __email, String __refLink)
	        throws AddressException, MessagingException {
		EmailUtil.getInstance().sendEmail(__email, "Xác thực tài khoản",
		        "Vui lòng bấm vào link sau để xác thực tài khoản:<br>" + __refLink,
		        RecipientType.TO);
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
	 * Sets the map token of user.
	 *
	 * @param __mapTokenOfUser the map token of user
	 */
	public void setMapTokenOfUser(Map<String, LoginResponse> __mapTokenOfUser) {
		this.mapTokenOfUser = __mapTokenOfUser;
	}

	/**
	 * Sets the map user current token.
	 *
	 * @param __mapUserCurrentToken the map user current token
	 */
	public void setMapUserCurrentToken(Map<Integer, LoginResponse> __mapUserCurrentToken) {
		this.mapUserCurrentToken = __mapUserCurrentToken;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#validateAccountToken(java.lang.String)
	 */
	@Override
	public Account validateAccountToken(String __token) {
		try {
			// Check token valid
			LoginResponse _credentials = this.mapTokenOfUser.get(__token);
			if (_credentials == null) {
				// Not login
				throw new AuthenticateException("Invalid token", Status.UNAUTHORIZED);
			}
			else {
				LoginResponse _currentCredentials = this.mapUserCurrentToken
				        .get(_credentials.getIdAccount());
				if (!_currentCredentials.getToken().equals(__token)) {
					// Old token
					throw new AuthenticateException("Token expired", Status.UNAUTHORIZED);
				}
				else if (_currentCredentials.getExpired().compareTo(new Date()) <= 0) {
					// Old token
					throw new AuthenticateException("Token expired", Status.UNAUTHORIZED);
				}
				else {
					// True token
					return this.accountDAO.getAccountById(_currentCredentials.getIdAccount());
				}
			}
		}
		finally {
			// TODO Scan mapTokenOfUser to remove 2-day-old-token
			final Iterator<Entry<String, LoginResponse>> _it = this.mapTokenOfUser.entrySet()
			        .iterator();
			_it.forEachRemaining(new Consumer<Entry<String, LoginResponse>>() {

				@Override
				public void accept(Entry<String, LoginResponse> __t) {
					if (new Date().getTime()
			                - __t.getValue().getCreated().getTime() > (2 * 24 * 60 * 60 * 1000)) {
						_it.remove();
					}
				}
			});
		}
	}
}
