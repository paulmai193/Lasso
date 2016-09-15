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

@Repository
public class ImplStyleDAO implements StyleDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory __sessionFactory) {
		this.sessionFactory = __sessionFactory;
	}

	public ImplStyleDAO() {
	}

	@Override
	public Style getStyleById(int __styleId) {
		return this.sessionFactory.getCurrentSession().get(Style.class, __styleId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Style> getStylesByTypesAndKeyword(List<TypesStyle> __typesStyles, int __offset,
	        int __limit, String __keyword) {
		if (__typesStyles.size() == 0) {
			return new ArrayList<>();
		}

		List<Integer> _ids = new ArrayList<>();
		for (TypesStyle _typesStyle : __typesStyles) {
			_ids.add(_typesStyle.getId().getStyleId());
		}
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Style.class)
		        .add(Restrictions.in("id", _ids));
		if (__keyword != null && !__keyword.isEmpty()) {
			_criteria.add(Restrictions.like("title", __keyword, MatchMode.ANYWHERE));
		}
		_criteria.addOrder(Order.asc("title")).setFirstResult(__offset).setMaxResults(__limit);
		return _criteria.list();
	}

}
