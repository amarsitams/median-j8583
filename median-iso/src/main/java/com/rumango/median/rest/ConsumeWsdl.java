package com.rumango.median.rest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Component
public class ConsumeWsdl {

	public String medianWsdlClient(String wsUrl, String soapAction, String xmlInput)
			throws MalformedURLException, IOException {
		// String wsURL = "http://192.168.0.94:7001/FCUBSFTService/FCUBSFTService?WSDL";
		String responseString = "";
		String outputString = "";
		URL url = new URL(wsUrl);
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) connection;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		byte[] buffer = new byte[xmlInput.length()];
		buffer = xmlInput.getBytes();
		bout.write(buffer);
		byte[] b = bout.toByteArray();
//		String SOAPAction = "http://192.168.0.92:8989/medianSoapWsImpl?wsdl";
		// Set the appropriate HTTP parameters.
		httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setRequestProperty("SOAPAction", soapAction);
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream out = httpConn.getOutputStream();
		// Write the content of the request to the outputstream of the HTTP Connection.
		out.write(b);
		out.close();
		// Ready with sending the request.

		// Read the response.
		InputStreamReader isr = null;
		if (httpConn.getResponseCode() == 200) {
			isr = new InputStreamReader(httpConn.getInputStream());
		} else {
			isr = new InputStreamReader(httpConn.getErrorStream());
		}
		BufferedReader in = new BufferedReader(isr);
		// Write the SOAP message response to a String.
		while ((responseString = in.readLine()) != null) {
			outputString = outputString + responseString;
		}
		System.err.println("response " + outputString);
		return outputString;
	}

	public String medianWsdlClient() throws MalformedURLException, IOException {
		// Code to make a webservice HTTP request
		String responseString = "";
		String outputString = "";
		String wsURL = "http://192.168.0.94:7001/FCUBSFTService/FCUBSFTService?WSDL";
		URL url = new URL(wsURL);
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) connection;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		String xmlInput = "<?xml version=\"1.0\"?>\r\n" + "\r\n"
				+ "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + "\r\n"
				+ "<env:Header/>\r\n" + "\r\n" + "\r\n" + "<env:Body>\r\n" + "\r\n" + "\r\n"
				+ "<QUERYCONTRACT_IOFS_REQ xmlns=\"http://fcubs.ofss.com/service/FCUBSFTService\">\r\n" + "\r\n"
				+ "\r\n" + "<FCUBS_HEADER>\r\n" + "\r\n" + "<SOURCE>FCAT</SOURCE>\r\n" + "\r\n"
				+ "<UBSCOMP>FCUBS</UBSCOMP>\r\n" + "\r\n" + "<MSGID>32353324433441</MSGID>\r\n" + "\r\n"
				+ "<CORRELID>32353324433441</CORRELID>\r\n" + "\r\n" + "<USERID>RAJESH01</USERID>\r\n" + "\r\n"
				+ "<BRANCH>101</BRANCH>\r\n" + "\r\n" + "<SERVICE>FCUBSFTService</SERVICE>\r\n" + "\r\n"
				+ "<OPERATION>QueryContract</OPERATION>\r\n" + "\r\n" + "<FUNCTIONID>FTDTRONL</FUNCTIONID>\r\n" + "\r\n"
				+ "</FCUBS_HEADER>\r\n" + "\r\n" + "\r\n" + "-<FCUBS_BODY>\r\n" + "\r\n" + "\r\n"
				+ "-<Contract-Details-IO>\r\n" + "\r\n" + "<CONTREFNO>101FTO1142460001</CONTREFNO>\r\n" + "\r\n"
				+ "</Contract-Details-IO>\r\n" + "\r\n" + "</FCUBS_BODY>\r\n" + "\r\n" + "</QUERYCONTRACT_IOFS_REQ>\r\n"
				+ "\r\n" + "</env:Body>\r\n" + "\r\n" + "</env:Envelope>";

		byte[] buffer = new byte[xmlInput.length()];
		buffer = xmlInput.getBytes();
		bout.write(buffer);
		byte[] b = bout.toByteArray();
		String SOAPAction = "http://192.168.0.92:8989/medianSoapWsImpl?wsdl";
		// Set the appropriate HTTP parameters.
		httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setRequestProperty("SOAPAction", SOAPAction);
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream out = httpConn.getOutputStream();
		// Write the content of the request to the outputstream of the HTTP Connection.
		out.write(b);
		out.close();
		// Ready with sending the request.

		// Read the response.
		InputStreamReader isr = null;
		if (httpConn.getResponseCode() == 200) {
			isr = new InputStreamReader(httpConn.getInputStream());
		} else {
			isr = new InputStreamReader(httpConn.getErrorStream());
		}

		BufferedReader in = new BufferedReader(isr);

		// Write the SOAP message response to a String.
		while ((responseString = in.readLine()) != null) {
			outputString = outputString + responseString;
		}

		System.err.println("response " + outputString);
		return outputString;
	}

	public void prettyFormatXml(String inputXml) {
		try {
			// Pretty XML
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new ByteArrayInputStream(inputXml.getBytes("utf-8"))));

			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nodeList = (NodeList) xPath.evaluate("//text()[normalize-space()='']", document,
					XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); ++i) {
				Node node = nodeList.item(i);
				node.getParentNode().removeChild(node);
			}
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			StringWriter stringWriter = new StringWriter();
			StreamResult streamResult = new StreamResult(stringWriter);
			transformer.transform(new DOMSource(document), streamResult);
			System.err.println("Format " + stringWriter.toString());
		} catch (Exception e) {
		}
	}

}
