package com.rumango.median.iso.dao.service;

import java.util.Date;
import java.util.List;

import com.rumango.median.entity.ExcelMaster;

public interface ExcelMasterService {

	public List<ExcelMaster> findAll();

	public ExcelMaster findByCondition(String externalSystem, String processFileName, Date processingDate);

}
