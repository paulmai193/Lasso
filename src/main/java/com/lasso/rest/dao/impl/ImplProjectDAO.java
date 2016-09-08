package com.lasso.rest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.ProjectDAO;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.model.datasource.TypesStyle;

/**
 * The Class ImplProjectDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplProjectDAO implements ProjectDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.ProjectDAO#getCategories(int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories(int __offset, int __limit) {
		Session _session = this.sessionFactory.getCurrentSession();
		Criteria _criteria = _session.createCriteria(Category.class).addOrder(Order.asc("title"))
		        .setFirstResult(__offset).setMaxResults(__limit);
		return _criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.ProjectDAO#getCategoryById(int)
	 */
	@Override
	public Category getCategoryById(int __idCategory) {
		return this.sessionFactory.getCurrentSession().get(Category.class, __idCategory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.ProjectDAO#getStylesByTypes(java.util.List, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Style> getStylesByTypes(List<TypesStyle> __typesStyles, int __offset, int __limit) {
		if (__typesStyles.size() == 0) {
			return new ArrayList<>();
		}

		List<Integer> _ids = new ArrayList<>();
		for (TypesStyle _typesStyle : __typesStyles) {
			_ids.add(_typesStyle.getId().getStyleId());
		}
		Session _session = this.sessionFactory.getCurrentSession();
		Criteria _criteria = _session.createCriteria(Style.class).add(Restrictions.in("id", _ids));
		return _criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.dao.ProjectDAO#getTypesByCategory(com.lasso.rest.model.datasource.Category)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getTypesByCategory(Category __category) {
		Session _session = this.sessionFactory.getCurrentSession();
		Criteria _criteria = _session.createCriteria(Type.class)
		        .add(Restrictions.eq("category", __category));
		return _criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.ProjectDAO#getTypesStylesByTypes(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TypesStyle> getTypesStylesByTypes(List<Type> __types) {
		if (__types.size() == 0) {
			return new ArrayList<>();
		}

		List<Integer> _pks = new ArrayList<>();
		for (Type _type : __types) {
			// TypesStylePK _pk = new TypesStylePK();
			// _pk.setTypeId(_type.getId().getId());

			_pks.add(_type.getId().getId());
		}
		Session _session = this.sessionFactory.getCurrentSession();
		Criteria _criteria = _session.createCriteria(TypesStyle.class)
		        .add(Restrictions.in("id.typeId", _pks));
		// String _queryString = "from TypesStyle where id.typeId in (:pks)";
		// Query _query = _session.createQuery(_queryString).setParameterList("pks", _pks);
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
