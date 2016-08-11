/*
 * 
 */
package com.lasso.rest.model.variable;

import com.lasso.exception.ObjectParamException;

/**
 * The Class AbstractParam.
 *
 * @author Paul Mai
 * @param <V>
 *        the value type
 */
public abstract class AbstractParam<V> {

	/** The original param. */
	private final String	originalParam;

	/** The value. */
	private final V			value;

	/**
	 * Instantiates a new abstract param.
	 *
	 * @param param the param
	 * @throws ObjectParamException the object param exception
	 */
	public AbstractParam(String param) throws ObjectParamException {
		this.originalParam = param;
		try {
			this.value = this.parse(param);
		}
		catch (ObjectParamException _ex) {
			throw _ex;
		}
		catch (Throwable t) {
			throw new ObjectParamException(t);
		}
	}

	/**
	 * Gets the original param.
	 *
	 * @return the original param
	 */
	public String getOriginalParam() {
		return this.originalParam;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public V getValue() {
		return this.value;
	}

	/**
	 * Parses the parameter to value.
	 *
	 * @param param
	 *        the param
	 * @return the value
	 * @throws Throwable
	 *         the throwable
	 */
	protected abstract V parse(String param) throws Throwable;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.value.toString();
	}

}
