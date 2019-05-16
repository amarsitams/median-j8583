package com.rumango.median.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rumango.median.entity.EmbedId;
import com.rumango.median.entity.ExcelMappingDetails;

@Repository
public interface ExcelDetailsRepository extends CrudRepository<ExcelMappingDetails, Long> {

	List<ExcelMappingDetails> findByEmbedId(EmbedId embedId);

}
