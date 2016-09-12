package com.lasso.rest.model.api.response;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lasso.rest.model.datasource.Account;
import com.lasso.rest.model.datasource.Portfolio;
import com.lasso.rest.model.datasource.Project;

/**
 * The Class ProjectDetailResponse.
 *
 * @author Paul Mai
 */
@JsonInclude(value = Include.NON_NULL)
@JsonSerialize(using = ProjectSerializer.class)
public class ProjectDetailResponse extends BaseResponse {

	/** The designer. */
	private Account		designer;

	/** The portfolio. */
	private Portfolio	portfolio;

	/** The prefix avatar url. */
	private String		prefixAvatarUrl;

	/** The prefix portfolio url. */
	private String		prefixPortfolioUrl;

	/** The categories. */
	private Project		project;

	/**
	 * Instantiates a new project response.
	 *
	 * @param __error the error
	 */
	public ProjectDetailResponse(boolean __error) {
		super(__error);
	}

	/**
	 * Instantiates a new project response.
	 *
	 * @param __error the error
	 * @param __message the message
	 */
	public ProjectDetailResponse(boolean __error, String __message) {
		super(__error, __message);
	}

	/**
	 * Instantiates a new project response.
	 *
	 * @param __error the error
	 * @param __message the message
	 * @param __detail the detail
	 */
	public ProjectDetailResponse(boolean __error, String __message, String __detail) {
		super(__error, __message, __detail);
	}

	/**
	 * Instantiates a new project detail response.
	 *
	 * @param __prefixPortfolioUrl the prefix portfolio url
	 * @param __prefixAvatarUrl the prefix avatar url
	 * @param __project the project
	 * @param __portfolio the portfolio
	 * @param __designer the designer
	 */
	public ProjectDetailResponse(String __prefixPortfolioUrl, String __prefixAvatarUrl,
			Project __project, Portfolio __portfolio, Account __designer) {
		super();
		this.prefixPortfolioUrl = __prefixPortfolioUrl;
		this.prefixAvatarUrl = __prefixAvatarUrl;
		this.project = __project;
		this.portfolio = __portfolio;
		this.designer = __designer;
	}

	/**
	 * Gets the designer.
	 *
	 * @return the designer
	 */
	public Account getDesigner() {
		return this.designer;
	}

	/**
	 * Gets the portfolio.
	 *
	 * @return the portfolio
	 */
	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	/**
	 * Gets the prefix avatar url.
	 *
	 * @return the prefixAvatarUrl
	 */
	public String getPrefixAvatarUrl() {
		return this.prefixAvatarUrl;
	}

	/**
	 * Gets the prefix portfolio url.
	 *
	 * @return the prefixPortfolioUrl
	 */
	public String getPrefixPortfolioUrl() {
		return this.prefixPortfolioUrl;
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return this.project;
	}

}

class ProjectSerializer extends JsonSerializer<ProjectDetailResponse> {

	@Override
	public void serialize(ProjectDetailResponse __value, JsonGenerator __gen,
			SerializerProvider __serializers) throws IOException, JsonProcessingException {
		__gen.writeStartObject();

		__gen.writeObjectField("error", __value.isError());
		if (__value.isError()) {
			__gen.writeObjectField("detail", __value.getDetail());
			__gen.writeObjectField("message", __value.getMessage());
		}
		__gen.writeObjectFieldStart("data");
		__gen.writeStringField("project_title", __value.getProject().getTitle());
		__gen.writeStringField("portfolio_title", __value.getProject().getTitle());
		__gen.writeStringField("portfolio_info", __value.getPortfolio().getInfo());
		if (__value.getDesigner().getImage().isEmpty()) {
			__gen.writeStringField("designer_avatar", "");
		}
		else {
			__gen.writeStringField("designer_avatar",
					__value.getPrefixAvatarUrl() + "/small/" + __value.getDesigner().getImage());
		}
		__gen.writeArrayFieldStart("images");
		if (!__value.getPortfolio().getImage().isEmpty()) {
			for (String _portfolioImage : __value.getPortfolio().getImage().split(",")) {
				if (!_portfolioImage.trim().isEmpty()) {
					__gen.writeStartObject();
					__gen.writeStringField("original",
							__value.getPrefixPortfolioUrl() + "/Original/" + _portfolioImage);
					__gen.writeStringField("small",
							__value.getPrefixPortfolioUrl() + "/small/" + _portfolioImage);
					__gen.writeEndObject();
				}
			}
		}
		__gen.writeEndArray();

		__gen.writeEndObject();
	}

}
