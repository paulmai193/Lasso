/*
 * 
 */
package com.lasso.rest.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
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
			throw new AuthenticateException("Email or password not valid", Status.UNAUTHORIZED);
		}
		else {
			_response = new LoginResponse(_account.getId(),
			        RandomStringUtils.randomAlphanumeric(200), _account.getStatus(),
			        _account.getRole());
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
			throw new ResourceExistException(__registerAccount.getEmail() + " was exist");
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
	public String resetPassword(String __email)
	        throws NotFoundException, AddressException, MessagingException {
		Account _account = this.accountDAO.getAccountByEmail(__email);
		if (_account == null) {
			throw new NotFoundException("Email not exist");
		}
		else {
			// Request email available, create new account
			Integer _uniqueCode = RandomUtils.nextInt(100000, 999999);
			_account.setActivationCode(_uniqueCode);
			_account.setModified();
			this.accountDAO.updateAccount(_account);
			String _query = MessageFormat.format(
			        "/account/activate?id={0, number,#}&ref={1, number,#}", _account.getId(),
			        _uniqueCode);
			return _query;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#sendResetPasswordEmail(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void sendResetPasswordEmail(String __email, String __refLink)
	        throws AddressException, MessagingException {
		EmailUtil.getInstance().sendEmail(__email, "Phục hồi mật khẩu",
		        "Vui lòng bấm vào link sau để phục hồi mật khẩu của bạn:<br>" + __refLink,
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
				this.mapTokenOfUser.remove(__token);
				throw new AuthenticateException("Token expired", Status.UNAUTHORIZED);
			}
			else if (_currentCredentials.getExpired().compareTo(new Date()) <= 0) {
				// Old token
				this.mapTokenOfUser.remove(__token);
				this.mapUserCurrentToken.remove(_credentials.getIdAccount());
				throw new AuthenticateException("Token expired", Status.UNAUTHORIZED);
			}
			else {
				// True token
				return this.accountDAO.getAccountById(_currentCredentials.getIdAccount());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#changeAvatar(com.lasso.rest.model.datasource.
	 * Account, java.io.InputStream, java.io.File)
	 */
	@Override
	public void changeAvatar(Account __account, InputStream __fileStream, File __destinationFile)
	        throws IOException, IllegalArgumentException {
		BufferedImage _buffered = ImageIO.read(__fileStream);
		if (_buffered == null) {
			throw new IllegalArgumentException("File not image");
		}
		ImageIO.write(_buffered, "jpg", __destinationFile);

		__account.setAvatar(__destinationFile.getName());
		__account.setModified();
		accountDAO.updateAccount(__account);
	}
}
