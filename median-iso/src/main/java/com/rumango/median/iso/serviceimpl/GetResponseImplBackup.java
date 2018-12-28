//package com.rumango.median.iso.serviceimpl;
//
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.util.Arrays;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//
//import org.apache.log4j.Logger;
//import org.jpos.iso.ISOException;
//import org.jpos.iso.ISOMsg;
//import org.jpos.iso.packager.GenericPackager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.rumango.median.iso.client.IsoJposResponse;
//import com.rumango.median.iso.client.RestClient;
//import com.rumango.median.iso.dao.service.AuditLogService;
//import com.rumango.median.iso.model.ValidateChannel;
//import com.rumango.median.iso.service.GetResponse;
//import com.rumango.median.iso.service.ModifyRequestAndResponse;
//
//@Service
//public class GetResponseImplBackup implements GetResponse {
//
//	// private String originalRequestString, modifiedRequestString,
//	// originalResponseString, modifiedResponseString;
//	// private ISOMsg originalRequestISOMsg, modifiedRequestISOMsg,
//	// originalResponseISOMsg, modifiedResponseISOMsg,
//	private Map<Integer, String> response = null;
//	private String receivedMsgStatus, sentMsgStatus, reason;
//
//	@Autowired
//	private ModifyRequestAndResponse modifyRequestAndResponse;
//
//	@Autowired
//	private AuditLogService auditLogService;
//
//	private final static Logger logger = Logger.getLogger(GetResponseImplBackup.class);
//
//	private Map<String, String> arrayToMap(String[] arrayOfString) {
//		return Arrays.asList(arrayOfString).stream().map(str -> str.split(":"))
//				.collect(Collectors.toMap(str -> str[0], str -> str[1]));
//	}
//
//	private boolean validateRequest() {
//		return true;
//	}
//
//	private boolean validateRequest(Map<Integer, String> isoMsg) {
//		boolean response = false;
//		ValidateChannel vc = null;
//		try {
//			logger.info("Calling rest call to validate ");
//			String channel = isoMsg == null ? "Channel Id Invalid" : isoMsg.get(2);
//			String tXiD = isoMsg == null ? "Transaction Id Invalid" : isoMsg.get(125);
//			logger.info("Channel Id :" + channel + " Transaction Id : " + tXiD);
//			if (!channel.equalsIgnoreCase("Channel Id Invalid") && channel != null
//					&& !tXiD.equalsIgnoreCase("Transaction Id Invalid") && tXiD != null)
//				vc = RestClient.callRestApi(channel, tXiD);
//			logger.info("ValidateChannel " + vc);
//			logger.info("Amount " + isoMsg.get(4) + "  Account Number" + isoMsg.get(102));
//			if (vc.getStatus().startsWith("00")) {
//				if (vc.getAmount() == Long.parseLong(isoMsg.get(4))) {
//					if (vc.getAccountNumber().equalsIgnoreCase(isoMsg.get(102)))
//						response = true;
//					else {
//						response = false;
//						reason = "Account numbers do not match";
//					}
//				} else {
//					response = false;
//					reason = "Amount do not match";
//				}
//			} else {
//				response = false;
//				reason = "Transaction Id | Channel Id Does not exists";
//			}
//		} catch (Exception e) {
//			logger.error("Exception in validate", e);
//			response = false;
//		}
//		return response;
//	}
//
//	private Map<Integer, String> getIsoFromString(String message, String s2) {
//		ISOMsg isoMsg = new ISOMsg();
//		isoMsg.setPackager(getPackager());
//		try {
//			String s = "1200𰿁  ?       804INTB310000000000000000010006622279201812061251522018120620006000000038401500119000100027503SWT03ITB17PRN 2020540404332";
//			isoMsg.unpack(message.getBytes("US-ASCII"));
//		} catch (UnsupportedEncodingException | ISOException e) {
//			e.printStackTrace();
//		}
//
//		String asccii = new String(message.substring(8, 25) // the ascii string in question 8 t0 47
//				.chars() // creates an IntStream
//				.mapToObj(c -> Character.toString((char) c)) // iterates and creates a new array of character
//				.collect(Collectors.joining()) // Collection utility of Java 8 stream api
//				.toCharArray() // The name says it all
//		);
//
//		logger.info("Ascii unpacked" + asccii);
//		logger.info("ISO unpacked" + isoMsg);
//
//		logISOMsg(isoMsg, "From Jpos Ascii header");
//
//		return null;
//	}
//
//	public GenericPackager getPackager() {
//		try {
//			InputStream inputstream = Thread.currentThread().getContextClassLoader().getResourceAsStream("basic.xml");
//			return new GenericPackager(inputstream);
//		} catch (ISOException e) {
//			logger.error("Exception while loading Generic Packager", e);
//			return null;
//		}
//	}
//
//	private Map<Integer, String> getIsoFromString(String message) {
//		Map<Integer, String> intMap = new LinkedHashMap<>();
//		try {
//			String[] splitted = message.split(";");
//			for (Map.Entry<String, String> entry : arrayToMap(splitted).entrySet()) {
//				intMap.put(Integer.parseInt(entry.getKey()), entry.getValue());
//			}
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//			return null;
//		}
//		return intMap;
//	}
//
//	public String convertAndRespond(String stringMessage, Map<String, String> map) {
//		Map<Integer, String> msg = null;
//		Map<Integer, String> isoMsg = null;
//		String response = null;
//		try {
//			map.put("originalRequestString", stringMessage);
//			isoMsg = getIsoFromString(stringMessage.substring(4), null);
//			logISOMsg(isoMsg, "REQUEST");
//			receivedMsgStatus = "SUCCESS";
//		} catch (Exception e) {
//			receivedMsgStatus = "FAIL";
//			logger.error("Exception while unpacking", e);
//		}
//		logger.info("inside convertAndRespond of GetResponseImpl ");
//		try {
//			if (validateRequest()) {// if (validateRequest(isoMsg)) {
//				Object[] objArray = IsoJposResponse.main(isoMsg);
//				logger.info("objArray size ::" + objArray.length);
//				response = (String) objArray[0];
//				map.put("originalResponseString", response);
//				logger.info("response ::" + response);
//				msg = (Map<Integer, String>) objArray[1];
//				if (msg != null) {
//					logISOMsg(msg, "RESPONSE");
//					sentMsgStatus = "SUCCESS";
//				}
//			} else {
//				logger.info("Validation failed with rest Api");
//				sentMsgStatus = "FAIL";
//				map.put("reason", reason);
//			}
//		} catch (Exception e) {
//			logger.error("Exception inside convertAndRespond of GetResponseImpl ", e);
//		} finally {
//			map.put("receivedMsgStatus", receivedMsgStatus);
//			map.put("sentMsgStatus", sentMsgStatus);
//			logger.info("receivedMsgStatus " + receivedMsgStatus + " received Response Status   " + sentMsgStatus);
//			try {
//				logger.info("Map Size" + map.size());
//				for (Map.Entry<String, String> set : map.entrySet()) {
//					logger.info(set.getKey() + ":" + set.getValue());
//				}
//				auditLogService.saveData(map);
//			} catch (Exception e) {
//				logger.warn("Exception while saving log information ", e);
//			}
//		}
//		return msg == null ? reason : msg.get(39);
//	}
//
//	public String convertAndRespond(String str) {
//		return "000";
//		//return new ClientSocket().run(str);
//	}
//
//	public String convertAndRespond(String input, Map<String, String> map, String str) {
////		Map<Integer, String> msg = null;
////		Map<Integer, String> isoMsg = null;
////		String response = null;
////		try {
////			isoMsg = getIsoFromString(input);
////			logISOMsg(isoMsg, "ORIGINAL MESSAGE");
////
////			isoMsg.setPackager(getPackager());
////			isoMsg.pack();
////
////			logISOMsg(isoMsg, "GENERIC MESSAGE");
////		} catch (ISOException e) {
////			logger.error("Exception while unpacking", e);
////		}
////		logger.info("inside convertAndRespond of GetResponseImpl ");
////		try {
////			if (validateRequest(isoMsg)) {
////				isoMsg.setPackager(getPackager());
////				Object[] objArray = IsoJposResponse.main(isoMsg);
////				logger.info("objArray size ::" + objArray.length);
////				response = (String) objArray[0];
////				logger.info("response ::" + response);
////				msg = (Map<Integer, String>) objArray[1];
////				if (msg != null)
////					logISOMsg(msg, "RESPONSE MESSAGE");
////			} else
////				logger.info("Validation failed with rest Api");
////		} catch (Exception e) {
////			logger.error("Exception inside convertAndRespond of GetResponseImpl ", e);
////		} finally {
////			map.put("receivedMsgStatus", receivedMsgStatus);
////			map.put("sentMsgStatus", sentMsgStatus);
////			logger.info("receivedMsgStatus " + receivedMsgStatus + " received Response Status   " + sentMsgStatus);
////			try {
////				auditLogService.saveData(map);
////			} catch (Exception e) {
////				logger.warn("Exception while saving log information ", e);
////			}
////		}
////		return msg == null ? "ERROR" : msg.getString(39);
//		return null;
//	}
//
//	private Map<Integer, String> convertStringToIntegerMap(Map<String, String> message) {
//		Map<Integer, String> intMap = new LinkedHashMap<>();
//		for (Map.Entry<String, String> entry : message.entrySet()) {
//			intMap.put(Integer.parseInt(entry.getKey()), entry.getValue());
//		}
//		return intMap;
//	}
//
//	private Map<Integer, String> getResponse(Map<Integer, String> isoMessage) throws Exception {
//		logger.info("inside getResponse of IsoMessageConvertionImpl");
//		try {
//			response = null;// new IsoJposResponse().call(isoMessage);
//		} catch (Exception e) {
//			response = null;
//			sentMsgStatus = "Exception while getResponse of IsoMessageConvertionImpl";
//			logger.warn(sentMsgStatus);
//			throw e;
//		}
//		if (response.toString() != "" && response != null)
//			sentMsgStatus = "SUCCESS";
//		return response;
//	}
//
//	private void logISOMsg(@NotEmpty @NotNull Map<Integer, String> msg, String stringMessage) {
//		int i = 0;
//		logger.info("-----------------" + stringMessage + "-----------------------");
//		for (Map.Entry<Integer, String> entry : msg.entrySet()) {
////			i = entry.getKey();
////			if (i == 123 | i == 124)
////				logger.info(i + " " + ":" + mask(entry.getValue()));
////			else
//			// responseString =
//			// responseString.append(i).append(":").append(msg.getString(i)).append(";");
//			logger.info(entry.getKey() + " " + ":" + entry.getValue());
//		}
//	}
//
//	private String mask(String accNo) {
//		StringBuilder sb = new StringBuilder(accNo);
//		if (sb.length() > 6)
//			sb.replace(6, accNo.length(), "X");
//		return sb.toString();
//	}
//
//	private void logISOMsg(@NotEmpty @NotNull ISOMsg msg, String stringMessage) {
//		// StringBuilder responseString = new StringBuilder();
//		try {
//			logger.info("-----------------" + stringMessage + "-----------------------");
//			for (int i = 0; i <= msg.getMaxField(); i++) {
//				if (msg.hasField(i)) {
//					if (i == 102 | i == 103)
//						logger.info(i + " " + ":" + mask(msg.getString(i)));
//					else
//						// responseString =
//						// responseString.append(i).append(":").append(msg.getString(i)).append(";");
//						logger.info(i + " " + ":" + msg.getString(i));
//				}
//			}
//		} catch (Exception e) {
//			logger.error("Exception occured", e);
//		}
//		// return responseString.toString();
//	}
//}
