package com.rumango.median.iso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rumango.median.iso.entity.NodeMap;

@Repository
public interface NodeMapRepository extends CrudRepository<NodeMap, Long> {

//	@Query(value = "select query from NodeMap where tagMapId=?1 ")
//	public List<NodeMap> findByTagMapId2(Long id);
//
//	public List<NodeMap> findByTagMapId(Long id);
//
//	public default Long getId(Long id) {
//		return findByTagMapId(id).get(0).getId();
//	}

	public default String getQuery(long from, long to, String nodeName) {
		String node1 = "";
		try {
			node1 = getNode(from, nodeName);
		} catch (Exception e) {
			return "";
		}
		System.out.println("getQuery node1::" + node1);
		return query(from, to, node1);
	}

	@Query(value = "select query from node_maps where tag_map_id="
			+ "(select id from tag_maps where from_system_id= ?1) and "
			+ "to_system_id=?2 and node1= ?3 ", nativeQuery = true)
	public String query(long from, long to, String node1);

	@Query(value = "select id from nodes n where n.name=?2 and tag_id= "
			+ "(select id from tags where external_system_id =?1)", nativeQuery = true)
	public String getNode(long from, String nodeName);

	public default String getDefault(long from, long to, String nodeName) {
		String node1 = "";
		try {
			node1 = getNode(from, nodeName);
		} catch (Exception e) {
			return "";
		}
		System.out.println("get default node1::" + node1);
		return getDef(from, to, node1);
	}

	@Query(value = "select def from node_maps where tag_map_id="
			+ "(select id from tag_maps where from_system_id= ?1) and "
			+ "to_system_id=?2 and node1= ?3 ", nativeQuery = true)
	public String getDef(long from, long to, String node1);

	public default String getQuery(String from, String to, String nodeName) {
		String node1 = "";
		try {
			node1 = getNode1(from, nodeName);
		} catch (Exception e) {
			return "";
		}
		System.out.println("getQuery node1::" + node1);
		return query(from, to, node1);
	}

	public default String getDefault(String from, String to, String nodeName) {
		String node1 = "";
		try {
			node1 = getNode1(from, nodeName);
		} catch (Exception e) {
			return "";
		}
		System.out.println("getDefault node1::" + node1);
		return getDef(from, to, node1);
	}

	@Query(value = "select query from node_maps where tag_map_id="
			+ "(select id from tag_maps where from_system_id=(select id from external_systems a where a.destination = ?1) and "
			+ "to_system_id=(select id from external_systems a where a.destination = ?2)) and node1= ?3 ", nativeQuery = true)
	public String query(String from, String to, String node1);

	@Query(value = "select id from nodes n where n.name=?2 and tag_id= "
			+ "(select id from tags where external_system_id =(select id from external_systems a where a.destination = ?1))", nativeQuery = true)
	public String getNode1(String from, String nodeName);

	@Query(value = "select def from node_maps where tag_map_id="
			+ "(select id from tag_maps where from_system_id=(select id from external_systems a where a.destination = ?1) and "
			+ "to_system_id=(select id from external_systems a where a.destination = ?2)) and node1= ?3 ", nativeQuery = true)
	public String getDef(String from, String to, String node1);

	@Query(value = "select nm from NodeMap nm where nm.tagMapId="
			+ "(select id from TagMap where fromSystemId=(select id from ExternalSystem a where a.destination = ?1) and "
			+ "toSystemId=(select id from ExternalSystem a where a.destination = ?2))")
	public List<NodeMap> getNodes(String from, String to);
}
