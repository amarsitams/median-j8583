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

	@Override
	public List<NodeMap> getNodeMaps(String from, String to) {
		return nodeMapRepository.getNodes(from, to);
	}

	@Override
	public String getQuery(String from, String to, String field) {
		return nodeMapRepository.getQuery(externalSystemRepository.getStringId(from),
				externalSystemRepository.getStringId(to), field);
	}

	@Override
	public String getDefaultValue(String from, String to, String field) {
		return nodeMapRepository.getDefault(externalSystemRepository.getStringId(from),
				externalSystemRepository.getStringId(to), field);
	}

	@Override
	public List<String> getLov(String from, String to, String field) {
		return null;
	}

	@Override
	public String getCondition(String from, String to, String field) {
		return null;
	}
}
