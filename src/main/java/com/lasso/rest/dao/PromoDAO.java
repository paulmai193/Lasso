/*
 * 
 */
package com.lasso.rest.dao;

import com.lasso.rest.model.datasource.PromoCode;
import com.lasso.rest.model.datasource.PromoHistory;

// TODO: Auto-generated Javadoc
/**
 * The Interface PromoDAO.
 *
 * @author Paul Mai
 */
public interface PromoDAO extends HibernateSession {

	/**
	 * Gets the promo code by code.
	 *
	 * @param __promoCode
	 *        the promo code
	 * @return the promo code by code
	 */
	PromoCode getPromoCodeByCode(String __promoCode);

	/**
	 * Gets the promo code by id.
	 *
	 * @param __promoCodeId
	 *        the promo code id
	 * @return the promo code by id
	 */
	PromoCode getPromoCodeById(int __promoCodeId);

	/**
	 * Gets the promo histroy by job id.
	 *
	 * @param __idJob
	 *        the id job
	 * @return the promo histroy by job id
	 */
	PromoHistory getPromoHistroyByJobId(int __idJob);

	/**
	 * Save promo history.
	 *
	 * @param __promoHistory
	 *        the promo history
	 */
	void savePromoHistory(PromoHistory __promoHistory);

}
