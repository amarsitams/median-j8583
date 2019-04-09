package com.rumango.median.iso.dao.service;

import java.util.List;
import java.util.Map;

import com.rumango.median.entity.MedianAuditLogs;
import com.rumango.median.iso.dto.IsoDetailsDto;

public interface AuditLogService {

	public void saveData(IsoDetailsDto isoDetailsDto);

	public void saveData(Map<String, String> statusMap);

	public MedianAuditLogs findById(int id);

	public MedianAuditLogs findByIp(String ip);

	public List<MedianAuditLogs> getAllLogs();
}
