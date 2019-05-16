package com.rumango.median.iso.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/excel") // /excelcolumns/postColumnMapping/ext1/ps1
public class ExcelFileUploadController {

	private final static Logger logger = Logger.getLogger(ExcelFileUploadController.class);

	/**
	 * Getting the mapping data here
	 * 
	 * @param extSys
	 * @param processName
	 * @param mappingData
	 */
	@PostMapping("/uploadfile/")
	public void postColumnMapping(@Valid @RequestBody MultipartFile multipartFile) {
		logger.info("****postColumnMapping****");
		if (null == multipartFile || "".equalsIgnoreCase(multipartFile.getName()))
			return;
// 			save file to directory before saving data to table
//			FileUploadResponseDTO fileRes = fileManageService.saveToDirectory(riskUploadFileRequestDTO.getFileName());
//			logger.info("****FCDController.postRiskUpload FileName--" + fileRes.getFileName());
//
//			if (null != fileRes && fileRes.getReturnCode() == 0) {
//				// saveStatus = fcdService.saveFromExcelToTable2(riskUploadFileRequestDTO,
//				// fileRes.getFileName());
//				saveStatus = fcdService.saveFromExcelToTable(riskUploadFileRequestDTO, fileRes.getFileName());
//				logger.info("****FCDController.postRiskUpload-savedatastatus--" + saveStatus + "****");
//			}
//		}
//
//		ModelAndView model = new ModelAndView();
//		List<RiskMaintenanceDetails> fileTypeList = fcdService.getReportAndTableNames();
//		if (null != fileTypeList)
//			logger.info("****FCDController.risk upload-fileTypeListSize= " + fileTypeList.size() + "***");
//		model.addObject("fileTypes", fileTypeList);
//		// if (!saveStatus)
//		model.addObject("message", saveStatus ? "Upload Success" : "Upload failed");
//		model.setViewName("riskUpload");
//		return model;

	}
}
