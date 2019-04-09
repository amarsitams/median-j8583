package com.rumango.median.soap.utils;

/**
 * @author lei2o
 *
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;

public class SendXmlRequestToFlexProducer
{
	private static final Logger LOGGER = Logger.getLogger(ConvertFlexResponseXmlFileToObject.class);

	public String sendXmlRequestFileToFlexProducer(String filePath,String ipAddress) throws RemoteException
	{
		try
		{
			new FlexXmlMessageToSoapXmlMessage().convertFlexXmlFileToSoapXmlFile(filePath);
			String responseMsg = sendRequestXmlFileToSoap(filePath,ipAddress);
			LOGGER.info("request message sent to flex");
			return responseMsg;
		} catch (Exception e)
		{
			e.printStackTrace();
			LOGGER.error("request message failed to be sent to flex");
			return null;
		}
	}

	public String sendXmlRequestStringToFlexProducer(String requestXml,String ipAddress) throws RemoteException
	{
		try
		{
			requestXml = new FlexXmlMessageToSoapXmlMessage().convertFlexXmlStringToSoapXmlMessage(requestXml);
			String responseXmlMessage = sendRequestXmlStringToSoap(requestXml,ipAddress);
			LOGGER.info("request message sent to flex successfully");
			return responseXmlMessage;
		} catch (Exception e)
		{
			e.printStackTrace();
			LOGGER.error("request message failed to be sent to flex");
			return null;
		}
	}

	public static String sendRequestXmlStringToSoap(String requestXml,String ipAndPort)
			throws IOException, UnsupportedOperationException, SOAPException
	{
		LOGGER.info("Sending request message sTRING to flex !!!");
		byte[] encoded = requestXml.getBytes();
		// String soapXml = new String(encoded, StandardCharsets.UTF_8);
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		java.net.URL endpoint = new URL("http://"+ipAndPort+"/FCUBSFTService/FCUBSFTService?WSDL");
		SOAPConnection connection = soapConnectionFactory.createConnection();
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(encoded));
		AttachmentPart attachment = message.createAttachmentPart();
		attachment.setContent("sm_content", "text/plain");
		attachment.setContentId("1.9f910338bf0cac0e783bfdec7e53be9237684caa8f8f4e6d@apache.org");
		message.addAttachmentPart(attachment);
		SOAPMessage response = connection.call(message, endpoint);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		response.writeTo(out);
		String strMsg = new String(out.toByteArray());
		LOGGER.info("response message sTRING got from flex successfully   !!!");
		return strMsg;
	}

	public static String sendRequestXmlFileToSoap(String filePath,String ipAndPort)
			throws IOException, UnsupportedOperationException, SOAPException
	{
		LOGGER.info("Sending request message FILE to flex !!!");
		byte[] encoded = Files.readAllBytes(Paths.get(filePath));
		// String soapXml = new String(encoded, StandardCharsets.UTF_8);
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		java.net.URL endpoint = new URL("http://"+ipAndPort+"/FCUBSFTService/FCUBSFTService?WSDL");
		SOAPConnection connection = soapConnectionFactory.createConnection();
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(encoded));
		AttachmentPart attachment = message.createAttachmentPart();
		attachment.setContent("sm_content", "text/plain");
		attachment.setContentId("1.9f910338bf0cac0e783bfdec7e53be9237684caa8f8f4e6d@apache.org");
		message.addAttachmentPart(attachment);
		SOAPMessage response = connection.call(message, endpoint);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		response.writeTo(out);
		String strMsg = new String(out.toByteArray());
		LOGGER.info("response message sTRING got from flex successfully");
		LOGGER.info("Now Sending response message file from flex to median system !!!");
		return strMsg;
	}
}
