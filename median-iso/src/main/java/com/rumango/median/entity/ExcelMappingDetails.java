/**
 * 
 */
package com.rumango.median.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author Amar
 *
 */

@Entity
@Table(name = "excel_mapping_details")
@JsonAutoDetect
public class ExcelMappingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private EmbedId embedId;
	private String columnnName;
	private String dataType;
	private String defaultValue;
	private String excelMappingColumn;
	private String tableMappingColumn;
	private boolean isMandatory;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EmbedId getEmbedId() {
		return embedId;
	}

	public void setEmbedId(EmbedId embedId) {
		this.embedId = embedId;
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

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getExcelMappingColumn() {
		return excelMappingColumn;
	}

	public void setExcelMappingColumn(String excelMappingColumn) {
		this.excelMappingColumn = excelMappingColumn;
	}

	public String getTableMappingColumn() {
		return tableMappingColumn;
	}

	public void setTableMappingColumn(String tableMappingColumn) {
		this.tableMappingColumn = tableMappingColumn;
	}

	/**
	 * @return the isMandatory
	 */
	public boolean isMandatory() {
		return isMandatory;
	}

	/**
	 * @param isMandatory the isMandatory to set
	 */
	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
}
