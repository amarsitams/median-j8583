package com.rumango.median.iso.serviceimpl;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import com.rumango.median.iso.dto.IsoPojo;
import com.rumango.median.iso.service.IsoConstants;
import com.rumango.median.iso.service.IsoUtil;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.IsoValue;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.parse.ConfigParser;

@Service
public class IsoUtilImpl implements IsoUtil {
	private final static Logger logger = Logger.getLogger(IsoUtilImpl.class);

	@Override
	public String isoToJson(Map<Integer, String> msg) {
		logger.info("Inside ISO to JSON");
		return "{" + msg.entrySet().stream()
				.map(e -> "\"" + e.getKey() + "\"" + ":\"" + String.valueOf(e.getValue()) + "\"")
				.collect(Collectors.joining(", ")) + "}";
	}

	@Override
	public String isoToXml(Map<Integer, String> msg) {
		logger.info("Inside ISO to XML");
		JSONObject json = new JSONObject(isoToJson(msg));
		return "<root>" + XML.toString(json) + "</root>";
	}

	public String toCsv(String stringMessage, String isoVersion) {
		Map<Integer, String> map;
		try {
			map = unpackMessage(stringMessage, isoVersion);
			if (map != null) {
				StringBuilder sb = new StringBuilder();
				for (Map.Entry<Integer, String> entry : map.entrySet()) {
					sb.append(entry.getKey() + "; " + entry.getValue() + "  ");
				}
				return sb.toString();
			}
			return null;
		} catch (IOException | ParseException e) {
			logger.error("Exception while splitting ", e);
			return null;
		}
	}

	public Map<Integer, String> unpackMessage(String stringMessage, String isoVersion)
			throws IOException, ParseException {
		MessageFactory<IsoMessage> mfact = null;
		try {
			if (isoVersion.equalsIgnoreCase(IsoConstants.version_87))
				mfact = ConfigParser.createFromClasspathConfig("config87.xml");
			else if (isoVersion.equalsIgnoreCase(IsoConstants.version_93))
				mfact = ConfigParser.createFromClasspathConfig("config93.xml");
			else if (isoVersion.equalsIgnoreCase(IsoConstants.version_ge))
				mfact = ConfigParser.createFromClasspathConfig("configGe.xml");
			mfact.setIgnoreLastMissingField(true);
			IsoMessage m = mfact.parseMessage(stringMessage.getBytes(), 0);// mfact.getIsoHeader(0x1200).length()
			Map<Integer, String> toMap;
			if (m != null) {
				toMap = new LinkedHashMap<>();
				toMap.put(1, Integer.toString(m.getType(), 16));
				for (int i = 2; i <= 128; i++) {
					IsoValue<?> f = m.getField(i);
					if (f != null) {
						toMap.put(i, f.toString());
					}
				}
				return toMap;
			}
			return null;
		} catch (Exception e) {
			logger.warn("Exception while unpacking ", e);
			return null;
		}
	}

	public String packMessage(Map<Integer, String> isoMessage, String isoVersion) throws Exception {
		IsoPojo pojo;
		Map<Integer, IsoPojo> fields = null;
		MessageFactory<IsoMessage> mfact = null;
		try {
			if (isoVersion.equalsIgnoreCase(IsoConstants.version_87)) {
				fields = iso87Map();
				mfact = ConfigParser.createFromClasspathConfig("config87.xml");
			} else if (isoVersion.equalsIgnoreCase(IsoConstants.version_93)) {
				mfact = ConfigParser.createFromClasspathConfig("config93.xml");
				fields = iso93Map();
			} else if (isoVersion.equalsIgnoreCase(IsoConstants.version_ge)) {
				mfact = ConfigParser.createFromClasspathConfig("configGe.xml");
				fields = genericMap();
			}

			// mfact.setUseBinaryBitmap(true);
			IsoMessage m = mfact.newMessage(Integer.parseInt(isoMessage.get(1), 16));
			isoMessage.remove(1);
			m.setBinaryBitmap(true);
			for (Entry<Integer, String> element : isoMessage.entrySet()) {
				pojo = fields.get(element.getKey());
				m.setValue(element.getKey(), element.getValue(), pojo.getIsoType(), pojo.getLength());
			}
			return m.debugString();

		} catch (IOException e) {
			throw e;
			// return null;
		}
	}

