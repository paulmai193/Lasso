package com.lasso.template;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public interface EmailTemplate {

	String getContent() throws URISyntaxException, IOException;

	Map<String, File> getTemplate();

	void loadTemplate() throws URISyntaxException, IOException;
}
