/*
 * 
 */
package com.lasso.rest.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;

import javax.imageio.ImageIO;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.RandomStringUtils;
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
	private AccountDAO accountDAO;

	// /** The map Credentials of token. */
	// private Map<String, LoginResponse> mapTokenOfUser;
	//
	// /** The map user current Credentials. */
	// private Map<Integer, LoginResponse> mapUserCurrentToken;

	/**
	 * Instantiates a new impl account management.
	 */
	public ImplAccountManagement() {
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
		this.accountDAO.updateAccount(__account);
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
		else if (_account.getStatus().equals(Constant.ACC_NOT_ACTIVATE)) {
			throw new AuthenticateException("Account not activate", Status.FORBIDDEN);
		}
		else {
			String _token = RandomStringUtils.randomAlphanumeric(45);
			_account.setToken(_token);
			this.accountDAO.updateAccount(_account);

			_response = new LoginResponse(_account.getId(), _token, _account.getStatus(),
			        _account.getRole());
		}

		return _response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#logout(java.lang.Integer)
	 */
	@Override
	public void logout(Integer __idAccount) {
		Account _account = this.accountDAO.getAccountById(__idAccount);
		if (_account != null) {
			_account.setToken(null);
			this.accountDAO.updateAccount(_account);
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

			// Request email available, create new account
			String _otp = RandomStringUtils.randomNumeric(6);
			_account.setOtp(_otp.toString());
			_account.setModified();
			this.accountDAO.createAccount(_account);
			return MessageFormat.format("/active?otp={0}", _otp);
		}
		else {
			// Check status of exist account
			throw new ResourceExistException(__registerAccount.getEmail() + " was exist");
		}
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
			String _otp = RandomStringUtils.randomNumeric(6);
			_account.setOtp(_otp.toString());
			_account.setModified();
			this.accountDAO.updateAccount(_account);
			return MessageFormat.format("/reset?otp={0}", _otp);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#validateAccountToken(java.lang.String)
	 */
	@Override
	public Account validateAccountToken(String __token) {
		// Check token valid
		Account _account = this.accountDAO.getAccountByToken(__token);
		if (_account == null) {
			// Wrong login credentials
			throw new AuthenticateException("Invalid token", Status.UNAUTHORIZED);
		}
		else {
			return _account;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#verifyAccount(java.lang.String)
	 */
	@Override
	public boolean verifyAccount(String __otp) {
		Account _account = this.accountDAO.getAccountByOtp(__otp);
		if (_account == null) {
			return false;
		}
		else {
			_account.setOtp(null);
			_account.setStatus(Constant.ACC_ACTIVATE);
			_account.setModified();
			this.accountDAO.updateAccount(_account);
			return true;
		}
	}
}
