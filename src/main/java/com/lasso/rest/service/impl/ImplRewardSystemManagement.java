package com.lasso.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lasso.define.Constant;
import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.dao.CategoryDAO;
import com.lasso.rest.dao.JobDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.service.RewardSystemManagement;

import jersey.repackaged.com.google.common.base.Preconditions;

/**
 * The Class ImplRewardSystemManagement.
 *
 * @author Paul Mai
 */
public class ImplRewardSystemManagement implements RewardSystemManagement {

	/** The account DAO. */
	@Autowired
	private AccountDAO	accountDAO;

	/** The category DAO. */
	@Autowired
	private CategoryDAO	categoryDAO;

	@Autowired
	private JobDAO		jobDAO;

	/**
	 * Sets the account DAO.
	 *
	 * @param __accountDAO the new account DAO
	 */
	public void setAccountDAO(AccountDAO __accountDAO) {
		this.accountDAO = __accountDAO;
	}

	/**
	 * Sets the category DAO.
	 *
	 * @param __categoryDAO the new category DAO
	 */
	public void setCategoryDAO(CategoryDAO __categoryDAO) {
		this.categoryDAO = __categoryDAO;
	}

	public void setJobDAO(JobDAO __jobDAO) {
		this.jobDAO = __jobDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.RewardSystemManagement#updateDesignerReward(com.lasso.rest.model.
	 * datasource.Account)
	 */
	@Override
	public void updateDesignerReward(Account __account) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.RewardSystemManagement#updateUserReward(com.lasso.rest.model.
	 * datasource.Account)
	 */
	@Override
	public void updateUserReward(Account __user) {
		synchronized (__user) {
			int _reward = 1;

			try {
				// Have avatar
				_reward += this.updateAvatarReward(__user);

				// Complete profile
				_reward += this.updateProfileReward(__user);

				// Browse category
				_reward += this.updateBrowseCategory(__user);

				// Breaf jobs
				_reward += this.updateBriefJobReward(__user);
			}
			catch (Exception _ex) {
				// Mean user not match some conditions of new reward. Break and update current
			}

			__user.setRewards(_reward);
			this.accountDAO.updateAccount(__user);
		}
	}

	/**
	 * Update avatar reward.
	 *
	 * @param __account the account
	 * @return the int
	 */
	private int updateAvatarReward(Account __account) {
		Preconditions
		        .checkArgument(__account.getImage() != null && !__account.getImage().isEmpty());
		return 1;
	}

	/**
	 * Update browse category.
	 *
	 * @param __account the account
	 * @return the int
	 */
	private int updateBrowseCategory(Account __account) {
		List<Category> _categories = this.categoryDAO.getCategories(-1, 0, "");
		int _numberCategoryBrowsed = Constant.BROWSE_CATEGORY_STATISTIC.get(__account.getId());
		if (__account.getRole().byteValue() == Constant.ROLE_DESIGNER
		        && _numberCategoryBrowsed == _categories.size()) {
			// Designer must browse all categories to get 1 more point
			return 1;
		}
		else if (_numberCategoryBrowsed == _categories.size()) {
			// User must browse all categories to get 2 more point
			return 2;
		}
		else if (_numberCategoryBrowsed == 4) {
			// Designer must 4 categories to get 1 more point
			return 1;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Update profile reward.
	 *
	 * @param __account the account
	 * @return the int
	 */
	private int updateProfileReward(Account __account) {
		Preconditions
		        .checkArgument(__account.getEmail() != null && !__account.getEmail().isEmpty());
		Preconditions.checkArgument(__account.getHandphoneNumber() != null
		        && !__account.getHandphoneNumber().isEmpty());
		Preconditions.checkArgument(__account.getName() != null && !__account.getName().isEmpty());
		if (__account.getRole().byteValue() == Constant.ROLE_DESIGNER) {
			Preconditions.checkArgument(
			        __account.getAccountInfo() != null && !__account.getAccountInfo().isEmpty());
			Preconditions.checkArgument(__account.getAlternativeContact() != null
			        && !__account.getAlternativeContact().isEmpty());
		}
		else {
			Preconditions.checkArgument(__account.getCompanyAddress() != null
			        && !__account.getCompanyAddress().isEmpty());
			Preconditions.checkArgument(
			        __account.getCompanyName() != null && !__account.getCompanyName().isEmpty());
			Preconditions.checkArgument(__account.getCompanyTelephone() != null
			        && !__account.getCompanyTelephone().isEmpty());
		}
		return 1;
	}

	private int updateBriefJobReward(Account __user) {
		int _numberJob = this.jobDAO.getListJobsOfUser(__user.getId()).size();
		if (_numberJob == 0) {
			throw new IllegalArgumentException();
		}
		else if (_numberJob <= 15) {
			return _numberJob;
		}
		else if (_numberJob <= 80) {
			int _n = 20, _m = 16;
			float _f;
			do {
				_f = (_numberJob / _n);
				_n += 5;
				_m++;
			}
			while (_f > 1);
			return _m;
		}
		else if (_numberJob <= 149) {
			int _n = 90, _m = 28;
			float _f;
			do {
				_f = (_numberJob / _n);
				_n += 10;
				_m++;
			}
			while (_f > 1);
			return _m;
		}
		else {
			return 36;
		}
	}

}
