package com.rumango.median.iso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rumango.median.iso.entity.NodeMap;

public interface NodeMapRepository extends CrudRepository<NodeMap, Long> {

	@Query(value = "select query from NodeMap where tagMapId=?1 ")
	public List<NodeMap> findByTagMapId2(Long id);

	public List<NodeMap> findByTagMapId(Long id);

	public default Long getId(Long id) {
		return findByTagMapId(id).get(0).getId();
	}

	@Query(value = "select query from NodeMap where tagMapId="
			+ "(select id from TagMap where fromSystemId=(select id from ExternalSystem a where a.destination = ?1) and "
			+ "toSystemId=(select id from ExternalSystem a where a.destination = ?2)) and node1=(select id from Node n where n.name=?3)")
	public String getQuery(String from, String to, String nodeName);

	@Query(value = "select default_ from NodeMap where tagMapId="
			+ "(select id from TagMap where fromSystemId=(select id from ExternalSystem a where a.destination = ?1) and "
			+ "toSystemId=(select id from ExternalSystem a where a.destination = ?2)) and node1=(select id from Node n where n.name=?3)")
	public String getDefault(String from, String to, String nodeName);

	@Query(value = "select * from NodeMap where tagMapId="
			+ "(select id from TagMap where fromSystemId=(select id from ExternalSystem a where a.destination = ?1) and "
			+ "toSystemId=(select id from ExternalSystem a where a.destination = ?2))")
	public List<NodeMap> getNodes(String from, String to);
}
