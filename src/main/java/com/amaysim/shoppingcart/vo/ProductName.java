package com.amaysim.shoppingcart.vo;

/**
 * Enum for getting the code of an ordered product name
 *
 * @version  1.00, June 17, 2017 Herje
 */
public enum ProductName
{
	//~ Enum constants ---------------------------
	_1gb("1 GB Data-pack"), ult_large("Unlimited 5GB"), ult_medium("Unlimited 2GB"),

	ult_small("Unlimited 1GB");
	//~ Instance fields --------------------------
	/**  */
	private String text;
	//~ Constructors -----------------------------
	/**
	 * Creates a new ProductName object.
	 *
	 * @param  text
	 */
	ProductName(String text)
	{
		this.text = text;
	}
	//~ Methods ----------------------------------
	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 */
	public String getText()
	{
		return this.text;
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @param   text
	 * @return
	 */
	public static ProductName fromString(String text)
	{
		for (ProductName b : ProductName.values())
		{
			if (b.text.equalsIgnoreCase(text))
			{
				return b;
			}
		}

		return null;
	}
}
