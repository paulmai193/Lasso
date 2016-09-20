package com.lasso.rest.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.internal.FormDataParamInjectionFeature;
import org.glassfish.jersey.media.multipart.internal.MultiPartReaderClientSide;
import org.glassfish.jersey.media.multipart.internal.MultiPartReaderServerSide;
import org.glassfish.jersey.media.multipart.internal.MultiPartWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.lasso.define.Constant;
import com.lasso.exception.ObjectParamException;
import com.lasso.rest.controller.filter.AccountAllow;
import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.response.ChangeAvatarResponse;
import com.lasso.rest.model.api.response.UploadPortfolioResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.service.AccountManagement;
import com.lasso.rest.service.GenericManagement;
import com.lasso.rest.service.UploadImageManagement;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * The Class UploadController.
 *
 * @author Paul Mai
 */
@Controller
@Singleton
@Lazy(false)
@Path("/upload")
@Produces(value = { MediaType.APPLICATION_JSON })
public class UploadController extends BaseController implements Feature {

	/** The account management. */
	@Autowired
	private AccountManagement		accountManagement;

	/** The avatar storage path. */
	private String					avatarStoragePath;

	/** The generic management. */
	@Autowired
	private GenericManagement		genericManagement;

	/** The http host. */
	private String					httpHost;

	/** The temporary storage path. */
	private String					temporaryStoragePath;

	/** The upload image management. */
	@Autowired
	private UploadImageManagement	uploadImageManagement;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.core.Feature#configure(javax.ws.rs.core.FeatureContext)
	 */
	@Override
	public boolean configure(final FeatureContext __context) {
		// Config to apply multipart feature for this controller
		final RuntimeType runtime = __context.getConfiguration().getRuntimeType();

		if (RuntimeType.SERVER.equals(runtime)) {
			__context.register(FormDataParamInjectionFeature.class);
			__context.register(MultiPartReaderServerSide.class);
		}
		else {
			__context.register(MultiPartReaderClientSide.class);
		}

		__context.register(MultiPartWriter.class);

		return true;
	}

	/**
	 * Sets the account management.
	 *
	 * @param __accountManagement the new account management
	 */
	public void setAccountManagement(AccountManagement __accountManagement) {
		this.accountManagement = __accountManagement;
	}

	/**
	 * Sets the avatar storage path.
	 *
	 * @param __avatarStoragePath the new avatar storage path
	 */
	public void setAvatarStoragePath(String __avatarStoragePath) {
		this.avatarStoragePath = __avatarStoragePath;
	}

	/**
	 * Sets the generic management.
	 *
	 * @param __genericManagement the new generic management
	 */
	public void setGenericManagement(GenericManagement __genericManagement) {
		this.genericManagement = __genericManagement;
	}

	/**
	 * Sets the http host.
	 *
	 * @param __httpHost the new http host
	 */
	public void setHttpHost(String __httpHost) {
		this.httpHost = __httpHost;
	}

	/**
	 * Sets the temporary storage path.
	 *
	 * @param __temporaryStoragePath the new temporary storage path
	 */
	public void setTemporaryStoragePath(String __temporaryStoragePath) {
		this.temporaryStoragePath = __temporaryStoragePath;
	}

	/**
	 * Sets the upload image management.
	 *
	 * @param __uploadImageManagement the new upload image management
	 */
	public void setUploadImageManagement(UploadImageManagement __uploadImageManagement) {
		this.uploadImageManagement = __uploadImageManagement;
	}

