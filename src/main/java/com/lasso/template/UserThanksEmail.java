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
 * The Class UserThanksEmail.
 *
 * @author Paul Mai
 */
public class UserThanksEmail implements EmailTemplate {

	/** The first name. */
	private String	firstName;

	/** The template. */
	private File	template;

	/**
	 * Instantiates a new user thanks email.
	 *
	 * @param __firstName the first name
	 * @throws URISyntaxException the URI syntax exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public UserThanksEmail(String __firstName) throws URISyntaxException, IOException {
		super();
		this.firstName = __firstName;
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
		String[] _searchStrings = { "${FIRST-NAME}" };
		String[] _replaceStrings = { this.firstName };

		return StringUtils.replaceEach(_content, _searchStrings, _replaceStrings);
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
		_mapTemplate.put("img_01_user_thanks.jpg",
		        new File(this.template, "img_01_user_thanks.jpg"));
		_mapTemplate.put("title_1_user_thanks.jpg",
		        new File(this.template, "title_1_user_thanks.jpg"));
		_mapTemplate.put("seeyou.jpg", new File(this.template, "seeyou.jpg"));
		_mapTemplate.put("border.jpg", new File(this.template, "border.jpg"));
		_mapTemplate.put("fb.jpg", new File(this.template, "fb.jpg"));
		_mapTemplate.put("it.jpg", new File(this.template, "it.jpg"));
		_mapTemplate.put("tw1.jpg", new File(this.template, "tw1.jpg"));
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
		        .getResource("/email-template/user-thanks-you").toURI());
	}

}
