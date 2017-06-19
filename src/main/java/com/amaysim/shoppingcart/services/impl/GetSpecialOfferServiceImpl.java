package com.amaysim.shoppingcart.services.impl;

import com.amaysim.shoppingcart.services.GetSpecialOfferService;
import com.amaysim.shoppingcart.vo.SpecialOffer;

import java.io.File;
import java.io.FileNotFoundException;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Retrieve special offers
 *
 * @version  1.00, June 17, 2017 Herje
 */
public class GetSpecialOfferServiceImpl implements GetSpecialOfferService
{
	//~ Methods ----------------------------------
	/**
	 * Retrieve special offers
	 *
	 * @return  Map<String, SpecialOffer> - returns a map containing all the special offer
	 */
	@Override
	public Map<String, SpecialOffer> getSpecialOffer()
	{
		Map<String, SpecialOffer> offerMap = new HashMap<String, SpecialOffer>();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("specialOffer.txt").getFile());

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

					String[] specialOfferArr = readLine.split(";");

					if ((specialOfferArr != null) && (specialOfferArr.length > 0) && (specialOfferArr.length == 6))
					{
						String prodCode = specialOfferArr[0];
						String buyQuantity = specialOfferArr[1];
						String payQuantity = specialOfferArr[2];
						String price = specialOfferArr[3];
						String bundle = specialOfferArr[4];
						String bulk = specialOfferArr[5];

						SpecialOffer specialOffer = new SpecialOffer();
						specialOffer.setProductCode(prodCode);
						if (buyQuantity.isEmpty())
						{
							buyQuantity = "0";
						}

						if (payQuantity.isEmpty())
						{
							payQuantity = "0";
						}

						if (price.isEmpty())
						{
							price = "0";
						}
						specialOffer.setBuyQuantity(Integer.valueOf(buyQuantity));
						specialOffer.setPayQuantity(Integer.valueOf(payQuantity));
						specialOffer.setPrice(new BigDecimal(price));
						specialOffer.setBulk(bulk);
						specialOffer.setBundleProduct(bundle);

						offerMap.put(specialOffer.getProductCode(), specialOffer);
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
			} // end try-catch-finally
		} // end if

		return offerMap;
	}
}
