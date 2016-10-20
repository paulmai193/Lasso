package com.lasso.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lasso.define.Constant;
import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.dao.CategoryDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.service.RewardSystemManagement;

import jersey.repackaged.com.google.common.base.Preconditions;

public class ImplRewardSystemManagement implements RewardSystemManagement {

	@Autowired
	private AccountDAO	accountDAO;

	@Autowired
	private CategoryDAO	categoryDAO;

	public void setAccountDAO(AccountDAO __accountDAO) {
		this.accountDAO = __accountDAO;
	}

	public void setCategoryDAO(CategoryDAO __categoryDAO) {
		this.categoryDAO = __categoryDAO;
	}

	@Override
	public void updateUserReward(Account __user) {
		synchronized (__user) {
			int _reward = 1;

			try {
				// Have avatar
				_reward += updateAvatarReward(__user);

				// Complete profile
				_reward += updateProfileReward(__user);

				// Browse category
				_reward += updateBrowseCategory(__user);
			}
			catch (Exception _ex) {
				// Mean user not match some conditions of new reward. Break and update current
			}

			__user.setRewards(_reward);
			this.accountDAO.updateAccount(__user);
		}
	}

	@Override
	public void updateDesignerReward(Account __account) {
		// TODO Auto-generated method stub

	}

	private int updateAvatarReward(Account __account) {
		Preconditions
		        .checkArgument(__account.getImage() != null && !__account.getImage().isEmpty());
		return 1;
	}

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

}
