package com.lasso.rest.service;

import com.lasso.rest.model.datasource.Account;

/**
 * The Interface RewardSystemManagement.
 *
 * @author Paul Mai
 */
public interface RewardSystemManagement {

	/**
	 * Update designer reward.
	 *
	 * @param __designer the designer
	 */
	void updateDesignerReward(Account __designer);

	/**
	 * Update user reward.
	 *
	 * @param __account the account
	 */
	void updateUserReward(Account __account);
}
