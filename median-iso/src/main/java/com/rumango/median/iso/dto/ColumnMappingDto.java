/**
 * 
 */
package com.rumango.median.iso.dto;

import java.util.List;

/**
 * @author Amar
 *
 */
public class ColumnMappingDto {

	private List<ExcelMapping> excelMapping;
	private List<ExcelMappingDto> excelMappingDto;

	public List<ExcelMapping> getExcelMapping() {
		return excelMapping;
	}

	public void setExcelMapping(List<ExcelMapping> excelMapping) {
		this.excelMapping = excelMapping;
	}

	public List<ExcelMappingDto> getExcelMappingDto() {
		return excelMappingDto;
	}

	public void setExcelMappingDto(List<ExcelMappingDto> excelMappingDto) {
		this.excelMappingDto = excelMappingDto;
	}

}
