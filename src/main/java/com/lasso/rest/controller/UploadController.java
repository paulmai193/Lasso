package com.lasso.rest.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.inject.Singleton;
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

import com.lasso.exception.ObjectParamException;
import com.lasso.rest.controller.filter.AccountAuthenticate;
import com.lasso.rest.model.api.response.ChangeAvatarResponse;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.service.AccountManagement;

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
	private AccountManagement accountManagement;

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
	 * Upload avatar.
	 *
	 * @param __context the context
	 * @param __fileStream the file stream
	 * @param __fileMetaData the file meta data
	 * @return the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@POST
	@Path("/avatar")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@AccountAuthenticate
	public Response uploadAvatar(@Context SecurityContext __context,
			@FormDataParam("file") InputStream __fileStream,
			@FormDataParam("file") FormDataContentDisposition __fileMetaData) throws IOException {
		if (__fileStream == null) {
			throw new ObjectParamException("File not found");
		}
		Account _account = (Account) __context.getUserPrincipal();
		File _avatar = new File("" + _account.getId() + "-" + UUID.randomUUID() + ".jpg");
		try {
			this.accountManagement.changeAvatar(_account, __fileStream, _avatar);
			return this.success(new ChangeAvatarResponse(_avatar.getName()));
		}
		catch (IllegalArgumentException _ex) {
			return this.fail(new ChangeAvatarResponse(true, _ex.getMessage()), Status.BAD_REQUEST);
		}
	}

}
