package com.lasso.rest.controller.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

import com.lasso.define.Constant;

/**
 * The Interface AccountAllow.
 *
 * @author Paul Mai
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@NameBinding
public @interface AccountAllow {

	/**
	 * Roles.
	 *
	 * @return the string[]
	 */
	String[] roles() default { "" + Constant.ROLE_DESIGNER, "" + Constant.ROLE_USER };

	/**
	 * Status.
	 *
	 * @return the string[]
	 */
	String[] status() default { "" + Constant.ACC_ACTIVATE, "" + Constant.ACC_NOT_ACTIVATE };

}
