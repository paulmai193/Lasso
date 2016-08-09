/*
 * 
 */
package com.lasso.rest.service.impl;

import java.text.MessageFormat;
import java.util.List;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.exception.ResourceExistException;
import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.variable.EmailParam;
import com.lasso.rest.service.AccountManagement;
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
	private AccountDAO accountDAO;

	/**
	 * Gets the account DAO.
	 *
	 * @return the account DAO
	 */
	public AccountDAO getAccountDAO() {
		return this.accountDAO;
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
	 * Instantiates a new impl account management.
	 */
	public ImplAccountManagement() {
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
	public String registerUserAccount(Account __newAccount) {
		// Check email account exist first
		String _email = __newAccount.getEmail();
		if (this.checkExistEmail(new EmailParam(_email))) {
			throw new ResourceExistException("Email " + __newAccount.getEmail() + " was exist");
		}
		else {
			// Request email available, create new account
			Integer _id = this.accountDAO.createAccount(__newAccount);
			Integer _uniqueCode = RandomUtils.nextInt(100000, 999999);
			String _query = MessageFormat.format("?id={1}&code={1}", _id, _uniqueCode);
			return EncryptionUtil.encode(_query);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.AccountManagement#checkExistEmail(com.lasso.rest.model.variable.
	 * EmailParam)
	 */
	@Override
	public boolean checkExistEmail(EmailParam __email) {
		if (this.accountDAO.getAccountsByEmail(__email.getValue()).size() > 0) {
			return true;
		}
		else {
			return false;
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
		EmailUtil.instance.sendEmail(__email, "Xác thực tài khoản",
		        "Vui lòng bấm vào link sau để xác thực tài khoản:<br>" + __refLink,
		        RecipientType.TO);
	}
}
