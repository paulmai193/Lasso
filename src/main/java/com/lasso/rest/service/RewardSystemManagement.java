package com.lasso.rest.service;

import com.lasso.rest.model.datasource.Account;

public interface RewardSystemManagement {

	void updateUserReward(Account __account);

	void updateDesignerReward(Account __account);
}
