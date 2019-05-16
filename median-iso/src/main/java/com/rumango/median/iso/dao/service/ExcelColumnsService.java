package com.rumango.median.iso.dao.service;

import java.util.List;

import com.rumango.median.entity.ExcelStaticColumns;

public interface ExcelColumnsService {

	public List<ExcelStaticColumns> findAll();
	
	public List<String> findAllColumnDescription();

}
