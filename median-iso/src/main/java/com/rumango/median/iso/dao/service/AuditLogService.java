package com.rumango.median.iso.dao.service;

import java.util.List;
import java.util.Map;

import com.rumango.median.iso.entity.AuditLog;

public interface AuditLogService {

	public void saveData(Map<String, String> statusMap);

	public AuditLog findById(int id);

	public AuditLog findByIp(String ip);

	public List<AuditLog> getAllLogs();
}