	/**
	 * Upload avatar.
	 *
	 * @param __context the context
	 * @param __request the request
	 * @param __fileStream the file stream
	 * @param __fileMetaData the file meta data
	 * @return the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws UnirestException the unirest exception
	 */
	@POST
	@Path("/avatar")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@AccountAuthenticate
	public Response uploadAvatar(@Context SecurityContext __context,
	        @Context HttpServletRequest __request, @FormDataParam("file") InputStream __fileStream,
	        @FormDataParam("file") FormDataContentDisposition __fileMetaData)
	        throws IOException, UnirestException {
		Account _account = (Account) __context.getUserPrincipal();
		String _webContextStoragePath = this.genericManagement
		        .loadWebContextStoragePath(_account.getAppSession());
		if (__fileStream == null && __fileMetaData == null) {
			throw new ObjectParamException("Invalid file upload");
		}
		String _uploadedFileName = __fileMetaData.getFileName();
		String _fileExtension = _uploadedFileName.substring(_uploadedFileName.lastIndexOf(".") + 1,
		        _uploadedFileName.length());
		File _avatar = new File(_webContextStoragePath + this.avatarStoragePath + "/Original/"
		        + this.uploadImageManagement.generateImageName(_fileExtension));
		try {
			// Save original file
			this.uploadImageManagement.saveFile(__fileStream, _avatar, _fileExtension);

			// Resize into 3 other size
			File _icon = new File(
			        _webContextStoragePath + this.avatarStoragePath + "/Icon/" + _avatar.getName());
			this.uploadImageManagement.resizeImage(_avatar, _icon, 45D, 45D);
			File _small = new File(_webContextStoragePath + this.avatarStoragePath + "/Small/"
			        + _avatar.getName());
			this.uploadImageManagement.resizeImage(_avatar, _small, 90D, 90D);
			File _retina = new File(_webContextStoragePath + this.avatarStoragePath + "/Retina/"
			        + _avatar.getName());
			this.uploadImageManagement.resizeImage(_avatar, _retina, 180D, 180D);

			// Save avatar name to account
			this.accountManagement.changeAvatar(_account, _avatar.getName());

			// Response
			String _prefixUrl = this.httpHost + this.avatarStoragePath;
			return this
			        .success(new ChangeAvatarResponse(_prefixUrl + "/Original/" + _avatar.getName(),
			                _prefixUrl + "/Small/" + _avatar.getName(),
			                _prefixUrl + "/Icon/" + _avatar.getName(),
			                _prefixUrl + "/Retina/" + _avatar.getName()));
		}
		catch (IllegalArgumentException _ex) {
			return this.fail(new ChangeAvatarResponse(true, _ex.getMessage()), Status.BAD_REQUEST);
		}
	}

	/**
	 * Upload job.
	 *
	 * @param __context the context
	 * @param __request the request
	 * @param __fileStream the file stream
	 * @param __fileMetaData the file meta data
	 * @return the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws UnirestException the unirest exception
	 */
	@POST
	@Path("/job")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@AccountAuthenticate
	@AccountAllow(roles = "" + Constant.ROLE_USER, status = "" + Constant.ACC_ACTIVATE)
	public Response uploadJob(@Context SecurityContext __context,
	        @Context HttpServletRequest __request, @FormDataParam("file") InputStream __fileStream,
	        @FormDataParam("file") FormDataContentDisposition __fileMetaData)
	        throws IOException, UnirestException {
		Account _user = (Account) __context.getUserPrincipal();
		if (__fileStream == null && __fileMetaData == null) {
			throw new ObjectParamException("Invalid file upload");
		}
		String _uploadedFileName = __fileMetaData.getFileName();
		String _fileExtension = _uploadedFileName.substring(_uploadedFileName.lastIndexOf(".") + 1,
		        _uploadedFileName.length());
		File _image = new File(
		        this.genericManagement.loadWebContextStoragePath(_user.getAppSession())
		                + this.temporaryStoragePath + "/"
		                + this.uploadImageManagement.generateImageName(_fileExtension));
		try {
			// Save original file
			this.uploadImageManagement.saveFile(__fileStream, _image, _fileExtension);

			// Response
			return this.success(new UploadPortfolioResponse(_image.getName()));
		}
		catch (IllegalArgumentException _ex) {
			return this.fail(new UploadPortfolioResponse(true, _ex.getMessage()),
			        Status.BAD_REQUEST);
		}
	}

	/**
	 * Upload portfolio.
	 *
	 * @param __context the context
	 * @param __request the request
	 * @param __fileStream the file stream
	 * @param __fileMetaData the file meta data
	 * @return the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws UnirestException the unirest exception
	 */
	@POST
	@Path("/portfolio")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@AccountAuthenticate
	@AccountAllow(roles = "" + Constant.ROLE_DESIGNER, status = "" + Constant.ACC_ACTIVATE)
	public Response uploadPortfolio(@Context SecurityContext __context,
	        @Context HttpServletRequest __request, @FormDataParam("file") InputStream __fileStream,
	        @FormDataParam("file") FormDataContentDisposition __fileMetaData)
	        throws IOException, UnirestException {
		Account _designer = (Account) __context.getUserPrincipal();
		if (__fileStream == null && __fileMetaData == null) {
			throw new ObjectParamException("Invalid file upload");
		}
		String _uploadedFileName = __fileMetaData.getFileName();
		String _fileExtension = _uploadedFileName.substring(_uploadedFileName.lastIndexOf(".") + 1,
		        _uploadedFileName.length());
		File _image = new File(
		        this.genericManagement.loadWebContextStoragePath(_designer.getAppSession())
		                + this.temporaryStoragePath + "/"
		                + this.uploadImageManagement.generateImageName(_fileExtension));
		try {
			// Save original file
			this.uploadImageManagement.saveFile(__fileStream, _image, _fileExtension);

			// Response
			return this.success(new UploadPortfolioResponse(_image.getName()));
		}
		catch (IllegalArgumentException _ex) {
			return this.fail(new UploadPortfolioResponse(true, _ex.getMessage()),
			        Status.BAD_REQUEST);
		}
	}

}
