package com.rumango.median.iso.dao.serviceimpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rumango.median.iso.dao.AuditLogRepository;
import com.rumango.median.iso.dao.service.AuditLogService;
import com.rumango.median.iso.entity.MedianAuditLogs;
import com.rumango.median.iso.service.IsoConstants;
import com.rumango.median.iso.service.IsoUtil;

@Service
public class AuditLogServiceImpl implements AuditLogService {

	private final static Logger logger = Logger.getLogger(AuditLogServiceImpl.class);
	// private boolean isPCIMask = true;

	@Autowired
	private IsoUtil isoUtil;

	@Autowired
	private AuditLogRepository auditLogRepository;

	@Override
	@Transactional
	public void saveData(Map<String, String> statusMap) {
		logger.info(" Inside save data ");
		try {
			MedianAuditLogs auditLog = new MedianAuditLogs();
			auditLog.setExternalSystemId(
					statusMap.containsKey("extSysId") ? Long.parseLong(statusMap.get("extSysId")) : 4L);
			auditLog.setCreatedAt(
					statusMap.containsKey("createdAt") ? Timestamp.valueOf(statusMap.get("createdAt")) : null);
			auditLog.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			auditLog.setIpAddress(statusMap.containsKey("IP") ? statusMap.get("IP") : null);
			auditLog.setExternalSystemName(
					statusMap.containsKey("externalSystemName") ? statusMap.get("externalSystemName") : "ITAX");
			auditLog.setMedianUuid(statusMap.containsKey("uuid") ? statusMap.get("uuid") : null);
			auditLog.setRequestStatus(
					statusMap.containsKey("receivedMsgStatus") ? statusMap.get("receivedMsgStatus") : "FAIL");
			auditLog.setResponseStatus(
					statusMap.containsKey("sentMsgStatus") ? statusMap.get("sentMsgStatus") : "FAIL");
			auditLog.setReason(statusMap.containsKey("reason") ? statusMap.get("reason") : "");

			auditLog.setOriginal_request_isomsg(
					statusMap.containsKey("originalRequestString") ? statusMap.get("originalRequestString") : null);
			auditLog.setModified_request_isomsg(
					statusMap.containsKey("modifiedRequestString") ? statusMap.get("modifiedRequestString") : null);
			auditLog.setOriginal_response_isomsg(
					statusMap.containsKey("originalResponseString") ? statusMap.get("originalResponseString") : null);
			auditLog.setModified_response_isomsg(
					statusMap.containsKey("modifiedResponseString") ? statusMap.get("modifiedResponseString") : null);

			auditLog.setModified_response_isomsg(
					statusMap.containsKey("modifiedResponseString") ? statusMap.get("modifiedResponseString") : null);

			auditLog.setOriginal_request_splitted(statusMap.containsKey("originalRequestString")
					? isoUtil.toCsv(statusMap.get("originalRequestString"), IsoConstants.version_93)
					: null);
			auditLog.setModified_request_splitted(statusMap.containsKey("modifiedRequestString")
					? isoUtil.toCsv(statusMap.get("modifiedRequestString"), IsoConstants.version_93)
					: null);
			auditLog.setOriginal_response_splitted(statusMap.containsKey("originalResponseString")
					? isoUtil.toCsv(statusMap.get("originalResponseString"), IsoConstants.version_93)
					: null);
			auditLog.setModified_response_splitted(statusMap.containsKey("modifiedResponseString")
					? isoUtil.toCsv(statusMap.get("modifiedResponseString"), IsoConstants.version_93)
					: null);

			logger.info(auditLog.toString());
			auditLogRepository.save(auditLog);

		} catch (Exception e) {
			logger.error("Exception while saving  data ", e);
		}
	}

	public String mask(String accNo) {
		StringBuilder sb = new StringBuilder(accNo);
		if (sb.length() > 6) {
			sb.replace(6, accNo.length(), "X");
			return sb.toString();
		} else
			return sb.toString();
	}

	@Override
	public MedianAuditLogs findById(int id) {
		return auditLogRepository.findByIpAddress("192.168.1.4").get(0);
	}

	@Override
	public List<MedianAuditLogs> getAllLogs() {
		return (List<MedianAuditLogs>) auditLogRepository.findAll();
	}

	@Override
	public MedianAuditLogs findByIp(String ip) {
		return auditLogRepository.findByIpAddress(ip).get(0);
	}
}
