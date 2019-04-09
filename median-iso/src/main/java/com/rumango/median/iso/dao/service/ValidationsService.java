package com.rumango.median.iso.dao.service;

import java.util.List;
import java.util.Map;

import com.rumango.median.entity.ExternalSystem;
import com.rumango.median.entity.NodeMap;

public interface ValidationsService {

	public String getDestinationModuleCode(String from);

	public String getModuleCode(String destination);

	public ExternalSystem getExtSystem(String fromIp);

	public Map<Integer, NodeMap> getAllValidations(String from);

	public List<NodeMap> getAllValidations(String from, String to);

	public List<NodeMap> getNodeMaps(String from, String to);

	public String getQuery(String from, String field);

	public String getDefaultValue(String from, String field);

	public List<String> getLov(String from, String field);

	public String getCondition(String from, String field);

	public String getDestIpAndPort(String from);

}
