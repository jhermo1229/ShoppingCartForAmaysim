package com.amaysim.shoppingcart.vo;

import java.math.BigDecimal;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public class Promo
{
	//~ Instance fields --------------------------
	/**  */
	BigDecimal discount;

	/**  */
	String PromoCode;
	//~ Methods ----------------------------------
	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public String getPromoCode()
	{
		return PromoCode;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @param  promoCode
	 */
	public void setPromoCode(String promoCode)
	{
		PromoCode = promoCode;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public BigDecimal getDiscount()
	{
		return discount;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @param  bigDecimal
	 */
	public void setDiscount(BigDecimal bigDecimal)
	{
		this.discount = bigDecimal;
	}
}
