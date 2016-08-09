/*
 * 
 */
package com.lasso.rest.model.datasource;

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

@Entity
@Table(catalog = "art_design", name = "accounts")
@DynamicInsert(true)
@DynamicUpdate(true)
public final class Account {

	@Column(name = "account_info")
	@Type(type = "text")
	private String	accountInfo;

	@Column(length = 50, name = "activation_code")
	private Integer	activationCode;

	@Column(length = 50, name = "alternative_contact")
	private String	alternativeContact;

	@Column(length = 250, name = "image")
	private String	avatar;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country	country;

	@Column(length = 19, name = "created")
	private Date	created;

	@Column(length = 50, name = "email")
	private String	email;

	@Id
	@GeneratedValue
	@Column(length = 11, name = "id")
	private Integer	id;

	@Column(length = 19, name = "modified")
	private Date	modified;

	@Column(length = 100, name = "name")
	private String	name;

	@Column(length = 40, name = "password")
	private String	password;

	@Column(length = 1, name = "payment_method")
	private Byte	payment;

	@Column(length = 50, name = "handphone_number")
	private String	phone;

	@Column(length = 1, name = "role")
	private Byte	role;

	@Column(length = 1, name = "status")
	private Byte	status;

	@Column(length = 1, name = "subscribe")
	private Byte	subscribe;

	public Account() {
	}

	public Account(AccountRegisterRequest __accountRegister) {
		this.alternativeContact = __accountRegister.getAlternativeContact();
		this.avatar = "";
		this.country = __accountRegister.getCountry();
		this.created = new Date();
		this.email = __accountRegister.getEmail();
		this.name = __accountRegister.getName();
		this.password = __accountRegister.getPassword();
		this.payment = __accountRegister.getPayment();
		this.phone = __accountRegister.getPhone();
		this.role = __accountRegister.getRole();
	}

	public String getAccountInfo() {
		return this.accountInfo;
	}

	public Integer getActivationCode() {
		return this.activationCode;
	}

	public String getAlternativeContact() {
		return this.alternativeContact;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public Country getCountry() {
		return this.country;
	}

	public Date getCreated() {
		return this.created;
	}

	public String getEmail() {
		return this.email;
	}

	public Integer getId() {
		return this.id;
	}

	public Date getModified() {
		return this.modified;
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.password;
	}

	public Byte getPayment() {
		return this.payment;
	}

	public String getPhone() {
		return this.phone;
	}

	public Byte getRole() {
		return this.role;
	}

	public Byte getStatus() {
		return this.status;
	}

	public Byte getSubscribe() {
		return this.subscribe;
	}

	public void setAccountInfo(String __accountInfo) {
		this.accountInfo = __accountInfo;
	}

	public void setActivationCode(Integer __activationCode) {
		this.activationCode = __activationCode;
	}

	public void setAlternativeContact(String __alternativeContact) {
		this.alternativeContact = __alternativeContact;
	}

	public void setAvatar(String __avatar) {
		this.avatar = __avatar;
	}

	public void setCountry(Country __country) {
		this.country = __country;
	}

	public void setCreated(Date __created) {
		this.created = __created;
	}

	public void setEmail(String __email) {
		this.email = __email;
	}

	public void setId(Integer __id) {
		this.id = __id;
	}

	public void setModified(Date __modified) {
		this.modified = __modified;
	}

	public void setName(String __name) {
		this.name = __name;
	}

	public void setPassword(String __password) {
		this.password = __password;
	}

	public void setPayment(Byte __payment) {
		this.payment = __payment;
	}

	public void setPhone(String __phone) {
		this.phone = __phone;
	}

	public void setRole(Byte __role) {
		this.role = __role;
	}

	public void setStatus(Byte __status) {
		this.status = __status;
	}

	public void setSubscribe(Byte __subscribe) {
		this.subscribe = __subscribe;
	}

}
