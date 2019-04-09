/**
 * 
 */
package com.rumango.median.soap.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lei2o
 *
 */
public class XmlTagInsertion
{
	private static final Logger LOGGER = LoggerFactory.getLogger(XmlTagInsertion.class);

	public String insertTagIntoExistingXMLString(String originalString, String stringToBeInserted, int index)
	{
		LOGGER.info("========>inside XmlTagInsertion.insertTagIntoExistingXMLString()<================");
		String newString = new String();
		for (int i = 0; i < originalString.length(); i++)
		{
			newString += originalString.charAt(i);
			if (i == index)
			{
				newString += stringToBeInserted;
			}
		}
		return newString;
	}
}
