/**
 * 
 */
package com.rumango.median.soap.service;

import java.util.List;
import java.util.Map;

import com.rumango.median.entity.NodeMap;
import com.rumango.median.soap.dto.FullXmlChangesObject;

/**
 * @author lei2o
 *
 */
public interface XMLValidationsService
{
	public FullXmlChangesObject doAllMandatoryTagsAddition(String from, String to);

	public String getNodeByIdAndTagId(Long id, Long tagId);

	public Map<String, String> getNodeMappings(Long tagMapId, Long fromExtSysTagId, Long toExtSysTagId);

	public List<NodeMap> getNodeMapValuesByGivingTagMaPId(Long tagMapId);

	public Long findTagMapIdBasedOnFromAndToSystemId(Long fromSysId, Long toSysId);

	public Long pullExternalSystemTagId(long externalSystemId);

	public Long getExternalSystemId(String extSysName);

	public Map<String, String> getDefaultValue(Long tagMapId, Map<String, String> tagsMappingMap);

	/**
	 * @param systemName
	 * @return
	 */
	String getDestinationIpAndPort(String systemName);
}
