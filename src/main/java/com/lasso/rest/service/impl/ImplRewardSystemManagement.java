package com.lasso.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lasso.define.Constant;
import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.service.RewardSystemManagement;

import jersey.repackaged.com.google.common.base.Preconditions;

public class ImplRewardSystemManagement implements RewardSystemManagement {

	@Autowired
	private AccountDAO accountDAO;

	public void setAccountDAO(AccountDAO __accountDAO) {
		this.accountDAO = __accountDAO;
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

	// private int updateBrowseCategory(Account __account, boolean __mustAll) {
	// if (__mustAll) {
	//
	// }
	// else {
	//
	// }
	// }

}
