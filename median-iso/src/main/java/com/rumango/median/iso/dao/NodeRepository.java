package com.rumango.median.iso.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rumango.median.iso.entity.Node;

public interface NodeRepository extends CrudRepository<Node, Long> {

	@Query(value = "select name from Node where id=?1 ")
	public String getNameFromId(Long id);

	public Optional<Node> findById(Long id);

	public default String getField(Long id) {
		return findById(id).orElse(null).getName();
	}
}
