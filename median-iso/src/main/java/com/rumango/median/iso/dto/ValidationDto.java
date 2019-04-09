package com.rumango.median.iso.dto;

import java.util.List;

public class ValidationDto {

	private String required, defaultValue, node1, node2, query;
	private List<String> lovValues, conditions;

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getNode1() {
		return node1;
	}

	public void setNode1(String node1) {
		this.node1 = node1;
	}

	public String getNode2() {
		return node2;
	}

	public void setNode2(String node2) {
		this.node2 = node2;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<String> getLovValues() {
		return lovValues;
	}

	public void setLovValues(List<String> lovValues) {
		this.lovValues = lovValues;
	}

	public List<String> getConditions() {
		return conditions;
	}

	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}

}
