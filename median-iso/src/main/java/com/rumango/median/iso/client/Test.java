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

		String iso93 = "1200FA3A800108E080000000000004000000061234560000110000000123450000000043111102155116000001181102155116181118110211020812312312232         123     2132           0533122003169876543210123456";

		String s = impl.packMessage(getMessage(), "93");
		System.out.println(s.substring(5));
		Map<Integer, String> toMap = impl.unpackMessage(s.substring(5), "93");
		for (Entry<Integer, String> entry : toMap.entrySet()) {
			System.out.println(entry.getKey() + "::" + entry.getValue());
		}

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
	}

	public static Map<Integer, String> getMessage() {
		Map<Integer, String> values = new LinkedHashMap<>();

// ISO-93
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
		return values;
	}
}
