package com.lasso.rest.dao;

import com.lasso.rest.model.datasource.PromoCode;
import com.lasso.rest.model.datasource.PromoHistory;

public interface PromoDAO extends HibernateSession {

	PromoHistory getPromoHistroyByJobId(int __idJob);

	PromoCode getPromoCodeById(int __promoCodeId);

}
