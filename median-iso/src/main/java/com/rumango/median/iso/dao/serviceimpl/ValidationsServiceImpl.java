package com.rumango.median.iso.dao.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.dao.ExternalSystemRepository;
import com.rumango.median.dao.NodeMapRepository;
import com.rumango.median.dao.NodeRepository;
import com.rumango.median.dao.TagMapConditionRepository;
import com.rumango.median.dao.TagMapRepository;
import com.rumango.median.entity.ExternalSystem;
import com.rumango.median.entity.NodeMap;
import com.rumango.median.entity.TagMapCondition;
import com.rumango.median.iso.dao.service.ValidationsService;
import com.rumango.median.iso.dto.ValidationDto;

@Service
public class ValidationsServiceImpl implements ValidationsService {

	private final static Logger logger = Logger.getLogger(ValidationsServiceImpl.class);
	@Autowired
	private ExternalSystemRepository externalSystemRepository;

	@Autowired
	private NodeMapRepository nodeMapRepository;

	@Autowired
	private TagMapRepository tagMapRepository;

	@Autowired
	private NodeRepository nodeRepository;
	
	@Autowired
	TagMapConditionRepository tagMapConditionRepository;
	
	public Map<Integer, ValidationDto> newValidations(String from) {
		Map<Integer, ValidationDto> map = new HashMap<>();
		String fromFiled, toField;
		List<NodeMap> nodeMapList = nodeMapRepository.findByTagMapId(tagMapRepository.getTagMapId(getExtSysid(from)));
		for (NodeMap nMap : nodeMapList) {
			//nMap.get
			Optional<TagMapCondition> tmc=tagMapConditionRepository.findById(nMap.getId());
			
			fromFiled = nodeRepository.getField(Long.parseLong(nMap.getNode1()));
			toField = nodeRepository.getField(Long.parseLong(nMap.getNode2()));
			nMap.setNode1(fromFiled);
			nMap.setNode2(toField);
		}
		for (NodeMap nMap : nodeMapList) {
			//map.put(Integer.parseInt(nMap.getNode1()), nMap);
		}
		return map;
	}

	public Map<Integer, NodeMap> getAllValidations(String from) {
		Map<Integer, NodeMap> map = new HashMap<>();
		String fromFiled, toField;
		List<NodeMap> nodeMapList = nodeMapRepository.findByTagMapId(tagMapRepository.getTagMapId(getExtSysid(from)));
		for (NodeMap nMap : nodeMapList) {
			fromFiled = nodeRepository.getField(Long.parseLong(nMap.getNode1()));
			toField = nodeRepository.getField(Long.parseLong(nMap.getNode2()));
			nMap.setNode1(fromFiled);
			nMap.setNode2(toField);
		}
		for (NodeMap nMap : nodeMapList) {
			map.put(Integer.parseInt(nMap.getNode1()), nMap);
		}
		return map;
	}

	public List<NodeMap> getAllValidations(String from, String to) {
		String fromFiled, toField;
//		// get tagmap id from from system
//		tagMapRepository.findByFromAndTo(getExtSysid(from), getExtSysid(to));
//		// find field numbers by node id
//				nodeRepository.getField(90L);
//		// find all based on tag map id

		List<NodeMap> nodeMapList = nodeMapRepository
				.findByTagMapId(tagMapRepository.findByFromAndTo(getExtSysid(from), getExtSysid(to)));
		for (NodeMap nMap : nodeMapList) {
			fromFiled = nodeRepository.getField(Long.parseLong(nMap.getNode1()));
			toField = nodeRepository.getField(Long.parseLong(nMap.getNode2()));
			nMap.setNode1(fromFiled);
			nMap.setNode2(toField);
		}
		return nodeMapList;
	}

	/**
	 * Gives module code i.e iso version of the external system
	 */
	@Override
	public String getModuleCode(String from) {
		try {
			return externalSystemRepository.getModuleCode(from);
		} catch (Exception e) {
			logger.error("Exception while getting module code ", e);
			return "";
		}
	}

	/**
	 * Gives destination module code i.e iso version of the external system
	 */
	@Override
	public String getDestinationModuleCode(String from) {
		try {
			return externalSystemRepository
					.getModuleCode(tagMapRepository.getToSystemId(externalSystemRepository.getExtSysId(from)));
		} catch (Exception e) {
			logger.error("Exception while getting destination module code ", e);
			return "";
		}
	}

	@Override
	public String getDestIpAndPort(String from) {
		return externalSystemRepository
				.getDestinationFromId(tagMapRepository.findByFromSystemId(getExtSysid(from)).getToSystemId());
	}

	@Override
	public List<NodeMap> getNodeMaps(String from, String to) {
		// return nodeMapRepository.getNodes(from, to);
		return null;
	}

	@Override
	public String getQuery(String from, String field) {
//		long extSysId = getExtSysid(from);
//		return nodeMapRepository.getQuery(extSysId, tagMapRepository.findByFromSystemId(extSysId).getToSystemId(),				field);
		return null;
	}

	@Override
	public String getDefaultValue(String from, String field) {
//		long extSysId = getExtSysid(from);
//		return nodeMapRepository.getDefault(extSysId, tagMapRepository.findByFromSystemId(extSysId).getToSystemId(),
//				field);
		return null;
	}

	@Override
	public List<String> getLov(String from, String field) {
		return null;
	}

	@Override
	public String getCondition(String from, String field) {
		return null;
	}

	private long getExtSysid(String destination) {
		return externalSystemRepository.findByDestination(destination).getId();
	}

	@Override
	public ExternalSystem getExtSystem(String fromIp) {
		return externalSystemRepository.findByDestination(fromIp);
	}
}
