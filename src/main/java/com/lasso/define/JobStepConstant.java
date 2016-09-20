package com.lasso.define;

public enum JobStepConstant {
	JOB_STEP_BRIEF((byte) 1, "Brief new job step"), JOB_STEP_CHOOSE_DESIGNER((byte) 2,
	        "Choose designer step"), JOB_STEP_CONFIRM((byte) 3, "Confirmation step"), JOB_STEP_PAY(
	                (byte) 4, "Payment step"), JOB_STEP_COMPLETE((byte) 5, "Completed step");

	private byte	stepCode;
	private String	stepName;

	private JobStepConstant(byte __step_code, String __step_name) {
		this.stepCode = __step_code;
		this.stepName = __step_name;
	}

	/**
	 * @return the stepCode
	 */
	public byte getStepCode() {
		return this.stepCode;
	}

	/**
	 * @return the stepName
	 */
	public String getStepName() {
		return this.stepName;
	}

	public static JobStepConstant getByCode(byte __code) {
		for (JobStepConstant _jobStepConstant : JobStepConstant.values()) {
			if (_jobStepConstant.getStepCode() == __code) {
				return _jobStepConstant;
			}
		}
		return null;
	}

}
