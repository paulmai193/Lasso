package com.lasso.rest.service;

import java.io.IOException;
import java.util.List;

import com.lasso.rest.model.api.request.ChooseDesignerForOrderRequest;
import com.lasso.rest.model.api.request.CompleteJobRequest;
import com.lasso.rest.model.api.request.ConfirmOrderRequest;
import com.lasso.rest.model.api.request.CreateNewOrderRequest;
import com.lasso.rest.model.api.request.EditOrderRequest;
import com.lasso.rest.model.api.request.PaymentForOrderRequest;
import com.lasso.rest.model.api.request.UsePromoCodeForOrder;
import com.lasso.rest.model.datasource.Account;
import com.mashape.unirest.http.exceptions.UnirestException;

import javassist.NotFoundException;

/**
 * The Interface UserManagement.
 *
 * @author Paul Mai
 */
public interface UserManagement extends ProjectManagement {

	/**
	 * Apply payment.
	 *
	 * @param __user the user
	 * @param __paymentForJobRequest the payment for job request
	 */
	void applyPayment(Account __user, PaymentForOrderRequest __paymentForJobRequest);

	/**
	 * Apply promo code for order.
	 *
	 * @param __user the user
	 * @param __usePromoCodeForOrder the use promo code for order
	 */
	void applyPromoCodeForOrder(Account __user, UsePromoCodeForOrder __usePromoCodeForOrder);

	/**
	 * Choose designer for offer.
	 *
	 * @param __user the user
	 * @param __chooseDesignerForOrderRequest the choose designer for order request
	 */
	void chooseDesignerForOrder(Account __user,
			ChooseDesignerForOrderRequest __chooseDesignerForOrderRequest);

	/**
	 * Complete job.
	 *
	 * @param __completeJobRequest the complete job request
	 */
	void completeJob(CompleteJobRequest __completeJobRequest);

	/**
	 * Confirm order.
	 *
	 * @param __user the user
	 * @param __confirmOrderRequest the confirm order request
	 */
	void confirmOrder(Account __user, ConfirmOrderRequest __confirmOrderRequest);

	/**
	 * Creates the new offer.
	 *
	 * @param __user the user
	 * @param __createNewOrderRequest the create new order request
	 * @throws UnirestException the unirest exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void createNewOrder(Account __user, CreateNewOrderRequest __createNewOrderRequest)
			throws UnirestException, IOException;

	/**
	 * Edits the offer.
	 *
	 * @param __user the user
	 * @param __editOrderRequest the edit order request
	 * @throws UnirestException the unirest exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void editOrder(Account __user, EditOrderRequest __editOrderRequest)
			throws UnirestException, IOException;

	/**
	 * Gets the job data of user by id.
	 *
	 * @param __user the user
	 * @param __idJob the id job
	 * @return the job data of user by id
	 * @throws NotFoundException the not found exception
	 */
	Object[] getJobDataOfUserById(Account __user, int __idJob) throws NotFoundException;

	/**
	 * Gets the list jobs data of user.
	 *
	 * @param __user the user
	 * @return the list jobs data of user
	 */
	List<Object[]> getListJobsDataOfUser(Account __user);

	/**
	 * Gets the list portfolios by condition.
	 *
	 * @param __index the index
	 * @param __size the size
	 * @param __idCategory the id category
	 * @param _idsStyle the ids style
	 * @param __idType the id type
	 * @param __filter the filter: relevancy - budget - quality
	 * @return the list portfolios data by condition {portoflio, designer}
	 */
	List<Object[]> getListPortfoliosByCondition(int __index, int __size, int __idCategory,
			List<Integer> _idsStyle, int __idType, Integer[] __filter);

	/**
	 * Gets the order data by id.
	 *
	 * @param __idJob the id job
	 * @return the order data by id
	 */
	Object[] getOrderDataById(int __idJob);

	/**
	 * Gets the payment detail of order.
	 *
	 * @param __user the user
	 * @param __idJob the id job
	 * @return the payment detail of order
	 */
	Object[] getPaymentDetailOfOrder(Account __user, int __idJob);

}
