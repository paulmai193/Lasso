package com.lasso.define;

/**
 * The Enum JobStageConstant.
 *
 * @author Paul Mai
 */
public enum JobStageConstant {

	/** The job stage 1st draft. */
	JOB_STAGE_1ST_DRAFT((byte) 1, "1st Draft"),

	/** The job stage completed. */
	JOB_STAGE_COMPLETED((byte) 4, "Completed"),

	/** The job stage final artwork. */
	JOB_STAGE_FINAL_ARTWORK((byte) 3, "Final Artwork"),

	/** The job stage revised. */
	JOB_STAGE_REVISED((byte) 2, "Revised");

	/**
	 * Gets the by code.
	 *
	 * @param __code the code
	 * @return the by code
	 */
	public static JobStageConstant getByCode(byte __code) {
		for (JobStageConstant _jobStageConstant : JobStageConstant.values()) {
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
	 * Instantiates a new job stage constant.
	 *
	 * @param __stageCode the stage code
	 * @param __stageName the stage name
	 */
	private JobStageConstant(byte __stageCode, String __stageName) {
		this.code = __stageCode;
		this.name = __stageName;
	}

	/**
	 * Gets the stage code.
	 *
	 * @return the code
	 */
	public byte getCode() {
		return this.code;
	}

	/**
	 * Gets the stage name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

}
