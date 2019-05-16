package com.rumango.median.iso.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rumango.median.entity.ExcelMaster;
import com.rumango.median.iso.dao.service.ExcelMasterService;
import com.rumango.median.iso.dto.ExternalSystemDto;

@RestController
@CrossOrigin("*")
@RequestMapping("/excelmaster")
public class ExcelMasterController {

	private final static Logger logger = Logger.getLogger(ExcelMasterController.class);

	@Autowired
	private ExcelMasterService excelMasterService;

	// /excelmaster/findAll
	@GetMapping("/findAll")
	public List<ExcelMaster> findAll() {
		logger.info(excelMasterService.findAll());
		return excelMasterService.findAll();
	}// ExcelMaster

	@PostMapping("/findByCondition/{processingDate}")
	public ExcelMaster findByCondition(@PathVariable("processingDate") Date processingDate,
			@Valid @RequestBody ExternalSystemDto externalSystemDto) {

		logger.info("{externalSystem }" + externalSystemDto.getExtSys() + "{processFileName }"
				+ externalSystemDto.getProcessName() + "/{processingDate }" + processingDate);

		return excelMasterService.findByCondition(externalSystemDto.getExtSys(), externalSystemDto.getProcessName(),
				processingDate);
	}

	/**
	 * Sending ExcelMaster object from front end
	 * 
	 * @param excelMaster
	 * @return
	 */
	@PostMapping("/findByCondition")
	public ExcelMaster findByCondition(@Valid @RequestBody ExcelMaster excelMaster) {

		logger.info("{externalSystem }" + excelMaster.getExternalSystem() + "{processFileName }"
				+ excelMaster.getProcessFileName() + "/{processingDate }" + excelMaster.getProcessingDate());

		ExcelMaster em = excelMasterService.findByCondition(excelMaster.getExternalSystem(),
				excelMaster.getProcessFileName(), excelMaster.getProcessingDate());
		logger.info("em ::" + em);

		return em;
	}

//	@GetMapping("/findByCondition/{externalSystem}/{processFileName}/{processingDate}")
//	public ExcelMaster findByCondition(@PathVariable("externalSystem") String externalSystem,
//			@PathVariable("processFileName") String processFileName,
//			@PathVariable("processingDate") Date processingDate) {
//		logger.info("{externalSystem }" + externalSystem + "{processFileName }" + processFileName
//				+ "/{processingDate }" + processingDate);
//		return excelMasterService.findByCondition(externalSystem, processFileName, processingDate);
//	}
}
