package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(catalog = "art_design", name = "accounts")
public class Account implements Serializable {

	private static final long	serialVersionUID	= 2562795016649482666L;

	@Id
	@GeneratedValue
	@Column(length = 11, name = "id")
	private Integer				id;

	@Column(length = 100, name = "name")
	private String				name;

	@Column(length = 50, name = "email")
	private String				email;

	@Column(length = 50, name = "handphone_number")
	private String				phone;

	@Column(length = 50, name = "alternative_contact")
	private String				alternativeContact;

	@Column(length = 40, name = "password")
	private String				password;

	@Column(length = 250, name = "image")
	private String				avatar;

	@Column(name = "account_info")
	@Type(type = "text")
	private String				accountInfo;

	@Column(length = 11, name = "country_id")
	private Integer				countryId;

	@Column(length = 1, name = "subscribe")
	private Byte				subscribe;

	@Column(length = 1, name = "payment_method")
	private Byte				payment;

	@Column(length = 1, name = "role")
	private Byte				role;

	@Column(length = 50, name = "activation_code")
	private Integer				activationCode;

	@Column(length = 1, name = "status")
	private Byte				status;

	@Column(length = 19, name = "created")
	private Date				created;

	@Column(length = 19, name = "modified")
	private Date				modified;

	public Account() {
	}

	@JsonCreator
	public Account(@JsonProperty("name") String __name, @JsonProperty("email") String __email,
	        @JsonProperty("phone") String __phone,
	        @JsonProperty("alt_contact") String __alternativeContact,
	        @JsonProperty("password") String __password,
	        @JsonProperty("localtion") Integer __countryId,
	        @JsonProperty("payment") Byte __payment) {
		this.name = __name;
		this.email = __email;
		this.phone = __phone;
		this.alternativeContact = __alternativeContact;
		this.password = __password;
		this.countryId = __countryId;
		this.payment = __payment;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer __id) {
		this.id = __id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String __name) {
		this.name = __name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String __email) {
		this.email = __email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String __phone) {
		this.phone = __phone;
	}

	public String getAlternativeContact() {
		return this.alternativeContact;
	}

	public void setAlternativeContact(String __alternativeContact) {
		this.alternativeContact = __alternativeContact;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String __password) {
		this.password = __password;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String __avatar) {
		this.avatar = __avatar;
	}

	public String getAccountInfo() {
		return this.accountInfo;
	}

	public void setAccountInfo(String __accountInfo) {
		this.accountInfo = __accountInfo;
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer __countryId) {
		this.countryId = __countryId;
	}

	public Byte getSubscribe() {
		return this.subscribe;
	}

	public void setSubscribe(Byte __subscribe) {
		this.subscribe = __subscribe;
	}

	public Byte getPayment() {
		return this.payment;
	}

	public void setPayment(Byte __payment) {
		this.payment = __payment;
	}

	public Byte getRole() {
		return this.role;
	}

	public void setRole(Byte __role) {
		this.role = __role;
	}

	public Integer getActivationCode() {
		return this.activationCode;
	}

	public void setActivationCode(Integer __activationCode) {
		this.activationCode = __activationCode;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte __status) {
		this.status = __status;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date __created) {
		this.created = __created;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date __modified) {
		this.modified = __modified;
	}

}