	public void logISOMsg(@NotEmpty @NotNull Map<Integer, String> msg, String stringMessage) {
		logger.info("-----------------" + stringMessage + "-----------------------");
		for (Map.Entry<Integer, String> entry : msg.entrySet()) {
			logger.info(entry.getKey() + " " + ":" + entry.getValue());
		}
	}

	public Map<Integer, IsoPojo> newISO87() {
		Map<Integer, IsoPojo> values = new LinkedHashMap<>();
		values.put(1, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(2, new IsoPojo(IsoType.LLVAR, 19));
		values.put(3, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(4, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(5, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(6, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(7, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(8, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(9, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(10, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(11, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(12, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(13, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(14, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(15, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(16, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(17, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(18, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(19, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(20, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(21, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(22, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(23, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(24, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(25, new IsoPojo(IsoType.NUMERIC, 2));
		values.put(26, new IsoPojo(IsoType.NUMERIC, 2));
		values.put(27, new IsoPojo(IsoType.NUMERIC, 1));
		values.put(28, new IsoPojo(IsoType.NUMERIC, 9));
		values.put(29, new IsoPojo(IsoType.NUMERIC, 9));
		values.put(30, new IsoPojo(IsoType.NUMERIC, 9));
		values.put(31, new IsoPojo(IsoType.NUMERIC, 9));
		values.put(32, new IsoPojo(IsoType.LLVAR, 11));
		values.put(33, new IsoPojo(IsoType.LLVAR, 11));
		values.put(34, new IsoPojo(IsoType.LLVAR, 28));
		values.put(35, new IsoPojo(IsoType.LLVAR, 37));
		values.put(36, new IsoPojo(IsoType.LLLVAR, 104));
		values.put(37, new IsoPojo(IsoType.ALPHA, 12));
		values.put(38, new IsoPojo(IsoType.ALPHA, 6));
		values.put(39, new IsoPojo(IsoType.ALPHA, 2));
		values.put(40, new IsoPojo(IsoType.ALPHA, 3));
		values.put(41, new IsoPojo(IsoType.ALPHA, 8));
		values.put(42, new IsoPojo(IsoType.ALPHA, 15));
		values.put(43, new IsoPojo(IsoType.ALPHA, 40));
		values.put(44, new IsoPojo(IsoType.LLVAR, 25));
		values.put(45, new IsoPojo(IsoType.LLVAR, 76));
		values.put(46, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(47, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(48, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(49, new IsoPojo(IsoType.ALPHA, 3));
		values.put(50, new IsoPojo(IsoType.ALPHA, 3));
		values.put(51, new IsoPojo(IsoType.ALPHA, 3));
		values.put(52, new IsoPojo(IsoType.BINARY, 8));
		values.put(53, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(54, new IsoPojo(IsoType.LLLVAR, 120));
		values.put(55, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(56, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(57, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(58, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(59, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(60, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(61, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(62, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(63, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(64, new IsoPojo(IsoType.BINARY, 8));
		values.put(65, new IsoPojo(IsoType.BINARY, 1));
		values.put(66, new IsoPojo(IsoType.NUMERIC, 1));
		values.put(67, new IsoPojo(IsoType.NUMERIC, 2));
		values.put(68, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(69, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(70, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(71, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(72, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(73, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(74, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(75, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(76, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(77, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(78, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(79, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(80, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(81, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(82, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(83, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(84, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(85, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(86, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(87, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(88, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(89, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(90, new IsoPojo(IsoType.NUMERIC, 42));
		values.put(91, new IsoPojo(IsoType.ALPHA, 1));
		values.put(92, new IsoPojo(IsoType.ALPHA, 2));
		values.put(93, new IsoPojo(IsoType.ALPHA, 6));
		values.put(94, new IsoPojo(IsoType.ALPHA, 7));
		values.put(95, new IsoPojo(IsoType.ALPHA, 42));
		values.put(96, new IsoPojo(IsoType.BINARY, 16));
		values.put(97, new IsoPojo(IsoType.NUMERIC, 17));
		values.put(98, new IsoPojo(IsoType.ALPHA, 25));
		values.put(99, new IsoPojo(IsoType.LLVAR, 11));
		values.put(100, new IsoPojo(IsoType.LLVAR, 11));
		values.put(101, new IsoPojo(IsoType.LLVAR, 17));
		values.put(102, new IsoPojo(IsoType.LLVAR, 28));
		values.put(103, new IsoPojo(IsoType.LLVAR, 28));
		values.put(104, new IsoPojo(IsoType.LLLVAR, 100));
		values.put(105, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(106, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(107, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(108, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(109, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(110, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(111, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(112, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(113, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(114, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(115, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(116, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(117, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(118, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(119, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(120, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(121, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(122, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(123, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(124, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(125, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(126, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(127, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(128, new IsoPojo(IsoType.BINARY, 8));

		return values;
	}

	private static Map<Integer, IsoPojo> genericMap() {
		Map<Integer, IsoPojo> values = new LinkedHashMap<>();
		values.put(1, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(2, new IsoPojo(IsoType.LLVAR, 19));
		values.put(3, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(4, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(5, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(6, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(7, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(8, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(9, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(10, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(11, new IsoPojo(IsoType.ALPHA, 12));
		values.put(12, new IsoPojo(IsoType.NUMERIC, 14));
		values.put(13, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(14, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(15, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(16, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(17, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(18, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(19, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(20, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(21, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(22, new IsoPojo(IsoType.LLVAR, 12));
		values.put(23, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(24, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(25, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(26, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(27, new IsoPojo(IsoType.NUMERIC, 1));
		values.put(28, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(29, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(30, new IsoPojo(IsoType.NUMERIC, 24));
		values.put(31, new IsoPojo(IsoType.LLVAR, 99));
		values.put(32, new IsoPojo(IsoType.LLVAR, 0));
		values.put(33, new IsoPojo(IsoType.LLLVAR, 11));
		values.put(34, new IsoPojo(IsoType.LLVAR, 28));
		values.put(35, new IsoPojo(IsoType.LLVAR, 37));
		values.put(36, new IsoPojo(IsoType.LLLVAR, 104));
		values.put(37, new IsoPojo(IsoType.LLVAR, 12));
		values.put(38, new IsoPojo(IsoType.LLVAR, 6));
		values.put(39, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(40, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(41, new IsoPojo(IsoType.LLVAR, 8));
		values.put(42, new IsoPojo(IsoType.LLVAR, 15));
		values.put(43, new IsoPojo(IsoType.LLVAR, 99));
		values.put(44, new IsoPojo(IsoType.LLVAR, 99));
		values.put(45, new IsoPojo(IsoType.LLVAR, 76));
		values.put(46, new IsoPojo(IsoType.LLLVAR, 204));
		values.put(47, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(48, new IsoPojo(IsoType.LLLVAR, 0));
		values.put(49, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(50, new IsoPojo(IsoType.LLVAR, 3));
		values.put(51, new IsoPojo(IsoType.LLVAR, 3));
		values.put(52, new IsoPojo(IsoType.LLBIN, 8));
		values.put(53, new IsoPojo(IsoType.LLBIN, 48));
		values.put(54, new IsoPojo(IsoType.LLLVAR, 120));
		values.put(55, new IsoPojo(IsoType.LLLBIN, 255));
		values.put(56, new IsoPojo(IsoType.LLLVAR, 35));
		values.put(57, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(58, new IsoPojo(IsoType.LLLVAR, 11));
		values.put(59, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(60, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(61, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(62, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(63, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(64, new IsoPojo(IsoType.BINARY, 8));
		values.put(65, new IsoPojo(IsoType.BINARY, 8));
		values.put(66, new IsoPojo(IsoType.LLLVAR, 204));
		values.put(67, new IsoPojo(IsoType.NUMERIC, 2));
		values.put(68, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(69, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(70, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(71, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(72, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(73, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(74, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(75, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(76, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(77, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(78, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(79, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(80, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(81, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(82, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(83, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(84, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(85, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(86, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(87, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(88, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(89, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(90, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(91, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(92, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(93, new IsoPojo(IsoType.LLLVAR, 11));
		values.put(94, new IsoPojo(IsoType.LLLVAR, 11));
		values.put(95, new IsoPojo(IsoType.LLVAR, 99));
		values.put(96, new IsoPojo(IsoType.LLLBIN, 999));
		values.put(97, new IsoPojo(IsoType.NUMERIC, 17));
		values.put(98, new IsoPojo(IsoType.LLVAR, 25));
		values.put(99, new IsoPojo(IsoType.LLVAR, 11));
		values.put(100, new IsoPojo(IsoType.LLLVAR, 11));
		values.put(101, new IsoPojo(IsoType.LLVAR, 17));
		values.put(102, new IsoPojo(IsoType.LLVAR, 0));
		values.put(103, new IsoPojo(IsoType.LLVAR, 0));
		values.put(104, new IsoPojo(IsoType.LLLVAR, 100));
		values.put(105, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(106, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(107, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(108, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(109, new IsoPojo(IsoType.LLVAR, 84));
		values.put(110, new IsoPojo(IsoType.LLVAR, 84));
		values.put(111, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(112, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(113, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(114, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(115, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(116, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(117, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(118, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(119, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(120, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(121, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(122, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(123, new IsoPojo(IsoType.LLVAR, 0));
		values.put(124, new IsoPojo(IsoType.LLLVAR, 0));
		values.put(125, new IsoPojo(IsoType.LLLVAR, 0));
		values.put(126, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(127, new IsoPojo(IsoType.LLLVAR, 999));
		return values;
	}

	private static Map<Integer, IsoPojo> iso87Map() {
		Map<Integer, IsoPojo> values = new LinkedHashMap<>();
		values.put(1, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(2, new IsoPojo(IsoType.LLVAR, 19));
		values.put(3, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(4, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(5, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(6, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(7, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(8, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(9, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(10, new IsoPojo(IsoType.ALPHA, 8));
		values.put(11, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(12, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(13, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(14, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(15, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(16, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(17, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(18, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(19, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(20, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(21, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(22, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(23, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(24, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(25, new IsoPojo(IsoType.NUMERIC, 2));
		values.put(26, new IsoPojo(IsoType.NUMERIC, 2));
		values.put(27, new IsoPojo(IsoType.NUMERIC, 1));
		values.put(28, new IsoPojo(IsoType.NUMERIC, 9));
		values.put(29, new IsoPojo(IsoType.NUMERIC, 9));
		values.put(30, new IsoPojo(IsoType.NUMERIC, 9));
		values.put(31, new IsoPojo(IsoType.NUMERIC, 9));
		values.put(32, new IsoPojo(IsoType.LLVAR, 11));
		values.put(33, new IsoPojo(IsoType.LLVAR, 11));
		values.put(34, new IsoPojo(IsoType.LLVAR, 28));
		values.put(35, new IsoPojo(IsoType.LLVAR, 37));
		values.put(36, new IsoPojo(IsoType.LLLVAR, 104));
		values.put(37, new IsoPojo(IsoType.ALPHA, 12));
		values.put(38, new IsoPojo(IsoType.LLVAR, 6));
		values.put(39, new IsoPojo(IsoType.LLVAR, 2));
		values.put(40, new IsoPojo(IsoType.LLVAR, 3));
		values.put(41, new IsoPojo(IsoType.ALPHA, 8));
		values.put(42, new IsoPojo(IsoType.NUMERIC, 15));
		values.put(43, new IsoPojo(IsoType.ALPHA, 40));
		values.put(44, new IsoPojo(IsoType.LLVAR, 25));
		values.put(45, new IsoPojo(IsoType.LLVAR, 76));
		values.put(46, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(47, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(48, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(49, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(50, new IsoPojo(IsoType.LLVAR, 3));
		values.put(51, new IsoPojo(IsoType.LLVAR, 3));
		values.put(52, new IsoPojo(IsoType.BINARY, 8));
		values.put(53, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(54, new IsoPojo(IsoType.LLLVAR, 120));
		values.put(55, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(56, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(57, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(58, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(59, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(60, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(61, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(62, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(63, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(64, new IsoPojo(IsoType.BINARY, 8));
		values.put(65, new IsoPojo(IsoType.BINARY, 1));
		values.put(66, new IsoPojo(IsoType.NUMERIC, 1));
		values.put(67, new IsoPojo(IsoType.NUMERIC, 2));
		values.put(68, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(69, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(70, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(71, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(72, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(73, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(74, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(75, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(76, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(77, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(78, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(79, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(80, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(81, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(82, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(83, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(84, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(85, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(86, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(87, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(88, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(89, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(90, new IsoPojo(IsoType.NUMERIC, 42));
		values.put(91, new IsoPojo(IsoType.LLVAR, 1));
		values.put(92, new IsoPojo(IsoType.LLVAR, 2));
		values.put(93, new IsoPojo(IsoType.LLVAR, 6));
		values.put(94, new IsoPojo(IsoType.LLVAR, 7));
		values.put(95, new IsoPojo(IsoType.LLVAR, 42));
		values.put(96, new IsoPojo(IsoType.BINARY, 16));
		values.put(97, new IsoPojo(IsoType.NUMERIC, 17));
		values.put(98, new IsoPojo(IsoType.LLVAR, 25));
		values.put(99, new IsoPojo(IsoType.LLVAR, 11));
		values.put(100, new IsoPojo(IsoType.LLVAR, 11));
		values.put(101, new IsoPojo(IsoType.LLVAR, 17));
		values.put(102, new IsoPojo(IsoType.LLVAR, 0));
		values.put(103, new IsoPojo(IsoType.LLVAR, 28));
		values.put(104, new IsoPojo(IsoType.LLLVAR, 100));
		values.put(105, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(106, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(107, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(108, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(109, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(110, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(111, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(112, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(113, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(114, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(115, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(116, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(117, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(118, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(119, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(120, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(121, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(122, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(123, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(124, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(125, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(126, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(127, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(128, new IsoPojo(IsoType.BINARY, 8));
		return values;
	}

	private static Map<Integer, IsoPojo> iso93Map() {
		Map<Integer, IsoPojo> values = new LinkedHashMap<>();
		values.put(1, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(2, new IsoPojo(IsoType.LLVAR, 19));
		values.put(3, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(4, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(5, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(6, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(7, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(8, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(9, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(10, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(11, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(12, new IsoPojo(IsoType.NUMERIC, 12));
		values.put(13, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(14, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(15, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(16, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(17, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(18, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(19, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(20, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(21, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(22, new IsoPojo(IsoType.ALPHA, 12));
		values.put(23, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(24, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(25, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(26, new IsoPojo(IsoType.NUMERIC, 4));
		values.put(27, new IsoPojo(IsoType.NUMERIC, 1));
		values.put(28, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(29, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(30, new IsoPojo(IsoType.NUMERIC, 24));
		values.put(31, new IsoPojo(IsoType.LLVAR, 99));
		values.put(32, new IsoPojo(IsoType.LLVAR, 11));
		values.put(33, new IsoPojo(IsoType.LLVAR, 11));
		values.put(34, new IsoPojo(IsoType.LLVAR, 28));
		values.put(35, new IsoPojo(IsoType.LLVAR, 37));
		values.put(36, new IsoPojo(IsoType.LLLVAR, 104));
		values.put(37, new IsoPojo(IsoType.ALPHA, 12));
		values.put(38, new IsoPojo(IsoType.ALPHA, 6));
		values.put(39, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(40, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(41, new IsoPojo(IsoType.ALPHA, 8));
		values.put(42, new IsoPojo(IsoType.ALPHA, 15));
		values.put(43, new IsoPojo(IsoType.LLVAR, 99));
		values.put(44, new IsoPojo(IsoType.LLVAR, 99));
		values.put(45, new IsoPojo(IsoType.LLVAR, 76));
		values.put(46, new IsoPojo(IsoType.LLLVAR, 204));
		values.put(47, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(48, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(49, new IsoPojo(IsoType.ALPHA, 3));
		values.put(50, new IsoPojo(IsoType.ALPHA, 3));
		values.put(51, new IsoPojo(IsoType.ALPHA, 3));
		values.put(52, new IsoPojo(IsoType.BINARY, 8));
		values.put(53, new IsoPojo(IsoType.LLBIN, 48));
		values.put(54, new IsoPojo(IsoType.LLLVAR, 120));
		values.put(55, new IsoPojo(IsoType.LLLBIN, 255));
		values.put(56, new IsoPojo(IsoType.LLVAR, 35));
		values.put(57, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(58, new IsoPojo(IsoType.LLVAR, 11));
		values.put(59, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(60, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(61, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(62, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(63, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(64, new IsoPojo(IsoType.BINARY, 8));
		values.put(65, new IsoPojo(IsoType.BINARY, 8));
		values.put(66, new IsoPojo(IsoType.LLLVAR, 204));
		values.put(67, new IsoPojo(IsoType.NUMERIC, 2));
		values.put(68, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(69, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(70, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(71, new IsoPojo(IsoType.NUMERIC, 8));
		values.put(72, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(73, new IsoPojo(IsoType.NUMERIC, 6));
		values.put(74, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(75, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(76, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(77, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(78, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(79, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(80, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(81, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(82, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(83, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(84, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(85, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(86, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(87, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(88, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(89, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(90, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(91, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(92, new IsoPojo(IsoType.NUMERIC, 3));
		values.put(93, new IsoPojo(IsoType.LLVAR, 11));
		values.put(94, new IsoPojo(IsoType.LLVAR, 11));
		values.put(95, new IsoPojo(IsoType.LLVAR, 99));
		values.put(96, new IsoPojo(IsoType.LLLBIN, 999));
		values.put(97, new IsoPojo(IsoType.NUMERIC, 17));
		values.put(98, new IsoPojo(IsoType.ALPHA, 25));
		values.put(99, new IsoPojo(IsoType.LLVAR, 11));
		values.put(100, new IsoPojo(IsoType.LLVAR, 11));
		values.put(101, new IsoPojo(IsoType.LLVAR, 17));
		values.put(102, new IsoPojo(IsoType.LLVAR, 28));
		values.put(103, new IsoPojo(IsoType.LLVAR, 28));
		values.put(104, new IsoPojo(IsoType.LLLVAR, 100));
		values.put(105, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(106, new IsoPojo(IsoType.NUMERIC, 16));
		values.put(107, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(108, new IsoPojo(IsoType.NUMERIC, 10));
		values.put(109, new IsoPojo(IsoType.LLVAR, 84));
		values.put(110, new IsoPojo(IsoType.LLVAR, 84));
		values.put(111, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(112, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(113, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(114, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(115, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(116, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(117, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(118, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(119, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(120, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(121, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(122, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(123, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(124, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(125, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(126, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(127, new IsoPojo(IsoType.LLLVAR, 999));
		values.put(128, new IsoPojo(IsoType.BINARY, 8));
		return values;
	}

}
