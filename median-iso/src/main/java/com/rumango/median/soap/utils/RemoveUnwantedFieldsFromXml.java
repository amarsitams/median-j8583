/**
 * 
 */
package com.rumango.median.soap.utils;

/**
 * @author lei2o
 *
 */
public class RemoveUnwantedFieldsFromXml
{
	public String removeUnwantedFieldfromXmlString(String mainXML,String regex)
	{
		mainXML = mainXML.replaceAll(mainXML, regex);
		return mainXML;
	}

}
