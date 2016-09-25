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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				id;

	@Lob
	@Column(name = "account_info")
	private String				accountInfo;

	@Column(name = "alternative_contact")
	private String				alternativeContact;

	@Column(name = "app_session")
	private String				appSession;

	@Column(name = "company_address")
	private String				companyAddress;

	@Column(name = "company_name")
	private String				companyName;

	@Column(name = "company_telephone")
	private String				companyTelephone;

	@Column(name = "country_id")
	private Integer				countryId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				created;

	private Byte				deleted;

	@Column(name = "device_id")
	private String				deviceId;

	private String				email;

	private Integer				featured;

	private Short				gender;

	@Column(name = "handphone_number")
	private String				handphoneNumber;

	private String				image;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				modified;

	private String				name;

	private String				otp;

	private String				password;

	@Column(name = "payment_method")
	private Byte				paymentMethod;

	private Integer				recommended;

	private Integer				rewards;

	private Byte				role;

	@Lob
	private String				settings;

	private Byte				status;

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
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @param __id the id to set
	 */
	public void setId(Integer __id) {
		this.id = __id;
	}

	/**
	 * @return the accountInfo
	 */
	public String getAccountInfo() {
		return this.accountInfo;
	}

	/**
	 * @param __accountInfo the accountInfo to set
	 */
	public void setAccountInfo(String __accountInfo) {
		this.accountInfo = __accountInfo;
	}

	/**
	 * @return the alternativeContact
	 */
	public String getAlternativeContact() {
		return this.alternativeContact;
	}

	/**
	 * @param __alternativeContact the alternativeContact to set
	 */
	public void setAlternativeContact(String __alternativeContact) {
		this.alternativeContact = __alternativeContact;
	}

	/**
	 * @return the appSession
	 */
	public String getAppSession() {
		return this.appSession;
	}

	/**
	 * @param __appSession the appSession to set
	 */
	public void setAppSession(String __appSession) {
		this.appSession = __appSession;
	}

	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return this.companyAddress;
	}

	/**
	 * @param __companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(String __companyAddress) {
		this.companyAddress = __companyAddress;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return this.companyName;
	}

	/**
	 * @param __companyName the companyName to set
	 */
	public void setCompanyName(String __companyName) {
		this.companyName = __companyName;
	}

	/**
	 * @return the companyTelephone
	 */
	public String getCompanyTelephone() {
		return this.companyTelephone;
	}

	/**
	 * @param __companyTelephone the companyTelephone to set
	 */
	public void setCompanyTelephone(String __companyTelephone) {
		this.companyTelephone = __companyTelephone;
	}

	/**
	 * @return the countryId
	 */
	public Integer getCountryId() {
		return this.countryId;
	}

	/**
	 * @param __countryId the countryId to set
	 */
	public void setCountryId(Integer __countryId) {
		this.countryId = __countryId;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return this.created;
	}

	/**
	 * @param __created the created to set
	 */
	public void setCreated(Date __created) {
		this.created = __created;
	}

	/**
	 * @return the deleted
	 */
	public Byte getDeleted() {
		return this.deleted;
	}

	/**
	 * @param __deleted the deleted to set
	 */
	public void setDeleted(Byte __deleted) {
		this.deleted = __deleted;
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return this.deviceId;
	}

	/**
	 * @param __deviceId the deviceId to set
	 */
	public void setDeviceId(String __deviceId) {
		this.deviceId = __deviceId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param __email the email to set
	 */
	public void setEmail(String __email) {
		this.email = __email;
	}

	/**
	 * @return the featured
	 */
	public Integer getFeatured() {
		return this.featured;
	}

	/**
	 * @param __featured the featured to set
	 */
	public void setFeatured(Integer __featured) {
		this.featured = __featured;
	}

	/**
	 * @return the gender
	 */
	public Short getGender() {
		return this.gender;
	}

	/**
	 * @param __gender the gender to set
	 */
	public void setGender(Short __gender) {
		this.gender = __gender;
	}

	/**
	 * @return the handphoneNumber
	 */
	public String getHandphoneNumber() {
		return this.handphoneNumber;
	}

	/**
	 * @param __handphoneNumber the handphoneNumber to set
	 */
	public void setHandphoneNumber(String __handphoneNumber) {
		this.handphoneNumber = __handphoneNumber;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return this.image;
	}

	/**
	 * @param __image the image to set
	 */
	public void setImage(String __image) {
		this.image = __image;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return this.modified;
	}

	/**
	 * @param __modified the modified to set
	 */
	public void setModified(Date __modified) {
		this.modified = __modified;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param __name the name to set
	 */
	public void setName(String __name) {
		this.name = __name;
	}

	/**
	 * @return the otp
	 */
	public String getOtp() {
		return this.otp;
	}

	/**
	 * @param __otp the otp to set
	 */
	public void setOtp(String __otp) {
		this.otp = __otp;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param __password the password to set
	 */
	public void setPassword(String __password) {
		this.password = __password;
	}

	/**
	 * @return the paymentMethod
	 */
	public Byte getPaymentMethod() {
		return this.paymentMethod;
	}

	/**
	 * @param __paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(Byte __paymentMethod) {
		this.paymentMethod = __paymentMethod;
	}

	/**
	 * @return the recommended
	 */
	public Integer getRecommended() {
		return this.recommended;
	}

	/**
	 * @param __recommended the recommended to set
	 */
	public void setRecommended(Integer __recommended) {
		this.recommended = __recommended;
	}

	/**
	 * @return the rewards
	 */
	public Integer getRewards() {
		return this.rewards;
	}

	/**
	 * @param __rewards the rewards to set
	 */
	public void setRewards(Integer __rewards) {
		this.rewards = __rewards;
	}

	/**
	 * @return the role
	 */
	public Byte getRole() {
		return this.role;
	}

	/**
	 * @param __role the role to set
	 */
	public void setRole(Byte __role) {
		this.role = __role;
	}

	/**
	 * @return the settings
	 */
	public String getSettings() {
		return this.settings;
	}

	/**
	 * @param __settings the settings to set
	 */
	public void setSettings(String __settings) {
		this.settings = __settings;
	}

	/**
	 * @return the status
	 */
	public Byte getStatus() {
		return this.status;
	}

	/**
	 * @param __status the status to set
	 */
	public void setStatus(Byte __status) {
		this.status = __status;
	}

	/**
	 * @return the subscribe
	 */
	public Byte getSubscribe() {
		return this.subscribe;
	}

	/**
	 * @param __subscribe the subscribe to set
	 */
	public void setSubscribe(Byte __subscribe) {
		this.subscribe = __subscribe;
	}

}
