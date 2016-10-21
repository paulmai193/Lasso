package com.lasso.rest.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.define.Constant;
import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.dao.CategoryDAO;
import com.lasso.rest.dao.JobAccountDAO;
import com.lasso.rest.dao.JobDAO;
import com.lasso.rest.dao.PortfolioDAO;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.service.RewardSystemManagement;

import jersey.repackaged.com.google.common.base.Preconditions;

/**
 * The Class ImplRewardSystemManagement.
 *
 * @author Paul Mai
 */
@Service
@Transactional
public class ImplRewardSystemManagement implements RewardSystemManagement {

	/** The account DAO. */
	@Autowired
	private AccountDAO		accountDAO;

	/** The category DAO. */
	@Autowired
	private CategoryDAO		categoryDAO;

	/** The job account DAO. */
	@Autowired
	private JobAccountDAO	jobAccountDAO;

	/** The job DAO. */
	@Autowired
	private JobDAO			jobDAO;

	/** The portfolio DAO. */
	@Autowired
	private PortfolioDAO	portfolioDAO;

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

	/**
	 * Sets the job account DAO.
	 *
	 * @param __jobAccountDAO the new job account DAO
	 */
	public void setJobAccountDAO(JobAccountDAO __jobAccountDAO) {
		this.jobAccountDAO = __jobAccountDAO;
	}

	/**
	 * Sets the job DAO.
	 *
	 * @param __jobDAO the new job DAO
	 */
	public void setJobDAO(JobDAO __jobDAO) {
		this.jobDAO = __jobDAO;
	}

	/**
	 * Sets the portfolio DAO.
	 *
	 * @param __portfolioDAO the new portfolio DAO
	 */
	public void setPortfolioDAO(PortfolioDAO __portfolioDAO) {
		this.portfolioDAO = __portfolioDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.RewardSystemManagement#updateDesignerReward(com.lasso.rest.model.
	 * datasource.Account)
	 */
	@Override
	public void updateDesignerReward(Account __designer) {
		synchronized (__designer) {
			int _reward = 1;

			try {
				// Have avatar
				_reward += this.updateAvatarReward(__designer);

				// Complete profile
				_reward += this.updateProfileReward(__designer);

				// Upload portfolio
				_reward += this.updatePortfoliosReward(__designer);

				// Browse all categories
				_reward += this.updateBrowseCategory(__designer);

				// Completed projects
				_reward += this.updateCompetedProjectsReward(__designer);
			}
			catch (Exception _ex) {
				// Mean user not match some conditions of new reward. Break and update current
			}

			__designer.setRewards(_reward);
			this.accountDAO.updateAccount(__designer);
		}
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
				Logger.getLogger(this.getClass()).warn(_ex.getMessage(), _ex);
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
		try {
			Preconditions
			.checkArgument(__account.getImage() != null && !__account.getImage().isEmpty());
			return 1;
		}
		catch (IllegalArgumentException _ex) {
			throw new IllegalArgumentException("Not match condition of upload avatar to get reward",
					_ex);
		}
	}

