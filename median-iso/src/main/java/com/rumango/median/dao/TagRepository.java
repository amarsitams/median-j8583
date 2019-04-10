/**						
 * 
 */
package com.rumango.median.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rumango.median.entity.Tag;

/**
 * @author lei2o
 *
 */

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
	@Query(value = "SELECT t FROM Tag t")
	List<Tag> getAllTags();

	@Query(value = "SELECT p FROM Tag p WHERE p.id = :id")
	List<Tag> getTagById(@Param("id") long id);

	@Query(value = "SELECT p FROM Tag p WHERE p.externalSystemId = :externalSystemId")
	Tag getTagIdByExternalSystemId(@Param("externalSystemId") long externalSystemId);

}
