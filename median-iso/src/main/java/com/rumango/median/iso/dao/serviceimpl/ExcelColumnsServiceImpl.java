package com.rumango.median.iso.dao.serviceimpl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.dao.ExcelColumnsRepository;
import com.rumango.median.entity.ExcelStaticColumns;
import com.rumango.median.iso.dao.service.ExcelColumnsService;

@Service
public class ExcelColumnsServiceImpl implements ExcelColumnsService {

	@Autowired
	private ExcelColumnsRepository excelColumnsRepository;

	@Override
	public List<ExcelStaticColumns> findAll() {
		return (List<ExcelStaticColumns>) excelColumnsRepository.findAll();
	}

	@Override
	public List<String> findAllColumnDescription() {
		List<String> columnDescriptions = new LinkedList<>();
		for (ExcelStaticColumns excelColumns : findAll())
			columnDescriptions.add(excelColumns.getColumnnDescription());
		return columnDescriptions;
	}
}
