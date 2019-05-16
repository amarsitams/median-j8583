package com.rumango.median.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rumango.median.entity.ExcelStaticColumns;

@Repository
public interface ExcelColumnsRepository extends CrudRepository<ExcelStaticColumns, Long> {
}
