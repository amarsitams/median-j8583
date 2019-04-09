/**
 * 
 */
package com.rumango.median.soap.utils;

/**
 * @author lei2o
 *
 */
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.ofss.fcubs.service.fcubsftservice.QUERYCONTRACTIOFSRESResponse;

public class ConvertFlexResponseXmlFileToObject {
	private static final Logger LOGGER = Logger.getLogger(ConvertFlexResponseXmlFileToObject.class);

	public QUERYCONTRACTIOFSRESResponse convertResponseXmlFileToObject(String filepath) {
		try {
			File file = new File(filepath);
			JAXBContext jaxbContext = JAXBContext.newInstance(QUERYCONTRACTIOFSRESResponse.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			QUERYCONTRACTIOFSRESResponse querycontractiofsresResponse = (QUERYCONTRACTIOFSRESResponse) jaxbUnmarshaller
					.unmarshal(file);
			LOGGER.info(querycontractiofsresResponse);
			return querycontractiofsresResponse;
		} catch (JAXBException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return null;
		}
	}
}
