package com.rumango.median.iso.test;
//package com.rumango.median.iso.client;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
//import org.apache.log4j.Logger;
//import org.jpos.iso.ISOChannel;
//import org.jpos.iso.ISOException;
//import org.jpos.iso.ISOMsg;
//import org.jpos.iso.ISOPackager;
//import org.jpos.iso.channel.ASCIIChannel;
//import org.jpos.iso.packager.GenericPackager;
//
//public class IsoJposResponseOld implements Callable<String> {
//
//	private ISOMsg isoMsg;
//	private ISOChannel channel;
//
//	private static String response = null;
//	private static Map<Integer, String> responseIsoMsg = null;
//	private final static Logger logger = Logger.getLogger(IsoJposResponseOld.class);
//
//	public static void main(String[] args) {
//		
//	}
//	public static Object[] main(Map<Integer, String> isoMsg) {
//		ExecutorService service = Executors.newFixedThreadPool(1);
//		Future<String> task = service.submit(new IsoJposResponseOld(isoMsg));
//		try {
//			response = task.get();
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
//		service.shutdownNow();
//		logger.info("Finacle response code is: " + response);
//		Object[] elements = new Object[2];
//		elements[0] = response;
//		elements[1] = responseIsoMsg;
//		return elements;
//	}
//
//	public IsoJposResponseOld(Map<Integer, String> iso) {
//		this.isoMsg = convertMapToIsoMsg(iso);
//		try {
//			ASCIIChannel ac = new ASCIIChannel("172.16.2.225", 52000, getPackager());
//			ac.setTimeout(5000);
//			this.channel = ac;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public IsoJposResponseOld(String ipAddress, int port, ISOPackager packager) {
//		try {
//			this.channel = new ASCIIChannel(ipAddress, port, packager);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private GenericPackager getPackager() {
//		try {
//			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//			InputStream inputstream = classLoader.getResourceAsStream("basic.xml");
//			return new GenericPackager(inputstream);
//		} catch (ISOException e) {
//			logger.error(e);
//			return null;
//		}
//	}
//
//	@Override
//	public String call() throws Exception {
//		logger.info("Call ...");
//		ISOMsg r = null;
//		StringBuilder responseString = new StringBuilder();
//		try {
//			channel.connect();
//			if (channel.isConnected()) {
//				logger.info("Channel connected. Sending...");
//			}
//			channel.send(this.isoMsg);
//
//			r = channel.receive();
//			if (r != null) {
//				logger.info("Response received");
//			}
//			if (r.getString(39).equals("000")) {
//				logger.info("Transaction SUCCESS");
//			} else {
//				logger.info("Transaction FAIL");
//			}
//
//			for (int i = 0; i <= r.getMaxField(); i++) {
//				if (r.hasField(i)) {
//					responseString = responseString.append(i).append(":").append(r.getString(i)).append(";");
//				}
//			}
//			responseIsoMsg = convertIsoMsgToMap(r);
//			channel.disconnect();
//		} catch (IOException e) {
//			logger.error(e);
//			return null;
//		} catch (ISOException e) {
//			logger.error(e);
//			return null;
//		}
//		return responseString.toString();
//	}
//
//	private Map<Integer, String> convertIsoMsgToMap(ISOMsg message) {
//		Map<Integer, String> intMap = new LinkedHashMap<>();
//		try {
//			for (int i = 0; i <= message.getMaxField(); i++) {
//				if (message.hasField(i)) {
//					intMap.put(i, message.getString(i));
//				}
//			}
//		} catch (Exception e) {
//			logger.error("Exception occured", e);
//		}
//		return intMap;
//	}
//
//	private ISOMsg convertMapToIsoMsg(Map<Integer, String> message) {
//		ISOMsg isoMsg = new ISOMsg();
//		for (Map.Entry<Integer, String> entry : message.entrySet()) {
//			isoMsg.set(entry.getKey(), entry.getValue());
//		}
//		return isoMsg;
//	}
//}