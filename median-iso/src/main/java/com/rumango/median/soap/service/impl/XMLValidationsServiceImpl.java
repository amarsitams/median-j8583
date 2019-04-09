/**
 * 
 */
package com.rumango.median.soap.service.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.dao.ExternalSystemRepository;
import com.rumango.median.dao.NodeMapRepository;
import com.rumango.median.dao.NodeRepository;
import com.rumango.median.dao.TagMapRepository;
import com.rumango.median.dao.TagRepository;
import com.rumango.median.entity.ExternalSystem;
import com.rumango.median.entity.Node;
import com.rumango.median.entity.NodeMap;
import com.rumango.median.entity.Tag;
import com.rumango.median.entity.TagMap;
import com.rumango.median.soap.dto.FullXmlChangesObject;
import com.rumango.median.soap.service.XMLValidationsService;

/**
 * @author lei2o
 *
 */
@Service
public class XMLValidationsServiceImpl implements XMLValidationsService
{
	private static final Logger LOGGER = Logger.getLogger(XMLValidationsServiceImpl.class);

	public XMLValidationsServiceImpl()
	{
		// LOGGER.info("================>XMLValidationsServiceImpl<============");
	}

	@Autowired
	ExternalSystemRepository externalSystemRepository;

	@Autowired
	TagRepository tagRepository;

	@Autowired
	TagMapRepository tagMapRepository;

	@Autowired
	NodeMapRepository nodeMapRepository;

	@Autowired
	NodeRepository nodeRepository;

	@Override
	public FullXmlChangesObject doAllMandatoryTagsAddition(String from, String to)
	{
		FullXmlChangesObject fullXmlChangesObject = new FullXmlChangesObject();
		// LOGGER.info("====>doAllMandatoryTagsAddition(String from, String
		// to)<=====================");

		LOGGER.info("from sys: " + from + "  to sys : " + "-->" + to);
		/*
		 * STEP - 1
		 * 
		 * 
		 */

		Long fromExtSysId = externalSystemRepository.getExternalSysDetail(from).getId();
		Long toExtSysId = externalSystemRepository.getExternalSysDetail(to).getId();
		LOGGER.info(" SOURCE externalSystemsId :  " + fromExtSysId + " DESTINATION externalSystemsId : " + toExtSysId);

		String fromExternalSysName = externalSystemRepository.getOneExternalSystemById(fromExtSysId).getExtSysCode();
		String toExternalSysName = externalSystemRepository.getOneExternalSystemById(toExtSysId).getExtSysCode();
		LOGGER.info("from external sys name: " + fromExternalSysName + "to external sys name " + toExternalSysName);

		fullXmlChangesObject.setSourceSystemName(fromExternalSysName);
		fullXmlChangesObject.setDestinationSystemName(toExternalSysName);
		LOGGER.info("\n full xml changes object details: \n");
		LOGGER.info(fullXmlChangesObject.toString());
		/*
		 * get Destination IP and port
		 * 
		 * 
		 */
		String destinationIpAndPort = getDestinationIpAndPort(toExternalSysName);
		fullXmlChangesObject.setDestinationIpAndPort(destinationIpAndPort);
		
		/*
		 * 
		 * STEP - 2
		 * 
		 */
		Long fromExtSysTagId = pullExternalSystemTagId(fromExtSysId);
		Long toExtSysTagId = pullExternalSystemTagId(toExtSysId);
		LOGGER.info(" SOURCE  externalSystems TagId: " + fromExtSysTagId + " \n DESTINATION externalSystemsTagId : "
				+ toExtSysTagId);
		/*
		 * 
		 * STEP - 3
		 * 
		 */
		Long tagMapId = findTagMapIdBasedOnFromAndToSystemId(fromExtSysId, toExtSysId);
		LOGGER.info(" TagMap id Between SOURCE AND DESTINATION is : " + tagMapId);
		/*
		 * STEP - 4
		 * 
		 */
		List<String> sourceTagsList = new LinkedList<String>();
		List<String> destinationTagsList = new LinkedList<String>();
		List<String> defaultValuesList = new LinkedList<String>();

		Map<String, String> nodeIdMaps = getNodeMappings(tagMapId, fromExtSysTagId, toExtSysTagId);
		LOGGER.info(nodeIdMaps);
		Set<Map.Entry<String, String>> nodeIdMapSet = nodeIdMaps.entrySet();

		// LOGGER.info("below are the node ID Mappings :\n ");

		// LOGGER.info(
		// "fromExtSysTagId" + "====>" + "sourceNodeId" + " " + "toExtSysTagId" +
		// "====>" + "destinationNodeId");
		for (Map.Entry<String, String> it : nodeIdMapSet)
		{
			String sourceNodeId = it.getKey();
			String destinationNodeId = it.getValue();

			LOGGER.info(fromExtSysTagId + "====>" + sourceNodeId + "   " + toExtSysTagId + "====>" + destinationNodeId);
			String sourceNodeName = getNodeByIdAndTagId(Long.parseLong(sourceNodeId), fromExtSysTagId);
			String desinationNodeName = getNodeByIdAndTagId(Long.parseLong(destinationNodeId), toExtSysTagId);
			sourceTagsList.add(sourceNodeName);
			destinationTagsList.add(desinationNodeName);

		}

		fullXmlChangesObject.setSourceTagNameList(sourceTagsList);
		fullXmlChangesObject.setDestinationTagNameList(destinationTagsList);
		LOGGER.info(fullXmlChangesObject);

		/*
		 * STEP - 5
		 * 
		 * 
		 */
		for (Map.Entry<String, String> it : nodeIdMapSet)
		{
			String sourceNodeId = it.getKey();
			String destinationNodeId = it.getValue();

			NodeMap nodeMap = nodeMapRepository.getDefaultValueFromNodeMap(tagMapId, sourceNodeId, destinationNodeId);
			String defaultValue = nodeMap.getDef();
			defaultValuesList.add(defaultValue);
			LOGGER.info(defaultValue);
		}
		fullXmlChangesObject.setDefaultValues(defaultValuesList);
		// System.err.println(fullXmlChangesObject);
		LOGGER.info(fullXmlChangesObject);
		return fullXmlChangesObject;
	}
	
