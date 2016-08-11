/*
 * 
 */
package com.lasso.rest.service.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.NotFoundException;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.define.Constant;
import com.lasso.exception.ResourceExistException;
import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.model.api.request.AccountRegisterRequest;
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
			_account.setModified(new Date());
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
	 * @see com.lasso.rest.service.AccountManagement#getAllAccounts()
	 */
	@Override
	public List<Account> getAllAccounts() {
		return this.accountDAO.getAll();
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
		_account.setModified(new Date());
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
			_account.setModified(new Date());
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
}
