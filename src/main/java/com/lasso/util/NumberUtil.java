package com.lasso.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Class NumberUtil.
 *
 * @author Paul Mai
 */
public final class NumberUtil {

	/**
	 * Round.
	 *
	 * @param __number the number
	 * @param __places the places
	 * @return the double
	 */
	public static double round(double __number, int __places) {
		BigDecimal bd = new BigDecimal(__number);
		return bd.setScale(__places, RoundingMode.HALF_UP).doubleValue();
	}
}
