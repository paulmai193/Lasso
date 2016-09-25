package com.lasso.rest.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lasso.rest.dao.AccountDAO;
import com.lasso.rest.dao.BannerDAO;
import com.lasso.rest.dao.CategoryDAO;
import com.lasso.rest.dao.JobAccountDAO;
import com.lasso.rest.dao.JobDAO;
import com.lasso.rest.dao.JobStyleDAO;
import com.lasso.rest.dao.MessageDAO;
import com.lasso.rest.dao.PortfolioDAO;
import com.lasso.rest.dao.PortfolioTypeDAO;
import com.lasso.rest.dao.ProjectDAO;
import com.lasso.rest.dao.StyleDAO;
import com.lasso.rest.dao.TypeDAO;
import com.lasso.rest.dao.TypeStyleDAO;
import com.lasso.rest.model.api.response.ListProjectsResponse;
import com.lasso.rest.model.api.response.ProjectDetailResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Banner;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.PortfolioType;
import com.lasso.rest.model.datasource.Project;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;
import com.lasso.rest.model.datasource.TypesStyle;
import com.lasso.rest.service.GenericManagement;
import com.lasso.rest.service.ProjectManagement;
import com.lasso.rest.service.UploadImageManagement;

/**
 * The Class ImplProjectManagement.
 *
 * @author Paul Mai
 */
@Service
@Transactional
public class ImplProjectManagement implements ProjectManagement {

	/** The account DAO. */
	@Autowired
	protected AccountDAO			accountDAO;

	/** The banner DAO. */
	@Autowired
	protected BannerDAO				bannerDAO;

	/** The category DAO. */
	@Autowired
	protected CategoryDAO			categoryDAO;

	/** The generic management. */
	@Autowired
	protected GenericManagement		genericManagement;

	/** The job account DAO. */
	@Autowired
	protected JobAccountDAO			jobAccountDAO;

	/** The job DAO. */
	@Autowired
	protected JobDAO				jobDAO;

	/** The job storage path. */
	protected String				jobStoragePath;

	/** The job type DAO. */
	@Autowired
	protected JobStyleDAO			jobStyleDAO;

	/** The message DAO. */
	@Autowired
	protected MessageDAO			messageDAO;

	/** The portfolio DAO. */
	@Autowired
	protected PortfolioDAO			portfolioDAO;

	/** The portfolio type DAO. */
	@Autowired
	protected PortfolioTypeDAO		portfolioTypeDAO;

	/** The project DAO. */
	@Autowired
	protected ProjectDAO			projectDAO;

	/** The style DAO. */
	@Autowired
	protected StyleDAO				styleDAO;

	/** The temporary storage path. */
	protected String				temporaryStoragePath;

	/** The type DAO. */
	@Autowired
	protected TypeDAO				typeDAO;

	/** The type style DAO. */
	@Autowired
	protected TypeStyleDAO			typeStyleDAO;

