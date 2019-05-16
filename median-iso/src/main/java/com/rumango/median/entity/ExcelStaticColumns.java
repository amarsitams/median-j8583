package com.rumango.median.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Entity
@Table(name = "excel_static_columns")
@JsonAutoDetect
public class ExcelStaticColumns implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "column_name")
	private String columnnName;

	@Column(name = "data_type")
	private String dataType;

	@Column(name = "column_description")
	private String columnDescription;

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

	public String getColumnnDescription() {
		return columnDescription;
	}

	public void setColumnnDescription(String columnnDescription) {
		this.columnDescription = columnnDescription;
	}

}