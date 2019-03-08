package com.rumango.median.iso.serviceimpl;
//package com.rumango.median.iso.serviceimpl;
//
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//
//import com.rumango.median.iso.client.ClientSocket;
//import com.rumango.median.iso.dao.service.AuditLogService;
//import com.rumango.median.iso.dao.service.ValidationsService;
//import com.rumango.median.iso.dto.IsoDetailsDto;
//import com.rumango.median.iso.service.GetResponse;
//import com.rumango.median.iso.service.ModifyRequestAndResponse;
//
//@Service
//@Scope("prototype")
//public class GetResponseImpl implements GetResponse {
//
//	private String modifiedRequestString = null, originalResponseString = null, modifiedResponseString = null,
//			response = null;
//	private String receivedMsgStatus = "FAIL", sentMsgStatus = "FAIL";
//	private ClientSocket clientSocket;
//
//	@Autowired
//	private ValidationsService validationsService;
//
//	@Autowired
//	private ModifyRequestAndResponse modifyRequestAndResponse;
//
//	@Autowired
//	private AuditLogService auditLogService;
//
//	private final static Logger logger = Logger.getLogger(GetResponseImpl.class);
//
//	public String convertAndRespond(String stringMessage, IsoDetailsDto dto) {
//		logger.info("inside convertAndRespond ");
////		dto.setOriginalRequestString(stringMessage);
////		try {
////			if (setMandatory(dto) && checkIfAllowed(dto)) {
////				// originalRequestString = stringMessage;
////				modifiedRequestString = modifyRequestAndResponse.modifyRequest(stringMessage, "87").substring(5);
////				logger.info(" modifiedRequestString " + modifiedRequestString);
////				dto.setModifiedRequestString(modifiedRequestString);
////				if (modifiedRequestString != null)
////					receivedMsgStatus = "SUCCESS";
////				originalResponseString = getResponse(modifiedRequestString, dto);
////				logger.info("originalResponseString  " + originalResponseString);
////				dto.setOriginalResponseString(originalResponseString);
////				modifiedResponseString = modifyRequestAndResponse.modifyResponse(originalResponseString, "87")
////						.substring(5);
////				if (modifiedResponseString != null)
////					sentMsgStatus = "SUCCESS";
////				logger.info(" modifiedResponseString " + modifiedResponseString);
////				map.put("modifiedResponseString", modifiedResponseString);
////			}
////		} catch (Exception e) {
////			modifiedResponseString = "";
////			logger.warn("Exception inside convertAndRespond of IsoMessageConvertor ", e);
////		} finally {
////			map.put("receivedMsgStatus", receivedMsgStatus);
////			map.put("sentMsgStatus", sentMsgStatus);
////			map.put("isoMessageAllowed", sentMsgStatus);
////			logger.info("receivedMsgStatus ::" + receivedMsgStatus + "  received Response Status   ::" + sentMsgStatus);
////			try {
////				auditLogService.saveData(map);
////			} catch (Exception e) {
////				logger.warn("Exception while saving log information ", e);
////			}
////		}
////		modifiedRequestString = null;
////		originalResponseString = null;
////		modifiedResponseString = null;
////		response = null;
////		receivedMsgStatus = "FAIL";
////		sentMsgStatus = "FAIL";
//		// return map.containsKey(modifiedResponseString) ?
//		// map.get(modifiedResponseString) : "0";
//
//		return "";
//
//	}
//
//	@Override
//	public boolean setMandatory(Map<String, String> map) {
//		logger.info("Setting mandatory fields for processing request");
//		// fromIp
//		try {
//			map.put("sourceVersion", validationsService.getModuleCode(map.get("fromIp")));
//			logger.info("sourceVersion:: " + validationsService.getModuleCode(map.get("fromIp")));
//			map.put("targetVersion", validationsService.getDestinationModuleCode(map.get("fromIp")));
//			logger.info("targetVersion:: " + validationsService.getDestinationModuleCode(map.get("fromIp")));
//			return true;
//		} catch (Exception e) {
//			// logger.error("Exception while Setting mandatory fields for processing
//			// request", e);
//			return false;
//		}
//	}
//
//	public boolean setMandatory(IsoDetailsDto dto) {
//		logger.info("Setting mandatory fields for processing request");
//		// fromIp
//		try {
//			dto.setSourceVersion(validationsService.getModuleCode(dto.getFromIp()));
//			logger.info("sourceVersion:: " + dto.getSourceVersion());
//			dto.setTargetVersion(validationsService.getDestinationModuleCode(dto.getFromIp()));
//			logger.info("targetVersion:: " + dto.getTargetVersion());
//			return true;
//		} catch (Exception e) {
//			// logger.error("Exception while Setting mandatory fields for processing
//			// request", e);
//			return false;
//		}
//	}
//
//	private final boolean checkIfAllowed(IsoDetailsDto dto) {
//		logger.info("checking if iso message is allowed or not");
//		try {
//			if (!dto.getSourceVersion().equalsIgnoreCase("") && !dto.getTargetVersion().equalsIgnoreCase("")
//					&& dto.getTargetVersion().length() == 2 && dto.getTargetVersion().length() == 2) {
//				logger.info("checking if iso message is allowed or not :: " + true);
//				return true;
//			} else {
//				logger.info("checking if iso message is allowed or not :: " + false);
//				return false;
//			}
//
//		} catch (Exception e) {
//			// logger.error("Exception while checking if iso message is allowed or not", e);
//			return false;
//		}
//	}
//
//	private final boolean checkIfAllowed(Map<String, String> map) {
//		logger.info("checking if iso message is allowed or not");
//		try {
//			if (!map.get("sourceVersion").equalsIgnoreCase("") && !map.get("targetVersion").equalsIgnoreCase("")
//					&& map.get("sourceVersion").length() == 2 && map.get("targetVersion").length() == 2) {
//				logger.info("checking if iso message is allowed or not :: " + true);
//				return true;
//			} else {
//				logger.info("checking if iso message is allowed or not :: " + false);
//				return false;
//			}
//
//		} catch (Exception e) {
//			// logger.error("Exception while checking if iso message is allowed or not", e);
//			return false;
//		}
//	}
//
//	@Override
//	public String convertAndRespond(String inputMessage, Map<String, String> map) {
//		logger.info("inside convertAndRespond ");
//		map.put("originalRequestString", inputMessage);
//		try {
//			if (setMandatory(map) && checkIfAllowed(map)) {
//				// originalRequestString = stringMessage;
//				modifiedRequestString = modifyRequestAndResponse.modifyRequest(inputMessage, map).substring(5);
//				logger.info(" modifiedRequestString " + modifiedRequestString);
//				map.put("modifiedRequestString", modifiedRequestString);
//				if (modifiedRequestString != null)
//					receivedMsgStatus = "SUCCESS";
//				originalResponseString = getResponse(modifiedRequestString, map);
//				logger.info("originalResponseString  " + originalResponseString);
//				map.put("originalResponseString", originalResponseString);
//				modifiedResponseString = modifyRequestAndResponse.modifyResponse(originalResponseString, map)
//						.substring(5);
//				if (modifiedResponseString != null)
//					sentMsgStatus = "SUCCESS";
//				logger.info(" modifiedResponseString " + modifiedResponseString);
//				map.put("modifiedResponseString", modifiedResponseString);
//			}
//		} catch (Exception e) {
//			modifiedResponseString = null;
//			logger.warn("Exception inside convertAndRespond of IsoMessageConvertor ", e);
//		} finally {
//			map.put("receivedMsgStatus", receivedMsgStatus);
//			map.put("sentMsgStatus", sentMsgStatus);
//			map.put("isoMessageAllowed", sentMsgStatus);
//			logger.info("receivedMsgStatus ::" + receivedMsgStatus + "  received Response Status   ::" + sentMsgStatus);
//			try {
//				auditLogService.saveData(map);
//			} catch (Exception e) {
//				logger.warn("Exception while saving log information ", e);
//			}
//		}
////		modifiedRequestString = null;
////		originalResponseString = null;
////		modifiedResponseString = null;
////		response = null;
////		receivedMsgStatus = "FAIL";
////		sentMsgStatus = "FAIL";
//		// return map.containsKey(modifiedResponseString) ?
//		// map.get(modifiedResponseString) : "0";
//
//		return modifiedResponseString == null ? "00" : modifiedResponseString;
//	}
//
//	private String getResponse(String isoMessage, Map<String, String> map) throws Exception {
//		logger.info("inside getResponse of IsoMessageConvertionImpl");
//		int port = 2108;
//		String targetIp = "192.168.0.100";
//		try {
//			try {
//				String ipAndPort = validationsService.getDestIpAndPort(map.get("IP"));
//
////				system = mapSystemsRepository.getByfromSystem(map.get("IP"));
////				logger.info("MapSystems data" + system.toString());
////				targetIp = system.getToSystem();
////				port = system.getToPort();
//
//				targetIp = ipAndPort.substring(0, targetIp.indexOf(":"));
//				port = Integer.parseInt(ipAndPort.substring(targetIp.indexOf(":")));
//				logger.info("IP :: " + targetIp + "  PORT:  " + port);
//
//			} catch (Exception e) {
//				logger.error("Target Ip not available, connecting to default ");
//			}
////			clientSocket = new ClientSocket();
////			clientSocket.setValues(80000, true, targetIp, port);
////			response = clientSocket.run(isoMessage);
//
//			response = "0200F27A200108E0800000000000040000001011404630001000000000001070000130094304        09430409430401300130404069405005942924A3FBBMOB00002000000000105817test|Kimani|Elizabeth||0008527001       003130010008527001";
//		} catch (Exception e) {
//			response = "";
//			sentMsgStatus = "Exception while getResponse of IsoMessageConvertionImpl";
//			logger.warn(sentMsgStatus);
//			throw e;
//		}
//		return response;
//	}
//
//	private String getResponse(String isoMessage, IsoDetailsDto dto) throws Exception {
//		logger.info("inside getResponse of IsoMessageConvertionImpl");
//		int port = 2108;
//		String targetIp = "192.168.0.100";
//		try {
//			try {
//				String ipAndPort = validationsService.getDestIpAndPort(dto.getFromIp());
//
////				system = mapSystemsRepository.getByfromSystem(map.get("IP"));
////				logger.info("MapSystems data" + system.toString());
////				targetIp = system.getToSystem();
////				port = system.getToPort();
//
//				targetIp = ipAndPort.substring(0, targetIp.indexOf(":"));
//				port = Integer.parseInt(ipAndPort.substring(targetIp.indexOf(":")));
//				logger.info("IP :: " + targetIp + "  PORT:  " + port);
//
//			} catch (Exception e) {
//				logger.error("Target Ip not available, connecting to default ");
//			}
////			clientSocket = new ClientSocket();
////			clientSocket.setValues(80000, true, targetIp, port);
////			response = clientSocket.run(isoMessage);
//
//			response = "0200F27A200108E0800000000000040000001011404630001000000000001070000130094304        09430409430401300130404069405005942924A3FBBMOB00002000000000105817test|Kimani|Elizabeth||0008527001       003130010008527001";
//		} catch (Exception e) {
//			response = "";
//			sentMsgStatus = "Exception while getResponse of IsoMessageConvertionImpl";
//			logger.warn(sentMsgStatus);
//			throw e;
//		}
//		return response;
//	}
//
//	@Override
//	public String convertAndRespond(String stringMessage) {
//		return null;// convertAndRespond(stringMessage, null);
//	}
//}

