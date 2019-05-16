package com.rumango.median.iso.dao.serviceimpl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.dao.ExcelMasterRepository;
import com.rumango.median.entity.ExcelMaster;
import com.rumango.median.iso.dao.service.ExcelMasterService;

@Service
public class ExcelMasterServiceImpl implements ExcelMasterService {

	@Autowired
	private ExcelMasterRepository excelMasterRepository;

	@Override
	public List<ExcelMaster> findAll() {
		List<ExcelMaster> list = new LinkedList<>();
		excelMasterRepository.findAll().forEach(list::add);
//		System.err.println(list);
		return list;
	}

	@Override
	public ExcelMaster findByCondition(String externalSystem, String processFileName, Date processingDate) {
		return excelMasterRepository.findByExternalSystemAndProcessFileNameAndProcessingDate(externalSystem,
				processFileName, processingDate);
	}
}
