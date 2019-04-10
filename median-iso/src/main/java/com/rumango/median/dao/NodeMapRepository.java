package com.rumango.median.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rumango.median.entity.NodeMap;

@Repository
public interface NodeMapRepository extends CrudRepository<NodeMap, Long> {

	public List<NodeMap> findByTagMapId(Long id);

	/*
	 * Debo
	 */
	@Query(value = " SELECT nmap FROM NodeMap nmap")
	List<NodeMap> getAllNodeMaps();

	@Query(value = " SELECT nmap FROM NodeMap nmap WHERE nmap.id = :id ")
	List<NodeMap> getNode1Node2FromNodeMaps(@Param("id") long id);

	@Query(value = " SELECT nmap FROM NodeMap nmap WHERE nmap.tagMapId = :tagMapId ")
	List<NodeMap> getNodeMapByPassingTagMapId(@Param("tagMapId") long tagMapId);

	@Query(" SELECT nmap FROM NodeMap nmap WHERE nmap.tagMapId = :tagMapId AND nmap.node1 = :node1 AND nmap.node2 = :node2")
	public NodeMap getDefaultValueFromNodeMap(@Param("tagMapId") long tagMapId, @Param("node1") String node1,
			@Param("node2") String node2);
}
