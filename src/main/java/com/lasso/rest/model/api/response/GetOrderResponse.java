package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.define.JobConfirmationConstant;
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
@JsonSerialize(using = GetConfirmJobSerializer.class)
public class GetOrderResponse extends BaseResponse {

	/** The data. */
	private Object[]	data;

	/** The prefix avatar. */
	private String		prefixAvatar;

	/** The prefix category. */
	private String		prefixCategory;

	/** The prefix job. */
	private String		prefixJob;

	/** The prefix style. */
	private String		prefixStyle;

	/** The prefix type. */
	private String		prefixType;

	/**
	 * Instantiates a new gets the order response.
	 *
	 * @param __error the error
	 */
	public GetOrderResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new gets the order response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public GetOrderResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new gets the order response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public GetOrderResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new gets the order response.
	 *
	 * @param __data the data
	 * @param __prefixAvatar the prefix avatar
	 * @param __prefixStyle the prefix style
	 * @param __prefixType the prefix type
	 * @param __prefixCategory the prefix category
	 * @param __prefixJob the prefix job
	 */
	public GetOrderResponse(Object[] __data, String __prefixAvatar, String __prefixStyle,
			String __prefixType, String __prefixCategory, String __prefixJob) {
		super();
		this.data = __data;
		this.prefixAvatar = __prefixAvatar;
		this.prefixStyle = __prefixStyle;
		this.prefixType = __prefixType;
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

	/**
	 * Gets the prefix style.
	 *
	 * @return the prefixStyle
	 */
	public String getPrefixStyle() {
		return this.prefixStyle;
	}

	/**
	 * Gets the prefix type.
	 *
	 * @return the prefixType
	 */
	public String getPrefixType() {
		return this.prefixType;
	}

}

class GetConfirmJobSerializer extends JsonSerializer<GetOrderResponse> {

	@SuppressWarnings("unchecked")
	@Override
	public void serialize(GetOrderResponse __value, JsonGenerator __gen,
			SerializerProvider __serializers) throws IOException, JsonProcessingException {

		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeObjectFieldStart("data");
		Job _job = (Job) __value.getData()[0];
		List<Object[]> _designersJobs = (List<Object[]>) __value.getData()[1];
		List<Type> _types = (List<Type>) __value.getData()[2];
		Style _style = (Style) __value.getData()[3];
		Category _category = (Category) __value.getData()[4];

		__gen.writeStringField("job_description", _job.getDescription());
		__gen.writeNumberField("job_step", _job.getStep());

		__gen.writeObjectFieldStart("category");
		__gen.writeNumberField("category_id", _category.getId());
		__gen.writeStringField("category_title", _category.getTitle());
		__gen.writeObjectFieldStart("images");
		this.serializeImage(__gen, __value.getPrefixCategory(), _category.getImage());
		__gen.writeEndObject();
		__gen.writeEndObject();

		__gen.writeArrayFieldStart("types");
		_types.forEach(new Consumer<Type>() {

			@Override
			public void accept(Type __type) {
				try {
					__gen.writeStartObject();
					__gen.writeNumberField("type_id", __type.getId());
					__gen.writeStringField("type_title", __type.getTitle());
					__gen.writeObjectFieldStart("images");
					GetConfirmJobSerializer.this.serializeImage(__gen, __value.getPrefixType(),
							__type.getImage());
					__gen.writeEndObject();
					__gen.writeEndObject();
				}
				catch (IOException _ex) {
					Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
				}

			}
		});
		__gen.writeEndArray();

		__gen.writeObjectFieldStart("style");
		__gen.writeNumberField("style_id", _style.getId());
		__gen.writeStringField("style_title", _style.getTitle());
		__gen.writeObjectFieldStart("images");
		this.serializeImage(__gen, __value.getPrefixStyle(), _style.getImage());
		__gen.writeEndObject();
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

		__gen.writeNumberField("budget", _job.getBudget());
		DateFormat _dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		__gen.writeStringField("submission", _dateFormat.format(_job.getSubmission()));
		__gen.writeStringField("last_submission", _dateFormat.format(_job.getLatestSubmission()));
		__gen.writeStringField("objective", _job.getObjective());
		__gen.writeStringField("asset_url", _job.getAssetsUrl());

		List<Account> _unConfirm = new ArrayList<>(), _confirm = new ArrayList<>();
		List<Object[]> _counterOffer = new ArrayList<>();
		_designersJobs.forEach(new Consumer<Object[]>() {

			@Override
			public void accept(Object[] __obs) {
				try {
					JobsAccount _jobsAccount = (JobsAccount) __obs[0];
					Account _designer = (Account) __obs[1];
					if (_jobsAccount.getConfirm()
							.equals(JobConfirmationConstant.JOB_UN_CONFIRM.getCode())) {
						_unConfirm.add(_designer);
					}
					else if (_jobsAccount.getConfirm()
							.equals(JobConfirmationConstant.JOB_CONFIRM.getCode())) {
						_confirm.add(_designer);
					}
					if (_jobsAccount.getCounter().compareTo(0D) > 0) {
						_counterOffer.add(__obs);
					}

				}
				catch (Exception _ex) {
					Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
				}
			}
		});
		__gen.writeObjectFieldStart("designers");
		__gen.writeArrayFieldStart("un_confirm");
		this.serializeAccounts(__gen, _unConfirm, __value.getPrefixAvatar());
		__gen.writeEndArray();

		__gen.writeArrayFieldStart("confirm");
		this.serializeAccounts(__gen, _confirm, __value.getPrefixAvatar());
		__gen.writeEndArray();

		__gen.writeArrayFieldStart("counter_offer");
		this.serializeCounterAccounts(__gen, _counterOffer, __value.getPrefixAvatar());
		__gen.writeEndArray();
		__gen.writeEndObject();
		__gen.writeEndObject();

		__gen.writeEndObject();
	}

	private void serializeAccounts(JsonGenerator __gen, List<Account> __accounts,
			String __prefixUrl) {
		__accounts.forEach(new Consumer<Account>() {

			@Override
			public void accept(Account __account) {
				try {
					__gen.writeStartObject();
					__gen.writeNumberField("account_id", __account.getId());
					__gen.writeStringField("account_name", __account.getName());
					__gen.writeNumberField("account_reward",
							__account.getRewards() == 0 ? 1 : __account.getRewards());
					__gen.writeObjectFieldStart("avatar");
					GetConfirmJobSerializer.this.serializeImage(__gen, __prefixUrl,
							__account.getImage());
					__gen.writeEndObject();
					__gen.writeEndObject();
				}
				catch (IOException _ex) {
					Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
				}
			}
		});
	}

	private void serializeCounterAccounts(JsonGenerator __gen, List<Object[]> __counterAccounts,
			String __prefixUrl) {
		__counterAccounts.forEach(new Consumer<Object[]>() {

			@Override
			public void accept(Object[] __counterAccount) {
				try {
					Account _account = (Account) __counterAccount[1];
					Double _counter = (Double) __counterAccount[0];
					__gen.writeStartObject();
					__gen.writeNumberField("account_id", _account.getId());
					__gen.writeStringField("account_name", _account.getName());
					__gen.writeNumberField("account_reward",
							_account.getRewards() == 0 ? 1 : _account.getRewards());
					__gen.writeObjectFieldStart("avatar");
					GetConfirmJobSerializer.this.serializeImage(__gen, __prefixUrl,
							_account.getImage());
					__gen.writeEndObject();
					__gen.writeNumberField("counter", _counter);
					__gen.writeEndObject();
				}
				catch (IOException _ex) {
					Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
				}
			}
		});
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