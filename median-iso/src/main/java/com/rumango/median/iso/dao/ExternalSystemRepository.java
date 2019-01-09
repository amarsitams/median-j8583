package com.rumango.median.iso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rumango.median.iso.entity.ExternalSystem;

@Repository
public interface ExternalSystemRepository extends CrudRepository<ExternalSystem, Long> {

	@Query(value = "select id from ExternalSystem a where a.extSysCode = ?1")
	public List<Integer> findIdBySysCode(String extSysCode);

	public ExternalSystem findByExtSysCode(String extSysCode);

	public default Long getLongId(String extSysCode) {
		return findByExtSysCode(extSysCode).getId();
	}

	public default String getStringId(String extSysCode) {
		return findByExtSysCode(extSysCode).getId() + "";
	}
}
