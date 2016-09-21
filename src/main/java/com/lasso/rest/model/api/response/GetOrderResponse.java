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

@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = JobDetailSerializer.class)
public class GetOrderResponse extends BaseResponse {

	/** The data. */
	private Object[]	data;

	private String		prefixAvatar;
	private String		prefixStyle;
	private String		prefixType;
	private String		prefixCategory;
	private String		prefixJob;

	public GetOrderResponse(boolean __error) {
		super(__error);
	}

	public GetOrderResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	public GetOrderResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * @return the data
	 */
	public Object[] getData() {
		return this.data;
	}

	/**
	 * @return the prefixAvatar
	 */
	public String getPrefixAvatar() {
		return this.prefixAvatar;
	}

	/**
	 * @return the prefixStyle
	 */
	public String getPrefixStyle() {
		return this.prefixStyle;
	}

	/**
	 * @return the prefixType
	 */
	public String getPrefixType() {
		return this.prefixType;
	}

	/**
	 * @return the prefixCategory
	 */
	public String getPrefixCategory() {
		return this.prefixCategory;
	}

	public String getPrefixJob() {
		return this.prefixJob;
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

		Job _job = (Job) __value.getData()[0];
		List<Object[]> _designersJobs = (List<Object[]>) __value.getData()[1];
		List<Type> _types = (List<Type>) __value.getData()[2];
		Style _style = (Style) __value.getData()[3];
		Category _category = (Category) __value.getData()[4];

		__gen.writeStringField("job_description", _job.getDescription());

		__gen.writeObjectFieldStart("category");
		__gen.writeNumberField("category_id", _category.getId());
		__gen.writeStringField("category_title", _category.getTitle());
		__gen.writeObjectFieldStart("images");
		if (_category.getImage() == null || _category.getImage().trim().isEmpty()) {
			__gen.writeStringField("original", "");
			__gen.writeStringField("small", "");
			__gen.writeStringField("icon", "");
			__gen.writeStringField("retina", "");
		}
		else {
			__gen.writeStringField("original",
			        __value.getPrefixCategory() + "/Original/" + _category.getImage().trim());
			__gen.writeStringField("small",
			        __value.getPrefixCategory() + "/Small/" + _category.getImage().trim());
			__gen.writeStringField("icon",
			        __value.getPrefixCategory() + "/Icon/" + _category.getImage().trim());
			__gen.writeStringField("retina",
			        __value.getPrefixCategory() + "/Retina/" + _category.getImage().trim());
		}
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
					if (__type.getImage() == null || __type.getImage().trim().isEmpty()) {
						__gen.writeStringField("original", "");
						__gen.writeStringField("small", "");
						__gen.writeStringField("icon", "");
						__gen.writeStringField("retina", "");
					}
					else {
						__gen.writeStringField("original",
						        __value.getPrefixType() + "/Original/" + __type.getImage().trim());
						__gen.writeStringField("small",
						        __value.getPrefixType() + "/Small/" + __type.getImage().trim());
						__gen.writeStringField("icon",
						        __value.getPrefixType() + "/Icon/" + __type.getImage().trim());
						__gen.writeStringField("retina",
						        __value.getPrefixType() + "/Retina/" + __type.getImage().trim());
					}
					__gen.writeEndObject();
					__gen.writeEndObject();
				}
				catch (IOException _ex) {
					Logger.getLogger(getClass()).warn("Unwanted error", _ex);
				}

			}
		});
		__gen.writeEndArray();

		__gen.writeObjectFieldStart("style");
		__gen.writeNumberField("style_id", _style.getId());
		__gen.writeStringField("style_title", _style.getTitle());
		__gen.writeObjectFieldStart("images");
		if (_style.getImage() == null || _style.getImage().trim().isEmpty()) {
			__gen.writeStringField("original", "");
			__gen.writeStringField("small", "");
			__gen.writeStringField("icon", "");
			__gen.writeStringField("retina", "");
		}
		else {
			__gen.writeStringField("original",
			        __value.getPrefixStyle() + "/Original/" + _style.getImage().trim());
			__gen.writeStringField("small",
			        __value.getPrefixStyle() + "/Small/" + _style.getImage().trim());
			__gen.writeStringField("icon",
			        __value.getPrefixStyle() + "/Icon/" + _style.getImage().trim());
			__gen.writeStringField("retina",
			        __value.getPrefixStyle() + "/Retina/" + _style.getImage().trim());
		}
		__gen.writeEndObject();
		__gen.writeEndObject();

		__gen.writeArrayFieldStart("images");
		if (_job.getReference() != null && !_job.getReference().trim().isEmpty()) {
			for (String _referenceImage : _job.getReference().trim().split(",")) {
				if (!_referenceImage.trim().isEmpty()) {
					__gen.writeStartObject();
					__gen.writeStringField("original",
					        __value.getPrefixJob() + "/Original/" + _referenceImage.trim());
					__gen.writeStringField("small",
					        __value.getPrefixJob() + "/Small/" + _referenceImage.trim());
					__gen.writeStringField("icon",
					        __value.getPrefixJob() + "/Icon/" + _referenceImage.trim());
					__gen.writeStringField("retina",
					        __value.getPrefixJob() + "/Retina/" + _referenceImage.trim());
					__gen.writeEndObject();
				}
				else {
					__gen.writeStartObject();
					__gen.writeStringField("original", "");
					__gen.writeStringField("small", "");
					__gen.writeStringField("icon", "");
					__gen.writeStringField("retina", "");
					__gen.writeEndObject();
				}
			}
		}
		__gen.writeEndArray();

		__gen.writeNumberField("budget", _job.getBudget());
		DateFormat _dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		__gen.writeStringField("submission", _dateFormat.format(_job.getSubmission()));
		__gen.writeStringField("last_submission", _dateFormat.format(_job.getLatestSubmission()));
		__gen.writeStringField("objective", _job.getObjective());
		__gen.writeStringField("asset_url", _job.getAssetsUrl());

		List<Account> _unConfirm, _confirm, _counterOffer;
		_unConfirm = _confirm = _counterOffer = new ArrayList<>();
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
						_counterOffer.add(_designer);
					}

				}
				catch (Exception _ex) {
					Logger.getLogger(getClass()).warn("Unwanted error", _ex);
				}
			}
		});
		__gen.writeObjectFieldStart("designers");
		__gen.writeArrayFieldStart("un_confirm");
		_unConfirm.forEach(new Consumer<Account>() {

			@Override
			public void accept(Account __account) {
				try {
					__gen.writeStartObject();
					__gen.writeNumberField("account_id", __account.getId());
					__gen.writeStringField("account_name", __account.getName());
					__gen.writeNumberField("account_reward",
					        __account.getRewards() == 0 ? 1 : __account.getRewards());
					__gen.writeObjectFieldStart("avatar");
					if (__account.getImage() == null || __account.getImage().trim().isEmpty()) {
						__gen.writeStringField("original", "");
						__gen.writeStringField("small", "");
						__gen.writeStringField("icon", "");
						__gen.writeStringField("retina", "");
					}
					else {
						__gen.writeStringField("original", __value.getPrefixAvatar() + "/Original/"
						        + __account.getImage().trim());
						__gen.writeStringField("small", __value.getPrefixAvatar() + "/Small/"
						        + __account.getImage().trim());
						__gen.writeStringField("icon",
						        __value.getPrefixAvatar() + "/Icon/" + __account.getImage().trim());
						__gen.writeStringField("retina", __value.getPrefixAvatar() + "/Retina/"
						        + __account.getImage().trim());
					}
					__gen.writeEndObject();
					__gen.writeEndObject();
				}
				catch (IOException _ex) {
					Logger.getLogger(getClass()).warn("Unwanted error", _ex);
				}
			}
		});
		__gen.writeEndArray();

		__gen.writeArrayFieldStart("confirm");
		_confirm.forEach(new Consumer<Account>() {

			@Override
			public void accept(Account __account) {
				try {
					__gen.writeStartObject();
					__gen.writeNumberField("account_id", __account.getId());
					__gen.writeStringField("account_name", __account.getName());
					__gen.writeNumberField("account_reward",
					        __account.getRewards() == 0 ? 1 : __account.getRewards());
					__gen.writeObjectFieldStart("avatar");
					if (__account.getImage() == null || __account.getImage().trim().isEmpty()) {
						__gen.writeStringField("original", "");
						__gen.writeStringField("small", "");
						__gen.writeStringField("icon", "");
						__gen.writeStringField("retina", "");
					}
					else {
						__gen.writeStringField("original", __value.getPrefixAvatar() + "/Original/"
						        + __account.getImage().trim());
						__gen.writeStringField("small", __value.getPrefixAvatar() + "/Small/"
						        + __account.getImage().trim());
						__gen.writeStringField("icon",
						        __value.getPrefixAvatar() + "/Icon/" + __account.getImage().trim());
						__gen.writeStringField("retina", __value.getPrefixAvatar() + "/Retina/"
						        + __account.getImage().trim());
					}
					__gen.writeEndObject();
					__gen.writeEndObject();
				}
				catch (IOException _ex) {
					Logger.getLogger(getClass()).warn("Unwanted error", _ex);
				}
			}
		});
		__gen.writeEndArray();

		__gen.writeArrayFieldStart("counter_offer");
		_counterOffer.forEach(new Consumer<Account>() {

			@Override
			public void accept(Account __account) {
				try {
					__gen.writeStartObject();
					__gen.writeNumberField("account_id", __account.getId());
					__gen.writeStringField("account_name", __account.getName());
					__gen.writeNumberField("account_reward",
					        __account.getRewards() == 0 ? 1 : __account.getRewards());
					__gen.writeObjectFieldStart("avatar");
					if (__account.getImage() == null || __account.getImage().trim().isEmpty()) {
						__gen.writeStringField("original", "");
						__gen.writeStringField("small", "");
						__gen.writeStringField("icon", "");
						__gen.writeStringField("retina", "");
					}
					else {
						__gen.writeStringField("original", __value.getPrefixAvatar() + "/Original/"
						        + __account.getImage().trim());
						__gen.writeStringField("small", __value.getPrefixAvatar() + "/Small/"
						        + __account.getImage().trim());
						__gen.writeStringField("icon",
						        __value.getPrefixAvatar() + "/Icon/" + __account.getImage().trim());
						__gen.writeStringField("retina", __value.getPrefixAvatar() + "/Retina/"
						        + __account.getImage().trim());
					}
					__gen.writeEndObject();
					__gen.writeEndObject();
				}
				catch (IOException _ex) {
					Logger.getLogger(getClass()).warn("Unwanted error", _ex);
				}
			}
		});
		__gen.writeEndArray();
		__gen.writeEndObject();

		__gen.writeEndObject();

	}

}