package com.rumango.median.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rumango.median.entity.ExcelMaster;

@Repository
public interface ExcelMasterRepository extends CrudRepository<ExcelMaster, Long> {

	public ExcelMaster findByExternalSystemAndProcessFileNameAndProcessingDate(String externalSystem,
			String processFileName, Date processingDate);
}
