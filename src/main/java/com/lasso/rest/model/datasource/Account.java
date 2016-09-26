/*
 * 
 */
package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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

	/** The account info. */
	@Lob
	@Column(name = "account_info")
	private String				accountInfo;

	/** The alternative contact. */
	@Column(name = "alternative_contact")
	private String				alternativeContact;

	/** The app session. */
	@Column(name = "app_session")
	private String				appSession;

	/** The company address. */
	@Column(name = "company_address")
	private String				companyAddress;

	/** The company name. */
	@Column(name = "company_name")
	private String				companyName;

	/** The company telephone. */
	@Column(name = "company_telephone")
	private String				companyTelephone;

	/** The country id. */
	@Column(name = "country_id")
	private Integer				countryId;

	/** The created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	/** The deleted. */
	private Byte				deleted;

	/** The device id. */
	@Column(name = "device_id")
	private String				deviceId;

	/** The email. */
	private String				email;

	/** The featured. */
	private Integer				featured;

	/** The gender. */
	private Short				gender;

	/** The handphone number. */
	@Column(name = "handphone_number")
	private String				handphoneNumber;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				id;

	/** The image. */
	private String				image;

	/** The modified. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	/** The name. */
	private String				name;

	/** The otp. */
	private String				otp;

	/** The password. */
	private String				password;

	/** The payment method. */
	@Column(name = "payment_method")
	private Byte				paymentMethod;

	/** The recommended. */
	private Integer				recommended;

	/** The rewards. */
	private Integer				rewards;

	/** The role. */
	private Byte				role;

	/** The settings. */
	@Lob
	private String				settings;

	/** The status. */
	private Byte				status;

	/** The subscribe. */
	private Byte				subscribe;

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
		this.image = "";
		this.created = this.modified = new Date();
		this.deviceId = __accountRegister.getPushToken();
		this.email = __accountRegister.getEmail().getValue();
		this.modified = new Date();
		this.name = __accountRegister.getName();
		this.password = __accountRegister.getPassword();
		this.handphoneNumber = __accountRegister.getPhone().getValue();
		this.role = __accountRegister.getRole();
		this.rewards = 1;
		this.subscribe = __accountRegister.getSubscribe() ? (byte) 1 : (byte) 0;
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
	 * @return the accountInfo
	 */
	public String getAccountInfo() {
		return this.accountInfo;
	}

	/**
	 * Gets the alternative contact.
	 *
	 * @return the alternativeContact
	 */
	public String getAlternativeContact() {
		return this.alternativeContact;
	}

	/**
	 * Gets the app session.
	 *
	 * @return the appSession
	 */
	public String getAppSession() {
		return this.appSession;
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
	 * Gets the company telephone.
	 *
	 * @return the companyTelephone
	 */
	public String getCompanyTelephone() {
		return this.companyTelephone;
	}

	/**
	 * Gets the country id.
	 *
	 * @return the countryId
	 */
	public Integer getCountryId() {
		return this.countryId;
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
	 * Gets the deleted.
	 *
	 * @return the deleted
	 */
	public Byte getDeleted() {
		return this.deleted;
	}

	/**
	 * Gets the device id.
	 *
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return this.deviceId;
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
	 * Gets the featured.
	 *
	 * @return the featured
	 */
	public Integer getFeatured() {
		return this.featured;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public Short getGender() {
		return this.gender;
	}

	/**
	 * Gets the handphone number.
	 *
	 * @return the handphoneNumber
	 */
	public String getHandphoneNumber() {
		return this.handphoneNumber;
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
	 * Gets the image.
	 *
	 * @return the image
	 */
	public String getImage() {
		return this.image;
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
	 * Gets the payment method.
	 *
	 * @return the paymentMethod
	 */
	public Byte getPaymentMethod() {
		return this.paymentMethod;
	}

	/**
	 * Gets the recommended.
	 *
	 * @return the recommended
	 */
	public Integer getRecommended() {
		return this.recommended;
	}

	/**
	 * Gets the rewards.
	 *
	 * @return the rewards
	 */
	public Integer getRewards() {
		return this.rewards;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Byte getRole() {
		return this.role;
	}

	/**
	 * Gets the settings.
	 *
	 * @return the settings
	 */
	public String getSettings() {
		return this.settings;
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
	public Byte getSubscribe() {
		return this.subscribe;
	}

	/**
	 * Sets the account info.
	 *
	 * @param __accountInfo the accountInfo to set
	 */
	public void setAccountInfo(String __accountInfo) {
		this.accountInfo = __accountInfo;
	}

	/**
	 * Sets the alternative contact.
	 *
	 * @param __alternativeContact the alternativeContact to set
	 */
	public void setAlternativeContact(String __alternativeContact) {
		this.alternativeContact = __alternativeContact;
	}

	/**
	 * Sets the app session.
	 *
	 * @param __appSession the appSession to set
	 */
	public void setAppSession(String __appSession) {
		this.appSession = __appSession;
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
	 * Sets the company telephone.
	 *
	 * @param __companyTelephone the companyTelephone to set
	 */
	public void setCompanyTelephone(String __companyTelephone) {
		this.companyTelephone = __companyTelephone;
	}

	/**
	 * Sets the country id.
	 *
	 * @param __countryId the countryId to set
	 */
	public void setCountryId(Integer __countryId) {
		this.countryId = __countryId;
	}

	/**
	 * Sets the created.
	 *
	 * @param __created the created to set
	 */
	public void setCreated(Date __created) {
		this.created = __created;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param __deleted the deleted to set
	 */
	public void setDeleted(Byte __deleted) {
		this.deleted = __deleted;
	}

	/**
	 * Sets the device id.
	 *
	 * @param __deviceId the deviceId to set
	 */
	public void setDeviceId(String __deviceId) {
		this.deviceId = __deviceId;
	}

	/**
	 * Sets the email.
	 *
	 * @param __email the email to set
	 */
	public void setEmail(String __email) {
		this.email = __email;
	}

	/**
	 * Sets the featured.
	 *
	 * @param __featured the featured to set
	 */
	public void setFeatured(Integer __featured) {
		this.featured = __featured;
	}

	/**
	 * Sets the gender.
	 *
	 * @param __gender the gender to set
	 */
	public void setGender(Short __gender) {
		this.gender = __gender;
	}

	/**
	 * Sets the handphone number.
	 *
	 * @param __handphoneNumber the handphoneNumber to set
	 */
	public void setHandphoneNumber(String __handphoneNumber) {
		this.handphoneNumber = __handphoneNumber;
	}

	/**
	 * Sets the id.
	 *
	 * @param __id the id to set
	 */
	public void setId(Integer __id) {
		this.id = __id;
	}

	/**
	 * Sets the image.
	 *
	 * @param __image the image to set
	 */
	public void setImage(String __image) {
		this.image = __image;
	}

	/**
	 * Sets the modified.
	 *
	 * @param __modified the modified to set
	 */
	public void setModified(Date __modified) {
		this.modified = __modified;
	}

	/**
	 * Sets the name.
	 *
	 * @param __name the name to set
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
	 * @param __password the password to set
	 */
	public void setPassword(String __password) {
		this.password = __password;
	}

	/**
	 * Sets the payment method.
	 *
	 * @param __paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(Byte __paymentMethod) {
		this.paymentMethod = __paymentMethod;
	}

	/**
	 * Sets the recommended.
	 *
	 * @param __recommended the recommended to set
	 */
	public void setRecommended(Integer __recommended) {
		this.recommended = __recommended;
	}

	/**
	 * Sets the rewards.
	 *
	 * @param __rewards the rewards to set
	 */
	public void setRewards(Integer __rewards) {
		this.rewards = __rewards;
	}

	/**
	 * Sets the role.
	 *
	 * @param __role the role to set
	 */
	public void setRole(Byte __role) {
		this.role = __role;
	}

	/**
	 * Sets the settings.
	 *
	 * @param __settings the settings to set
	 */
	public void setSettings(String __settings) {
		this.settings = __settings;
	}

	/**
	 * Sets the status.
	 *
	 * @param __status the status to set
	 */
	public void setStatus(Byte __status) {
		this.status = __status;
	}

	/**
	 * Sets the subscribe.
	 *
	 * @param __subscribe the subscribe to set
	 */
	public void setSubscribe(Byte __subscribe) {
		this.subscribe = __subscribe;
	}

}
