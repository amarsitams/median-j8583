package com.rumango.median.iso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rumango.median.iso.entity.TagMap;

public interface TagMapRepository extends CrudRepository<TagMap, Long> {

	@Query(value = "select id from TagMap where fromSystemId=?1 and toSystemId=?2 ")
	public Long findByFromAndTo(Long from, Long to);

	public List<TagMap> findByFromSystemIdAndToSystemId(Long from, Long to);

	public default Long getId(Long from, Long to) {
		return findByFromSystemIdAndToSystemId(from, to).get(0).getId();
	}

	public default Long getToSystemId(Long fromId) {
		return findByFromSystemId(fromId).getToSystemId();
	}
	
	public default Long getTagMapId(Long fromId) {
		return findByFromSystemId(fromId).getId();
	}

	public TagMap findByFromSystemId(Long from);
}
