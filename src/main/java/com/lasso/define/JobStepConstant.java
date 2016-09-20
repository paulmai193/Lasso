package com.lasso.define;

/**
 * The Enum JobStepConstant.
 *
 * @author Paul Mai
 */
public enum JobStepConstant {

	/** The job step brief. */
	JOB_STEP_BRIEF((byte) 1, "Brief new job step"),
	/** The job step choose designer. */
	JOB_STEP_CHOOSE_DESIGNER((byte) 2, "Choose designer step"),
	/** The job step complete. */
	JOB_STEP_COMPLETE((byte) 5, "Completed step"),
	/** The job step confirm. */
	JOB_STEP_CONFIRM((byte) 3, "Confirmation step"),
	/** The job step pay. */
	JOB_STEP_PAY((byte) 4, "Payment step");

	/**
	 * Gets the by code.
	 *
	 * @param __code the code
	 * @return the by code
	 */
	public static JobStepConstant getByCode(byte __code) {
		for (JobStepConstant _jobStepConstant : JobStepConstant.values()) {
			if (_jobStepConstant.getStepCode() == __code) {
				return _jobStepConstant;
			}
		}
		return null;
	}

	/** The step code. */
	private byte	stepCode;

	/** The step name. */
	private String	stepName;

	/**
	 * Instantiates a new job step constant.
	 *
	 * @param __step_code the step code
	 * @param __step_name the step name
	 */
	private JobStepConstant(byte __step_code, String __step_name) {
		this.stepCode = __step_code;
		this.stepName = __step_name;
	}

	/**
	 * Gets the step code.
	 *
	 * @return the stepCode
	 */
	public byte getStepCode() {
		return this.stepCode;
	}

	/**
	 * Gets the step name.
	 *
	 * @return the stepName
	 */
	public String getStepName() {
		return this.stepName;
	}

}
