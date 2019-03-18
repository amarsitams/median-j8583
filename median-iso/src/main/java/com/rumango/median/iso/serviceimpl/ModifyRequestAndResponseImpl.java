package com.rumango.median.iso.serviceimpl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.iso.dto.IsoDetailsDto;
import com.rumango.median.iso.service.ConvertIso;
import com.rumango.median.iso.service.IsoUtil;
import com.rumango.median.iso.service.ModifyRequestAndResponse;

@Service
public class ModifyRequestAndResponseImpl implements ModifyRequestAndResponse {

	private final static Logger logger = Logger.getLogger(ModifyRequestAndResponseImpl.class);

	private Map<Integer, String> originalRequestISOMsg, modifiedRequestISOMsg, originalResponseISOMsg,
			modifiedResponseISOMsg;

	@Autowired
	private ConvertIso convertIso;

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
}
