package com.lasso.define;

/**
 *
 * @author Paul Mai
 */
public enum JobStageConstant {

	JOB_STEP_BRIEF((byte) 1, "1st Draft"),

	JOB_STEP_CHOOSE_DESIGNER((byte) 2, "Revised"),

	JOB_STEP_CONFIRM((byte) 3, "Final Artwork"),

	JOB_STEP_PAY((byte) 4, "Completed");

	/**
	 * Gets the by code.
	 *
	 * @param __code the code
	 * @return the by code
	 */
	public static JobStageConstant getByCode(byte __code) {
		for (JobStageConstant _jobStageConstant : JobStageConstant.values()) {
			if (_jobStageConstant.getStageCode() == __code) {
				return _jobStageConstant;
			}
		}
		return null;
	}

	private byte	stageCode;

	private String	stageName;

	private JobStageConstant(byte __stageCode, String __stageName) {
		this.stageCode = __stageCode;
		this.stageName = __stageName;
	}

	/**
	 * @return the stageCode
	 */
	public byte getStageCode() {
		return this.stageCode;
	}

	/**
	 * @return the stageName
	 */
	public String getStageName() {
		return this.stageName;
	}

}