	@Override
	public String getDestinationIpAndPort(String systemName)
	{
		ExternalSystem es = externalSystemRepository.getExternalSystemIdByExternalSystemCode(systemName);
		String ipAndPortDetails = es.getDestination();
		
		return ipAndPortDetails;
		
	}

	@Override
	public String getNodeByIdAndTagId(Long id, Long tagId)
	{
		Node n1 = nodeRepository.getNodeNameFromIdAndTagId(id, tagId);
		return n1.getName();
	}

	/*
	 * generating node mappings from node_maps table based on tagMapId and
	 * fromExtSysTagId,toExtSysTagId
	 */

	@Override
	public Map<String, String> getNodeMappings(Long tagMapId, Long fromExtSysTagId, Long toExtSysTagId)
	{
		Map<String, String> allNodesIdMap = new LinkedHashMap<String, String>();

		List<NodeMap> nodeMaps = getNodeMapValuesByGivingTagMaPId(tagMapId);

		for (NodeMap nodeMap : nodeMaps)
		{
			allNodesIdMap.put(nodeMap.getNode1(), nodeMap.getNode2());
		}
		return allNodesIdMap;
	}

	@Override
	public List<NodeMap> getNodeMapValuesByGivingTagMaPId(Long tagMapId)
	{
		return nodeMapRepository.getNodeMapByPassingTagMapId(tagMapId);
	}

	@Override
	public Long findTagMapIdBasedOnFromAndToSystemId(Long fromSysId, Long toSysId)
	{
		TagMap tagMap = tagMapRepository.findTagMapByFromAndToSystemId(fromSysId, toSysId);
		return tagMap.getId();
	}

	@Override
	public Long pullExternalSystemTagId(long externalSystemId)
	{
		Tag t1 = tagRepository.getTagIdByExternalSystemId(externalSystemId);
		return t1.getId();
	}

	@Override
	public Long getExternalSystemId(String extSysName)
	{
		LOGGER.info("===========>getExternalSystemId(String extSysName)");
		ExternalSystem oneExternalSystem = externalSystemRepository.getExternalSystemIdByExternalSystemCode(extSysName);
		LOGGER.info(oneExternalSystem.toString());

		return oneExternalSystem.getId();
	}

	@Override
	public Map<String, String> getDefaultValue(Long tagMapId, Map<String, String> tagsMappingMap)
	{
		return null;
	}

}
