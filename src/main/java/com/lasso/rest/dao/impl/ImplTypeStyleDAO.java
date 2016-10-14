/*
 * 
 */
package com.lasso.rest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.TypeStyleDAO;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.model.datasource.TypesStyle;

/**
 * The Class ImplTypeStyleDAO.
 *
 * @author Paul Mai
 */
@Repository
public class ImplTypeStyleDAO implements TypeStyleDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new impl type style DAO.
	 */
	public ImplTypeStyleDAO() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.TypeStyleDAO#getTypesStylesByStyles(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TypesStyle> getTypesStylesByStyles(List<Style> __styles) {
		if (__styles.size() == 0) {
			return new ArrayList<>();
		}

		List<Integer> _pks = new ArrayList<>();
		for (Style _style : __styles) {
			_pks.add(_style.getId());
		}
		Criteria _criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(TypesStyle.class).add(Restrictions.in("styleId", _pks));
		return _criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.TypeStyleDAO#getTypesStylesByTypes(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TypesStyle> getTypesStylesByTypes(List<Type> __types) {
		if (__types.size() == 0) {
			return new ArrayList<>();
		}

		List<Integer> _pks = new ArrayList<>();
		for (Type _type : __types) {
			_pks.add(_type.getId());
		}
		Criteria _criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(TypesStyle.class).add(Restrictions.in("typeId", _pks));
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
