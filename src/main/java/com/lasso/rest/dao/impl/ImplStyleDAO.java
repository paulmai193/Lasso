package com.lasso.rest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.StyleDAO;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.TypesStyle;

/**
 * The Class ImplStyleDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplStyleDAO implements StyleDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new impl style DAO.
	 */
	public ImplStyleDAO() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.StyleDAO#getById(int)
	 */
	@Override
	public Style getById(int __styleId) {
		return (Style) this.sessionFactory.getCurrentSession().createCriteria(Style.class)
		        .add(Restrictions.idEq(__styleId)).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.StyleDAO#getStylesByTypesAndKeyword(java.util.List, int, int,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Style> getStylesByTypesAndKeyword(List<TypesStyle> __typesStyles, int __offset,
	        int __limit, String __keyword) {
		if (__typesStyles.size() == 0) {
			return new ArrayList<>();
		}
		List<Integer> _ids = new ArrayList<>();
		for (TypesStyle _typesStyle : __typesStyles) {
			_ids.add(_typesStyle.getStyleId());
		}
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Style.class)
		        .add(Restrictions.in("id", _ids));
		if (__keyword != null && !__keyword.isEmpty()) {
			_criteria.add(Restrictions.like("title", __keyword, MatchMode.ANYWHERE));
		}
		_criteria.add(Restrictions.eq("status", (byte) 1)).add(Restrictions.eq("deleted", (byte) 0))
		        .addOrder(Order.asc("sort"));
		if (__offset > -1) {
			_criteria.setFirstResult(__offset).setMaxResults(__limit);
		}
		return _criteria.list();
	}

	/**
	 * Sets the session factory.
	 *
	 * @param __sessionFactory the new session factory
	 */
	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

}
