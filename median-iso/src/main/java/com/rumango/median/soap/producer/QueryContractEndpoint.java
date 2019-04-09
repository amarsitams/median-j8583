package com.rumango.median.soap.producer;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ofss.fcubs.service.fcubsftservice.QUERYCONTRACTIOFSREQRequest;
import com.ofss.fcubs.service.fcubsftservice.QUERYCONTRACTIOFSRESResponse;
import com.rumango.median.soap.service.MedianXmlConversion;
import com.rumango.median.soap.utils.ConvertFlexResponseXmlStringToObject;
import com.rumango.median.soap.utils.FlexRequestObjectToXmlConversion;
import com.rumango.median.soap.utils.SendXmlRequestToFlexProducer;
import com.rumango.median.soap.utils.XMLFormatter;

/**
 * @author lei2o
 *
 */

@Endpoint
public class QueryContractEndpoint {
	private static final Logger LOGGER = Logger.getLogger(QueryContractEndpoint.class);

	private static final String NAMESPACE_URI = "http://fcubs.ofss.com/service/FCUBSFTService";

	@SuppressWarnings("unused")
	private QueryContractRepository queryContractRepository;

	@Autowired
	MedianXmlConversion medianXmlConversion;

	@Autowired
	public QueryContractEndpoint(QueryContractRepository queryContractRepositoryObject) {
		queryContractRepository = queryContractRepositoryObject;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "QUERYCONTRACT_IOFS_REQ_Request")
	@ResponsePayload
	public QUERYCONTRACTIOFSRESResponse getQueryContractIofsResponse(
			@RequestPayload QUERYCONTRACTIOFSREQRequest request) throws IOException {
		String sourceName = request.getFCUBSHEADER().getSOURCE();
		String destinationName = request.getFCUBSHEADER().getDESTINATION();
		LOGGER.info("source: " + sourceName + " Destination: " + destinationName);
		/*
		 * receiving and formatting request xml from median producer
		 * 
		 */
		String medianRequestXmlString = new FlexRequestObjectToXmlConversion()
				.convertFlexRequestObjectToXmlString(request);
		medianRequestXmlString = new XMLFormatter().format(medianRequestXmlString).trim();
		LOGGER.info("Request object converted to MEDIAN request xml: \n");
		LOGGER.info("the MEDIAN request xml is : \n" + medianRequestXmlString);

		/*
		 * converting MedianXmlSring to FlexXmlString
		 * 
		 */
		Map<String, String> xmlAndIpDetailMap = medianXmlConversion
				.convertMedianXMLRequestToFlexXMLRequest(medianRequestXmlString, sourceName, destinationName);
		Set<Map.Entry<String, String>> xmlAndIpDetailMapSet = xmlAndIpDetailMap.entrySet();

		String ipDetails = null;
		String requestMessage = null;
		for (Map.Entry<String, String> entrySetObj : xmlAndIpDetailMapSet) {
			ipDetails = entrySetObj.getKey();
			requestMessage = entrySetObj.getValue();
		}

		String secondaryRequestXmlString = requestMessage;
		LOGGER.info("final SOAP XML string to be sent to flex is :=====> \n");
		LOGGER.info("\n" + secondaryRequestXmlString);

		/*
		 * getting response xml from flexcube
		 * 
		 */
		/// need to change this
		String flexcubeResponseXml = new SendXmlRequestToFlexProducer()
				.sendXmlRequestStringToFlexProducer(secondaryRequestXmlString, ipDetails);
		flexcubeResponseXml = new XMLFormatter().format(flexcubeResponseXml);
		LOGGER.info("below is the flexcube response XML : \n");
		LOGGER.info("\n" + flexcubeResponseXml);

		/*
		 * modifying response xml back to median banking server format
		 * 
		 */

		String medianResponseXmlString = medianXmlConversion
				.convertFlexResponseXMLToMedianXMLResponse(flexcubeResponseXml, sourceName, destinationName);

		/*
		 * now sending modified xml to primary banking system
		 * 
		 */

		QUERYCONTRACTIOFSRESResponse querycontractiofsres = new ConvertFlexResponseXmlStringToObject()
				.convertResponseXmlStringToObject(medianResponseXmlString);
		LOGGER.info("PROCESS COMPLETED SUCCESSFULLY  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return querycontractiofsres;
	}

}