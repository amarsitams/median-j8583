package com.rumango.median.iso.serviceimpl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.entity.NodeMap;
import com.rumango.median.iso.dao.service.ValidationsService;
import com.rumango.median.iso.dto.IsoDetailsDto;
import com.rumango.median.iso.service.ConvertIso;
import com.rumango.median.iso.service.ModifyRequestAndResponse;
import com.rumango.median.iso.util.IsoUtil;

@Service
public class ModifyRequestAndResponseImpl implements ModifyRequestAndResponse {

	private final static Logger logger = Logger.getLogger(ModifyRequestAndResponseImpl.class);

	private Map<Integer, String> originalRequestISOMsg, modifiedRequestISOMsg, originalResponseISOMsg,
			modifiedResponseISOMsg;

	@Autowired
	private ConvertIso convertIso;

	@Autowired
	private ValidationsService validationsService;

	@Autowired
	private IsoUtil isoUtil;

	public String modifyRequest(IsoDetailsDto isoDetailsDto) throws Exception {
		logger.info("inside modifyRequest");
		String stringMessage;
		// unpack
		try {
			if (isoDetailsDto.getOriginalRequestString() == null | isoDetailsDto.getOriginalRequestString() == "")
				throw new Exception("Request message not valid");
			originalRequestISOMsg = isoUtil.unpackMessage(isoDetailsDto.getOriginalRequestString(),
					isoDetailsDto.getSourceVersion());
			isoUtil.logISOMsg(originalRequestISOMsg, "original Request message");

			/*
			 * Doing validations here
			 */
			originalRequestISOMsg = doValidations(originalRequestISOMsg, isoDetailsDto.getFromIp());

			// read and convert
			if (originalRequestISOMsg != null) {
				modifiedRequestISOMsg = convertIso.convertIso(isoDetailsDto.getSourceVersion(),
						isoDetailsDto.getTargetVersion(), originalRequestISOMsg);
			}

			isoUtil.logISOMsg(modifiedRequestISOMsg, "modified Request message");

			stringMessage = isoUtil.packMessage(modifiedRequestISOMsg, isoDetailsDto.getTargetVersion());

		} catch (Exception e) {
			logger.error(" Exception while converting request iso message ", e);
			throw e;
		}
		return stringMessage;
	}

	public String modifyResponse(IsoDetailsDto isoDetailsDto) throws Exception {
		String stringMessage;
		try {
			// unpack
			if (isoDetailsDto.getOriginalResponseString() == null | isoDetailsDto.getOriginalResponseString() == "")
				throw new Exception("Response message not valid");
			originalResponseISOMsg = isoUtil.unpackMessage(isoDetailsDto.getOriginalResponseString(),
					isoDetailsDto.getTargetVersion());

			isoUtil.logISOMsg(originalResponseISOMsg, "original response iso message");

			// read and convert
			modifiedResponseISOMsg = convertIso.convertIso(isoDetailsDto.getTargetVersion(),
					isoDetailsDto.getSourceVersion(), originalResponseISOMsg);

			isoUtil.logISOMsg(modifiedResponseISOMsg, "modified response message");
			// pack
			stringMessage = isoUtil.packMessage(modifiedResponseISOMsg, isoDetailsDto.getSourceVersion());
		} catch (Exception e) {
			logger.error("Exception while converting response iso message", e);
			throw e;
		}
		return stringMessage;
	}

	/**
	 * Does the validations for the iso message
	 * 
	 * @param isoMsg
	 * @param fromIp
	 * @return
	 */
	private Map<Integer, String> doValidations(Map<Integer, String> isoMsg, String fromIp) {
		logger.info("inside do valications");
		Map<Integer, String> temp = new LinkedHashMap<>(isoMsg);
		Map<Integer, NodeMap> validations = null;
		try {
			validations = validationsService.getAllValidations(fromIp);
		} catch (Exception e) {
			validations = null;
		}

		if (validations != null) {
			for (Map.Entry<Integer, NodeMap> entry : validations.entrySet()) {
				if (isoMsg.containsKey(entry.getKey())) {
					logger.warn(
							"Validations ::" + entry.getKey() + " :: " + validations.get(entry.getKey()).toString());
					temp.put(entry.getKey(), validations.get(entry.getKey()).getDef());
				}
			}
		}
		return temp;
	}

}
