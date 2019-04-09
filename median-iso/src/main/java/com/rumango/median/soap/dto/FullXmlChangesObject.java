/**
 * 
 */
package com.rumango.median.soap.dto;

import java.util.List;

/**
 * @author lei2o
 *
 */
public class FullXmlChangesObject
{
	private String sourceSystemName;
	private String destinationSystemName;
	private String destinationIpAndPort;

	private List<String> sourceTagNameList;
	private List<String> destinationTagNameList;
	private List<String> defaultValues;

	/**
	 * @return the sourceSystemName
	 */
	/**
	 * 
	 */
	public FullXmlChangesObject()
	{
		// System.err.println("FullXmlChangesObject created");
	}

	/**
	 * @return the destinationIpAndPort
	 */
	public String getDestinationIpAndPort()
	{
		return destinationIpAndPort;
	}

	/**
	 * @param destinationIpAndPort
	 *            the destinationIpAndPort to set
	 */
	public void setDestinationIpAndPort(String destinationIpAndPort)
	{
		this.destinationIpAndPort = destinationIpAndPort;
	}

	public String getSourceSystemName()
	{
		return sourceSystemName;
	}

	/**
	 * @param sourceSystemName
	 *            the sourceSystemName to set
	 */
	public void setSourceSystemName(String sourceSystemName)
	{
		this.sourceSystemName = sourceSystemName;
	}

	/**
	 * @return the destinationSystemName
	 */
	public String getDestinationSystemName()
	{
		return destinationSystemName;
	}

	/**
	 * @param destinationSystemName
	 *            the destinationSystemName to set
	 */
	public void setDestinationSystemName(String destinationSystemName)
	{
		this.destinationSystemName = destinationSystemName;
	}

	/**
	 * @return the sourceTagNameList
	 */
	public List<String> getSourceTagNameList()
	{
		return sourceTagNameList;
	}

	/**
	 * @param sourceTagNameList
	 *            the sourceTagNameList to set
	 */
	public void setSourceTagNameList(List<String> sourceTagNameList)
	{
		this.sourceTagNameList = sourceTagNameList;
	}

	/**
	 * @return the destinationTagNameList
	 */
	public List<String> getDestinationTagNameList()
	{
		return destinationTagNameList;
	}

	/**
	 * @param destinationTagNameList
	 *            the destinationTagNameList to set
	 */
	public void setDestinationTagNameList(List<String> destinationTagNameList)
	{
		this.destinationTagNameList = destinationTagNameList;
	}

	/**
	 * @return the defaultValues
	 */
	public List<String> getDefaultValues()
	{
		return defaultValues;
	}

	/**
	 * @param defaultValues
	 *            the defaultValues to set
	 */
	public void setDefaultValues(List<String> defaultValues)
	{
		this.defaultValues = defaultValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FullXmlChangesObject [sourceSystemName=" + sourceSystemName + ", destinationSystemName="
				+ destinationSystemName + ", destinationIpAndPort=" + destinationIpAndPort + ", sourceTagNameList="
				+ sourceTagNameList + ", destinationTagNameList=" + destinationTagNameList + ", defaultValues="
				+ defaultValues + "]";
	}

}
