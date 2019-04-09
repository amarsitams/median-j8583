package com.rumango.median.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rumango.median.entity.MapSystems;

@Repository
public interface MapSystemsRepository extends CrudRepository<MapSystems, Long> {

	//@Query(value = "select from MapSystems a where a.fromSystem = ?1")
	public MapSystems getByfromSystem(String fromSystem);
}
