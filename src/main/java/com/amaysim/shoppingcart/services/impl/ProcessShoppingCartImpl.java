package com.amaysim.shoppingcart.services.impl;

import com.amaysim.shoppingcart.services.GetProductService;
import com.amaysim.shoppingcart.services.GetPromoService;
import com.amaysim.shoppingcart.services.GetSpecialOfferService;
import com.amaysim.shoppingcart.services.ProcessShoppingCart;
import com.amaysim.shoppingcart.vo.Product;
import com.amaysim.shoppingcart.vo.ProductName;
import com.amaysim.shoppingcart.vo.Promo;
import com.amaysim.shoppingcart.vo.ShoppingCart;
import com.amaysim.shoppingcart.vo.SpecialOffer;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * Process shopping cart of customers
 *
 * @version  1.00, June 17, 2017
 */
public class ProcessShoppingCartImpl implements ProcessShoppingCart
{
	//~ Static fields/initializers ---------------
	/**  */
	private static final String PERCENT = "100";
	//~ Instance fields --------------------------
	/**  */
	GetProductService getProductService = new GetProductServiceImpl();

	/**  */
	Map<String, SpecialOffer> offerMap = new HashMap<String, SpecialOffer>();

	/**  */
	GetSpecialOfferService offerService = new GetSpecialOfferServiceImpl();

	/**  */
	Map<String, Product> productMap = new HashMap<String, Product>();

	/**  */
	Map<String, Promo> promoMap = new HashMap<String, Promo>();

	/**  */
	GetPromoService promoService = new GetPromoServiceImpl();
	//~ Methods ----------------------------------
	/** @see  com.amaysim.shoppingcart.services.ProcessShoppingCart#getShopCart(java.util.Map) */
	public Map<String, ShoppingCart> getShopCart(Map<String, ShoppingCart> shoppingCartMap)
	{
		productMap = getProductService.getProduct();
		offerMap = offerService.getSpecialOffer();
		promoMap = promoService.getPromo();
		Map<String, ShoppingCart> shopCartFinal = new HashMap<String, ShoppingCart>();

		for (Map.Entry<String, ShoppingCart> shoppingCart : shoppingCartMap.entrySet())
		{
			Map<String, ShoppingCart> shopCart = new HashMap<String, ShoppingCart>();

			// get the code from the product name
			String productName = shoppingCart.getKey();
			ProductName productCode = ProductName.fromString(productName);

			// Search the product from the product mapping list
			for (Map.Entry<String, Product> product : productMap.entrySet())
			{
				// if matches, do the process
				if (productCode.toString().equals(product.getKey()))
				{
					// process the shopping cart
					shopCart = processShoppingCart(shoppingCart, product);

					break;
				}
			}

			if (!shopCartFinal.isEmpty())
			{
				for (Map.Entry<String, ShoppingCart> shopCartDuplicates : shopCartFinal.entrySet())
				{
					for (Map.Entry<String, ShoppingCart> shopCartCurrent : shopCart.entrySet())
					{
						if ((shopCartDuplicates.getKey().equals(shopCartCurrent.getKey()))
							&& (shopCartCurrent.getValue().getQuantity() != 0))
						{
							int addQuantity = shopCartCurrent.getValue().getQuantity()
								+ shopCartDuplicates.getValue().getQuantity();
							shopCartCurrent.getValue().setQuantity(addQuantity);
						}
					}
				}
			}

			// add to the final shopping cart list
			for (Map.Entry<String, ShoppingCart> getShopCart : shopCart.entrySet())
			{
				shopCartFinal.put(getShopCart.getKey(), getShopCart.getValue());
			}
		} // end for

		return shopCartFinal;
	}
	
	/**
	 * Process the shopping cart and checks for promotions
	 *
	 * @param   shoppingCart  - single product from the shopping cart of the user
	 * @param   product       - product from the main list (product equals to the product chosen by the user
	 * @return  Map<String, ShoppingCart> - Returns a single or with bundle product
	 */
	private Map<String, ShoppingCart> processShoppingCart(Entry<String, ShoppingCart> shoppingCart,
		Entry<String, Product> product)
	{
		boolean isWithSpecialOffer = false;
		Map<String, ShoppingCart> shopCartMapFinal = new HashMap<String, ShoppingCart>();
		ShoppingCart shopCartFinal = new ShoppingCart();

		ShoppingCart shopCart = shoppingCart.getValue();
		Product productFinal = product.getValue();
		int quantity = shopCart.getQuantity();
		ProductName productCode = ProductName.fromString(shoppingCart.getKey());

		for (Map.Entry<String, SpecialOffer> specialOffer : offerMap.entrySet())
		{
			SpecialOffer offer = specialOffer.getValue();

			// Is the product and quantity qualified
			if (specialOffer.getKey().equals(productCode.toString()) && (quantity >= offer.getBuyQuantity()))
			{
				isWithSpecialOffer = true;

				// set the initial shopping cart values
				shopCartFinal.setProductCode(shoppingCart.getKey());
				shopCartFinal.setQuantity(quantity);
				shopCartFinal.setPrice(productFinal.getPrice().multiply(new BigDecimal(quantity)));

				if ((offer.getPayQuantity() != 0) && (quantity == offer.getBuyQuantity()))
				{
					shopCartFinal.setPrice(productFinal.getPrice().multiply(
							new BigDecimal(offer.getPayQuantity())));
				}

				if (offer.getBulk().equals("Y"))
				{
					shopCartFinal.setPrice(offer.getPrice().multiply(new BigDecimal(quantity)));
				}

				if ((offer.getBundleProduct() != null) && !offer.getBundleProduct().isEmpty())
				{
					ShoppingCart addedBundle = new ShoppingCart();
					ProductName bundleName = ProductName.valueOf(offer.getBundleProduct());
					addedBundle.setProductCode(bundleName.getText());
					addedBundle.setQuantity(quantity);
					shopCartMapFinal.put(addedBundle.getProductCode(), addedBundle);
				}

				break;
			}
		} // end for

		if (!isWithSpecialOffer)
		{
			shopCartFinal.setProductCode(shoppingCart.getKey());
			shopCartFinal.setQuantity(quantity);
			shopCartFinal.setPrice(productFinal.getPrice().multiply(new BigDecimal(quantity)));
		}

		// check if promo code is present
		if (null != shopCart.getPromoCode())
		{
			for (Map.Entry<String, Promo> promoOffer : promoMap.entrySet())
			{
				Promo promo = promoOffer.getValue();
				if (shopCart.getPromoCode().equals(promo.getPromoCode()))
				{
					BigDecimal amountDiscount = shopCartFinal.getPrice().multiply(promo.getDiscount().divide(
								new BigDecimal(PERCENT)));

					BigDecimal finalPrice = shopCartFinal.getPrice().subtract(amountDiscount);
					shopCartFinal.setPrice(finalPrice);
				}
			}
		}

		shopCartMapFinal.put(shopCartFinal.getProductCode(), shopCartFinal);

		return shopCartMapFinal;
	}
}
