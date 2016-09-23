package com.lasso.rest.model.api.response;

import java.util.List;

public class MessageDetailResponse extends BaseResponse {

	private GetOrderResponse	orderDetail;
	private List<Object[]>		messageDatas;
	private String				prefixUrl;

	public MessageDetailResponse(GetOrderResponse __orderDetail, List<Object[]> __messageDatas,
	        String __prefixUrl) {
		super();
		this.orderDetail = __orderDetail;
		this.messageDatas = __messageDatas;
		this.prefixUrl = __prefixUrl;
	}

	public MessageDetailResponse(boolean __error) {
		super(__error);
		// TODO Auto-generated constructor stub
	}

	public MessageDetailResponse(boolean __error, String __message) {
		super(__error, __message);
		// TODO Auto-generated constructor stub
	}

	public MessageDetailResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the orderDetail
	 */
	public GetOrderResponse getOrderDetail() {
		return this.orderDetail;
	}

	/**
	 * @return the messageDatas
	 */
	public List<Object[]> getMessageDatas() {
		return this.messageDatas;
	}

	/**
	 * @return the prefixUrl
	 */
	public String getPrefixUrl() {
		return this.prefixUrl;
	}

}
