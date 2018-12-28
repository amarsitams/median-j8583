package com.rumango.median.iso.serviceimpl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.iso.service.IsoUtil;
import com.rumango.median.iso.service.ModifyRequestAndResponse;
import com.rumango.median.iso.service.ValidateFields;

@Service
public class ModifyRequestAndResponseImpl implements ModifyRequestAndResponse {

	private final static Logger logger = Logger.getLogger(ModifyRequestAndResponseImpl.class);

	private Map<Integer, String> originalRequestISOMsg, modifiedRequestISOMsg, originalResponseISOMsg,
			modifiedResponseISOMsg;

	@Autowired
	private ValidateFields validateFields;

	@Autowired
	private IsoUtil isoUtil;

	@Override
	public String modifyRequest(String requestMsg, String isoVersion) throws Exception {
		logger.info("inside modifyRequest");
		String stringMessage;
		// unpack
		try {
			if (requestMsg == null | requestMsg == "")
				throw new Exception("Request message invalid");
			originalRequestISOMsg = isoUtil.unpackMessage(requestMsg, isoVersion);
			isoUtil.logISOMsg(originalRequestISOMsg, "original Request message");

			// read and convert
			if (originalRequestISOMsg != null)
				modifiedRequestISOMsg = validateFields.incoming(originalRequestISOMsg);

			isoUtil.logISOMsg(modifiedRequestISOMsg, "modified Request message");

			stringMessage = isoUtil.packMessage(modifiedRequestISOMsg, isoVersion);

		} catch (Exception e) {
			logger.error(" Exception while converting request iso message ", e);
			throw e;
		}
		return stringMessage; // clientSocket.run(stringMessage);
	}

	@Override
	public String modifyResponse(String responseMsg, String isoVersion) throws Exception {
		String stringMessage;
		try {
			// unpack
			if (responseMsg == null | responseMsg == "")
				throw new Exception("Response message invalid");
			originalResponseISOMsg = isoUtil.unpackMessage(responseMsg, isoVersion);
			// logger.info("original_response_isomsg.toString()" + new
			// String(originalResponseISOMsg.pack()));
			isoUtil.logISOMsg(originalResponseISOMsg, "original response iso message");
			// isoMap.put("originalResponseISOMsg", originalResponseISOMsg);
			// read and convert
			modifiedResponseISOMsg = validateFields.outgoing(originalResponseISOMsg);
			// isoMap.put("modifiedResponseISOMsg", modifiedResponseISOMsg);
			isoUtil.logISOMsg(modifiedResponseISOMsg, "modified response message");
			// pack
			stringMessage = isoUtil.packMessage(modifiedResponseISOMsg, isoVersion);
		} catch (Exception e) {
			logger.error("Exception while converting response iso message", e);
			throw e;
		}
		return stringMessage;
	}

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

}
