package com.lasso.define;

/**
 * The Enum JobStageConstant.
 *
 * @author Paul Mai
 */
public enum JobStageConstant {

	/** The job step brief. */
	JOB_STEP_BRIEF((byte) 1, "1st Draft"),

	/** The job step choose designer. */
	JOB_STEP_CHOOSE_DESIGNER((byte) 2, "Revised"),

	/** The job step confirm. */
	JOB_STEP_CONFIRM((byte) 3, "Final Artwork"),

	/** The job step pay. */
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

	/** The stage code. */
	private byte	stageCode;

	/** The stage name. */
	private String	stageName;

	/**
	 * Instantiates a new job stage constant.
	 *
	 * @param __stageCode the stage code
	 * @param __stageName the stage name
	 */
	private JobStageConstant(byte __stageCode, String __stageName) {
		this.stageCode = __stageCode;
		this.stageName = __stageName;
	}

	/**
	 * Gets the stage code.
	 *
	 * @return the stageCode
	 */
	public byte getStageCode() {
		return this.stageCode;
	}

	/**
	 * Gets the stage name.
	 *
	 * @return the stageName
	 */
	public String getStageName() {
		return this.stageName;
	}

}
