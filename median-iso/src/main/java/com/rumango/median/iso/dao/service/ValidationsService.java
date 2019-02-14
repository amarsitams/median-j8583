package com.rumango.median.iso.dao.service;

import java.util.List;

import com.rumango.median.iso.entity.NodeMap;

public interface ValidationsService {

	public List<NodeMap> getNodeMaps(String from, String to);

	public String getQuery(String from, String to, String field);

	public String getDefaultValue(String from, String to, String field);

	public List<String> getLov(String from, String to, String field);

	public String getCondition(String from, String to, String field);

}
