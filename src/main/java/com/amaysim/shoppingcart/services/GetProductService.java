package com.amaysim.shoppingcart.services;

import com.amaysim.shoppingcart.vo.Product;

import java.util.Map;


/**
 * Retrieve products
 *
 * @version  1.00, June 17, 2017
 */
public interface GetProductService
{
	//~ Methods ----------------------------------
	/**
	 * Retrieve products
	 *
	 * @return  products
	 */
	Map<String, Product> getProduct();
}
