package com.rumango.median.soap.utils;

/**
 * @author lei2o
 *
 */

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.ofss.fcubs.service.fcubsftservice.QUERYCONTRACTIOFSRESResponse;

public class ConvertFlexResponseXmlStringToObject {
	private static final Logger LOGGER = Logger.getLogger(ConvertFlexResponseXmlStringToObject.class);

	public QUERYCONTRACTIOFSRESResponse convertResponseXmlStringToObject(String responseXml) {

		LOGGER.info("=======>ConvertFlexResponseXmlStringToObject.convertResponseXmlFileToObject()<============");
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(QUERYCONTRACTIOFSRESResponse.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			QUERYCONTRACTIOFSRESResponse querycontractiofsresResponse = (QUERYCONTRACTIOFSRESResponse) jaxbUnmarshaller
					.unmarshal(new StringReader(responseXml));
			LOGGER.info(querycontractiofsresResponse);
			return querycontractiofsresResponse;
		} catch (JAXBException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return null;
		}
	}
}