	/** The upload image management. */
	@Autowired
	protected UploadImageManagement	uploadImageManagement;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getCategoriesByIndexAndKeyword(int, int,
	 * java.lang.String)
	 */
	@Override
	public List<Category> getCategoriesByIndexAndKeyword(int __index, int __size,
	        String __keyword) {
		return this.categoryDAO.getCategories(__index, __size, __keyword);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getCategoryById(int)
	 */
	@Override
	public Category getCategoryById(int __idCategory) {
		return this.categoryDAO.getCategoryById(__idCategory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getJobById(int)
	 */
	@Override
	public Job getJobById(int __idJob) {
		return this.jobDAO.getJobById(__idJob);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getListBanner()
	 */
	@Override
	public List<Banner> getListBanner() {
		return this.bannerDAO.getListBanner();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getListTypesByIdCategory(int)
	 */
	@Override
	public List<Type> getListTypesByIdCategory(int __idCategory) {
		Category _category = this.categoryDAO.getCategoryById(__idCategory);
		if (_category == null) {
			return new ArrayList<>();
		}
		List<Type> _types = this.typeDAO.getTypesByCategory(_category);
		return _types;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getTypeByIdPortfolio(int)
	 */
	@Override
	public List<Type> getListTypesByIdPortfolio(int __idPortfolio) {
		// Get list portfolio type from id portfolio
		List<PortfolioType> _portfolioTypes = this.portfolioTypeDAO
		        .getListByIdPortfolio(__idPortfolio);
		List<Integer> _listIdsType = new ArrayList<>();
		if (_portfolioTypes.isEmpty()) {
			return new ArrayList<>();
		}

		// Get list type from list ID
		for (PortfolioType _portfolioType : _portfolioTypes) {
			_listIdsType.add(_portfolioType.getTypeId());
		}

		return this.typeDAO.getListByByListIds(_listIdsType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getProjectDetailById(int, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ProjectDetailResponse getProjectDetailById(int __idProject, String __prefixPortfolioUrl,
	        String __prefixAvatarUrl) {
		try {
			Project _project = this.projectDAO.getProjectById(__idProject);
			Category _category = this.categoryDAO.getCategoryById(_project.getCategoryId());
			Portfolio _portfolio = this.portfolioDAO.getPortfolioByProject(_project);
			Account _account = this.accountDAO.getAccountById(_portfolio.getAccountId());
			return new ProjectDetailResponse(__prefixPortfolioUrl, __prefixAvatarUrl, _project,
			        _portfolio, _account, _category);
		}
		catch (NullPointerException _ex) {
			throw new NotFoundException("No detail information");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getProjectsBySubCategoryAndKeyword(int, int,
	 * int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ListProjectsResponse getProjectsBySubCategoryAndKeyword(int __idStyle, int __index,
	        int __size, String __keyword, String __prefixProjectUrl, String __prefixAvatarUrl) {
		List<Object[]> _datas = new ArrayList<>();
		List<Project> _projects = this.projectDAO.searchProjects(__idStyle, __keyword, __index,
		        __size);
		if (_projects.isEmpty() && __index == 0) {
			_projects = this.projectDAO.getRamdom(__size);
		}
		for (Project _project : _projects) {
			try {
				Object[] _data = { _project, "" };
				Account _account = this.accountDAO.getAccountById(_project.getAccountId());
				_data[1] = _account.getImage();

				_datas.add(_data);
			}
			catch (Exception _ex) {
				Logger.getLogger(this.getClass()).warn("Problem with project " + _project.getId(),
				        _ex);
			}
		}
		ListProjectsResponse _listProjectsResponse = new ListProjectsResponse(__index + __size,
		        __prefixProjectUrl, __prefixAvatarUrl, _datas);
		return _listProjectsResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getStyleById(int)
	 */
	@Override
	public Style getStyleById(int __styleId) {
		return this.styleDAO.getById(__styleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#getSubCategoriesByIndexAndKeyword(int, int,
	 * int, java.lang.String)
	 */
	@Override
	public List<Style> getSubCategoriesByIndexAndKeyword(int __idCategory, int __index, int __size,
	        String __keyword) {
		// Get Category from id
		Category _category = this.categoryDAO.getCategoryById(__idCategory);
		if (_category == null) {
			return new ArrayList<>();
		}

		// Get Type from Category
		List<Type> _types = this.typeDAO.getTypesByCategory(_category);
		if (_types.size() == 0) {
			return new ArrayList<>();
		}

		// Get TypesStyle from Types
		List<TypesStyle> _typesStyles = this.typeStyleDAO.getTypesStylesByTypes(_types);
		if (_typesStyles.size() == 0) {
			return new ArrayList<>();
		}

		// Get Style from TypesStyles
		return this.styleDAO.getStylesByTypesAndKeyword(_typesStyles, __index, __size, __keyword);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#removeOldTemporaryFiles(java.lang.String)
	 */
	@Override
	public void removeOldTemporaryFiles(String __webContextStoragePath) {
		File _tempDir = new File(__webContextStoragePath + this.temporaryStoragePath);
		if (_tempDir != null && _tempDir.isDirectory()) {
			for (File _tempFIle : _tempDir.listFiles()) {
				if (System.currentTimeMillis() - _tempFIle.lastModified() > 7200000) {
					_tempFIle.delete();
				}
			}
		}
	}

	/**
	 * @return the accountDAO
	 */
	public AccountDAO getAccountDAO() {
		return this.accountDAO;
	}

	/**
	 * @param __accountDAO the accountDAO to set
	 */
	public void setAccountDAO(AccountDAO __accountDAO) {
		this.accountDAO = __accountDAO;
	}

	/**
	 * @return the bannerDAO
	 */
	public BannerDAO getBannerDAO() {
		return this.bannerDAO;
	}

	/**
	 * @param __bannerDAO the bannerDAO to set
	 */
	public void setBannerDAO(BannerDAO __bannerDAO) {
		this.bannerDAO = __bannerDAO;
	}

	/**
	 * @return the categoryDAO
	 */
	public CategoryDAO getCategoryDAO() {
		return this.categoryDAO;
	}

	/**
	 * @param __categoryDAO the categoryDAO to set
	 */
	public void setCategoryDAO(CategoryDAO __categoryDAO) {
		this.categoryDAO = __categoryDAO;
	}

	/**
	 * @return the genericManagement
	 */
	public GenericManagement getGenericManagement() {
		return this.genericManagement;
	}

	/**
	 * @param __genericManagement the genericManagement to set
	 */
	public void setGenericManagement(GenericManagement __genericManagement) {
		this.genericManagement = __genericManagement;
	}

	/**
	 * @return the jobAccountDAO
	 */
	public JobAccountDAO getJobAccountDAO() {
		return this.jobAccountDAO;
	}

	/**
	 * @param __jobAccountDAO the jobAccountDAO to set
	 */
	public void setJobAccountDAO(JobAccountDAO __jobAccountDAO) {
		this.jobAccountDAO = __jobAccountDAO;
	}

	/**
	 * @return the jobDAO
	 */
	public JobDAO getJobDAO() {
		return this.jobDAO;
	}

	/**
	 * @param __jobDAO the jobDAO to set
	 */
	public void setJobDAO(JobDAO __jobDAO) {
		this.jobDAO = __jobDAO;
	}

	/**
	 * @return the jobStoragePath
	 */
	public String getJobStoragePath() {
		return this.jobStoragePath;
	}

	/**
	 * @param __jobStoragePath the jobStoragePath to set
	 */
	public void setJobStoragePath(String __jobStoragePath) {
		this.jobStoragePath = __jobStoragePath;
	}

	/**
	 * @return the jobStyleDAO
	 */
	public JobStyleDAO getJobStyleDAO() {
		return this.jobStyleDAO;
	}

	/**
	 * @param __jobStyleDAO the jobStyleDAO to set
	 */
	public void setJobStyleDAO(JobStyleDAO __jobStyleDAO) {
		this.jobStyleDAO = __jobStyleDAO;
	}

	/**
	 * @return the messageDAO
	 */
	public MessageDAO getMessageDAO() {
		return this.messageDAO;
	}

	/**
	 * @param __messageDAO the messageDAO to set
	 */
	public void setMessageDAO(MessageDAO __messageDAO) {
		this.messageDAO = __messageDAO;
	}

	/**
	 * @return the portfolioDAO
	 */
	public PortfolioDAO getPortfolioDAO() {
		return this.portfolioDAO;
	}

	/**
	 * @param __portfolioDAO the portfolioDAO to set
	 */
	public void setPortfolioDAO(PortfolioDAO __portfolioDAO) {
		this.portfolioDAO = __portfolioDAO;
	}

	/**
	 * @return the portfolioTypeDAO
	 */
	public PortfolioTypeDAO getPortfolioTypeDAO() {
		return this.portfolioTypeDAO;
	}

	/**
	 * @param __portfolioTypeDAO the portfolioTypeDAO to set
	 */
	public void setPortfolioTypeDAO(PortfolioTypeDAO __portfolioTypeDAO) {
		this.portfolioTypeDAO = __portfolioTypeDAO;
	}

	/**
	 * @return the projectDAO
	 */
	public ProjectDAO getProjectDAO() {
		return this.projectDAO;
	}

	/**
	 * @param __projectDAO the projectDAO to set
	 */
	public void setProjectDAO(ProjectDAO __projectDAO) {
		this.projectDAO = __projectDAO;
	}

	/**
	 * @return the styleDAO
	 */
	public StyleDAO getStyleDAO() {
		return this.styleDAO;
	}

	/**
	 * @param __styleDAO the styleDAO to set
	 */
	public void setStyleDAO(StyleDAO __styleDAO) {
		this.styleDAO = __styleDAO;
	}

	/**
	 * @return the temporaryStoragePath
	 */
	public String getTemporaryStoragePath() {
		return this.temporaryStoragePath;
	}

	/**
	 * @param __temporaryStoragePath the temporaryStoragePath to set
	 */
	public void setTemporaryStoragePath(String __temporaryStoragePath) {
		this.temporaryStoragePath = __temporaryStoragePath;
	}

	/**
	 * @return the typeDAO
	 */
	public TypeDAO getTypeDAO() {
		return this.typeDAO;
	}

	/**
	 * @param __typeDAO the typeDAO to set
	 */
	public void setTypeDAO(TypeDAO __typeDAO) {
		this.typeDAO = __typeDAO;
	}

	/**
	 * @return the typeStyleDAO
	 */
	public TypeStyleDAO getTypeStyleDAO() {
		return this.typeStyleDAO;
	}

	/**
	 * @param __typeStyleDAO the typeStyleDAO to set
	 */
	public void setTypeStyleDAO(TypeStyleDAO __typeStyleDAO) {
		this.typeStyleDAO = __typeStyleDAO;
	}

	/**
	 * @return the uploadImageManagement
	 */
	public UploadImageManagement getUploadImageManagement() {
		return this.uploadImageManagement;
	}

	/**
	 * @param __uploadImageManagement the uploadImageManagement to set
	 */
	public void setUploadImageManagement(UploadImageManagement __uploadImageManagement) {
		this.uploadImageManagement = __uploadImageManagement;
	}

}
