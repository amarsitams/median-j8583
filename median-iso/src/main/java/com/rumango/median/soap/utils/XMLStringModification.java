package com.rumango.median.soap.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.log4j.Logger;

/**
 * @author lei2o
 *
 */
public class XMLStringModification
{
	private static final Logger LOGGER = Logger.getLogger(XMLStringModification.class);

	public String deleteFirstLine(String xmlString) throws IOException
	{
		//LOGGER.info("============>XMLStringModification.deleteFirstLine(String xmlString) <======= ");

		StringReader stringReader = null;
		BufferedReader bufferedReader = null;
		try
		{
			stringReader = new StringReader(xmlString);
			bufferedReader = new BufferedReader(stringReader);
			String mainline = "";
			String line;
			if ((bufferedReader.readLine()) != null)
			{
				// System.err.println("do nothing");
				// do nothing
			}
			while ((line = bufferedReader.readLine()) != null)
			{
				// System.err.println("Line removing");

				mainline += line + "\r\n";
			}
			return mainline;
		} catch (Exception e)
		{
			e.printStackTrace();
			LOGGER.error("============>XMLStringModification" + "Xml string modification deleteFirstLine() failed");
			return null;
		} finally
		{
			if (bufferedReader != null)
			{
				bufferedReader.close();
			}
			if (stringReader != null)
			{
				stringReader.close();
			}
		}
	}
}
