package com.rumango.median.rest;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedianRestController {

	@Autowired
	private ConsumeWsdl consumeWsdl;

	@PostMapping("/handlejson")
	public String jsonSoapRequest(@RequestBody String jsonData) {
		String xml = null;
		try {
			System.err.println("Incoming Object Json " + jsonData);
			// JSON TO XML
			JSONObject json = new JSONObject(jsonData);
			xml = XML.toString(json);
			xml = consumeWsdl.medianWsdlClient("", "", xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

	@PostMapping("/handlexml")
	public String handleXml(@RequestBody String xmlData) {
		String xml = null;
		try {
			System.err.println("Incoming Object Xml \n" + xmlData + "\n\n");
			xml = consumeWsdl.medianWsdlClient("", "", xmlData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}
}
