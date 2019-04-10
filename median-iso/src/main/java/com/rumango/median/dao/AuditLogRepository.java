package com.rumango.median.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rumango.median.entity.MedianAuditLogs;

@Repository
public interface AuditLogRepository extends CrudRepository<MedianAuditLogs, Long> {

	public List<MedianAuditLogs> findById(int id);

	List<MedianAuditLogs> findByIpAddress(String id);

}
