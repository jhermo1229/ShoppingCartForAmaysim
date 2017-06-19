package com.amaysim.shoppingcart.services;

import com.amaysim.shoppingcart.vo.Promo;

import java.util.Map;


/**
 * Retrieve Promo
 *
 * @version  1.00, June 17, 2017
 */
public interface GetPromoService
{
	//~ Methods ----------------------------------
	/**
	 * Retrieve Promo
	 *
	 * @return  promo list
	 */
	Map<String, Promo> getPromo();
}
