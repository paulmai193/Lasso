/*
 * 
 */
package com.lasso.main;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * The Class LassoApplication.
 *
 * @author Paul Mai
 */
public class LassoApplication extends ResourceConfig {

	/**
	 * Instantiates a new lasso application.
	 */
	public LassoApplication() {
		this.register(RequestContextFilter.class);
		this.register(JacksonFeature.class);
		this.packages("com.lasso.rest");
	}

}
