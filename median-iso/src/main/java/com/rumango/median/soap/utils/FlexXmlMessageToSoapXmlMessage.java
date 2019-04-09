/**
 * 
 */
package com.rumango.median.soap.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * @author lei2o
 *
 */
public class FlexXmlMessageToSoapXmlMessage
{
	private static final Logger LOGGER = Logger.getLogger(FlexXmlMessageToSoapXmlMessage.class);

	public String convertFlexXmlStringToSoapXmlMessage(String requestXml)
	{
		LOGGER.info(
				"================>FlexXmlMessageToSoapXmlMessage.convertFlexXmlStringToSoapXmlMessage <==================");
		String fullXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:fcub=\"http://fcubs.ofss.com/service/FCUBSFTService\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "";
		fullXml += requestXml;
		fullXml += " </soapenv:Body>\r\n" + "</soapenv:Envelope>";
		fullXml = fullXml.replaceAll("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
		return fullXml;
	}

	public void convertFlexXmlFileToSoapXmlFile(String xmlFilePath) throws IOException
	{
		// pass the path to the file as a parameter
		FileReader fr = new FileReader(xmlFilePath);
		String fullXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:fcub=\"http://fcubs.ofss.com/service/FCUBSFTService\">\r\n"
				+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "";

		int i;
		while ((i = fr.read()) != -1)
		{
			fullXml += (char) i;
		}
		fullXml += " </soapenv:Body>\r\n" + "</soapenv:Envelope>";
		fullXml.replaceAll("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");

		writeUsingFileWriter(fullXml, xmlFilePath);
		if (fr != null)
			fr.close();
	}

	private void writeUsingFileWriter(String data, String xmlFilePath) throws IOException
	{
		File file = new File(xmlFilePath);
		if (file.exists())
		{
			file.createNewFile();
		}
		FileWriter fr = null;

		try
		{
			fr = new FileWriter(file);
			fr.write(data);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				fr.close();
			} catch (IOException e)
			{
				e.printStackTrace();
				LOGGER.error(e.toString());
			}
		}
	}
	
}
