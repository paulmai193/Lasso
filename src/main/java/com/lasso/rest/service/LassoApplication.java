package com.lasso.rest.service;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class LassoApplication extends ResourceConfig {

	public LassoApplication() {
		this.register(RequestContextFilter.class);
		this.register(JacksonFeature.class);
		this.packages("com.lasso.rest");
	}

}
