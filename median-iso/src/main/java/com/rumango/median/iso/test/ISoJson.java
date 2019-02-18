package com.rumango.median.iso.test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ISoJson {

	public static void main(String[] args) throws JsonProcessingException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name", "Mars");
		data.put("age", 32);
		data.put("city", "NY");
		String map = "{" + data.entrySet().stream()
				.map(e -> "\"" + e.getKey() + "\"" + ":\"" + String.valueOf(e.getValue()) + "\"")
				.collect(Collectors.joining(", ")) + "}";

//		Gson gs=new Gson();
//		GsonNObject json = new JSONObject();
//		gs.putAll(data);
//		System.out.printf("JSON: %s", json.toString());

		JSONObject json = new JSONObject(map);
		String xml = XML.toString(json);

		System.out.println(xml);

	}
}
