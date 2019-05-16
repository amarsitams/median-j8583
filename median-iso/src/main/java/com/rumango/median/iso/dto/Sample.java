package com.rumango.median.iso.dto;

import java.util.List;

public class Sample {

	private ExternalSystemDto externalSystemDto;
	private List<ExcelMappingDto> mappinglist;

	public ExternalSystemDto getExternalSystemDto() {
		return externalSystemDto;
	}

	public void setExternalSystemDto(ExternalSystemDto externalSystemDto) {
		this.externalSystemDto = externalSystemDto;
	}

	public List<ExcelMappingDto> getMappinglist() {
		return mappinglist;
	}

	public void setMappinglist(List<ExcelMappingDto> mappinglist) {
		this.mappinglist = mappinglist;
	}
}
