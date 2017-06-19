package com.amaysim.shoppingcart.services;

import com.amaysim.shoppingcart.vo.ShoppingCart;

import java.util.Map;


/**
 * Interface for the main process
 *
 * @version  1.00, June 17, 2017 Herje
 */
public interface ProcessShoppingCart
{
	//~ Methods ----------------------------------
	/**
	 * Process the shopping cart of the customer
	 *
	 * @param   shoppingCartMap  - contains the product the user wanst to buy
	 * @return  Map<String, ShoppingCart> - returns a map containing the processed product, quantity, price
	 */
	Map<String, ShoppingCart> getShopCart(Map<String, ShoppingCart> shoppingCartMap);
}
