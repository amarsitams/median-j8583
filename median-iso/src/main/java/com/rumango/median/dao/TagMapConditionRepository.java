package com.rumango.median.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rumango.median.entity.TagMapCondition;

@Repository
public interface TagMapConditionRepository extends CrudRepository<TagMapCondition, Long> {

}
