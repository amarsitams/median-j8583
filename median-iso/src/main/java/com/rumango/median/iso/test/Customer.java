package com.rumango.median.iso.test;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "root")
//@XmlType(propOrder = { "map", "other" })
public class Customer {

	private Map<String, String> addressMap = new HashMap<String, String>();

	public Map<String, String> getAddressMap() {
		return addressMap;
	}

	@XmlElement(name = "map")
	@XmlJavaTypeAdapter(MapAdapter.class)
	public void setAddressMap(Map<String, String> addressMap) {
		this.addressMap = addressMap;
	}

}