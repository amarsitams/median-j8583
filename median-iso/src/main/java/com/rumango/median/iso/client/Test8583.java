package com.rumango.median.iso.client;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.rumango.median.iso.serviceimpl.IsoUtilImpl;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.IsoValue;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.parse.ConfigParser;

public class Test8583 {

	public static void main(String[] args) throws Exception {

		IsoUtilImpl impl = new IsoUtilImpl();

//		String iso87 = "0200F27A200108E0800000000000040000001011404630001000000000001070000130094304        09430409430401300130404069405005942924A3FBBMOB00002000000000105817test|Kimani|Elizabeth||0008527001       404130010008527001";
//		Map<Integer, String> map = impl.unpackMessage(iso87, "87");
//		for (Entry<Integer, String> entry : map.entrySet()) {
//			System.out.println(entry.getKey() + "::" + entry.getValue());
//		}

//		String s = packMessage(getMessage(), "87");
//		System.out.println(s);
//		Map<Integer, String> toMap = impl.unpackMessage(s, "87");
//		for (Entry<Integer, String> entry : toMap.entrySet()) {
//			System.out.println(entry.getKey() + ";" + entry.getValue());
//		}

//		String iso93 = "1200FA3A800108E080000000000004000000061234560000110000000123450000000043111102155116000001181102155116181118110211020812312312232         123     2132           0533122003169876543210123456";
//
//		IsoUtilImpl impl = new IsoUtilImpl();
//		Map<Integer, String> map = impl.unpackMessage(iso93, "93");
//		for (Entry<Integer, String> entry : map.entrySet()) {
//			System.out.println(entry.getKey() + "::" + entry.getValue());
//		}

//		System.out.println(getMessage());
//		parse(getMessage());

//		String s = packMessage(getMessage(), null);
//		System.out.println(s.substring(5));
//		Map<Integer, String> toMap = unpackMessage(s.substring(5));
//		for (Entry<Integer, String> entry : toMap.entrySet()) {
//			System.out.println(entry.getKey() + "::" + entry.getValue());
//		}

//		String s = "1200F030810100008000000000000600002804ITAX400000000000000000393900000758756 2018121916323020181219200080000001140413009000000100313001190001000403IBT010ABC0000019";
//		parse(s);
	}

	public static Map<Integer, String> getMessage() {
		Map<Integer, String> values = new LinkedHashMap<>();
		Calendar cal = Calendar.getInstance();
		String MM = "" + (1 + cal.get(Calendar.MONTH));
		String DD = "" + cal.get(Calendar.DAY_OF_MONTH);
		String hh = "" + cal.get(Calendar.HOUR_OF_DAY);
		String mm = "" + cal.get(Calendar.MINUTE);
		String ss = "" + cal.get(Calendar.SECOND);
		String YY = "" + cal.get(Calendar.YEAR);
		if (MM.length() < 2) {
			MM = "0" + MM;
		}
		if (DD.length() < 2) {
			DD = "0" + DD;
		}
		if (hh.length() < 2) {
			hh = "0" + hh;
		}
		if (mm.length() < 2) {
			mm = "0" + mm;
		}
		if (ss.length() < 2) {
			ss = "0" + ss;
		}
		if (YY.length() < 2) {
			YY = "0" + YY;
		}

// ISO-93
		values.put(1, " 1200");
		values.put(2, " 123456");
		values.put(3, " 000011");
		values.put(4, " 000000012345");
		values.put(5, " 000000004311");
		values.put(7, " 1102155116");
		values.put(11, " 000001");
		values.put(12, " 181102155116");
		values.put(13, " 1811");
		values.put(15, " 181102");
		values.put(17, " 1102");
		values.put(32, " 12312312");
		values.put(37, " 232         ");
		values.put(41, " 123     ");
		values.put(42, " 2132           ");
		values.put(43, " 33122");
		values.put(49, " 003");
		values.put(102, " 9876543210123456");

		// ISO-87
//		values.put(1, "0200");
//		values.put(2, "1140463000");
//		values.put(3, "100000");
//		values.put(4, "107000");
//		values.put(7, "0130094304");
//		values.put(10, "");
//		values.put(11, "094304");
//		values.put(12, "094304");
//		values.put(13, "0130");
//		values.put(15, "0130");
//		values.put(19, "404");
//		values.put(32, "940500");
//		values.put(37, "5942924A3FBB");
//		values.put(41, "MOB00002");
//		values.put(42, "000000000105817");
//		values.put(43, "test|Kimani|Elizabeth||0008527001");
//		values.put(49, "404");
//		values.put(102, "0010008527001");

		// INTB
//		values.put(1, "1200");
//		values.put(2, "INTB");
//		values.put(3, "310000");
//		values.put(4, "100");
//		values.put(11, "622279");
//		values.put(12, YY + MM + DD + hh + mm + ss);
//		values.put(17, YY + MM + DD);
//		values.put(24, "200");
//		values.put(32, "000000");
//		values.put(49, "840");
//		values.put(102, "001190001000275");
//		values.put(123, "SWT");
//		values.put(124, "ITB");
//		values.put(125, "PRN 2020540404332");

		// ITAX
//		values.put(2, "ITAX");
//		values.put(3, "400000");
//		values.put(4, "39.39");
//		values.put(11, "758756");
//		values.put(12, YY + MM + DD + hh + mm + ss);
//		values.put(17, YY + MM + DD);
//		values.put(24, "200");
//		values.put(32, "00000011");
//		values.put(49, "404");
//		values.put(102, "0090000001003");
//		values.put(103, "0011900010004");
//		values.put(123, "IBT");
//		values.put(125, "ABC0000019");

		return values;// debugString() new String(m.writeData())
	}

