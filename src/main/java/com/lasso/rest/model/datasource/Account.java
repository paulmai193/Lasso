/*
 * 
 */
package com.lasso.rest.model.datasource;

import java.security.Principal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import com.lasso.rest.model.api.request.AccountRegisterRequest;
import com.lasso.rest.model.api.request.DesignerRegisterRequest;
import com.lasso.rest.model.api.request.UserRegisterRequest;

/**
 * The Class Account.
 *
 * @author Paul Mai
 */
@Entity
@Table(catalog = "art_design", name = "accounts")
@DynamicInsert(true)
@DynamicUpdate(true)
public final class Account implements Principal {

	/** The account info. */
	@Column(name = "account_info")
	@Type(type = "text")
	private String	accountInfo;

	/** The activation code. */
	@Column(length = 50, name = "activation_code")
	private Integer	activationCode;

	/** The alternative contact. */
	@Column(length = 50, name = "alternative_contact")
	private String	alternativeContact;

	/** The avatar. */
	@Column(length = 250, name = "image")
	private String	avatar;

	/** The company address. */
	@Column(length = 250, name = "company_address")
	private String	companyAddress;

	/** The company name. */
	@Column(length = 100, name = "company_name")
	private String	companyName;

	/** The company phone. */
	@Column(length = 50, name = "company_telephone")
	private String	companyPhone;

	/** The country. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country	country;

	/** The created. */
	@Column(length = 19, name = "created")
	private Date	created;

	/** The email. */
	@Column(length = 50, name = "email")
	private String	email;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(length = 11, name = "id")
	private Integer	id;

	/** The modified. */
	@Column(length = 19, name = "modified")
	private Date	modified;

	/** The name. */
	@Column(length = 100, name = "name")
	private String	name;

	/** The otp. */
	@Column(length = 45, name = "otp")
	private String	otp;

	/** The password. */
	@Column(length = 40, name = "password")
	private String	password;

	/** The payment. */
	@Column(length = 1, name = "payment_method")
	private Byte	payment;

	/** The phone. */
	@Column(length = 50, name = "handphone_number")
	private String	phone;

	/** The value. */
	@Column(length = 1, name = "role")
	private Byte	role;

	/** The status. */
	@Column(length = 1, name = "status")
	private Byte	status;

	/** The subscribe. */
	@Column(length = 1, name = "subscribe")
	private Boolean	subscribe;

