package com.rumango.median.soap.utils;

/**
 * @author lei2o
 *
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.log4j.Logger;

public class WriteResponseXMLToFile
{
	private static final Logger LOGGER = Logger.getLogger(WriteResponseXMLToFile.class);

	public String writeXmlToFile(String argument, int id)
	{
		LOGGER.info("==============>WriteResponseXMLToFile.writeXmlToFile()<==============");
		BufferedWriter bufferedWriter = null;
		try
		{
			String responseFilePath = "D:\\median_xml_files\\response\\response.xml";
			File myFile = new File(responseFilePath);
			if (!myFile.exists())
			{
				myFile.createNewFile();
			}
			Writer writer = new FileWriter(myFile);
			bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(argument);
			LOGGER.info("Response xml written to file system");
			return responseFilePath;
		} catch (IOException e)
		{
			e.printStackTrace();
			LOGGER.error("Response xml failed to be written to file system");
			return null;
		} finally
		{
			try
			{
				if (bufferedWriter != null)
					bufferedWriter.close();
			} catch (Exception ex)
			{
				ex.printStackTrace();
				LOGGER.error("Exception in closing costly resources i.e bufferedWriter");
			}
		}
	}
}
