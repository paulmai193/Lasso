/*
 * 
 */
package com.lasso.template;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * The Class UserResetPasswordEmail.
 *
 * @author Paul Mai
 */
public class UserResetPasswordEmail extends BasicEmail implements EmailTemplate {

	/** The first name. */
	private String	firstName;

	/** The activate link. */
	private String	resetLink;

	/** The template. */
	private File	template;

	public UserResetPasswordEmail() {
	}

	/**
	 * Instantiates a new user reset password email.
	 *
	 * @param __firstName
	 *        the first name
	 * @param __resetLink
	 *        the reset link
	 * @throws URISyntaxException
	 *         the URI syntax exception
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	public UserResetPasswordEmail(String __firstName, String __resetLink)
	        throws URISyntaxException, IOException {
		super();
		this.firstName = __firstName;
		this.resetLink = __resetLink;
		this.loadTemplate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.template.EmailTemplate#getContent()
	 */
	@Override
	public String getContent() throws URISyntaxException, IOException {
		// Load html template
		File _htmlTemplate = new File(this.template, "newsletter.html");
		String _content = FileUtils.readFileToString(_htmlTemplate);
		String[] _searchStrings = { "${FIRST-NAME}", "${RESET_LINK}" };
		String[] _replaceStrings = { this.firstName, this.resetLink };

		_content = StringUtils.replaceEach(_content, _searchStrings, _replaceStrings);

		return this.setSocialLink(_content);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.template.EmailTemplate#getTemplate()
	 */
	@Override
	public Map<String, File> getTemplate() {
		Map<String, File> _mapTemplate = new HashMap<>();
		_mapTemplate.put("logo_user.jpg", new File(this.template, "logo_user.jpg"));
		_mapTemplate.put("img_01_user_forgot.jpg",
		        new File(this.template, "img_01_user_forgot.jpg"));
		_mapTemplate.put("title_1_user_forgot.jpg",
		        new File(this.template, "title_1_user_forgot.jpg"));
		_mapTemplate.put("bt2.png", new File(this.template, "bt2.png"));
		_mapTemplate.put("seeyou.jpg", new File(this.template, "seeyou.jpg"));
		_mapTemplate.put("border.jpg", new File(this.template, "border.jpg"));
		_mapTemplate.put("c1.jpg", new File(this.template, "c1.jpg"));
		_mapTemplate.put("fb.jpg", new File(this.template, "fb.jpg"));
		_mapTemplate.put("it.jpg", new File(this.template, "it.jpg"));
		_mapTemplate.put("tw1.jpg", new File(this.template, "tw1.jpg"));
		_mapTemplate.put("d2.jpg", new File(this.template, "d2.jpg"));
		_mapTemplate.put("googleplay.jpg", new File(this.template, "googleplay.jpg"));
		_mapTemplate.put("appstore.jpg", new File(this.template, "appstore.jpg"));
		_mapTemplate.put("footer.jpg", new File(this.template, "footer.jpg"));
		return _mapTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.template.EmailTemplate#loadTemplate()
	 */
	@Override
	public void loadTemplate() throws URISyntaxException, IOException {
		this.template = new File(this.getClass().getClassLoader()
		        .getResource("/email-template/user-forgot-pass").toURI());
	}

}
