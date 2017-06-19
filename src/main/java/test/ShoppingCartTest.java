package test;

import com.amaysim.shoppingcart.services.GetProductService;
import com.amaysim.shoppingcart.services.GetPromoService;
import com.amaysim.shoppingcart.services.GetSpecialOfferService;
import com.amaysim.shoppingcart.services.ProcessShoppingCart;
import com.amaysim.shoppingcart.services.impl.GetProductServiceImpl;
import com.amaysim.shoppingcart.services.impl.GetPromoServiceImpl;
import com.amaysim.shoppingcart.services.impl.GetSpecialOfferServiceImpl;
import com.amaysim.shoppingcart.services.impl.ProcessShoppingCartImpl;
import com.amaysim.shoppingcart.vo.Product;
import com.amaysim.shoppingcart.vo.Promo;
import com.amaysim.shoppingcart.vo.ShoppingCart;
import com.amaysim.shoppingcart.vo.SpecialOffer;

import junit.framework.Assert;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.HashMap;
import java.util.Map;


/**
 * JUNIT Test and Runner for Amaysim Shopping Cart
 *
 * @version  1.00 June 17, 2017 Herje
 */
public class ShoppingCartTest
{
	//~ Methods ----------------------------------
	/** Test if product list can be fetch */
	@Test
	public void testGetProduct()
	{
		GetProductService fetchProduct = new GetProductServiceImpl();
		Map<String, Product> fetchMap = fetchProduct.getProduct();
		Assert.assertNotNull(fetchMap);
	}
	
	/** Test if special offer list can be fetch */
	@Test
	public void testGetSpecialOffer()
	{
		GetSpecialOfferService specialOffer = new GetSpecialOfferServiceImpl();
		Map<String, SpecialOffer> fetchMap = specialOffer.getSpecialOffer();
		Assert.assertNotNull(fetchMap);
	}
	
	/** Test if promo list can be fetch */
	@Test
	public void testGetPromo()
	{
		GetPromoService fetchPromo = new GetPromoServiceImpl();
		Map<String, Promo> promoMap = fetchPromo.getPromo();
		Assert.assertNotNull(promoMap);
	}
	
	/** Test a single product if can be process */
	@Test
	public void testShoppingCartOne()
	{
		ProcessShoppingCart process = new ProcessShoppingCartImpl();
		ShoppingCart shopCart = new ShoppingCart();

		shopCart.setProductCode("Unlimited 1GB");
		shopCart.setQuantity(2);

		Map<String, ShoppingCart> shoppingCartMap = new HashMap<String, ShoppingCart>();
		Map<String, ShoppingCart> shoppingCartFinal = new HashMap<String, ShoppingCart>();
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);
		shoppingCartFinal = process.getShopCart(shoppingCartMap);
		BigDecimal expected = new BigDecimal("49.80");
		BigDecimal total = BigDecimal.ZERO;

		for (Map.Entry<String, ShoppingCart> checkCart : shoppingCartFinal.entrySet())
		{
			ShoppingCart shopCart2 = checkCart.getValue();
			total = total.add(shopCart2.getPrice());
		}

