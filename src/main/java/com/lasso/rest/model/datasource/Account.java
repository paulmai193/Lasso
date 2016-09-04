/*
 * 
 */
package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.lasso.rest.model.api.request.AccountRegisterRequest;
import com.lasso.rest.model.api.request.DesignerRegisterRequest;
import com.lasso.rest.model.api.request.UserRegisterRequest;

/**
 * The Class Account.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "accounts")
@DynamicInsert(true)
@DynamicUpdate(true)
public final class Account implements Principal, Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The id. */
	private AccountPK			id;

	/** The account info. */
	private String				accountInfo;

	/** The activation code. */
	private int					activationCode;

	/** The alternative contact. */
	private String				alternativeContact;

	/** The app session. */
	private String				appSession;

	/** The company address. */
	private String				companyAddress;

	/** The company name. */
	private String				companyName;

	/** The company telephone. */
	private String				companyTelephone;

	/** The created. */
	private Date				created;

	/** The email. */
	private String				email;

	/** The handphone number. */
	private String				handphoneNumber;

	/** The image. */
	private String				image;

	/** The modified. */
	private Date				modified;

	/** The name. */
	private String				name;

	/** The otp. */
	private String				otp;

	/** The password. */
	private String				password;

	/** The payment method. */
	private byte				paymentMethod;

	/** The role. */
	private byte				role;

	/** The status. */
	private byte				status;

	/** The subscribe. */
	private byte				subscribe;

	/** The web session. */
	private String				webSession;

	/** The country. */
	private Country				country;

	/**
	 * Instantiates a new account.
	 */
	public Account() {
	}

	/**
	 * Instantiates a new account.
	 *
	 * @param __accountRegister the account register
	 */
	public Account(AccountRegisterRequest __accountRegister) {
		if (__accountRegister instanceof DesignerRegisterRequest) {
			// Designer
			this.alternativeContact = __accountRegister.getAlternativeContact();
			this.image = "";
			this.country = __accountRegister.getCountry();
			this.created = new Date();
			this.email = __accountRegister.getEmail().getValue();
			this.modified = new Date();
			this.name = __accountRegister.getName();
			this.password = __accountRegister.getPassword();
			this.paymentMethod = ((DesignerRegisterRequest) __accountRegister).getPayment();
			this.handphoneNumber = __accountRegister.getPhone().getValue();
			this.role = __accountRegister.getRole();
			this.subscribe = (byte) (__accountRegister.getSubscribe() ? 1 : 0);
		}
		else if (__accountRegister instanceof UserRegisterRequest) {
			// User
			this.alternativeContact = __accountRegister.getAlternativeContact();
			this.image = "";
			this.companyAddress = ((UserRegisterRequest) __accountRegister).getCompanyAddress();
			this.companyName = ((UserRegisterRequest) __accountRegister).getCompanyName();
			this.companyTelephone = ((UserRegisterRequest) __accountRegister).getCompanyPhone();
			this.country = __accountRegister.getCountry();
			this.created = new Date();
			this.email = __accountRegister.getEmail().getValue();
			this.modified = new Date();
			this.name = __accountRegister.getName();
			this.password = __accountRegister.getPassword();
			this.handphoneNumber = __accountRegister.getPhone().getValue();
			this.role = __accountRegister.getRole();
			this.subscribe = (byte) (__accountRegister.getSubscribe() ? 1 : 0);
		}

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@EmbeddedId
	public AccountPK getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(AccountPK id) {
		this.id = id;
	}

	/**
	 * Gets the account info.
	 *
	 * @return the account info
	 */
	@Lob
	@Column(name = "account_info")
	public String getAccountInfo() {
		return this.accountInfo;
	}

	/**
	 * Sets the account info.
	 *
	 * @param accountInfo the new account info
	 */
	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	/**
	 * Gets the activation code.
	 *
	 * @return the activation code
	 */
	@Column(name = "activation_code")
	public int getActivationCode() {
		return this.activationCode;
	}

	/**
	 * Sets the activation code.
	 *
	 * @param activationCode the new activation code
	 */
	public void setActivationCode(int activationCode) {
		this.activationCode = activationCode;
	}

	/**
	 * Gets the alternative contact.
	 *
	 * @return the alternative contact
	 */
	@Column(name = "alternative_contact", length = 50)
	public String getAlternativeContact() {
		return this.alternativeContact;
	}

	/**
	 * Sets the alternative contact.
	 *
	 * @param alternativeContact the new alternative contact
	 */
	public void setAlternativeContact(String alternativeContact) {
		this.alternativeContact = alternativeContact;
	}

	/**
	 * Gets the app session.
	 *
	 * @return the app session
	 */
	@Column(name = "app_session", length = 45)
	public String getAppSession() {
		return this.appSession;
	}

	/**
	 * Sets the app session.
	 *
	 * @param appSession the new app session
	 */
	public void setAppSession(String appSession) {
		this.appSession = appSession;
	}

	/**
	 * Gets the company address.
	 *
	 * @return the company address
	 */
	@Column(name = "company_address", length = 250)
	public String getCompanyAddress() {
		return this.companyAddress;
	}

	/**
	 * Sets the company address.
	 *
	 * @param companyAddress the new company address
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	@Column(name = "company_name", length = 100)
	public String getCompanyName() {
		return this.companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets the company telephone.
	 *
	 * @return the company telephone
	 */
	@Column(name = "company_telephone", length = 50)
	public String getCompanyTelephone() {
		return this.companyTelephone;
	}

	/**
	 * Sets the company telephone.
	 *
	 * @param companyTelephone the new company telephone
	 */
	public void setCompanyTelephone(String companyTelephone) {
		this.companyTelephone = companyTelephone;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated() {
		return this.created;
	}

	/**
	 * Sets the created.
	 *
	 * @param created the new created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	@Column(nullable = false, length = 50)
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the handphone number.
	 *
	 * @return the handphone number
	 */
	@Column(name = "handphone_number", nullable = false, length = 50)
	public String getHandphoneNumber() {
		return this.handphoneNumber;
	}

	/**
	 * Sets the handphone number.
	 *
	 * @param handphoneNumber the new handphone number
	 */
	public void setHandphoneNumber(String handphoneNumber) {
		this.handphoneNumber = handphoneNumber;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	@Column(nullable = false, length = 250)
	public String getImage() {
		return this.image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Gets the modified.
	 *
	 * @return the modified
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModified() {
		return this.modified;
	}

	/**
	 * Sets the modified.
	 *
	 * @param modified the new modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * Sets the modified.
	 */
	public void setModified() {
		this.setModified(new Date());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.Principal#getName()
	 */
	@Column(nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the otp.
	 *
	 * @return the otp
	 */
	@Column(length = 45)
	public String getOtp() {
		return this.otp;
	}

	/**
	 * Sets the otp.
	 *
	 * @param otp the new otp
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	@Column(nullable = false, length = 40)
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the payment method.
	 *
	 * @return the payment method
	 */
	@Column(name = "payment_method")
	public byte getPaymentMethod() {
		return this.paymentMethod;
	}

	/**
	 * Sets the payment method.
	 *
	 * @param paymentMethod the new payment method
	 */
	public void setPaymentMethod(byte paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	@Column(nullable = false)
	public byte getRole() {
		return this.role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(byte role) {
		this.role = role;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	@Column(nullable = false)
	public byte getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(byte status) {
		this.status = status;
	}

	/**
	 * Gets the subscribe.
	 *
	 * @return the subscribe
	 */
	public byte getSubscribe() {
		return this.subscribe;
	}

	/**
	 * Sets the subscribe.
	 *
	 * @param subscribe the new subscribe
	 */
	public void setSubscribe(byte subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * Gets the web session.
	 *
	 * @return the web session
	 */
	@Column(name = "web_session", length = 45)
	public String getWebSession() {
		return this.webSession;
	}

	/**
	 * Sets the web session.
	 *
	 * @param webSession the new web session
	 */
	public void setWebSession(String webSession) {
		this.webSession = webSession;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	// bi-directional many-to-one association to Country
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "countrie_id", nullable = false, insertable = false, updatable = false)
	public Country getCountry() {
		return this.country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
}
