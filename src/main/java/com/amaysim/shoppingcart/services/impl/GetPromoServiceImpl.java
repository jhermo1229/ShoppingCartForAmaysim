package com.amaysim.shoppingcart.services.impl;

import com.amaysim.shoppingcart.services.GetPromoService;
import com.amaysim.shoppingcart.vo.Promo;

import java.io.File;
import java.io.FileNotFoundException;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Retrieves Promo
 *
 * @version  1.00, June 17, 2017
 */
public class GetPromoServiceImpl implements GetPromoService
{
	//~ Methods ----------------------------------
	/** @see  com.amaysim.shoppingcart.services.GetPromoService#getPromo() */
	@Override
	public Map<String, Promo> getPromo()
	{
		Map<String, Promo> promoMap = new HashMap<String, Promo>();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("promoCode.txt").getFile());

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

					String[] promoArr = readLine.split(";");

					if ((promoArr != null) && (promoArr.length > 0) && (promoArr.length == 2))
					{
						String promoCode = promoArr[0];
						String discount = promoArr[1];

						Promo promo = new Promo();
						promo.setPromoCode(promoCode);
						promo.setDiscount(new BigDecimal(discount));

						promoMap.put(promo.getPromoCode(), promo);
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
		}

		return promoMap;
	}
}