//	private Map<String, String> arrayToMap(String[] arrayOfString) {
//		return Arrays.asList(arrayOfString).stream().map(str -> str.split(":"))
//				.collect(Collectors.toMap(str -> str[0], str -> str[1]));
//	}

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
////		ISOMsg isoMsg = new ISOMsg();
////		isoMsg.setPackager(getPackager());
////		try {
////			String s = "1200𰿁  ?       804INTB310000000000000000010006622279201812061251522018120620006000000038401500119000100027503SWT03ITB17PRN 2020540404332";
////			isoMsg.unpack(message.getBytes("US-ASCII"));
////		} catch (UnsupportedEncodingException | ISOException e) {
////			e.printStackTrace();
////		}
////
////		String asccii = new String(message.substring(8, 25) // the ascii string in question 8 t0 47
////				.chars() // creates an IntStream
////				.mapToObj(c -> Character.toString((char) c)) // iterates and creates a new array of character
////				.collect(Collectors.joining()) // Collection utility of Java 8 stream api
////				.toCharArray() // The name says it all
////		);
////
////		logger.info("Ascii unpacked" + asccii);
////		logger.info("ISO unpacked" + isoMsg);
////
////		logISOMsg(isoMsg, "From Jpos Ascii header");
//
//		return null;
//	}
//
//	public void getPackager() {
////		try {
////			InputStream inputstream = Thread.currentThread().getContextClassLoader().getResourceAsStream("basic.xml");
////			return new GenericPackager(inputstream);
////		} catch (ISOException e) {
////			logger.error("Exception while loading Generic Packager", e);
////			return null;
////		}
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
//	public String convertAndRespondOld(String stringMessage, Map<String, String> map) {
//		Map<Integer, String> msg = null;
//		Map<Integer, String> isoMsg = null;
//		String response = null;
//		try {
//			map.put("originalRequestString", stringMessage);
//			isoMsg = getIsoFromString(stringMessage.substring(4), null);
//			isoUtil.logISOMsg(isoMsg, "REQUEST");
//			receivedMsgStatus = "SUCCESS";
//		} catch (Exception e) {
//			receivedMsgStatus = "FAIL";
//			logger.error("Exception while unpacking", e);
//		}
//		logger.info("inside convertAndRespond of GetResponseImpl ");
//		try {
//			if (validateRequest()) {// if (validateRequest(isoMsg)) {
//				Object[] objArray = null;// IsoJposResponse.main(isoMsg);
//				logger.info("objArray size ::" + objArray.length);
//				response = (String) objArray[0];
//				map.put("originalResponseString", response);
//				logger.info("response ::" + response);
//				msg = (Map<Integer, String>) objArray[1];
//				if (msg != null) {
//					isoUtil.logISOMsg(msg, "RESPONSE");
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
//		try {
//			modifyRequestAndResponse.modifyRequest(str);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "000";
//		// return new ClientSocket().run(str);
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
////	private Map<Integer, String> getResponse(Map<Integer, String> isoMessage) throws Exception {
////		logger.info("inside getResponse of IsoMessageConvertionImpl");
////		try {
////			response = null;// new IsoJposResponse().call(isoMessage);
////		} catch (Exception e) {
////			response = null;
////			sentMsgStatus = "Exception while getResponse of IsoMessageConvertionImpl";
////			logger.warn(sentMsgStatus);
////			throw e;
////		}
////		if (response.toString() != "" && response != null)
////			sentMsgStatus = "SUCCESS";
////		return response;
////	}
//
//	private String mask(String accNo) {
//		StringBuilder sb = new StringBuilder(accNo);
//		if (sb.length() > 6)
//			sb.replace(6, accNo.length(), "X");
//		return sb.toString();
//	}
//
//	public void logISOMsg(@NotEmpty @NotNull String msg, String stringMessage) {
////		// StringBuilder responseString = new StringBuilder();
////		try {
////			logger.info("-----------------" + stringMessage + "-----------------------");
////			for (int i = 0; i <= msg.getMaxField(); i++) {
////				if (msg.hasField(i)) {
////					if (i == 102 | i == 103)
////						logger.info(i + " " + ":" + mask(msg.getString(i)));
////					else
////						// responseString =
////						// responseString.append(i).append(":").append(msg.getString(i)).append(";");
////						logger.info(i + " " + ":" + msg.getString(i));
////				}
////			}
////		} catch (Exception e) {
////			logger.error("Exception occured", e);
////		}
////		// return responseString.toString();
//	}
