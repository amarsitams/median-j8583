package com.rumango.median.iso.dao.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.iso.dao.ExternalSystemRepository;
import com.rumango.median.iso.dao.NodeMapRepository;
import com.rumango.median.iso.dao.NodeRepository;
import com.rumango.median.iso.dao.TagMapRepository;
import com.rumango.median.iso.dao.service.ValidationsService;
import com.rumango.median.iso.entity.NodeMap;

@Service
public class ValidationsServiceImpl implements ValidationsService {

	@Autowired
	private ExternalSystemRepository externalSystemRepository;

	@Autowired
	private NodeMapRepository nodeMapRepository;

	@Autowired
	private TagMapRepository tagMapRepository;

	@Autowired
	private NodeRepository nodeRepository;

	public String getSourceVersion(String from) {
		return null;// getExtSysid(from); //TODO get versions
	}

	public String getDestinationVersion(String from) {
		return null;
	}

	@Override
	public String getDestIpAndPort(String from) {
		return externalSystemRepository
				.getDestinationFromId(tagMapRepository.findByFromSystemId(getExtSysid(from)).getToSystemId());
	}

	@Override
	public List<NodeMap> getNodeMaps(String from, String to) {
		return nodeMapRepository.getNodes(from, to);
	}

	@Override
	public String getQuery(String from, String field) {
		long extSysId = getExtSysid(from);
		return nodeMapRepository.getQuery(extSysId, tagMapRepository.findByFromSystemId(extSysId).getToSystemId(),
				field);
	}

	@Override
	public String getDefaultValue(String from, String field) {
		long extSysId = getExtSysid(from);
		return nodeMapRepository.getDefault(extSysId, tagMapRepository.findByFromSystemId(extSysId).getToSystemId(),
				field);
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
		return externalSystemRepository.findByDestinationContaining(destination).getId();
	}
}
