/*
 * 
 */
package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.define.JobConfirmationConstant;
import com.lasso.define.JobStageConstant;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.JobsAccount;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;

/**
 * The Class GetOrderResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = GetOfferSerializer.class)
public class GetOfferResponse extends BaseResponse {

	/** The data. */
	private Object[]	data;

	/** The prefix avatar. */
	private String		prefixAvatar;

	/** The prefix category. */
	private String		prefixCategory;

	/** The prefix job. */
	private String		prefixJob;

	/**
	 * Instantiates a new gets the order response.
	 *
	 * @param __error the error
	 */
	public GetOfferResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new gets the order response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public GetOfferResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new gets the order response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public GetOfferResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new gets the order response.
	 *
	 * @param __data the data
	 * @param __prefixAvatar the prefix avatar
	 * @param __prefixCategory the prefix category
	 * @param __prefixJob the prefix job
	 */
	public GetOfferResponse(Object[] __data, String __prefixAvatar, String __prefixCategory,
			String __prefixJob) {
		super();
		this.data = __data;
		this.prefixAvatar = __prefixAvatar;
		this.prefixCategory = __prefixCategory;
		this.prefixJob = __prefixJob;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Object[] getData() {
		return this.data;
	}

	/**
	 * Gets the prefix avatar.
	 *
	 * @return the prefixAvatar
	 */
	public String getPrefixAvatar() {
		return this.prefixAvatar;
	}

	/**
	 * Gets the prefix category.
	 *
	 * @return the prefixCategory
	 */
	public String getPrefixCategory() {
		return this.prefixCategory;
	}

	/**
	 * Gets the prefix job.
	 *
	 * @return the prefixJob
	 */
	public String getPrefixJob() {
		return this.prefixJob;
	}

}

class GetOfferSerializer extends JsonSerializer<GetOfferResponse> {

	@SuppressWarnings("unchecked")
	@Override
	public void serialize(GetOfferResponse __value, JsonGenerator __gen,
			SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeObjectFieldStart("data");
		Job _job = (Job) __value.getData()[0];
		Account _user = (Account) __value.getData()[1];
		List<Style> _styles = (List<Style>) __value.getData()[2];
		Type _type = (Type) __value.getData()[3];
		Category _category = (Category) __value.getData()[4];
		JobsAccount _jobsAccount = (JobsAccount) __value.getData()[5];

		__gen.writeStringField("job_description", _job.getDescription());
		__gen.writeNumberField("job_step", _job.getStep());

		__gen.writeObjectFieldStart("user");
		this.serializeAccounts(__gen, _user, __value.getPrefixAvatar());
		__gen.writeEndObject();

		__gen.writeObjectFieldStart("category");
		__gen.writeNumberField("category_id", _category.getId());
		__gen.writeStringField("title", _category.getTitle());
		__gen.writeObjectFieldStart("images");
		this.serializeImage(__gen, __value.getPrefixCategory(), _category.getImage());
		__gen.writeEndObject();
		__gen.writeEndObject();

		__gen.writeArrayFieldStart("styles");
		_styles.forEach(_style -> {
			try {
				__gen.writeStartObject();
				__gen.writeNumberField("style_id", _style.getId());
				__gen.writeStringField("title", _style.getTitle());
				__gen.writeEndObject();
			}
			catch (IOException _ex) {
				Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
			}

		});
		__gen.writeEndArray();

		__gen.writeObjectFieldStart("type");
		__gen.writeNumberField("type_id", _type.getId());
		__gen.writeStringField("type_title", _type.getTitle());
		__gen.writeEndObject();

		__gen.writeArrayFieldStart("images");
		if (_job.getReference() != null && !_job.getReference().trim().isEmpty()) {
			for (String _referenceImage : _job.getReference().trim().split(",")) {
				__gen.writeStartObject();
				this.serializeImage(__gen, __value.getPrefixJob(), _referenceImage);
				__gen.writeEndObject();
			}
		}
		__gen.writeEndArray();

		__gen.writeNumberField("job_budget", _job.getBudget());
		DateFormat _dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		__gen.writeStringField("submission", _dateFormat.format(_job.getSubmission()));
		__gen.writeStringField("last_submission", _dateFormat.format(_job.getLatestSubmission()));
		__gen.writeStringField("objective", _job.getObjective());
		__gen.writeStringField("asset_url", _job.getAssetsUrl());
		__gen.writeStringField("further_information", _job.getFurtherInformation());
		String _status;
		if (_job.getPaid().byteValue() == (byte) 0) {
			if (_jobsAccount.getConfirm().byteValue() == JobConfirmationConstant.JOB_UN_CONFIRM
					.getCode()) {
				_status = "job_confirm";
			}
			else {
				_status = "job_wait_accept";
			}
		}
		else if (_job.getStage().byteValue() == JobStageConstant.JOB_STAGE_COMPLETED.getCode()) {
			_status = "job_completed";
		}
		else {
			_status = "job_explain";
		}
		__gen.writeStringField("status", _status);
		__gen.writeNumberField("counter_amount", _jobsAccount.getCounter());

		__gen.writeEndObject();

		__gen.writeEndObject();
	}

	private void serializeAccounts(JsonGenerator __gen, Account __account, String __prefixUrl) {
		try {
			__gen.writeNumberField("account_id", __account.getId());
			__gen.writeStringField("account_name", __account.getName());
			__gen.writeNumberField("account_reward",
					__account.getRewards() == 0 ? 1 : __account.getRewards());
			__gen.writeObjectFieldStart("avatar");
			GetOfferSerializer.this.serializeImage(__gen, __prefixUrl, __account.getImage());
			__gen.writeEndObject();
		}
		catch (IOException _ex) {
			Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
		}
	}

	private void serializeImage(JsonGenerator __gen, String __prefixUrl, String imageName) {
		try {
			if (imageName == null || imageName.trim().isEmpty()) {
				__gen.writeStringField("original", "");
				__gen.writeStringField("small", "");
				__gen.writeStringField("icon", "");
				__gen.writeStringField("retina", "");
			}
			else {
				__gen.writeStringField("original", __prefixUrl + "/Original/" + imageName.trim());
				__gen.writeStringField("small", __prefixUrl + "/Small/" + imageName.trim());
				__gen.writeStringField("icon", __prefixUrl + "/Icon/" + imageName.trim());
				__gen.writeStringField("retina", __prefixUrl + "/Retina/" + imageName.trim());
			}
		}
		catch (Exception _ex) {
			Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
		}

	}

}