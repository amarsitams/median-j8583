/**
 * 
 */
package com.rumango.median.soap.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.soap.dto.FullXmlChangesObject;
import com.rumango.median.soap.service.MedianXmlConversion;
import com.rumango.median.soap.service.XMLValidationsService;
import com.rumango.median.soap.utils.AddStringAtParticularIndex;
import com.rumango.median.soap.utils.XMLFormatter;
import com.rumango.median.soap.utils.XMLStringModification;

/**
 * @author lei2o
 *
 */
@Service
public class MedianXmlConversionImpl implements MedianXmlConversion
{
	private static final Logger LOGGER = Logger.getLogger(MedianXmlConversionImpl.class);

	@Autowired
	XMLValidationsService xmlValidationsService;

	@Override
	public Map<String, String> convertMedianXMLRequestToFlexXMLRequest(String requestXML, String sourceName,
			String destinationName) throws IOException
	{
		// converting median xml to flexcube xml
		LOGGER.info("converting median xml to flexcube xml \n ");
		requestXML = new XMLStringModification().deleteFirstLine(requestXML);
		requestXML = requestXML.replaceAll("QUERYCONTRACT_IOFS_REQ_Request", "QUERYCONTRACT_IOFS_REQ");

		// getting tag add Point
		int tagAddPoint = requestXML.lastIndexOf("</SOURCE>") + "</SOURCE>".length();

		/*
		 * getting full xml changes object
		 */
		FullXmlChangesObject fullXmlChangesObject = xmlValidationsService.doAllMandatoryTagsAddition(sourceName,
				destinationName);
		LOGGER.info("THE FullXmlChangesObject THAT IS RETURNED IN QueryContractEndpoint is below :");
		LOGGER.info(fullXmlChangesObject);
		/*
		 * getting destination portdetails from FullXmlChangesObject
		 */

		String destinationIpAndPortDetails = fullXmlChangesObject.getDestinationIpAndPort();
		/*
		 * Now starting the manipulation process
		 */
		if (fullXmlChangesObject.getSourceSystemName().equalsIgnoreCase(sourceName)
				&& fullXmlChangesObject.getDestinationSystemName().equalsIgnoreCase(destinationName))
		{
			requestXML = doAllTagsAddition(fullXmlChangesObject, requestXML, tagAddPoint);
		} else
		{
			LOGGER.info("this SOURCE AND DEST mapping is not available in median database");
		}
		requestXML = new XMLFormatter().format(requestXML).trim();
		requestXML = new XMLStringModification().deleteFirstLine(requestXML);
		LOGGER.info("Mandatory Fields Are added here");
		LOGGER.info("\n the request xml is: " + requestXML);
		Map<String, String> requestXmlAndDestinationDetailsMap = new HashMap<String, String>();
		requestXmlAndDestinationDetailsMap.put(destinationIpAndPortDetails, requestXML);

		return requestXmlAndDestinationDetailsMap;
	}

	@Override
	public String doAllTagsAddition(FullXmlChangesObject fullXmlChangesObject, String requestXML, int index)
	{

		List<String> sourceNodeNamesList = fullXmlChangesObject.getSourceTagNameList();
		List<String> destinationNodeNamesList = fullXmlChangesObject.getDestinationTagNameList();
		List<String> valuesList = fullXmlChangesObject.getDefaultValues();

		String sourceNode = "";
		String destinationNode = "";
		String defaultValue = "";

		for (int i = 0; i < sourceNodeNamesList.size(); i++)
		{
			sourceNode = sourceNodeNamesList.get(i);
			destinationNode = destinationNodeNamesList.get(i);
			defaultValue = valuesList.get(i);
			LOGGER.info("source node: " + sourceNode + " destination node :" + destinationNode + " default value: "
					+ defaultValue);

			/*
			 * removing empty Single nodes i.e <SOURCE/> with <SOURCE></SOURCE>
			 */
			String emptySourceNode = "<" + sourceNode + "/>";
			if (requestXML.contains(emptySourceNode))
			{
				// System.err.println("empty node is being replaced");
				LOGGER.info("empty node is being replaced");
				requestXML = requestXML.replaceAll(emptySourceNode, "<" + sourceNode + ">" + "</" + sourceNode + ">");
			}

			/*
			 * breaking the loop for empty sourcetags breaking the loop for empty
			 * destinationtags
			 */
			if (sourceNode.equals("") || destinationNode.equals(""))
			{
				LOGGER.error("source node is nil or destination node is nil ");
				break;
			}

			/*
			 * Now starting the manipulation
			 */

			if (requestXML.contains(sourceNode))
			{
				requestXML = requestXML.replaceAll(sourceNode, destinationNode);
				LOGGER.info(" requestXML.replaceAll(sourceNode, destinationNode) COMPLETED");
			}

			// System.err.println(requestXML);

			String finalDestinationNodeStarting = "<" + destinationNode + ">";
			String finalDestinationNodeEnding = "</" + destinationNode + ">";
			LOGGER.info(finalDestinationNodeStarting);
			LOGGER.info(finalDestinationNodeEnding);

			int valueIndexStarting = requestXML.lastIndexOf(finalDestinationNodeStarting)
					+ finalDestinationNodeStarting.length();
			int valueIndexEnding = requestXML.indexOf(finalDestinationNodeEnding);
			// LOGGER.info(" valueIndexStarting :" + valueIndexStarting);
			// LOGGER.info(" valueIndexEnding :" + valueIndexEnding);
			if (valueIndexStarting == valueIndexEnding)
			{
				/*
				 * adding default values
				 */
				requestXML = new AddStringAtParticularIndex().insertString(requestXML, defaultValue,
						valueIndexStarting - 1);

			} else
			{
				// do nothing
			}

			// LOGGER.info("default value is: " + defaultValue);
		}
		return requestXML;
	}

