/**
 * 
 */
package com.rumango.median.soap.service;

import java.io.IOException;
import java.util.Map;

import com.rumango.median.soap.dto.FullXmlChangesObject;

/**
 * @author lei2o
 *
 */
public interface MedianXmlConversion
{

	/**
	 * @param responseXML
	 * @param sourceName
	 * @param destinationName
	 * @return
	 * @throws IOException
	 */
	String convertFlexResponseXMLToMedianXMLResponse(String responseXML, String sourceName, String destinationName)
			throws IOException;

	/**
	 * @param fullXmlChangesObject
	 * @param responseXML
	 * @param index
	 * @return
	 */
	String doAllTagsAdditionReverse(FullXmlChangesObject fullXmlChangesObject, String responseXML, int index);

	/**
	 * @param fullXmlChangesObject
	 * @param requestXML
	 * @param index
	 * @return
	 */
	String doAllTagsAddition(FullXmlChangesObject fullXmlChangesObject, String requestXML, int index);

	/**
	 * @param requestXML
	 * @param sourceName
	 * @param destinationName
	 * @return
	 * @throws IOException
	 */
	Map<String, String> convertMedianXMLRequestToFlexXMLRequest(String requestXML, String sourceName, String destinationName)
			throws IOException;

}
