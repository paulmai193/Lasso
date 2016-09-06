/*
 * 
 */
package com.lasso.rest.model.datasource;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Country.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "countries")
public final class Country implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** The accounts. */
	private Set<Account>		accounts;

	/** The code. */
	private String				code;

	/** The created. */
	private Timestamp			created;

	/** The id. */
	private int					id;

	/** The modified. */
	private Date				modified;

	/** The name. */
	private String				name;

	/** The sort. */
	private int					sort;

	/** The status. */
	private byte				status;

	/**
	 * Instantiates a new country.
	 */
	public Country() {
	}

	/**
	 * Adds the account.
	 *
	 * @param account the account
	 * @return the account
	 */
	public Account addAccount(Account account) {
		this.getAccounts().add(account);
		account.setCountry(this);

		return account;
	}

	/**
	 * Gets the accounts.
	 *
	 * @return the accounts
	 */
	// bi-directional many-to-one association to Account
	@OneToMany(mappedBy = "country")
	public Set<Account> getAccounts() {
		return this.accounts;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	@Column(nullable = false, length = 2)
	public String getCode() {
		return this.code;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public Timestamp getCreated() {
		return this.created;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	public int getId() {
		return this.id;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Column(nullable = false, length = 255)
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the sort.
	 *
	 * @return the sort
	 */
	@Column(nullable = false)
	public int getSort() {
		return this.sort;
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
	 * Removes the account.
	 *
	 * @param account the account
	 * @return the account
	 */
	public Account removeAccount(Account account) {
		this.getAccounts().remove(account);
		account.setCountry(null);

		return account;
	}

	/**
	 * Sets the accounts.
	 *
	 * @param accounts the new accounts
	 */
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Sets the created.
	 *
	 * @param created the new created
	 */
	public void setCreated(Timestamp created) {
		this.created = created;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
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
	 * Sets the sort.
	 *
	 * @param sort the new sort
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(byte status) {
		this.status = status;
	}

}
