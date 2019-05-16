package com.rumango.median.iso.dao.service;

import java.util.List;

import com.rumango.median.entity.EmbedId;
import com.rumango.median.iso.dto.ColumnMappingDto;
import com.rumango.median.iso.dto.ExcelMappingDto;

public interface ExcelDetailService {

	public void convertToExcelDetail(List<ExcelMappingDto> excelMappingDtoList);

	public void convertToExcelDetailAndSave(List<ExcelMappingDto> excelMappingDtoList, EmbedId id);

	public void convertToExcelDetailAndSave(ColumnMappingDto columnMappingDto, EmbedId id);

	public void save();

	public void doProcess(EmbedId embedId);

}
