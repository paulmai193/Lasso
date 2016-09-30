package com.lasso.template;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class DesignerActivateEmail implements EmailTemplate {

	private String	firstName;
	private String	activateLink;
	private File	template;

	public DesignerActivateEmail(String __firstName, String __activateLink)
	        throws URISyntaxException, IOException {
		super();
		this.firstName = __firstName;
		this.activateLink = __activateLink;
		this.loadTemplate();
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @return the activateLink
	 */
	public String getActivateLink() {
		return this.activateLink;
	}

	@Override
	public String getContent() throws URISyntaxException, IOException {
		// Load html template
		File _htmlTemplate = new File(template, "newsletter.html");
		String _content = FileUtils.readFileToString(_htmlTemplate);
		String[] _searchStrings = { "${FIRST-NAME}", "${ACTIVATE_LINK}" };
		String[] _replaceStrings = { this.firstName, this.activateLink };

		return StringUtils.replaceEach(_content, _searchStrings, _replaceStrings);
	}

	@Override
	public Map<String, File> getTemplate() {
		Map<String, File> _mapTemplate = new HashMap<>();
		_mapTemplate.put("logo_designer.jpg", new File(template, "logo_designer.jpg"));
		_mapTemplate.put("img_01_active.jpg", new File(template, "img_01_active.jpg"));
		_mapTemplate.put("title_1_active.jpg", new File(template, "title_1_active.jpg"));
		_mapTemplate.put("seeyou.jpg", new File(template, "seeyou.jpg"));
		_mapTemplate.put("border.jpg", new File(template, "border.jpg"));
		_mapTemplate.put("fb.jpg", new File(template, "fb.jpg"));
		_mapTemplate.put("it.jpg", new File(template, "it.jpg"));
		_mapTemplate.put("tw1.jpg", new File(template, "tw1.jpg"));
		_mapTemplate.put("googleplay.jpg", new File(template, "googleplay.jpg"));
		_mapTemplate.put("appstore.jpg", new File(template, "appstore.jpg"));
		return _mapTemplate;
	}

	@Override
	public void loadTemplate() throws URISyntaxException, IOException {
		this.template = new File(
		        this.getClass().getClassLoader().getResource("designer-active-account").toURI());
	}

}
