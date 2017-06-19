package com.amaysim.shoppingcart.vo;

import java.math.BigDecimal;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public class SpecialOffer
{
	//~ Instance fields --------------------------
	/**  */
	String bulk;

	/**  */
	String bundleProduct;

	/**  */
	int buyQuantity;

	/**  */
	int payQuantity;

	/**  */
	BigDecimal price;

	/**  */
	String productCode;
	//~ Methods ----------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public String getBulk()
	{
		return bulk;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @param  bulk
	 */
	public void setBulk(String bulk)
	{
		this.bulk = bulk;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public int getBuyQuantity()
	{
		return buyQuantity;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @param  buyQuantity
	 */
	public void setBuyQuantity(int buyQuantity)
	{
		this.buyQuantity = buyQuantity;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public int getPayQuantity()
	{
		return payQuantity;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @param  payQuantity
	 */
	public void setPayQuantity(int payQuantity)
	{
		this.payQuantity = payQuantity;
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
	 * @param  price
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
	public String getBundleProduct()
	{
		return bundleProduct;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @param  bundleProduct
	 */
	public void setBundleProduct(String bundleProduct)
	{
		this.bundleProduct = bundleProduct;
	}
}
