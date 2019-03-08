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

//
//	@Override
//	public String modifyRequest(String requestMsg, String isoVersion) throws Exception {
//		logger.info("inside modifyRequest");
//		String stringMessage;
//		// unpack
//		try {
//			if (requestMsg == null | requestMsg == "")
//				throw new Exception("Request message not valid");
//			originalRequestISOMsg = isoUtil.unpackMessage(requestMsg, isoVersion);
//			isoUtil.logISOMsg(originalRequestISOMsg, "original Request message");
//
//			// read and convert
//			if (originalRequestISOMsg != null) {
//				modifiedRequestISOMsg = validateFields.incoming(originalRequestISOMsg);
//			}
//
//			isoUtil.logISOMsg(modifiedRequestISOMsg, "modified Request message");
//
//			stringMessage = isoUtil.packMessage(modifiedRequestISOMsg, isoVersion);
//
//		} catch (Exception e) {
//			logger.error(" Exception while converting request iso message ", e);
//			throw e;
//		}
//		return stringMessage;
//	}
//
//	@Override
//	public String modifyResponse(String responseMsg, String isoVersion) throws Exception {
//		String stringMessage;
//		try {
//			// unpack
//			if (responseMsg == null | responseMsg == "")
//				throw new Exception("Response message not valid");
//			originalResponseISOMsg = isoUtil.unpackMessage(responseMsg, isoVersion); // TODO CBS ISO version
//			// logger.info("original_response_isomsg.toString()" + new
//			// String(originalResponseISOMsg.pack()));
//
//			// isoUtil.logISOMsg(originalResponseISOMsg, "original response iso message");
//
//			// isoMap.put("originalResponseISOMsg", originalResponseISOMsg);
//			// read and convert
//			modifiedResponseISOMsg = validateFields.outgoing(originalResponseISOMsg); // TODO Convert and validate
//			// isoMap.put("modifiedResponseISOMsg", modifiedResponseISOMsg);
//
//			// isoUtil.logISOMsg(modifiedResponseISOMsg, "modified response message");
//
//			// pack
//			stringMessage = isoUtil.packMessage(modifiedResponseISOMsg, isoVersion); // TODO SOurce ISO version
//		} catch (Exception e) {
//			logger.error("Exception while converting response iso message", e);
//			throw e;
//		}
//		return stringMessage;
//	}
//
// public String modifyRequest(String requestMsg, Map<String, String> map)
// throws Exception {
//		logger.info("inside modifyRequest");
//		String stringMessage;
//		// unpack
//		try {
//			if (requestMsg == null | requestMsg == "")
//				throw new Exception("Request message invalid");
//			originalRequestISOMsg = isoUtil.unpackMessage(requestMsg, map.get("sourceVersion")); // TODO Source ISO
//			isoUtil.logISOMsg(originalRequestISOMsg, "original Request message");
//
//			// Convert iso message and validate fileds
//			if (originalRequestISOMsg != null) {
//				modifiedRequestISOMsg = originalRequestISOMsg;
//				// modifiedRequestISOMsg =
//				// validateFields.incoming(convertIso.iso87TO93(originalRequestISOMsg));
//			}
//			isoUtil.logISOMsg(modifiedRequestISOMsg, "modified Request message");
//			stringMessage = isoUtil.packMessage(modifiedRequestISOMsg, map.get("targetVersion")); // TODO CBS ISO
//
//		} catch (Exception e) {
//			logger.error(" Exception while converting request iso message ", e);
//			throw e;
//		}
//		return stringMessage; // clientSocket.run(stringMessage);
//	}
//
//	public String modifyResponse(String responseMsg, Map<String, String> map) throws Exception {
//		String stringMessage;
//		try {
//			// unpack
//			if (responseMsg == null | responseMsg == "")
//				throw new Exception("Response message invalid");
//			originalResponseISOMsg = isoUtil.unpackMessage(responseMsg, map.get("targetVersion")); // TODO CBS ISO
//			// logger.info("original_response_isomsg.toString()" + new
//			// String(originalResponseISOMsg.pack()));
//			isoUtil.logISOMsg(originalResponseISOMsg, "original response iso message");
//			// isoMap.put("originalResponseISOMsg", originalResponseISOMsg);
//			// read and convert
//			modifiedResponseISOMsg = originalResponseISOMsg; // TODO Convert and validate
//			// modifiedResponseISOMsg = validateFields.outgoing(originalResponseISOMsg);
//			// isoMap.put("modifiedResponseISOMsg", modifiedResponseISOMsg);
//			isoUtil.logISOMsg(modifiedResponseISOMsg, "modified response message");
//			// pack
//			stringMessage = isoUtil.packMessage(modifiedResponseISOMsg, map.get("sourceVersion")); // TODO SOurce ISO
//		} catch (Exception e) {
//			logger.error("Exception while converting response iso message", e);
//			throw e;
//		}
//		return stringMessage;
//	}

//	private Map<Integer, String> validateResponse(Map<Integer, String> isoMessage) {
//		try {
//			// validateFields.outgoing(isoMessage);
//			return isoMessage;
//		} catch (Exception e) {
//			logger.warn("Exception while updateRequestIso ", e);
//		}
//		return isoMessage;
//	}

//	private Map<Integer, String> validateRequest(Map<Integer, String> isoMessage) {
//		try {
//			// validateFields.incoming(isoMessage);
//			return isoMessage;// validateFields.iso87TO93(isoMessage);
//		} catch (Exception e) {
//			logger.warn("Exception while updateResponseIso ", e);
//		}
//		return isoMessage;
//	}

//	private String mask(String accNo) {
//		StringBuilder sb = new StringBuilder(accNo);
//		if (sb.length() > 6)
//			sb.replace(6, accNo.length(), "X");
//		return sb.toString();
//	}
