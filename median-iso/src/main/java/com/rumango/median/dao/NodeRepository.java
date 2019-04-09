package com.rumango.median.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rumango.median.entity.Node;

@Repository
public interface NodeRepository extends CrudRepository<Node, Long> {

	@Query(value = "select name from Node where id=?1 ")
	public String getNameFromId(Long id);

	public Optional<Node> findById(Long id);

	public default String getField(Long id) {
		return findById(id).orElse(null).getName();
	}
	/*
	 * Debo
	 */

	@Query(value = "SELECT n FROM Node n")
	public List<Node> getAllNodes();

	@Query(value = "SELECT n FROM Node n WHERE id= :id AND tagId= :tagId")
	public Node getNodeNameFromIdAndTagId(@Param("id") Long id, @Param("tagId") Long tagId);

}
