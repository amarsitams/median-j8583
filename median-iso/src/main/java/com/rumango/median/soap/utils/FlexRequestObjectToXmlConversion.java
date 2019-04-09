package com.rumango.median.soap.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import com.ofss.fcubs.service.fcubsftservice.QUERYCONTRACTIOFSREQRequest;

/**
 * @author lei2o
 *
 */

public class FlexRequestObjectToXmlConversion {
	private static final Logger LOGGER = Logger.getLogger(FlexRequestObjectToXmlConversion.class);
	String requestFilePath = "D:\\median_xml_files\\request\\request.xml";

	public void convertFlexRequestObjectToXmlFile(QUERYCONTRACTIOFSREQRequest req) {
		try {
			File file = new File(requestFilePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(QUERYCONTRACTIOFSREQRequest.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(req, file);

		} catch (JAXBException e) {
			e.printStackTrace();
			LOGGER.error(e.toString());
		}
	}

	public String convertFlexRequestObjectToXmlString(QUERYCONTRACTIOFSREQRequest req) {
		try {
			// LOGGER.info(
			// "==>FlexRequestObjectToXmlConversion.convertFlexRequestObjectToXmlString(QUERYCONTRACTIOFSREQRequest
			// req)");

			JAXBContext jaxbContext = JAXBContext.newInstance(QUERYCONTRACTIOFSREQRequest.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// jaxbMarshaller.marshal(req, System.out);
			StringWriter stringWriter = new StringWriter();
			jaxbMarshaller.marshal(req, stringWriter);
			String xmlString = stringWriter.toString();
			// LOGGER.info("Flex Request object converted to xml sring");
			return xmlString;

		} catch (JAXBException e) {
			LOGGER.error("Flex Request failed to be converted to xml sring");
			return null;
		}
	}

	public void removeFirstLineOfFile(String fileName) throws IOException {
		// LOGGER.info("==>FlexRequestObjectToXmlConversion.removeFirstLineOfFile(String
		// fileName)");

		// System.out.println("first line removing initialized");
		RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
		// Initial write position
		long writePosition = raf.getFilePointer();
		raf.readLine();
		// Shift the next lines upwards.
		long readPosition = raf.getFilePointer();

		byte[] buff = new byte[1024];
		int n;
		while (-1 != (n = raf.read(buff))) {
			raf.seek(writePosition);
			raf.write(buff, 0, n);
			readPosition += n;
			writePosition += n;
			raf.seek(readPosition);
		}
		raf.setLength(writePosition);
		raf.close();
		// LOGGER.info("first line deletion completed");
	}
}
