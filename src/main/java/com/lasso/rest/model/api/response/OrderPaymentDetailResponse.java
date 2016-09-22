package com.lasso.rest.model.api.response;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lasso.rest.model.datasource.Job;
import com.lasso.rest.model.datasource.PromoCode;
import com.lasso.rest.model.datasource.PromoHistory;
import com.lasso.rest.model.datasource.Type;

@JsonInclude(value = Include.NON_NULL)

public class OrderPaymentDetailResponse extends BaseResponse {

	private Job				job;

	private PromoCode		promoCode;

	private PromoHistory	promoHistory;
	private List<Type>		types;

	public OrderPaymentDetailResponse(Job __job, PromoCode __promoCode, PromoHistory __promoHistory,
	        List<Type> __types) {
		super();
		this.job = __job;
		this.promoCode = __promoCode;
		this.promoHistory = __promoHistory;
		this.types = __types;
	}

	public OrderPaymentDetailResponse(boolean __error) {
		super(__error);
	}

	public OrderPaymentDetailResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	public OrderPaymentDetailResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * @return the job
	 */
	public Job getJob() {
		return this.job;
	}

	/**
	 * @return the promoCode
	 */
	public PromoCode getPromoCode() {
		return this.promoCode;
	}

	/**
	 * @return the promoHistory
	 */
	public PromoHistory getPromoHistory() {
		return this.promoHistory;
	}

	public List<Type> getTypes() {
		return this.types;
	}
}

class OrderPaymentDetailSerializer extends JsonSerializer<OrderPaymentDetailResponse> {

	@Override
	public void serialize(OrderPaymentDetailResponse __value, JsonGenerator __gen,
	        SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();
		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeObjectFieldStart("data");
		this.serializeOrder(__gen, __value.getJob());
		__gen.writeEndObject();
		__gen.writeEndObject();
	}

	private void serializeOrder(JsonGenerator __gen, Job __job) {
		try {
			__gen.writeStringField("job_description", __job.getDescription());
		}
		catch (IOException _ex) {
			Logger.getLogger(getClass()).warn("Unwanted error", _ex);
		}

	}

}