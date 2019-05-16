package com.rumango.median.iso.controller;

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

import com.rumango.median.entity.EmbedId;
import com.rumango.median.entity.ExcelStaticColumns;
import com.rumango.median.iso.dao.service.ExcelColumnsService;
import com.rumango.median.iso.dao.service.ExcelDetailService;
import com.rumango.median.iso.dto.ColumnMappingDto;

@RestController
@CrossOrigin("*")
@RequestMapping("/excelcolumns") // /excelcolumns/postColumnMapping/ext1/ps1
public class ExcelStaticColumnsController {

	private final static Logger logger = Logger.getLogger(ExcelStaticColumnsController.class);

	@Autowired
	private ExcelColumnsService excelColumnsService;

	@Autowired
	private ExcelDetailService excelDetailService;

	@GetMapping("/findAll")
	public List<ExcelStaticColumns> findAll() {
		return excelColumnsService.findAll();
	}

	@GetMapping("/findAllColumn")
	public List<String> findAllColumnDescription() {
		return excelColumnsService.findAllColumnDescription();
	}

	/**
	 * Getting the mapping data here
	 * 
	 * @param extSys
	 * @param processName
	 * @param mappingData
	 */
	@PostMapping("/postColumnMapping/{extSys}/{processName}")
	public void postColumnMapping(@PathVariable("extSys") String extSys,
			@PathVariable("processName") String processName, @Valid @RequestBody ColumnMappingDto columnMappingDto) {
//		if (null == columnMappingDto) {
//			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
//		}

		logger.info("columnMappingDto" + columnMappingDto);

		EmbedId id = new EmbedId();
		id.setExternalSystem(extSys);
		id.setProcessName(processName);
		try {
			excelDetailService.convertToExcelDetailAndSave(columnMappingDto, id);
		} catch (Exception e) {
		}
//		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/processFile/{extSys}/{processName}")
	public void processFile(@PathVariable("extSys") String extSys, @PathVariable("processName") String processName) {
		try {
			EmbedId id = new EmbedId();
			id.setExternalSystem(extSys);
			id.setProcessName(processName);
			excelDetailService.doProcess(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/test")
	public void postColumnMappingNew() {
		excelDetailService.save();
	}

//	@GetMapping("/find")
//	public List<ExcelDetail> findAllDetail() {
//		return excelDetailService.findAll();
//	}
//
//	@GetMapping("/findone")
//	public List<ExcelDetail> findone() {
//		return excelDetailService.findAll();
//	}
}
