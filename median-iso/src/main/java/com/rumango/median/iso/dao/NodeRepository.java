package com.rumango.median.iso.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rumango.median.iso.entity.Node;

public interface NodeRepository extends CrudRepository<Node, Long> {

	@Query(value = "select name from Node where id=?1 ")
	public String getNameFromId(Long id);

}
