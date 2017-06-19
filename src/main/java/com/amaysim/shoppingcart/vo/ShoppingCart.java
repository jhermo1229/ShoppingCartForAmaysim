package com.amaysim.shoppingcart.vo;

import java.math.BigDecimal;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public class ShoppingCart
{
	//~ Instance fields --------------------------
	/**  */
	BigDecimal price;

	/**  */
	String productCode;

	/**  */
	String promoCode;

	/**  */
	int quantity;
	//~ Methods ----------------------------------
	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public String getProductCode()
	{
		return productCode;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @param  productCode
	 */
	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public int getQuantity()
	{
		return quantity;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @param  quantity
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public BigDecimal getPrice()
	{
		return price;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @param  totalPrice
	 */
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public String getPromoCode()
	{
		return promoCode;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @param  promoCode
	 */
	public void setPromoCode(String promoCode)
	{
		this.promoCode = promoCode;
	}
}
