package com.rumango.median.soap.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author lei2o
 *
 */
public class XMLFormatter
{

	private static final Logger LOGGER = Logger.getLogger(XMLFormatter.class);

	public XMLFormatter()
	{
	}

	public String format(String unformattedXml)
	{
		// LOGGER.info("=======>XMLFormatter.format(String unformattedXml)<==========");
		try
		{
			final Document document = parseXmlFile(unformattedXml);
			OutputFormat format = new OutputFormat(document);
			format.setLineWidth(65);
			format.setIndenting(true);
			format.setIndent(2);
			Writer out = new StringWriter();
			XMLSerializer serializer = new XMLSerializer(out, format);
			serializer.serialize(document);
			return out.toString();

		} catch (IOException e)
		{
			e.printStackTrace();
			LOGGER.error("=======>XMLFormatter ------ error in formatting xml string");
			return null;
		}
	}

	private Document parseXmlFile(String in)
	{
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(in));
			return db.parse(is);
		} catch (ParserConfigurationException e)
		{
			throw new RuntimeException(e);
		} catch (SAXException e)
		{
			throw new RuntimeException(e);
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}