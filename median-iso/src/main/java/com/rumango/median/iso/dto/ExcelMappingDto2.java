package com.rumango.median.iso.dto;

public class ExcelMappingDto2 {
	private String columnnName;
	private String dataType;
	private String columnDescription;
	private String defaultValue;
	private String excelColumn;
	private String dbColumnName;
	// Added for sending these from Front end
	private String extSysName;
	private String processName;

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

	public String getExcelColumn() {
		return excelColumn;
	}

	public void setExcelColumn(String excelColumn) {
		this.excelColumn = excelColumn;
	}

	public String getDbColumnName() {
		return dbColumnName;
	}

	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
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

}
