package com.rumango.median.iso.dto;

public class ExcelMapping {
	private String columnnName;
	private String dataType;
	private String defaultValue;
	private String mappingColumn;
	private boolean isMandatory;

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

	@Override
	public String toString() {
		return "ExcelMapping [columnnName=" + columnnName + ", dataType=" + dataType + ", defaultValue=" + defaultValue
				+ ", mappingColumn=" + mappingColumn + "]";
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

}
