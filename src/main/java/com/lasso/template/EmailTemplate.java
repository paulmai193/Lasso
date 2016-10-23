/*
 * 
 */
package com.lasso.template;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * The Interface EmailTemplate.
 *
 * @author Paul Mai
 */
public interface EmailTemplate {

	/**
	 * Gets the content.
	 *
	 * @return the content
	 * @throws URISyntaxException
	 *         the URI syntax exception
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	String getContent() throws URISyntaxException, IOException;

	/**
	 * Gets the template.
	 *
	 * @return the template
	 */
	Map<String, File> getTemplate();

	/**
	 * Load template.
	 *
	 * @throws URISyntaxException
	 *         the URI syntax exception
	 * @throws IOException
	 *         Signals that an I/O exception has occurred.
	 */
	void loadTemplate() throws URISyntaxException, IOException;
}
