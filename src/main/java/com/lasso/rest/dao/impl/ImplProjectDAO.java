package com.lasso.rest.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lasso.rest.dao.ProjectDAO;
import com.lasso.rest.model.datasource.Project;

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
	 * @see com.lasso.rest.dao.ProjectDAO#getProjectById(int)
	 */
	@Override
	public Project getProjectById(int __idProject) {
		return (Project) this.sessionFactory.getCurrentSession().createCriteria(Project.class)
				.add(Restrictions.idEq(__idProject)).add(Restrictions.eq("deleted", (byte) 0))
				.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.ProjectDAO#getRamdom(java.lang.Integer, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getRamdom(Integer __idStyle, int __limit) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Project.class);
		if (__idStyle != null) {
			_criteria.add(Restrictions.eq("id.styleId", __idStyle));
		}
		_criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"))
		.add(Restrictions.eq("status", (byte) 1)).add(Restrictions.eq("deleted", (byte) 0))
		.addOrder(Order.asc("title")).setMaxResults(__limit);
		return _criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.dao.ProjectDAO#searchProjects(java.lang.Integer, java.lang.String, int,
	 * int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> searchProjects(Integer __idStyle, String __keyword, int __offset,
			int __limit) {
		Criteria _criteria = this.sessionFactory.getCurrentSession().createCriteria(Project.class);
		if (__idStyle != null) {
			_criteria.add(Restrictions.eq("id.styleId", __idStyle));
		}
		if (__keyword != null && !__keyword.isEmpty()) {
			_criteria.add(Restrictions.like("title", __keyword, MatchMode.ANYWHERE));
		}
		_criteria.add(Restrictions.eq("status", (byte) 1)).add(Restrictions.eq("deleted", (byte) 0))
		.addOrder(Order.asc("title")).setFirstResult(__offset).setMaxResults(__limit);
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
