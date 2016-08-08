package com.lasso.rest.service;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class LassoApplication extends ResourceConfig {

	public LassoApplication() {
		register(RequestContextFilter.class);
		register(JacksonFeature.class);
		packages("com.lasso.rest");
	}

}
