package com.rumango.median.iso.client;

import java.util.LinkedHashMap;
import java.util.Map;

import com.rumango.median.iso.service.ConvertIso;
import com.rumango.median.iso.serviceimpl.ConvertIsoImpl;
import com.rumango.median.iso.util.impl.IsoUtilImpl;

public class Test {

	public static void main(String[] args) throws Exception {
		IsoUtilImpl impl = new IsoUtilImpl();
		ConvertIso convertIso = new ConvertIsoImpl();

		Map<Integer, String> isoMsgTemp;

		Map<Integer, String> isoMsg = getMessage("93");
		impl.logISOMsg(isoMsg, "=============INPUT============");

		// String xml = impl.isoToXml(isoMsg);
		String xml2 = "<11>000001</11><12>181102155116</12><13>1811</13><15>181102</15><37>232</37><49>003</49><17>1102</17><1>1200</1><2>123456</2><3>000011</3><102>9876543210123456</102><4>000000012345</4><5>000000004311</5><7>1102155116</7><41>123</41><42>2132</42><32>12312312</32><43>33122</43>";

		System.err.println("ISO to xml " + xml2);

//		System.err.println("JSON  to ISO " + impl.jsonToIso(json));

		impl.logISOMsg(impl.xmlToIso(xml2), "=============OUTPUT============");

//		isoMsgTemp = convertIso.convertIso("93", "87", isoMsg);

//		impl.logISOMsg(isoMsgTemp, "=============OUTPUT============");

//		json = impl.packMessage(isoMsg, "87");
//		System.err.println(json);
//
//		isoMsgTemp = impl.unpackMessage(json.substring(5), "93");
//		impl.logISOMsg(isoMsgTemp, "=============isoMsgTemp============");
//
//		json = impl.isoToJson(isoMsgTemp);
//		System.err.println(json);
//
//		isoMsgTemp = impl.jsonToIso(json);
//		impl.logISOMsg(isoMsg, "=============OUTPUT============");

//		json = impl.packMessage(isoMsgTemp, "93");
//		System.err.println(json);
//
//		isoMsgTemp = impl.unpackMessage(json.substring(5), "93");
//		impl.logISOMsg(isoMsgTemp, "=============OUTPUT============");
//
//		json = impl.packMessage(isoMsgTemp, "93");
//		System.err.println(json);

	}

	void test() throws Exception {
		ConvertIsoImpl convertIsoImpl = new ConvertIsoImpl();

		IsoUtilImpl impl = new IsoUtilImpl();
		String temp;

		Map<Integer, String> isoMsg = getMessage("93");
		impl.logISOMsg(isoMsg, "=============INPUT============");
		isoMsg = convertIsoImpl.convertIso("ge", "93", isoMsg);

		impl.logISOMsg(isoMsg, "=============93============");

		temp = impl.packMessage(isoMsg, "93");
		System.err.println(temp);

		isoMsg = impl.unpackMessage(temp.substring(5), "93");
		// System.err.println(impl.unpackMessage(temp.substring(5),"93"));

		isoMsg = convertIsoImpl.convertIso("93", "ge", isoMsg);
		impl.logISOMsg(isoMsg, "=============ge============");

		temp = impl.packMessage(isoMsg, "ge");
		System.err.println(temp);

//		String json = impl.isoToJson(isoMsg);
//		System.err.println("JSON ::" + json);
//
//		String xml = impl.isoToXml(isoMsg);
//		System.err.println("XML ::" + xml);

//		System.err.println("Json To XML ::" + impl.jsonToXml(json));
//
//		System.err.println("Json To ISO ::" + impl.jsonToIso(json));
//
//		System.err.println("XML To Json ::" + impl.xmlToJson(xml));
//
//		System.err.println("XML To ISO ::" + impl.xmlToIso(xml));

//		System.err.println("XML To ISO ::" + impl.jsonToIso(impl.xmlToJson(xml)));

// 		impl.jsonToIso(impl.xmlToJson(xml));
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
