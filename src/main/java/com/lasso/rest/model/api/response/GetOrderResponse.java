package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.JobsAccount;
import com.lasso.rest.model.datasource.Portfolio;
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

	/** The prefix portfolio. */
	private String		prefixPortfolio;

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
	 * @param __prefixPortfolio the prefix portfolio
	 */
	public GetOrderResponse(Object[] __data, String __prefixAvatar, String __prefixStyle,
			String __prefixType, String __prefixCategory, String __prefixJob,
			String __prefixPortfolio) {
		super();
		this.data = __data;
		this.prefixAvatar = __prefixAvatar;
		this.prefixStyle = __prefixStyle;
		this.prefixType = __prefixType;
		this.prefixCategory = __prefixCategory;
		this.prefixJob = __prefixJob;
		this.prefixPortfolio = __prefixPortfolio;
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
	 * Gets the prefix portfolio.
	 *
	 * @return the prefix portfolio
	 */
	public String getPrefixPortfolio() {
		return this.prefixPortfolio;
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
		List<Style> _styles = (List<Style>) __value.getData()[2];
		Type _type = (Type) __value.getData()[3];
		Category _category = (Category) __value.getData()[4];
		float _serviceFee = (float) __value.getData()[5];

		__gen.writeStringField("job_description", _job.getDescription());
		__gen.writeNumberField("job_step", _job.getStep());

		__gen.writeObjectFieldStart("category");
		__gen.writeNumberField("category_id", _category.getId());
		__gen.writeStringField("title", _category.getTitle());
		__gen.writeObjectFieldStart("images");
		this.serializeImage(__gen, __value.getPrefixCategory(), _category.getImage());
		__gen.writeEndObject();
		__gen.writeEndObject();

		__gen.writeArrayFieldStart("styles");
		_styles.forEach(__style -> {
			try {
				__gen.writeStartObject();
				__gen.writeNumberField("style_id", __style.getId());
				__gen.writeStringField("title", __style.getTitle());
				__gen.writeObjectFieldStart("images");
				GetConfirmJobSerializer.this.serializeImage(__gen, __value.getPrefixStyle(),
						__style.getImage());
				__gen.writeEndObject();
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
		__gen.writeObjectFieldStart("image");
		this.serializeImage(__gen, __value.getPrefixType(), _type.getImage());
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

		__gen.writeNumberField("job_budget", _job.getBudget());
		__gen.writeNumberField("job_fee", _serviceFee);
		DateFormat _dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		__gen.writeStringField("submission", _dateFormat.format(_job.getSubmission()));
		__gen.writeStringField("last_submission", _dateFormat.format(_job.getLatestSubmission()));
		__gen.writeStringField("objective", _job.getObjective());
		__gen.writeStringField("asset_url", _job.getAssetsUrl());

		List<Object[]> _confirm = new ArrayList<>(), _counterOffer = new ArrayList<>();
		_designersJobs.forEach(__obs -> {
			try {
				JobsAccount _jobsAccount = (JobsAccount) __obs[0];
				if (_jobsAccount.getConfirm().byteValue() == JobConfirmationConstant.JOB_CONFIRM
						.getCode()
						|| _jobsAccount.getConfirm()
						.byteValue() == JobConfirmationConstant.JOB_ACCEPT.getCode()) {
					_confirm.add(__obs);
				}
				if (_jobsAccount.getCounter().compareTo(0D) > 0) {
					_counterOffer.add(__obs);
				}

			}
			catch (Exception _ex) {
				Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
			}
		});
		__gen.writeObjectFieldStart("designers");
		__gen.writeArrayFieldStart("selected");
		this.serializeAccounts(__gen, _designersJobs, __value.getPrefixAvatar(),
				__value.getPrefixPortfolio());
		__gen.writeEndArray();

		__gen.writeArrayFieldStart("confirm");
		this.serializeAccounts(__gen, _confirm, __value.getPrefixAvatar(),
				__value.getPrefixPortfolio());
		__gen.writeEndArray();

		__gen.writeArrayFieldStart("counter_offer");
		this.serializeCounterAccounts(__gen, _counterOffer, __value.getPrefixAvatar(),
				__value.getPrefixPortfolio());
		__gen.writeEndArray();
		__gen.writeEndObject();
		__gen.writeEndObject();

		__gen.writeEndObject();
	}

	private void serializeAccounts(JsonGenerator __gen, List<Object[]> __unConfirm,
			String __prefixAvatar, String __prefixPortfolio) {
		__unConfirm.forEach(__accounts -> {
			try {
				Account _account = (Account) __accounts[1];
				Portfolio _portfolio = (Portfolio) __accounts[2];
				__gen.writeStartObject();
				__gen.writeNumberField("designer_id", _account.getId());
				__gen.writeStringField("designer_name", _account.getName());
				__gen.writeNumberField("designer_reward",
						_account.getRewards() == 0 ? 1 : _account.getRewards());
				__gen.writeObjectFieldStart("designer_avatar");
				GetConfirmJobSerializer.this.serializeImage(__gen, __prefixAvatar,
						_account.getImage());
				__gen.writeEndObject();
				__gen.writeObjectFieldStart("portfolio_image");
				String _portfolioImage = _portfolio.getImage().split(",")[0];
				GetConfirmJobSerializer.this.serializeImage(__gen, __prefixPortfolio,
						_portfolioImage);
				__gen.writeEndObject();
				__gen.writeEndObject();
			}
			catch (IOException _ex) {
				Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
			}
		});
	}

	private void serializeCounterAccounts(JsonGenerator __gen, List<Object[]> __counterAccounts,
			String __prefixAvatar, String __prefixPortfolio) {
		__counterAccounts.forEach(__counterAccount -> {
			try {
				Portfolio _portfolio = (Portfolio) __counterAccount[2];
				Account _account = (Account) __counterAccount[1];
				Double _counter = ((JobsAccount) __counterAccount[0]).getCounter();
				__gen.writeStartObject();
				__gen.writeNumberField("designer_id", _account.getId());
				__gen.writeStringField("designer_name", _account.getName());
				__gen.writeNumberField("designer_reward",
						_account.getRewards() == 0 ? 1 : _account.getRewards());
				__gen.writeObjectFieldStart("designer_avatar");
				GetConfirmJobSerializer.this.serializeImage(__gen, __prefixAvatar,
						_account.getImage());
				__gen.writeEndObject();
				__gen.writeObjectFieldStart("portfolio_image");
				GetConfirmJobSerializer.this.serializeImage(__gen, __prefixPortfolio,
						_portfolio.getImage());
				__gen.writeEndObject();
				__gen.writeNumberField("counter", _counter);
				__gen.writeEndObject();
			}
			catch (IOException _ex) {
				Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
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