	/**
	 * Update brief job reward.
	 *
	 * @param __user the user
	 * @return the int
	 */
	private int updateBriefJobReward(Account __user) {
		int _numberJob = this.jobDAO.getListJobsOfUser(__user.getId()).size();
		if (_numberJob == 0) {
			throw new IllegalArgumentException("Not match condition of brief job to get reward");
		}
		else if (_numberJob <= 15) {
			return _numberJob;
		}
		else if (_numberJob <= 80) {
			int _n = 20, _m = 15;
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

	/**
	 * Update browse category.
	 *
	 * @param __account the account
	 * @return the int
	 */
	private int updateBrowseCategory(Account __account) {
		List<Category> _categories = this.categoryDAO.getCategories(-1, 0, "");
		int _numberCategoryBrowsed;
		Set<Integer> _browsedCateogries = Constant.BROWSE_CATEGORY_STATISTIC.get(__account.getId());
		if (_browsedCateogries == null) {
			_numberCategoryBrowsed = 0;
		}
		else {
			_numberCategoryBrowsed = _browsedCateogries.size();
		}
		if (__account.getRole().byteValue() == Constant.ROLE_DESIGNER
				&& _numberCategoryBrowsed == _categories.size()) {
			// Designer must browse all categories to get 1 more point
			if (__account.getRewards().intValue() == 4) {
				return 1;
			}
			else {
				return 0;
			}
		}
		else if (_numberCategoryBrowsed == _categories.size()) {
			// User must browse all categories to get 2 more point
			return 2;
		}
		else if (_numberCategoryBrowsed >= 4) {
			// User must 4 categories to get 1 more point
			return 1;
		}
		else {
			throw new IllegalArgumentException(
					"Not match condition of browsed categories (current is "
							+ _numberCategoryBrowsed + ") to get reward");
		}
	}

	/**
	 * Update competed projects reward.
	 *
	 * @param __account the account
	 * @return the int
	 */
	private int updateCompetedProjectsReward(Account __account) {
		int _numberCompletedProjects = this.jobAccountDAO
				.getListCompletedJobsAccountOfDesigner(__account.getId()).size();
		if (_numberCompletedProjects == 0) {
			throw new IllegalArgumentException(
					"Not match condition of completed projects to get reward");
		}
		else if (_numberCompletedProjects <= 10) {
			return _numberCompletedProjects;
		}
		else if (_numberCompletedProjects <= 100) {
			int _n = 15, _m = 10;
			float _f;
			do {
				_f = (_numberCompletedProjects / _n);
				_n += 5;
				_m++;
			}
			while (_f > 1);
			return _m;
		}
		else if (_numberCompletedProjects <= 149) {
			int _n = 110, _m = 25;
			float _f;
			do {
				_f = (_numberCompletedProjects / _n);
				_n += 10;
				_m++;
			}
			while (_f > 1);
			return _m;
		}
		else {
			return 31;
		}
	}

	/**
	 * Update portfolios reward.
	 *
	 * @param __account the account
	 * @return the int
	 */
	private int updatePortfoliosReward(Account __account) {
		int _numberPortfolios = this.portfolioDAO.getAllPortfoliosOfAccount(__account).size();
		if (__account.getRewards().intValue() == 3 && _numberPortfolios == 4) {
			return 1;
		}
		else if (__account.getRewards().intValue() > 3 && _numberPortfolios > 4) {
			if (_numberPortfolios <= 9) {
				return _numberPortfolios - 4;
			}
			else {
				return 5;
			}
		}
		else {
			throw new IllegalArgumentException(
					"Not match condition of uploaded portfolios to get reward");
		}
	}

	/**
	 * Update profile reward.
	 *
	 * @param __account the account
	 * @return the int
	 */
	private int updateProfileReward(Account __account) {
		try {
			Preconditions
			.checkArgument(__account.getEmail() != null && !__account.getEmail().isEmpty());
			Preconditions.checkArgument(__account.getHandphoneNumber() != null
					&& !__account.getHandphoneNumber().isEmpty());
			Preconditions
			.checkArgument(__account.getName() != null && !__account.getName().isEmpty());
			if (__account.getRole().byteValue() == Constant.ROLE_DESIGNER) {
				Preconditions.checkArgument(__account.getAccountInfo() != null
						&& !__account.getAccountInfo().isEmpty());
				Preconditions.checkArgument(__account.getAlternativeContact() != null
						&& !__account.getAlternativeContact().isEmpty());
			}
			else {
				Preconditions.checkArgument(__account.getCompanyAddress() != null
						&& !__account.getCompanyAddress().isEmpty());
				Preconditions.checkArgument(__account.getCompanyName() != null
						&& !__account.getCompanyName().isEmpty());
				Preconditions.checkArgument(__account.getCompanyTelephone() != null
						&& !__account.getCompanyTelephone().isEmpty());
			}
			return 1;
		}
		catch (IllegalArgumentException _ex) {
			throw new IllegalArgumentException(
					"Not match condition of completed profile to get reward");
		}

	}

}
