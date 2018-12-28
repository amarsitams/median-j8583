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
import com.rumango.median.iso.entity.AuditLog;
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
			AuditLog auditLog = new AuditLog();
			auditLog.setExternalSystemId(10L);
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
	public AuditLog findById(int id) {
		return auditLogRepository.findByIpAddress("192.168.1.4").get(0);
	}

	@Override
	public List<AuditLog> getAllLogs() {
		return (List<AuditLog>) auditLogRepository.findAll();
	}

	@Override
	public AuditLog findByIp(String ip) {
		return auditLogRepository.findByIpAddress(ip).get(0);
	}
}

//	// Modified for postgre sql
//	@Transactional
//	public void saveData(ISOMsg originalRequestISOMsg, ISOMsg modifiedRequestISOMsg, ISOMsg originalResponseISOMsg,
//			ISOMsg modifiedResponseISOMsg, Map<String, String> statusMap) {
//		logger.info(" Inside save data ");
//		try {
//			AuditLog log = new AuditLog();
//			log.setTimeStamp(new Timestamp(System.currentTimeMillis()));
//			log.setIpAddress(statusMap.containsKey("IP") ? statusMap.get("IP") : null);
//			log.setMedianUuid(statusMap.containsKey("uuid") ? statusMap.get("uuid") : null);
//			log.setRequestStatus(
//					statusMap.containsKey("receivedMsgStatus") ? statusMap.get("receivedMsgStatus") : "FAIL");
//			log.setResponseStatus(statusMap.containsKey("sentMsgStatus") ? statusMap.get("sentMsgStatus") : "FAIL");
//
//			log.setOriginal_request(originalRequestISOMsg == null ? statusMap.get("originalRequestISOMsg")
//					: isoToString(originalRequestISOMsg, "93"));
//			log.setModified_request_isomsg(
//					modifiedRequestISOMsg == null ? null : isoToString(modifiedRequestISOMsg, "87"));
//			log.setOriginal_response_isomsg(
//					originalResponseISOMsg == null ? null : isoToString(originalResponseISOMsg, "87"));
//			log.setModified_response_isomsg(
//					modifiedResponseISOMsg == null ? null : isoToString(modifiedResponseISOMsg, "93"));
//			log.setOriginal_request_splitted(originalRequestISOMsg == null ? null
//					: isoSplittedString(originalRequestISOMsg, "originalRequestISOMsg"));
//			log.setModified_request_splitted(modifiedRequestISOMsg == null ? null
//					: isoSplittedString(modifiedRequestISOMsg, "modifiedRequestISOMsg"));
//			log.setOriginal_response_splitted(originalResponseISOMsg == null ? null
//					: isoSplittedString(originalResponseISOMsg, "originalResponseISOMsg"));
//			log.setModified_response_splitted(modifiedResponseISOMsg == null ? null
//					: isoSplittedString(modifiedResponseISOMsg, "modifiedResponseISOMsg"));
//			auditLogRepository.save(log);
//			logger.info(log.toString());
//		} catch (Exception e) {
//			logger.info("Exception while saving  data ", e);
//		}
//		// logger.info(" saved data successfully");
//	}
