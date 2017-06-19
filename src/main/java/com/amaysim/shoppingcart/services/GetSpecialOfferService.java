package com.amaysim.shoppingcart.services;

import com.amaysim.shoppingcart.vo.SpecialOffer;

import java.util.Map;


/**
 * Retrieve Special offers
 *
 * @version  1.00, June 17, 2018
 */
public interface GetSpecialOfferService
{
	//~ Methods ----------------------------------
	/**
	 * Retrieve Special offers
	 *
	 * @return  list of special offers
	 */
	Map<String, SpecialOffer> getSpecialOffer();
}
