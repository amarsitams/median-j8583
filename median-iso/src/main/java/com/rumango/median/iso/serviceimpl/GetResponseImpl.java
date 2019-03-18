package com.rumango.median.iso.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.iso.dao.service.AuditLogService;
import com.rumango.median.iso.dao.service.ValidationsService;
import com.rumango.median.iso.dto.IsoDetailsDto;
import com.rumango.median.iso.service.GetResponse;
import com.rumango.median.iso.service.ModifyRequestAndResponse;

@Service
//@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GetResponseImpl implements GetResponse {

	public GetResponseImpl() {
		logger.info("GetResponseImpl object created");
	}

	@Autowired
	private ValidationsService validationsService;

	@Autowired
	private ModifyRequestAndResponse modifyRequestAndResponse;

	@Autowired
	private AuditLogService auditLogService;

	private String responseMsg;

	private final static Logger logger = Logger.getLogger(GetResponseImpl.class);

	public String convertAndRespond(String stringMessage, IsoDetailsDto dto) {
		responseMsg = "";
		logger.info("inside convertAndRespond ");
		dto.setOriginalRequestString(stringMessage);
		try {
			if (setMandatory(dto) && checkIfAllowed(dto)) {
				// originalRequestString
				dto.setModifiedRequestString(modifyRequestAndResponse.modifyRequest(dto).substring(5));
				// dto.setModifiedRequestString(modifyRequestAndResponse.modifyRequest(stringMessage,
				// "87").substring(5));
				logger.info(" modifiedRequestString " + dto.getModifiedRequestString());

				if (dto.getModifiedRequestString() != null)
					dto.setReceivedMsgStatus("SUCCESS");

				// originalResponseString
				dto.setOriginalResponseString(getResponse(dto.getModifiedRequestString(), dto));
				logger.info("originalResponseString  " + dto.getOriginalResponseString());
				dto.setModifiedResponseString(modifyRequestAndResponse.modifyResponse(dto).substring(5));
//				dto.setModifiedResponseString(
//						modifyRequestAndResponse.modifyResponse(dto.getOriginalResponseString(), "87").substring(5));
				if (dto.getModifiedResponseString() != null)
					dto.setSentMsgStatus("SUCCESS");
				logger.info(" modifiedResponseString " + dto.getModifiedResponseString());
				responseMsg = dto.getModifiedResponseString();
			}
		} catch (Exception e) {
			dto.setModifiedResponseString("");
			logger.warn("Exception inside convertAndRespond of IsoMessageConvertor ", e);
		} finally {
			logger.info("Inside finally");
			try {
				auditLogService.saveData(dto);
			} catch (Exception e) {
				logger.warn("Exception while saving log information ", e);
			}
			dto.clear(dto);
		}

		return responseMsg;

	}

	public boolean setMandatory(IsoDetailsDto dto) {
		logger.info("Setting mandatory fields for processing request");
		// fromIp
		try {
			dto.setSourceVersion(validationsService.getModuleCode(dto.getFromIp()));
			logger.info("sourceVersion:: " + dto.getSourceVersion());
			dto.setTargetVersion(validationsService.getDestinationModuleCode(dto.getFromIp()));
			logger.info("targetVersion:: " + dto.getTargetVersion());
			return true;
		} catch (Exception e) {
			// logger.error("Exception while Setting mandatory fields for processing
			// request", e);
			return false;
		}
	}

	private final boolean checkIfAllowed(IsoDetailsDto dto) {
		logger.info("checking if iso message is allowed or not");
		try {
			if (!dto.getSourceVersion().equalsIgnoreCase("") && !dto.getTargetVersion().equalsIgnoreCase("")
					&& dto.getTargetVersion().length() == 2 && dto.getTargetVersion().length() == 2) {
				logger.info("checking if iso message is allowed or not :: " + true);
				return true;
			} else {
				logger.info("checking if iso message is allowed or not :: " + false);
				return false;
			}

		} catch (Exception e) {
			// logger.error("Exception while checking if iso message is allowed or not", e);
			return false;
		}
	}

	private String getResponse(String isoMessage, IsoDetailsDto dto) throws Exception {
		logger.info("inside getResponse of IsoMessageConvertionImpl");
		int port = 2108;
		String targetIp = "192.168.0.100";
		try {
			try {
				String ipAndPort = validationsService.getDestIpAndPort(dto.getFromIp());
				targetIp = ipAndPort.substring(0, targetIp.indexOf(":"));
				port = Integer.parseInt(ipAndPort.substring(targetIp.indexOf(":")));
				logger.info("IP :: " + targetIp + "  PORT:  " + port);

			} catch (Exception e) {
				logger.error("Target Ip not available, connecting to default ");
			}
			dto.setResponse(
					"0200F27A200108E0800000000000040000001011404630001000000000001070000130094304        09430409430401300130404069405005942924A3FBBMOB00002000000000105817test|Kimani|Elizabeth||0008527001       003130010008527001");
		} catch (Exception e) {
			dto.setResponse("");
			throw e;
		}
		return dto.getResponse();
	}

}
