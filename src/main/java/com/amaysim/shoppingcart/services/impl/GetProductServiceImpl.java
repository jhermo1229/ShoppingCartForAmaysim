package com.amaysim.shoppingcart.services.impl;

import com.amaysim.shoppingcart.services.GetProductService;
import com.amaysim.shoppingcart.vo.Product;

import java.io.File;
import java.io.FileNotFoundException;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Retrieves products
 *
 * @version  1.00, June 17, 2017 Herje
 */
public class GetProductServiceImpl implements GetProductService
{
	//~ Methods ----------------------------------
	/** @see  com.amaysim.shoppingcart.services.GetProductService#getProduct() */
	@Override
	public Map<String, Product> getProduct()
	{
		Map<String, Product> productMap = new HashMap<String, Product>();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("product.txt").getFile());

		if ((file != null) && file.exists())
		{
			Scanner scanner = null;
			try
			{
				scanner = new Scanner(file);
				// get first line
				scanner.nextLine();
				while (scanner.hasNextLine())
				{
					String readLine = scanner.nextLine();

					String[] productArr = readLine.split(";");

					if ((productArr != null) && (productArr.length > 0) && (productArr.length == 2))
					{
						String prodCode = productArr[0];
						String price = productArr[1];

						Product product = new Product();
						product.setPrice(new BigDecimal(price));

						product.setProductCode(prodCode);

						productMap.put(product.getProductCode(), product);
					}
				}
			}
			catch (FileNotFoundException fnf)
			{
				fnf.printStackTrace();
			}
			finally
			{
				if (scanner != null)
				{
					scanner.close();
					scanner = null;
				}
			}
		} // end if

		return productMap;
	}
}
