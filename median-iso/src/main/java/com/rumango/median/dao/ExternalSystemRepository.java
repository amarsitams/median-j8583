package com.rumango.median.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rumango.median.entity.ExternalSystem;

@Repository
public interface ExternalSystemRepository extends CrudRepository<ExternalSystem, Long> {

	@Query(value = "select processCode from ExternalSystem a where a.extSysName = ?1")
	public List<String> findProcessByExtSysName(String extSysName);

	@Query(value = "select id from ExternalSystem a where a.extSysCode = ?1")
	public List<Integer> findIdBySysCode(String extSysCode);

	public ExternalSystem findByExtSysCode(String extSysCode);

	public default Long getLongId(String extSysCode) {
		return findByExtSysCode(extSysCode).getId();
	}

	public default String getStringId(String extSysCode) {
		return findByExtSysCode(extSysCode).getId() + "";
	}

	public default String getModuleCode(Long extSysId) {
		return getModuleCodeFromId(extSysId);
	}

	public default String getModuleCode(String destination) {
		return findByDestination(destination).getModuleCode();
	}

	public default Long getExtSysId(String destination) {
		return findByDestination(destination).getId();
	}

	public ExternalSystem findByDestination(String destination);

	// public ExternalSystem findByDestinationContaining(String destination);

	@Query(value = "select destination from ExternalSystem a where a.id = ?1")
	public String getDestinationFromId(Long id);

	@Query(value = "select a from ExternalSystem a where a.id = ?1")
	public ExternalSystem getDestinationFromIp(String id);

	@Query(value = "select moduleCode from ExternalSystem a where a.id = ?1")
	public String getModuleCodeFromId(Long id);

	/*
	 * DEbo
	 * 
	 */
	@Query(value = "SELECT es FROM ExternalSystem es WHERE es.id = :id")
	ExternalSystem getOneExternalSystemById(@Param("id") long id);

	@Query(value = " SELECT es FROM ExternalSystem es")
	List<ExternalSystem> getAllExternalSystems();

	@Query(value = "SELECT es FROM ExternalSystem es WHERE es.id = :id")
	List<ExternalSystem> getExternalSystemById(@Param("id") long id);

	@Query(value = "SELECT es FROM ExternalSystem es WHERE es.extSysCode = :extSysCode")
	ExternalSystem getExternalSystemIdByExternalSystemCode(@Param("extSysCode") String extSysCode);

	@Query(value = "SELECT es FROM ExternalSystem es WHERE es.extSysCode = :extSysCode")
	ExternalSystem getExternalSysDetail(@Param("extSysCode") String extSysCode);
}
