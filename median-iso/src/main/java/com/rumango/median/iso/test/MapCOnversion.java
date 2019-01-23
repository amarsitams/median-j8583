package com.rumango.median.iso.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlRootElement(name = "Map")
class MapElements {
	@XmlAttribute
	public String key;
	@XmlAttribute
	public String value;

	public MapElements() {
	} // Required by JAXB

	public MapElements(String key, String value) {
		this.key = key;
		this.value = value;
	}
}

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
class EmployeeMap {

	private Map<String, String> employeeMap = new LinkedHashMap<>();

	public Map<String, String> getEmployeeMap() {
		return employeeMap;
	}

	public void setEmployeeMap(Map<String, String> employeeMap) {
		this.employeeMap = employeeMap;
	}
}

public class MapCOnversion extends XmlAdapter<MapElements[], Map<String, String>> {
	public MapCOnversion() {
	}

	public MapElements[] marshal(Map<String, String> arg0) throws Exception {
		MapElements[] mapElements = new MapElements[arg0.size()];
		int i = 0;
		for (Map.Entry<String, String> entry : arg0.entrySet())
			mapElements[i++] = new MapElements(entry.getKey(), entry.getValue());

		return mapElements;
	}

	public Map<String, String> unmarshal(MapElements[] arg0) throws Exception {
		Map<String, String> r = new TreeMap<String, String>();
		for (MapElements mapelement : arg0)
			r.put(mapelement.key, mapelement.value);
		return r;
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("1", "Amar 1");
		map.put("2", "Amar 2");
		map.put("3", "Amar 3");
		map.put("4", "Amar 4");

//		System.out.println(new MapCOnversion().marshal(map));
//
//		MapElements[] ary = new MapCOnversion().marshal(map);
//		for (MapElements ma : ary) {
//			System.out.println(ma);
//			// System.out.println(ma.key + ma.value);
//		}

	    EmployeeMap employeeMap = new EmployeeMap();
	    employeeMap.setEmployeeMap(map);
	    
		JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeMap.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(map, System.out);
	}
}