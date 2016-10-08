package com.lasso.define;

/**
 * The Enum JobConfirmationConstant.
 *
 * @author Paul Mai
 */
public enum JobConfirmationConstant {

	/** The job accept. */
	JOB_ACCEPT((byte) 2, "Accept"),

	/** The job confirm. */
	JOB_CONFIRM((byte) 1, "Confirm"),

	/** The job reject. */
	JOB_REJECT((byte) -1, "Reject"),

	/** The job un confirm. */
	JOB_UN_CONFIRM((byte) 0, "Un-confirm");

	/**
	 * Gets the by code.
	 *
	 * @param __code the code
	 * @return the by code
	 */
	public static JobConfirmationConstant getByCode(byte __code) {
		for (JobConfirmationConstant _jobStageConstant : JobConfirmationConstant.values()) {
			if (_jobStageConstant.getCode() == __code) {
				return _jobStageConstant;
			}
		}
		return null;
	}

	/** The stage code. */
	private byte	code;

	/** The stage name. */
	private String	name;

	/**
	 * Instantiates a new job confirmation constant.
	 *
	 * @param __code the code
	 * @param __name the name
	 */
	private JobConfirmationConstant(byte __code, String __name) {
		this.code = __code;
		this.name = __name;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public byte getCode() {
		return this.code;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

}