		Assert.assertTrue(total.compareTo(expected) == 0);
	}
	
	/** Test multiple product */
	@Test
	public void testShoppingCartMultiple()
	{
		ProcessShoppingCart process = new ProcessShoppingCartImpl();
		Map<String, ShoppingCart> shoppingCartMap = new HashMap<String, ShoppingCart>();
		Map<String, ShoppingCart> shoppingCartFinal = new HashMap<String, ShoppingCart>();

		ShoppingCart shopCart = new ShoppingCart();

		shopCart.setProductCode("Unlimited 1GB");
		shopCart.setQuantity(5);
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);
		shopCart = new ShoppingCart();
		shopCart.setProductCode("Unlimited 5GB");
		shopCart.setQuantity(2);
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);
		shoppingCartFinal = process.getShopCart(shoppingCartMap);
		BigDecimal expected = new BigDecimal("214.30");
		BigDecimal total = BigDecimal.ZERO;
		for (Map.Entry<String, ShoppingCart> checkCart : shoppingCartFinal.entrySet())
		{
			ShoppingCart shopCart2 = checkCart.getValue();
			total = total.add(shopCart2.getPrice());
		}

		Assert.assertTrue(total.compareTo(expected) == 0);
	}
	
	/** Test unlimited 1 GB using a special offer */
	@Test
	public void testShoppingCartWithSpecialOfferOne()
	{
		ProcessShoppingCart process = new ProcessShoppingCartImpl();
		Map<String, ShoppingCart> shoppingCartMap = new HashMap<String, ShoppingCart>();
		Map<String, ShoppingCart> shoppingCartFinal = new HashMap<String, ShoppingCart>();
		ShoppingCart shopCart = new ShoppingCart();
		shopCart.setProductCode("Unlimited 1GB");
		shopCart.setQuantity(3);
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);
		shoppingCartFinal = process.getShopCart(shoppingCartMap);
		BigDecimal expected = new BigDecimal("49.80");
		BigDecimal total = BigDecimal.ZERO;
		for (Map.Entry<String, ShoppingCart> checkCart : shoppingCartFinal.entrySet())
		{
			ShoppingCart shopCart2 = checkCart.getValue();
			total = total.add(shopCart2.getPrice());
		}

		Assert.assertTrue(total.compareTo(expected) == 0);
	}
	
	/** Test unlimited 5 gb using a special offer */
	@Test
	public void testShoppingCartWithSpecialOfferTwo()
	{
		ProcessShoppingCart process = new ProcessShoppingCartImpl();
		Map<String, ShoppingCart> shoppingCartMap = new HashMap<String, ShoppingCart>();
		Map<String, ShoppingCart> shoppingCartFinal = new HashMap<String, ShoppingCart>();
		ShoppingCart shopCart = new ShoppingCart();
		shopCart.setProductCode("Unlimited 5GB");
		shopCart.setQuantity(4);
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);
		shoppingCartFinal = process.getShopCart(shoppingCartMap);
		BigDecimal expected = new BigDecimal("159.60");
		BigDecimal total = BigDecimal.ZERO;
		for (Map.Entry<String, ShoppingCart> checkCart : shoppingCartFinal.entrySet())
		{
			ShoppingCart shopCart2 = checkCart.getValue();
			total = total.add(shopCart2.getPrice());
		}

		Assert.assertTrue(total.compareTo(expected) == 0);
	}
	
	/** Run Scenario one as instructed */
	@Test
	public void testShoppingCartScenarioOne()
	{
		ProcessShoppingCart process = new ProcessShoppingCartImpl();
		Map<String, ShoppingCart> shoppingCartMap = new HashMap<String, ShoppingCart>();
		Map<String, ShoppingCart> shoppingCartFinal = new HashMap<String, ShoppingCart>();

		ShoppingCart shopCart = new ShoppingCart();

		shopCart.setProductCode("Unlimited 1GB");
		shopCart.setQuantity(3);
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);
		shopCart = new ShoppingCart();
		shopCart.setProductCode("Unlimited 5GB");
		shopCart.setQuantity(1);
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);
		shoppingCartFinal = process.getShopCart(shoppingCartMap);
		BigDecimal expected = new BigDecimal("94.70");
		BigDecimal total = BigDecimal.ZERO;
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, ShoppingCart> checkCart : shoppingCartFinal.entrySet())
		{
			ShoppingCart shopCart2 = checkCart.getValue();
			total = total.add(shopCart2.getPrice());
			sb.append(shopCart2.getQuantity() + " ");
			sb.append(shopCart2.getProductCode() + ", ");
		}
		System.out.println("SCENARIO 1: " + sb.toString() + " " + total);
		Assert.assertTrue(total.compareTo(expected) == 0);
	}
	
	/** Run scenario two as instructed */
	@Test
	public void testShoppingCartScenarioTwo()
	{
		ProcessShoppingCart process = new ProcessShoppingCartImpl();
		Map<String, ShoppingCart> shoppingCartMap = new HashMap<String, ShoppingCart>();
		Map<String, ShoppingCart> shoppingCartFinal = new HashMap<String, ShoppingCart>();

		ShoppingCart shopCart = new ShoppingCart();

		shopCart.setProductCode("Unlimited 1GB");
		shopCart.setQuantity(2);
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);
		shopCart = new ShoppingCart();
		shopCart.setProductCode("Unlimited 5GB");
		shopCart.setQuantity(4);
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);
		shoppingCartFinal = process.getShopCart(shoppingCartMap);
		BigDecimal expected = new BigDecimal("209.40");
		BigDecimal total = BigDecimal.ZERO;
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, ShoppingCart> checkCart : shoppingCartFinal.entrySet())
		{
			ShoppingCart shopCart2 = checkCart.getValue();
			total = total.add(shopCart2.getPrice());
			sb.append(shopCart2.getQuantity() + " ");
			sb.append(shopCart2.getProductCode() + ", ");
		}
		System.out.println("SCENARIO 2: " + sb.toString() + " " + total);
		Assert.assertTrue(total.compareTo(expected) == 0);
	}
	
	/** Run scenario three as instructed */
	@Test
	public void testShoppingCartScenarioThree()
	{
		ProcessShoppingCart process = new ProcessShoppingCartImpl();
		Map<String, ShoppingCart> shoppingCartMap = new HashMap<String, ShoppingCart>();
		Map<String, ShoppingCart> shoppingCartFinal = new HashMap<String, ShoppingCart>();

		ShoppingCart shopCart = new ShoppingCart();

		shopCart.setProductCode("Unlimited 1GB");
		shopCart.setQuantity(1);
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);
		shopCart = new ShoppingCart();
		shopCart.setProductCode("Unlimited 2GB");
		shopCart.setQuantity(2);
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);
		shoppingCartFinal = process.getShopCart(shoppingCartMap);
		BigDecimal expected = new BigDecimal("84.70");
		BigDecimal total = BigDecimal.ZERO;
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, ShoppingCart> checkCart : shoppingCartFinal.entrySet())
		{
			ShoppingCart shopCart2 = checkCart.getValue();
			if (null != shopCart2.getPrice())
			{
				total = total.add(shopCart2.getPrice());
			}
			sb.append(shopCart2.getQuantity() + " ");
			sb.append(shopCart2.getProductCode() + ", ");
		}
		System.out.println("SCENARIO 3: " + sb.toString() + " " + total);
		Assert.assertTrue(total.compareTo(expected) == 0);
	}
	
	/** Run scenario 4 as instructed */
	@Test
	public void testShoppingCartScenarioFour()
	{
		ProcessShoppingCart process = new ProcessShoppingCartImpl();
		Map<String, ShoppingCart> shoppingCartMap = new HashMap<String, ShoppingCart>();
		Map<String, ShoppingCart> shoppingCartFinal = new HashMap<String, ShoppingCart>();

		ShoppingCart shopCart = new ShoppingCart();

		shopCart.setProductCode("Unlimited 1GB");
		shopCart.setQuantity(1);
		shopCart.setPromoCode("I<3AMAYSIM");
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);
		shopCart = new ShoppingCart();
		shopCart.setProductCode("1 GB Data-pack");
		shopCart.setQuantity(1);
		shopCart.setPromoCode("I<3AMAYSIM");
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);
		shoppingCartFinal = process.getShopCart(shoppingCartMap);
		BigDecimal expected = new BigDecimal("31.32");
		BigDecimal total = BigDecimal.ZERO;
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, ShoppingCart> checkCart : shoppingCartFinal.entrySet())
		{
			ShoppingCart shopCart2 = checkCart.getValue();
			if (null != shopCart2.getPrice())
			{
				total = total.add(shopCart2.getPrice());
			}
			sb.append(shopCart2.getQuantity() + " ");
			sb.append(shopCart2.getProductCode() + ", ");
		}
		System.out.println("SCENARIO 4: " + sb.toString() + " " + total.setScale(2, RoundingMode.CEILING));
		Assert.assertTrue(total.compareTo(expected) == 0);
	}
	
	/** Run other possible scenarios - just change the values */
	@Test
	public void testShoppingCartAnythingGoes()
	{
		ProcessShoppingCart process = new ProcessShoppingCartImpl();
		Map<String, ShoppingCart> shoppingCartMap = new HashMap<String, ShoppingCart>();
		Map<String, ShoppingCart> shoppingCartFinal = new HashMap<String, ShoppingCart>();

		ShoppingCart shopCart = new ShoppingCart();

		shopCart.setProductCode("Unlimited 1GB");
		shopCart.setQuantity(3);
		shopCart.setPromoCode("I<3AMAYSIM");
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);

		shopCart = new ShoppingCart();
		shopCart.setProductCode("1 GB Data-pack");
		shopCart.setQuantity(1);
		shopCart.setPromoCode("I<3AMAYSIM");
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);

		shopCart = new ShoppingCart();
		shopCart.setProductCode("Unlimited 5GB");
		shopCart.setQuantity(5);
		shopCart.setPromoCode("I<3AMAYSIM");
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);

		shopCart = new ShoppingCart();
		shopCart.setProductCode("Unlimited 2GB");
		shopCart.setQuantity(1);
		shopCart.setPromoCode("I<3AMAYSIM");
		shoppingCartMap.put(shopCart.getProductCode(), shopCart);

		shoppingCartFinal = process.getShopCart(shoppingCartMap);
		BigDecimal expected = new BigDecimal("260.19");
		BigDecimal total = BigDecimal.ZERO;
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, ShoppingCart> checkCart : shoppingCartFinal.entrySet())
		{
			ShoppingCart shopCart2 = checkCart.getValue();
			if (null != shopCart2.getPrice())
			{
				total = total.add(shopCart2.getPrice());
			}
			sb.append(shopCart2.getQuantity() + " ");
			sb.append(shopCart2.getProductCode() + ", ");
		}
		// System.out.println("ANYTHING: " + sb.toString() + " " + total.setScale(2, RoundingMode.CEILING));
		Assert.assertTrue(total.compareTo(expected) == 0);
	}
}