	@Override
	public String doAllTagsAdditionReverse(FullXmlChangesObject fullXmlChangesObject, String responseXML, int index)
	{

		List<String> destinationNodeNamesList = fullXmlChangesObject.getSourceTagNameList();
		List<String> sourceNodeNamesList = fullXmlChangesObject.getDestinationTagNameList();
		List<String> valuesList = fullXmlChangesObject.getDefaultValues();

		String sourceNode = "";
		String destinationNode = "";
		String defaultValue = "";

		for (int i = 0; i < sourceNodeNamesList.size(); i++)
		{
			sourceNode = sourceNodeNamesList.get(i);
			destinationNode = destinationNodeNamesList.get(i);
			defaultValue = valuesList.get(i);
			LOGGER.info("source node: " + sourceNode + " destination node :" + destinationNode + " default value: "
					+ defaultValue);

			/*
			 * removing empty Single nodes i.e <SOURCE/> with <SOURCE></SOURCE>
			 */
			String emptySourceNode = "<" + sourceNode + "/>";
			if (responseXML.contains(emptySourceNode))
			{
				System.err.println("empty node is being replaced");
				responseXML = responseXML.replaceAll(emptySourceNode, "<" + sourceNode + ">" + "</" + sourceNode + ">");
			}

			/*
			 * breaking the loop for empty sourcetags breaking the loop for empty
			 * destinationtags
			 */
			if (sourceNode.equals("") || destinationNode.equals(""))
			{
				LOGGER.error("source node is nil or destination node is nil ");
				break;
			}

			/*
			 * Now starting the manipulation
			 */

			if (responseXML.contains(sourceNode))
			{
				responseXML = responseXML.replaceAll(sourceNode, destinationNode);
				LOGGER.info(" requestXML.replaceAll(sourceNode, destinationNode) COMPLETED");
			}

			// System.err.println(requestXML);

			/*
			 * Skipping default value addition part
			 */
			// String finalDestinationNodeStarting = "<" + destinationNode + ">";
			// String finalDestinationNodeEnding = "</" + destinationNode + ">";
			// LOGGER.info(finalDestinationNodeStarting);
			// LOGGER.info(finalDestinationNodeEnding);

			// int valueIndexStarting =
			// responseXML.lastIndexOf(finalDestinationNodeStarting)
			// + finalDestinationNodeStarting.length();
			// int valueIndexEnding = responseXML.indexOf(finalDestinationNodeEnding);
			// LOGGER.info(" valueIndexStarting :" + valueIndexStarting);
			// LOGGER.info(" valueIndexEnding :" + valueIndexEnding);
			// if (valueIndexStarting == valueIndexEnding)
			// {
			// /*
			// * adding default values
			// */
			// responseXML = new AddStringAtParticularIndex().insertString(responseXML,
			// defaultValue,
			// valueIndexStarting - 1);
			//
			// } else
			// {
			// // do nothing
			// }

			// LOGGER.info("default value is: " + defaultValue);
		}
		return responseXML;
	}

	@Override
	public String convertFlexResponseXMLToMedianXMLResponse(String responseXML, String sourceName,
			String destinationName) throws IOException
	{

		responseXML = responseXML.replaceAll("</S:Body>", "");
		responseXML = responseXML.replaceAll("</S:Envelope>", "");
		responseXML = new XMLStringModification().deleteFirstLine(responseXML);
		responseXML = new XMLStringModification().deleteFirstLine(responseXML);
		responseXML = new XMLStringModification().deleteFirstLine(responseXML);
		responseXML = responseXML.replaceAll("QUERYCONTRACT_IOFS_RES", "QUERYCONTRACT_IOFS_RES_Response");
		responseXML = new XMLFormatter().format(responseXML);
		LOGGER.info("\n" + responseXML);

		// converting flexcube response xml to median response xml
		LOGGER.info("converting flexcube response xml to median response xml \n ");

		// getting tag add Point
		int tagAddPoint = responseXML.lastIndexOf("</SOURCE>") + "</SOURCE>".length();

		/*
		 * getting full xml changes object
		 */
		FullXmlChangesObject fullXmlChangesObject = xmlValidationsService.doAllMandatoryTagsAddition(sourceName,
				destinationName);
		LOGGER.info("THE FullXmlChangesObject THAT IS RETURNED IN QueryContractEndpoint is below :");
		LOGGER.info(fullXmlChangesObject);

		/*
		 * Now starting the manipulation process
		 */
		if (fullXmlChangesObject.getSourceSystemName().equalsIgnoreCase(sourceName)
				&& fullXmlChangesObject.getDestinationSystemName().equalsIgnoreCase(destinationName))
		{
			responseXML = doAllTagsAdditionReverse(fullXmlChangesObject, responseXML, tagAddPoint);
		} else
		{
			LOGGER.info("this SOURCE AND DEST mapping is not available in median database");
		}

		responseXML = new XMLFormatter().format(responseXML).trim();
		responseXML = new XMLStringModification().deleteFirstLine(responseXML);
		LOGGER.info("Mandatory Fields Are added here");
		LOGGER.info("\n the response xml is: " + responseXML);

		return responseXML;
	}

}
