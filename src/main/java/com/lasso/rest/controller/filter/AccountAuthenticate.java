/*
 * 
 */
package com.lasso.rest.controller.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

/**
 * The Interface AccountAuthenticate.
 *
 * @author Paul Mai
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@NameBinding
public @interface AccountAuthenticate {

}
