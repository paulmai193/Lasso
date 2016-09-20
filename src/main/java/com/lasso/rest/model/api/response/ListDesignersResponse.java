package com.lasso.rest.model.api.response;

import java.util.List;

public class ListDesignersResponse extends BaseResponse {

	private String			prefixUrl;

	private List<Object[]>	datas;

	public ListDesignersResponse() {
		// TODO Auto-generated constructor stub
	}

	public ListDesignersResponse(boolean __error) {
		super(__error);
		// TODO Auto-generated constructor stub
	}

	public ListDesignersResponse(boolean __error, String __message) {
		super(__error, __message);
		// TODO Auto-generated constructor stub
	}

	public ListDesignersResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
		// TODO Auto-generated constructor stub
	}

}