	public static void parse(String s) throws IOException, ParseException {
		MessageFactory<IsoMessage> mfact = ConfigParser.createFromClasspathConfig("config.xml");
		mfact.setIgnoreLastMissingField(true);
		IsoMessage m = mfact.parseMessage(s.getBytes(), 0);// mfact.getIsoHeader(0x1200).length()
		if (m != null) {
			System.out.printf("Message type: %04x%n", m.getType());
			System.out.println("FIELD TYPE    VALUE");
			for (int i = 2; i <= 128; i++) {
				IsoValue<?> f = m.getField(i);
				if (f != null) {
					System.out.printf("%5d %-6s [", i, f.getType());
					System.out.print(f.toString());
					System.out.println(']');
				}
			}
		}
	}

	public static Map<Integer, String> unpackMessage(String stringMessage) throws IOException, ParseException {
		try {
			MessageFactory<IsoMessage> mfact = ConfigParser.createFromClasspathConfig("config.xml");
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
			System.out.println("Exception while unpacking " + e);
			return null;
		}
	}

	public static String packMessage(Map<Integer, String> isoMessage, String isoVersion) throws Exception {
		IsoPojo pojo;
		Map<Integer, IsoPojo> fields = setMap();
		MessageFactory<IsoMessage> mfact = null;
		try {
			mfact = ConfigParser.createFromClasspathConfig("config.xml");
			mfact.setUseBinaryBitmap(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
		IsoMessage m = mfact.newMessage(Integer.parseInt(isoMessage.get(1), 16));
		isoMessage.remove(1);
		m.setBinaryBitmap(true);
		for (Entry<Integer, String> element : isoMessage.entrySet()) {
			pojo = fields.get(element.getKey());
			m.setValue(element.getKey(), element.getValue(), pojo.getIsoType(), pojo.getLength());
		}
		return m.debugString();
	}

	public static Map<Integer, String> toMap(String s) throws IOException, ParseException {
		MessageFactory<IsoMessage> mfact = ConfigParser.createFromClasspathConfig("config.xml");
		mfact.setIgnoreLastMissingField(true);
		IsoMessage m = mfact.parseMessage(s.getBytes(), 0);// mfact.getIsoHeader(0x1200).length()
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
	}

	public static String mapToStringIso(Map<Integer, String> map) {
		IsoPojo pojo;
		Map<Integer, IsoPojo> fields = setMap();
		MessageFactory<IsoMessage> mfact = null;
		try {
			mfact = ConfigParser.createFromClasspathConfig("config.xml");
			mfact.setUseBinaryBitmap(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
		IsoMessage m = mfact.newMessage(Integer.parseInt(map.get(1), 16));
		map.remove(1);
		m.setBinaryBitmap(true);
		for (Entry<Integer, String> element : map.entrySet()) {
			pojo = fields.get(element.getKey());
			m.setValue(element.getKey(), element.getValue(), pojo.getIsoType(), pojo.getLength());
		}
		return m.debugString();
	}

	private static Map<Integer, IsoPojo> setMap() {
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
		values.put(97, new IsoPojo(IsoType.AMOUNT, 17));
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

}
