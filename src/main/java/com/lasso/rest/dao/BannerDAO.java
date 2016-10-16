/*
 * 
 */
package com.lasso.rest.dao;

import java.util.List;

import com.lasso.rest.model.datasource.Banner;

/**
 * The Interface BannerDAO.
 *
 * @author Paul Mai
 */
public interface BannerDAO extends HibernateSession {

	/**
	 * Gets the list banner.
	 *
	 * @param __type
	 *        the type
	 * @return the list banner
	 */
	List<Banner> getListBanner(short __type);

}
