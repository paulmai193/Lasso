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

import org.hibernate.annotations.ColumnDefault;
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

	/** The country. */
	private Country				country;

	/** The created. */
	private Date				created;

	/** The email. */
	private String				email;

	/** The handphone number. */
	private String				handphoneNumber;

	/** The id. */
	private AccountPK			id;

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

	/** The rewards. */
	private int					rewards				= 1;

	/** The role. */
	private byte				role;

	/** The settings. */
	private String				settings;

	/** The status. */
	private byte				status;

	/** The subscribe. */
	private byte				subscribe;

	/** The web session. */
	private String				webSession;

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
		this.id = new AccountPK(__accountRegister.getCountry().getId());
		this.image = "";
		this.country = __accountRegister.getCountry();
		this.created = new Date();
		this.email = __accountRegister.getEmail().getValue();
		this.modified = new Date();
		this.name = __accountRegister.getName();
		this.password = __accountRegister.getPassword();
		this.handphoneNumber = __accountRegister.getPhone().getValue();
		this.role = __accountRegister.getRole();
		this.subscribe = (byte) (__accountRegister.getSubscribe() ? 1 : 0);
		if (__accountRegister instanceof DesignerRegisterRequest) {
			// Designer
			this.alternativeContact = ((DesignerRegisterRequest) __accountRegister)
					.getAlternativeContact();
			this.paymentMethod = ((DesignerRegisterRequest) __accountRegister).getPayment();
		}
		else if (__accountRegister instanceof UserRegisterRequest) {
			// User
			this.companyAddress = ((UserRegisterRequest) __accountRegister).getCompanyAddress();
			this.companyName = ((UserRegisterRequest) __accountRegister).getCompanyName();
			this.companyTelephone = ((UserRegisterRequest) __accountRegister).getCompanyPhone()
					.getValue();
		}

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
	 * Gets the activation code.
	 *
	 * @return the activation code
	 */
	@Column(name = "activation_code")
	public int getActivationCode() {
		return this.activationCode;
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
	 * Gets the app session.
	 *
	 * @return the app session
	 */
	@Column(name = "app_session", length = 45)
	public String getAppSession() {
		return this.appSession;
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
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	@Column(name = "company_name", length = 100)
	public String getCompanyName() {
		return this.companyName;
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
	 * Gets the country.
	 *
	 * @return the country
	 */
	// bi-directional many-to-one association to Country
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", nullable = false, insertable = false, updatable = false)
	public Country getCountry() {
		return this.country;
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	@Column(nullable = false, length = 50)
	public String getEmail() {
		return this.email;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	@EmbeddedId
	public AccountPK getId() {
		return this.id;
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
	 * Gets the modified.
	 *
	 * @return the modified
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModified() {
		return this.modified;
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
	 * Gets the otp.
	 *
	 * @return the otp
	 */
	@Column(length = 45)
	public String getOtp() {
		return this.otp;
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
	 * Gets the payment method.
	 *
	 * @return the payment method
	 */
	@Column(name = "payment_method")
	public byte getPaymentMethod() {
		return this.paymentMethod;
	}

	/**
	 * Gets the rewards.
	 *
	 * @return the rewards
	 */
	@ColumnDefault("1")
	public int getRewards() {
		return this.rewards;
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
	 * Gets the settings.
	 *
	 * @return the settings
	 */
	@Lob
	public String getSettings() {
		return this.settings;
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
	 * Gets the subscribe.
	 *
	 * @return the subscribe
	 */
	public byte getSubscribe() {
		return this.subscribe;
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
	 * Sets the account info.
	 *
	 * @param accountInfo the new account info
	 */
	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
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
	 * Sets the alternative contact.
	 *
	 * @param alternativeContact the new alternative contact
	 */
	public void setAlternativeContact(String alternativeContact) {
		this.alternativeContact = alternativeContact;
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
	 * Sets the company address.
	 *
	 * @param companyAddress the new company address
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
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
	 * Sets the company telephone.
	 *
	 * @param companyTelephone the new company telephone
	 */
	public void setCompanyTelephone(String companyTelephone) {
		this.companyTelephone = companyTelephone;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(Country country) {
		this.country = country;
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
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(AccountPK id) {
		this.id = id;
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
	 * Sets the modified.
	 */
	public void setModified() {
		this.setModified(new Date());
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * Sets the rewards.
	 *
	 * @param __rewards the new rewards
	 */
	public void setRewards(int __rewards) {
		this.rewards = __rewards;
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
	 * Sets the settings.
	 *
	 * @param __settings the new settings
	 */
	public void setSettings(String __settings) {
		this.settings = __settings;
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
	 * Sets the subscribe.
	 *
	 * @param subscribe the new subscribe
	 */
	public void setSubscribe(byte subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * Sets the web session.
	 *
	 * @param webSession the new web session
	 */
	public void setWebSession(String webSession) {
		this.webSession = webSession;
	}
}
