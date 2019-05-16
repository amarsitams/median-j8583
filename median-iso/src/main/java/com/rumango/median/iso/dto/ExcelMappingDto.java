package com.rumango.median.iso.dto;

public class ExcelMappingDto {
	private Long id;
	private String columnnName;
	private String dataType;
	private String columnDescription;
	private boolean isMandatory;
	private String defaultValue;
	private String mappingColumn;
	// Added for sending these from Front end
	private String extSysName;
	private String processName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColumnnName() {
		return columnnName;
	}

	public void setColumnnName(String columnnName) {
		this.columnnName = columnnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColumnDescription() {
		return columnDescription;
	}

	public void setColumnDescription(String columnDescription) {
		this.columnDescription = columnDescription;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getMappingColumn() {
		return mappingColumn;
	}

	public void setMappingColumn(String mappingColumn) {
		this.mappingColumn = mappingColumn;
	}

	public String getExtSysName() {
		return extSysName;
	}

	public void setExtSysName(String extSysName) {
		this.extSysName = extSysName;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	@Override
	public String toString() {
		return "ExcelMappingDto [columnnName=" + columnnName + ", mappingColumn=" + mappingColumn + ", extSysName="
				+ extSysName + ", processName=" + processName + "]";
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
}
