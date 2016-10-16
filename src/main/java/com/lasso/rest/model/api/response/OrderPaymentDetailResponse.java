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
import com.lasso.rest.model.datasource.Category;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.PromoCode;
import com.lasso.rest.model.datasource.Style;
import com.lasso.rest.model.datasource.Type;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderPaymentDetailResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = OrderPaymentDetailSerializer.class)
public class OrderPaymentDetailResponse extends BaseResponse {

	/** The category. */
	private Category category;

	/** The job. */
	private Job job;

	/** The promo code. */
	private PromoCode promoCode;

	/** The types. */
	private List<Style> styles;

	/** The style. */
	private Type type;

	/**
	 * Instantiates a new order payment detail response.
	 *
	 * @param __error
	 *            the error
	 */
	public OrderPaymentDetailResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new order payment detail response.
	 *
	 * @param __error
	 *            the error
	 * @param __message
	 *            the message
	 */
	public OrderPaymentDetailResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new order payment detail response.
	 *
	 * @param __error
	 *            the error
	 * @param __message
	 *            the message
	 * @param __detail
	 *            the detail
	 */
	public OrderPaymentDetailResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new order payment detail response.
	 *
	 * @param __job
	 *            the job
	 * @param __promoCode
	 *            the promo code
	 * @param __styles
	 *            the styles
	 * @param __type
	 *            the type
	 * @param __category
	 *            the category
	 */
	public OrderPaymentDetailResponse(Job __job, PromoCode __promoCode, List<Style> __styles, Type __type,
			Category __category) {
		super();
		this.job = __job;
		this.promoCode = __promoCode;
		this.styles = __styles;
		this.type = __type;
		this.category = __category;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public Category getCategory() {
		return this.category;
	}

	/**
	 * Gets the job.
	 *
	 * @return the job
	 */
	public Job getJob() {
		return this.job;
	}

	/**
	 * Gets the promo code.
	 *
	 * @return the promoCode
	 */
	public PromoCode getPromoCode() {
		return this.promoCode;
	}

	/**
	 * Gets the styles.
	 *
	 * @return the styles
	 */
	public List<Style> getStyles() {
		return this.styles;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Type getType() {
		return this.type;
	}
}

class OrderPaymentDetailSerializer extends JsonSerializer<OrderPaymentDetailResponse> {

	@Override
	public void serialize(OrderPaymentDetailResponse __value, JsonGenerator __gen, SerializerProvider __serializers)
			throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeObjectFieldStart("data");
		this.serializeOrder(__gen, __value.getJob(), __value.getStyles(), __value.getType(), __value.getCategory());
		this.serializePayment(__gen, __value.getJob(), __value.getPromoCode());
		__gen.writeEndObject();
		__gen.writeEndObject();
	}

	private void serializeOrder(JsonGenerator __gen, Job __job, List<Style> __styles, Type __type,
			Category __category) {
		try {
			__gen.writeStringField("job_description", __job.getDescription());
			__gen.writeNumberField("job_amount", __job.getBudget() + __job.getFee());
			DateFormat _dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			__gen.writeStringField("submission", _dateFormat.format(__job.getSubmission()));
			__gen.writeStringField("last_submission", _dateFormat.format(__job.getLatestSubmission()));
			__gen.writeStringField("type_title", __type.getTitle());
			__gen.writeArrayFieldStart("style_titles");
			__styles.forEach(_style -> {
				try {
					__gen.writeString(_style.getTitle());
				} catch (Exception _ex) {
					Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
				}
			});
			__gen.writeEndArray();
			__gen.writeStringField("category_title", __category.getTitle());
		} catch (Exception _ex) {
			Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
		}
	}

	private void serializePayment(JsonGenerator __gen, Job __job, PromoCode __promoCode) {
		try {
			__gen.writeNumberField("job_budget", __job.getBudget());
			__gen.writeNumberField("job_fee", __job.getFee());
			if (__promoCode != null) {
				__gen.writeStringField("promo_code", __promoCode.getCode());
				__gen.writeNumberField("promo_value", __promoCode.getDiscount());
			} else {
				__gen.writeStringField("promo_code", "");
				__gen.writeNumberField("promo_value", 0);
			}
		} catch (Exception _ex) {
			Logger.getLogger(this.getClass()).warn("Unwanted error", _ex);
		}
	}

}