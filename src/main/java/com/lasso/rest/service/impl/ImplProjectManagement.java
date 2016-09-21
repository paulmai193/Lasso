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
	private AccountDAO				accountDAO;

	/** The banner DAO. */
	@Autowired
	private BannerDAO				bannerDAO;

	/** The category DAO. */
	@Autowired
	private CategoryDAO				categoryDAO;

	/** The generic management. */
	@Autowired
	private GenericManagement		genericManagement;

	/** The portfolio DAO. */
	@Autowired
	private PortfolioDAO			portfolioDAO;

	/** The portfolio type DAO. */
	@Autowired
	private PortfolioTypeDAO		portfolioTypeDAO;

	/** The project DAO. */
	@Autowired
	private ProjectDAO				projectDAO;

	/** The style DAO. */
	@Autowired
	private StyleDAO				styleDAO;

	/** The temporary storage path. */
	private String					temporaryStoragePath;

	/** The type DAO. */
	@Autowired
	private TypeDAO					typeDAO;

	/** The type style DAO. */
	@Autowired
	private TypeStyleDAO			typeStyleDAO;

	/** The upload image management. */
	@Autowired
	private UploadImageManagement	uploadImageManagement;

	/**
	 * Gets the account DAO.
	 *
	 * @return the accountDAO
	 */
	public AccountDAO getAccountDAO() {
		return this.accountDAO;
	}

	/**
	 * Gets the banner DAO.
	 *
	 * @return the banner DAO
	 */
	public BannerDAO getBannerDAO() {
		return this.bannerDAO;
	}

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

	/**
	 * Gets the category DAO.
	 *
	 * @return the categoryDAO
	 */
	public CategoryDAO getCategoryDAO() {
		return this.categoryDAO;
	}

	/**
	 * Gets the generic management.
	 *
	 * @return the genericManagement
	 */
	public GenericManagement getGenericManagement() {
		return this.genericManagement;
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

	/**
	 * Gets the portfolio DAO.
	 *
	 * @return the portfolioDAO
	 */
	public PortfolioDAO getPortfolioDAO() {
		return this.portfolioDAO;
	}

	/**
	 * Gets the portfolio type DAO.
	 *
	 * @return the portfolioTypeDAO
	 */
	public PortfolioTypeDAO getPortfolioTypeDAO() {
		return this.portfolioTypeDAO;
	}

	/**
	 * Gets the project DAO.
	 *
	 * @return the projectDAO
	 */
	public ProjectDAO getProjectDAO() {
		return this.projectDAO;
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

	/**
	 * Gets the style DAO.
	 *
	 * @return the styleDAO
	 */
	public StyleDAO getStyleDAO() {
		return this.styleDAO;
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

	/**
	 * Gets the temporary storage path.
	 *
	 * @return the temporaryStoragePath
	 */
	public String getTemporaryStoragePath() {
		return this.temporaryStoragePath;
	}

	/**
	 * Gets the type DAO.
	 *
	 * @return the typeDAO
	 */
	public TypeDAO getTypeDAO() {
		return this.typeDAO;
	}

	/**
	 * Gets the type style DAO.
	 *
	 * @return the typeStyleDAO
	 */
	public TypeStyleDAO getTypeStyleDAO() {
		return this.typeStyleDAO;
	}

	/**
	 * Gets the upload image management.
	 *
	 * @return the uploadImageManagement
	 */
	public UploadImageManagement getUploadImageManagement() {
		return this.uploadImageManagement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.ProjectManagement#removeOldTemporaryFiles(java.lang.String)
	 */
	@Override
	public void removeOldTemporaryFiles(String __webContextStoragePath) {
		File _tempDir = new File(__webContextStoragePath + this.getTemporaryStoragePath());
		if (_tempDir != null && _tempDir.isDirectory()) {
			for (File _tempFIle : _tempDir.listFiles()) {
				if (System.currentTimeMillis() - _tempFIle.lastModified() > 7200000) {
					_tempFIle.delete();
				}
			}
		}
	}

	/**
	 * Sets the account DAO.
	 *
	 * @param __accountDAO the new account DAO
	 */
	public void setAccountDAO(AccountDAO __accountDAO) {
		this.accountDAO = __accountDAO;
	}

	/**
	 * Sets the banner DAO.
	 *
	 * @param __bannerDAO the new banner DAO
	 */
	public void setBannerDAO(BannerDAO __bannerDAO) {
		this.bannerDAO = __bannerDAO;
	}

	/**
	 * Sets the category DAO.
	 *
	 * @param __categoryDAO the new category DAO
	 */
	public void setCategoryDAO(CategoryDAO __categoryDAO) {
		this.categoryDAO = __categoryDAO;
	}

	/**
	 * Sets the generic management.
	 *
	 * @param __genericManagement the genericManagement to set
	 */
	public void setGenericManagement(GenericManagement __genericManagement) {
		this.genericManagement = __genericManagement;
	}

	/**
	 * Sets the portfolio DAO.
	 *
	 * @param __portfolioDAO the new portfolio DAO
	 */
	public void setPortfolioDAO(PortfolioDAO __portfolioDAO) {
		this.portfolioDAO = __portfolioDAO;
	}

	/**
	 * Sets the portfolio type DAO.
	 *
	 * @param __portfolioTypeDAO the new portfolio type DAO
	 */
	public void setPortfolioTypeDAO(PortfolioTypeDAO __portfolioTypeDAO) {
		this.portfolioTypeDAO = __portfolioTypeDAO;
	}

	/**
	 * Sets the project DAO.
	 *
	 * @param __projectDAO the new project DAO
	 */
	public void setProjectDAO(ProjectDAO __projectDAO) {
		this.projectDAO = __projectDAO;
	}

	/**
	 * Sets the style DAO.
	 *
	 * @param __styleDAO the new style DAO
	 */
	public void setStyleDAO(StyleDAO __styleDAO) {
		this.styleDAO = __styleDAO;
	}

	/**
	 * Sets the temporary storage path.
	 *
	 * @param __temporaryStoragePath the temporaryStoragePath to set
	 */
	public void setTemporaryStoragePath(String __temporaryStoragePath) {
		this.temporaryStoragePath = __temporaryStoragePath;
	}

	/**
	 * Sets the type DAO.
	 *
	 * @param __typeDAO the new type DAO
	 */
	public void setTypeDAO(TypeDAO __typeDAO) {
		this.typeDAO = __typeDAO;
	}

	/**
	 * Sets the type style DAO.
	 *
	 * @param __typeStyleDAO the new type style DAO
	 */
	public void setTypeStyleDAO(TypeStyleDAO __typeStyleDAO) {
		this.typeStyleDAO = __typeStyleDAO;
	}

	/**
	 * Sets the upload image management.
	 *
	 * @param __uploadImageManagement the uploadImageManagement to set
	 */
	public void setUploadImageManagement(UploadImageManagement __uploadImageManagement) {
		this.uploadImageManagement = __uploadImageManagement;
	}

}
