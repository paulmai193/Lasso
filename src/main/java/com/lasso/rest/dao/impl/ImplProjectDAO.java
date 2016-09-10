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

import com.lasso.rest.dao.ProjectDAO;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.Project;
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
	 * @see com.lasso.rest.dao.ProjectDAO#getCategories(int, int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories(int __offset, int __limit, String __keyword) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Category.class);
		if (__keyword != null && !__keyword.isEmpty()) {
			_criteria.add(Restrictions.like("title", __keyword, MatchMode.ANYWHERE));
		}
		_criteria.addOrder(Order.asc("title")).setFirstResult(__offset).setMaxResults(__limit);
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
	 * @see com.lasso.rest.dao.ProjectDAO#getProjectById(int)
	 */
	@Override
	public Project getProjectById(int __idProject) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Project.class)
		        .add(Restrictions.eq("id.id", __idProject));
		return (Project) _criteria.uniqueResult();
	}

	@Override
	public Portfolio getPortfolioByProject(Project __project) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.ProjectDAO#getStylesByTypesAndKeyword(java.util.List, int, int,
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lasso.rest.dao.ProjectDAO#getTypesByCategory(com.lasso.rest.model.datasource.Category)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getTypesByCategory(Category __category) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Type.class)
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

			_pks.add(_type.getId().getId());
		}
		Criteria _criteria = this.sessionFactory.getCurrentSession()
		        .createCriteria(TypesStyle.class).add(Restrictions.in("id.typeId", _pks));
		return _criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.ProjectDAO#searchProjects(java.lang.Integer, java.lang.Integer,
	 * java.lang.String, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> searchProjects(Integer __idCategory, Integer __idStyle, String __keyword,
	        int __offset, int __limit) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Project.class);
		if (__idCategory != null) {
			_criteria.add(Restrictions.eq("categoryId", __idCategory));
		}
		if (__idStyle != null) {
			_criteria.add(Restrictions.eq("id.styleId", __idStyle));
		}
		if (__keyword != null && !__keyword.isEmpty()) {
			_criteria.add(Restrictions.like("title", __keyword, MatchMode.ANYWHERE));
		}
		_criteria.addOrder(Order.asc("title")).setFirstResult(__offset).setMaxResults(__limit);
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
