package com.rumango.median.iso.client;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.rumango.median.iso.serviceimpl.IsoUtilImpl;

public class Test {

	public static void main(String[] args) throws Exception {
		IsoUtilImpl impl = new IsoUtilImpl();

		// testing iso 87
//		String s = impl.packMessage(Test8583.getMessage(), "87");
//		System.out.println(s.substring(3));
//		Map<Integer, String> toMap = impl.unpackMessage(s.substring(3), "87");
//		for (Entry<Integer, String> entry : toMap.entrySet()) {
//			System.out.println(entry.getKey() + ";" + entry.getValue());
//		}

		// String iso93 =
		// "1200FA3A800108E080000000000004000000061234560000110000000123450000000043111102155116000001181102155116181118110211020812312312232
		// 123 2132 0533122003169876543210123456";

//		String s = impl.packMessage(getMessage(), "87");
//		System.out.println(s);
//
////		ClientSocketForSwitch cs = new ClientSocketForSwitch();
////		cs.setValues(4000, true, "192.168.0.35", 100901);
////		s = cs.run(s.substring(5));
////
////		System.out.println(s);
//
//		Map<Integer, String> toMap = impl.unpackMessage(s.substring(5), "87");
//		for (Entry<Integer, String> entry : toMap.entrySet()) {
//			System.out.println(entry.getKey() + ":" + entry.getValue());
//		}

//			System.out.println(getMessage());
//			parse(getMessage());

//			String s = packMessage(getMessage(), null);
//			System.out.println(s.substring(5));
//			Map<Integer, String> toMap = unpackMessage(s.substring(5));
//			for (Entry<Integer, String> entry : toMap.entrySet()) {
//				System.out.println(entry.getKey() + "::" + entry.getValue());
//			}

//			String s = "1200F030810100008000000000000600002804ITAX400000000000000000393900000758756 2018121916323020181219200080000001140413009000000100313001190001000403IBT010ABC0000019";
//			parse(s);

//		String s = impl.packMessage(getMessage("87"), "87");
//		String ss = "0200F23A801F08A08010000000000400000014940400502010010100000000000020000211154830000004154830021102110211 00000000 00000000 00000000 0000000006940400635617671644CAN00001test                                    82601620192019201920190850201001";
//		System.out.println(ss);

		String s = impl.packMessage(getMessage("87"), "87");
		System.out.println(s);
		ClientSocket cs = new ClientSocket();
		// cs.setValues(40000, true, "192.168.0.35", 10090);
		cs.setValues(80000, true, "192.168.0.100", 2108);
		// s = cs.run(s.substring(5));
		s = cs.run(s.substring(5));
		Map<Integer, String> toMap = impl.unpackMessage(s, "87");
		for (Entry<Integer, String> entry : toMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	public static Map<Integer, String> getMessage(String version) {
		Map<Integer, String> values = new LinkedHashMap<>();

//		Calendar cal = Calendar.getInstance();
//		String MM = "" + (1 + cal.get(Calendar.MONTH));
//		String DD = "" + cal.get(Calendar.DAY_OF_MONTH);
//		String hh = "" + cal.get(Calendar.HOUR_OF_DAY);
//		String mm = "" + cal.get(Calendar.MINUTE);
//		String ss = "" + cal.get(Calendar.SECOND);
//		String YY = "" + cal.get(Calendar.YEAR);
//		if (MM.length() < 2) {
//			MM = "0" + MM;
//		}
//		if (DD.length() < 2) {
//			DD = "0" + DD;
//		}
//		if (hh.length() < 2) {
//			hh = "0" + hh;
//		}
//		if (mm.length() < 2) {
//			mm = "0" + mm;
//		}
//		if (ss.length() < 2) {
//			ss = "0" + ss;
//		}
//		if (YY.length() < 2) {
//			YY = "0" + YY;
//		}

		// ISO-93
		if (version.equalsIgnoreCase("93")) {
			values.put(1, "1200");
			values.put(2, "123456");
			values.put(3, "000011");
			values.put(4, "000000012345");
			values.put(5, "000000004311");
			values.put(7, "1102155116");
			values.put(11, "000001");
			values.put(12, "181102155116");
			values.put(13, "1811");
			values.put(15, "181102");
			values.put(17, "1102");
			values.put(32, "12312312");
			values.put(37, "232");
			values.put(41, "123");
			values.put(42, "2132");
			values.put(43, "33122");
			values.put(49, "003");
			values.put(102, "9876543210123456");
		}

		// ISO-87
		if (version.equalsIgnoreCase("87")) {
			values.put(1, "0200");
			values.put(2, "1140463000");
			values.put(3, "100000");
			values.put(4, "107000");
			values.put(7, "0130094304");
			values.put(10, "");
			values.put(11, "094304");
			values.put(12, "094304");
			values.put(13, "0130");
			values.put(15, "0130");
			values.put(19, "404");
			values.put(32, "940500");
			values.put(37, "5942924A3FBB");
			values.put(41, "MOB00002");
			values.put(42, "000000000105817");
			values.put(43, "test|Kimani|Elizabeth||0008527001");
			values.put(49, "003");
			values.put(102, "0010008527001");
		}

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
		return values;
	}
}
