/*
 * 
 */
package com.lasso.rest.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lasso.define.Constant;
import com.lasso.exception.AuthenticateException;
import com.lasso.exception.ResourceExistException;
import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.model.api.request.AccountChangeDetailRequest;
import com.lasso.rest.model.api.request.AccountRegisterRequest;
import com.lasso.rest.model.api.request.DesignerChangeDetailRequest;
import com.lasso.rest.model.api.request.SettingsRequest;
import com.lasso.rest.model.api.request.UserChangeDetailRequest;
import com.lasso.rest.model.api.response.LoginResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.AccountSettings;
import com.lasso.rest.model.datasource.Country;
import com.lasso.rest.service.AccountManagement;
import com.lasso.template.DesignerActivateEmail;
import com.lasso.template.DesignerCongratEmail;
import com.lasso.template.DesignerResetPasswordEmail;
import com.lasso.template.EmailTemplate;
import com.lasso.template.UserActivateEmail;
import com.lasso.template.UserCongratEmail;
import com.lasso.template.UserResetPasswordEmail;
import com.lasso.util.EmailUtil;
import com.lasso.util.EncryptionUtil;

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
	private AccountDAO	accountDAO;

	/** The email util. */
	private EmailUtil	emailUtil;

	private String		httpHost;

	public void setHttpHost(String __httpHost) {
		this.httpHost = __httpHost;
	}

	/**
	 * Instantiates a new impl account management.
	 */
	public ImplAccountManagement() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.AccountManagement#changeAccountDetail(com.lasso.rest.model.datasource.
	 * Account, com.lasso.rest.model.api.request.AccountChangeDetailRequest)
	 */
	@Override
	public void changeAccountDetail(Account __account,
	        AccountChangeDetailRequest __accountChangeDetailRequest) {
		if (__accountChangeDetailRequest instanceof DesignerChangeDetailRequest) {
			__account.setAccountInfo(
			        ((DesignerChangeDetailRequest) __accountChangeDetailRequest).getAccountInfo());
			__account.setAlternativeContact(
			        ((DesignerChangeDetailRequest) __accountChangeDetailRequest)
			                .getAlternativeContact());
			__account.setPaymentMethod(
			        ((DesignerChangeDetailRequest) __accountChangeDetailRequest).getPayment());
		}
		else if (__accountChangeDetailRequest instanceof UserChangeDetailRequest) {
			__account.setCompanyAddress(
			        ((UserChangeDetailRequest) __accountChangeDetailRequest).getCompanyAddress());
			__account.setCompanyName(
			        ((UserChangeDetailRequest) __accountChangeDetailRequest).getCompanyName());
			__account.setCompanyTelephone(
			        ((UserChangeDetailRequest) __accountChangeDetailRequest).getCompanyPhone());
		}
		__account.setCountryId(__accountChangeDetailRequest.getCountry().getId());
		__account.setModified(new Date());
		__account.setHandphoneNumber(__accountChangeDetailRequest.getPhone().getValue());
		__account.setGender(__accountChangeDetailRequest.getGender());
		if (__accountChangeDetailRequest.getPassword() != null
		        && !__accountChangeDetailRequest.getPassword().isEmpty()) {
			__account.setPassword(__accountChangeDetailRequest.getPassword());
		}
		this.accountDAO.updateAccount(__account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#changeAvatar(com.lasso.rest.model.datasource.
	 * Account, java.lang.String)
	 */
	@Override
	public void changeAvatar(Account __account, String __avatarName) {
		__account.setImage(__avatarName);
		__account.setModified(new Date());
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
	 * @see com.lasso.rest.service.AccountManagement#resetPassword(java.lang.String)
	 */
	public String forgotPassword(String __email)
	        throws NotFoundException, AddressException, MessagingException {
		Account _account = this.accountDAO.getAccountByEmail(__email);
		if (_account == null) {
			throw new NotFoundException("Email not exist");
		}
		else {
			// Request email available, create new account
			String _otp = "" + EncryptionUtil.generateTOTP(_account.getEmail().getBytes());
			_account.setOtp(_otp.toString());
			_account.setModified(new Date());
			this.accountDAO.updateAccount(_account);
			return MessageFormat
			        .format((_account.getRole().equals((byte) 0) ? "user=" : "designer=")
			                + _account.getName() + "/reset?otp={0}", _otp);
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
	 * @see com.lasso.rest.service.AccountManagement#login(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public LoginResponse login(String __email, String __password, String __pushToken,
	        String __prefixAvatarUrl) {
		Account _account = this.accountDAO.getAccountByEmail(__email);
		LoginResponse _response;
		if (_account == null) {
			throw new AuthenticateException("Wrong email or password", Status.NOT_FOUND);
		}
		else if (!_account.getPassword().equals(__password)) {
			throw new AuthenticateException("Wrong email or password", Status.NOT_FOUND);
		}
		else {
			// Disable device id match this push token
			this.accountDAO.getAccountByDeviceId(__pushToken).forEach(_tempAccount -> {
				_tempAccount.setDeviceId(null);
				this.accountDAO.updateAccount(_tempAccount);
			});

			// generate push data
			String _token = RandomStringUtils.randomAlphanumeric(45);
			_account.setAppSession(_token);
			_account.setDeviceId(__pushToken);
			this.accountDAO.updateAccount(_account);

			_response = new LoginResponse(_token, _account, __prefixAvatarUrl);
		}

		return _response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#logout(com.lasso.rest.model.datasource.Account)
	 */
	@Override
	public void logout(Account __account) {
		__account.setAppSession(null);
		__account.setDeviceId(null);
		this.accountDAO.updateAccount(__account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.AccountManagement#registerUserAccount(com.lasso.rest.model.api.request
	 * .AccountRegisterRequest, com.lasso.rest.model.datasource.Country)
	 */
	@Override
	public String registerUserAccount(AccountRegisterRequest __registerAccount, Country __country) {
		// Check email account exist
		String _email = __registerAccount.getEmail().getValue();
		Account _account = this.accountDAO.getAccountByEmail(_email);
		if (_account == null) {
			_account = new Account(__registerAccount);
			_account.setCountryId(__country.getId());
			_account.setSettings(AccountSettings.createDefault());

			// Request email available, create new account
			String _otp = "" + EncryptionUtil.generateTOTP(_account.getEmail().getBytes());
			_account.setOtp(_otp.toString());
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
	 * @see com.lasso.rest.service.AccountManagement#resendActivate(com.lasso.rest.model.datasource.
	 * Account)
	 */
	@Override
	public String resendActivate(Account __account) {
		String _otp = "" + EncryptionUtil.generateTOTP(__account.getEmail().getBytes());
		__account.setOtp(_otp.toString());
		__account.setModified(new Date());
		this.accountDAO.createAccount(__account);
		return MessageFormat.format("/active?otp={0}", _otp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#resetPassword(com.lasso.rest.model.datasource.
	 * Account, java.lang.String)
	 */
	@Override
	public void resetPassword(Account __account, String __password) {
		__account.setPassword(__password);
		this.accountDAO.updateAccount(__account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#sendActivationEmail(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void sendActivationEmail(String __email, String __refLink, String __role,
	        String __firstName)
	        throws AddressException, MessagingException, URISyntaxException, IOException {
		EmailTemplate _emailTemplate;
		if (__role.equalsIgnoreCase("designer")) {
			_emailTemplate = new DesignerActivateEmail(__firstName, __refLink);
		}
		else {
			_emailTemplate = new UserActivateEmail(__firstName, __refLink);
		}
		this.emailUtil.sendEmailByTemplate(__email, "Activate Account", _emailTemplate.getContent(),
		        RecipientType.TO, _emailTemplate.getTemplate());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#sendResetPasswordEmail(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void sendResetPasswordEmail(String __email, String __refLink, String __role,
	        String __firstName)
	        throws AddressException, MessagingException, URISyntaxException, IOException {
		EmailTemplate _emailTemplate;
		if (__role.equalsIgnoreCase("designer")) {
			_emailTemplate = new DesignerResetPasswordEmail(__firstName, __refLink);
		}
		else {
			_emailTemplate = new UserResetPasswordEmail(__firstName, __refLink);
		}
		this.emailUtil.sendEmailByTemplate(__email, "Reset password", _emailTemplate.getContent(),
		        RecipientType.TO, _emailTemplate.getTemplate());
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
	 * Sets the email util.
	 *
	 * @param __emailUtil the new email util
	 */
	public void setEmailUtil(EmailUtil __emailUtil) {
		this.emailUtil = __emailUtil;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.service.AccountManagement#settings(com.lasso.rest.model.datasource.Account,
	 * com.lasso.rest.model.api.request.SettingsRequest)
	 */
	@Override
	public void settings(Account __account, SettingsRequest __settingsRequest)
	        throws JsonParseException, JsonMappingException, IOException {
		AccountSettings _settings = __account.getSettings();
		_settings.setAppSettings(__settingsRequest.getAppSettings());
		_settings.setEmailSettings(__settingsRequest.getEmailSettings());
		__account.setSettings(_settings);
		this.accountDAO.updateAccount(__account);
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
	 * @see com.lasso.rest.service.AccountManagement#verifyAccount(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public LoginResponse verifyAccount(String __type, String __otp, String __pushToken,
	        String __prefixAvatarUrl) {
		Account _account = this.accountDAO.getAccountByOtp(__otp);
		if (_account == null) {
			throw new BadRequestException("Invalid otp");
		}
		else {
			_account.setOtp(null);
			_account.setStatus(Constant.ACC_ACTIVATE);
			_account.setModified(new Date());
			this.accountDAO.updateAccount(_account);
			if (__type.equalsIgnoreCase("active")) {
				// TODO send welcome email
				new Thread(new Runnable() {

					@Override
					public void run() {
						String _link = ImplAccountManagement.this.httpHost + "?device_id="
						        + _account.getDeviceId();
						EmailTemplate _emailTemplate;
						try {
							if (_account.getRole().byteValue() == Constant.ROLE_DESIGNER) {
								_emailTemplate = new DesignerCongratEmail(_account.getName(),
								        _link);
							}
							else {
								_emailTemplate = new UserCongratEmail(_account.getName(), _link);
							}

							ImplAccountManagement.this.emailUtil.sendEmailByTemplate(
							        _account.getEmail(), "Congratuation",
							        _emailTemplate.getContent(), RecipientType.TO,
							        _emailTemplate.getTemplate());
						}
						catch (Exception _ex) {
							Logger.getLogger(this.getClass()).warn("Send email error", _ex);
						}

					}
				}, "Lasso Send Email").start();
			}
			return this.login(_account.getEmail(), _account.getPassword(), __pushToken,
			        __prefixAvatarUrl);
		}
	}
}
