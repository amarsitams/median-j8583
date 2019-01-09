package com.rumango.median.iso.dao.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.iso.dao.ExternalSystemRepository;
import com.rumango.median.iso.dao.NodeMapRepository;
import com.rumango.median.iso.dao.TagMapRepository;
import com.rumango.median.iso.dao.service.ValidationsService;

@Service
public class ValidationsServiceImpl implements ValidationsService {

	@Autowired
	private ExternalSystemRepository externalSystemRepository;

	@Autowired
	private NodeMapRepository nodeMapRepository;

	@Autowired
	private TagMapRepository tagMapRepository;

	@Override
	public String getQuery(String from, String to, int field) {
		nodeMapRepository.getQuery(externalSystemRepository.getStringId(from),
				externalSystemRepository.getStringId(to));
		return null;
	}

	@Override
	public String getDefaultValue(String from, String to, int field) {
		tagMapRepository.findByFromAndTo(externalSystemRepository.getLongId(from),
				externalSystemRepository.getLongId(to));
		return null;
	}

	@Override
	public List<String> getLov(String from, String to, int field) {
		return null;
	}
}