	/** The token. */
	@Column(length = 45, name = "app_session")
	private String	token;

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
			this.avatar = "";
			this.country = __accountRegister.getCountry();
			this.created = new Date();
			this.email = __accountRegister.getEmail().getValue();
			this.modified = new Date();
			this.name = __accountRegister.getName();
			this.password = __accountRegister.getPassword();
			this.payment = ((DesignerRegisterRequest) __accountRegister).getPayment();
			this.phone = __accountRegister.getPhone().getValue();
			this.role = __accountRegister.getRole();
			this.subscribe = __accountRegister.getSubscribe();
		}
		else if (__accountRegister instanceof UserRegisterRequest) {
			// User
			this.alternativeContact = __accountRegister.getAlternativeContact();
			this.avatar = "";
			this.companyAddress = ((UserRegisterRequest) __accountRegister).getCompanyAddress();
			this.companyName = ((UserRegisterRequest) __accountRegister).getCompanyName();
			this.companyPhone = ((UserRegisterRequest) __accountRegister).getCompanyPhone();
			this.country = __accountRegister.getCountry();
			this.created = new Date();
			this.email = __accountRegister.getEmail().getValue();
			this.modified = new Date();
			this.name = __accountRegister.getName();
			this.password = __accountRegister.getPassword();
			this.phone = __accountRegister.getPhone().getValue();
			this.role = __accountRegister.getRole();
			this.subscribe = __accountRegister.getSubscribe();
		}

	}

	/**
	 * Gets the account info.
	 *
	 * @return the account info
	 */
	public String getAccountInfo() {
		return this.accountInfo;
	}

	/**
	 * Gets the activation code.
	 *
	 * @return the activation code
	 */
	public Integer getActivationCode() {
		return this.activationCode;
	}

	/**
	 * Gets the alternative contact.
	 *
	 * @return the alternative contact
	 */
	public String getAlternativeContact() {
		return this.alternativeContact;
	}

	/**
	 * Gets the avatar.
	 *
	 * @return the avatar
	 */
	public String getAvatar() {
		return this.avatar;
	}

	/**
	 * Gets the company address.
	 *
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return this.companyAddress;
	}

	/**
	 * Gets the company name.
	 *
	 * @return the companyName
	 */
	public String getCompanyName() {
		return this.companyName;
	}

	/**
	 * Gets the company phone.
	 *
	 * @return the companyPhone
	 */
	public String getCompanyPhone() {
		return this.companyPhone;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry() {
		return this.country;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public Date getCreated() {
		return this.created;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Gets the modified.
	 *
	 * @return the modified
	 */
	public Date getModified() {
		return this.modified;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the otp.
	 *
	 * @return the otp
	 */
	public String getOtp() {
		return this.otp;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Gets the payment.
	 *
	 * @return the payment
	 */
	public Byte getPayment() {
		return this.payment;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Byte getRole() {
		return this.role;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Byte getStatus() {
		return this.status;
	}

	/**
	 * Gets the subscribe.
	 *
	 * @return the subscribe
	 */
	public Boolean getSubscribe() {
		return this.subscribe;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * Sets the.
	 *
	 * @param __accountRegister the account register
	 */
	public void set(AccountRegisterRequest __accountRegister) {
		if (__accountRegister instanceof DesignerRegisterRequest) {
			// Designer
			this.alternativeContact = __accountRegister.getAlternativeContact();
			this.avatar = "";
			this.country = __accountRegister.getCountry();
			this.created = new Date();
			this.email = __accountRegister.getEmail().getValue();
			this.modified = new Date();
			this.name = __accountRegister.getName();
			this.password = __accountRegister.getPassword();
			this.payment = ((DesignerRegisterRequest) __accountRegister).getPayment();
			this.phone = __accountRegister.getPhone().getValue();
			this.role = __accountRegister.getRole();
			this.subscribe = __accountRegister.getSubscribe();
		}
		else {
			// User
			this.alternativeContact = __accountRegister.getAlternativeContact();
			this.avatar = "";
			this.companyAddress = ((UserRegisterRequest) __accountRegister).getCompanyAddress();
			this.companyName = ((UserRegisterRequest) __accountRegister).getCompanyName();
			this.companyPhone = ((UserRegisterRequest) __accountRegister).getCompanyPhone();
			this.country = __accountRegister.getCountry();
			this.created = new Date();
			this.email = __accountRegister.getEmail().getValue();
			this.modified = new Date();
			this.name = __accountRegister.getName();
			this.password = __accountRegister.getPassword();
			this.phone = __accountRegister.getPhone().getValue();
			this.role = __accountRegister.getRole();
			this.subscribe = __accountRegister.getSubscribe();
		}
	}

	/**
	 * Sets the account info.
	 *
	 * @param __accountInfo the new account info
	 */
	public void setAccountInfo(String __accountInfo) {
		this.accountInfo = __accountInfo;
	}

	/**
	 * Sets the activation code.
	 *
	 * @param __activationCode the new activation code
	 */
	public void setActivationCode(Integer __activationCode) {
		this.activationCode = __activationCode;
	}

	/**
	 * Sets the alternative contact.
	 *
	 * @param __alternativeContact the new alternative contact
	 */
	public void setAlternativeContact(String __alternativeContact) {
		this.alternativeContact = __alternativeContact;
	}

	/**
	 * Sets the avatar.
	 *
	 * @param __avatar the new avatar
	 */
	public void setAvatar(String __avatar) {
		this.avatar = __avatar;
	}

	/**
	 * Sets the company address.
	 *
	 * @param __companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(String __companyAddress) {
		this.companyAddress = __companyAddress;
	}

	/**
	 * Sets the company name.
	 *
	 * @param __companyName the companyName to set
	 */
	public void setCompanyName(String __companyName) {
		this.companyName = __companyName;
	}

	/**
	 * Sets the company phone.
	 *
	 * @param __companyPhone the companyPhone to set
	 */
	public void setCompanyPhone(String __companyPhone) {
		this.companyPhone = __companyPhone;
	}

	/**
	 * Sets the country.
	 *
	 * @param __country the new country
	 */
	public void setCountry(Country __country) {
		this.country = __country;
	}

	/**
	 * Sets the created.
	 *
	 * @param __created the new created
	 */
	public void setCreated(Date __created) {
		this.created = __created;
	}

	/**
	 * Sets the email.
	 *
	 * @param __email the new email
	 */
	public void setEmail(String __email) {
		this.email = __email;
	}

	/**
	 * Sets the id.
	 *
	 * @param __id the new id
	 */
	public void setId(Integer __id) {
		this.id = __id;
	}

	/**
	 * <<<<<<< HEAD
	 * Sets the modified.
	 */
	public void setModified() {
		this.modified = new Date();
	}

	/**
	 * Sets the modified.
	 *
	 * @param __modified the new modified
	 */
	public void setModified(Date __modified) {
		this.modified = __modified;
	}

	/**
	 * =======
	 * >>>>>>> branch 'mai-bat-hu' of https://github.com/paulmai193/Lasso.git
	 * Sets the name.
	 *
	 * @param __name the new name
	 */
	public void setName(String __name) {
		this.name = __name;
	}

	/**
	 * Sets the otp.
	 *
	 * @param __otp the otp to set
	 */
	public void setOtp(String __otp) {
		this.otp = __otp;
	}

	/**
	 * Sets the password.
	 *
	 * @param __password the new password
	 */
	public void setPassword(String __password) {
		this.password = __password;
	}

	/**
	 * Sets the payment.
	 *
	 * @param __payment the new payment
	 */
	public void setPayment(Byte __payment) {
		this.payment = __payment;
	}

	/**
	 * Sets the phone.
	 *
	 * @param __phone the new phone
	 */
	public void setPhone(String __phone) {
		this.phone = __phone;
	}

	/**
	 * Sets the value.
	 *
	 * @param __role the new value
	 */
	public void setRole(Byte __role) {
		this.role = __role;
	}

	/**
	 * Sets the status.
	 *
	 * @param __status the new status
	 */
	public void setStatus(Byte __status) {
		this.status = __status;
	}

	/**
	 * Sets the subscribe.
	 *
	 * @param __subscribe the new subscribe
	 */
	public void setSubscribe(Boolean __subscribe) {
		this.subscribe = __subscribe;
	}

	/**
	 * Sets the token.
	 *
	 * @param __token the token to set
	 */
	public void setToken(String __token) {
		this.token = __token;
	}
}
