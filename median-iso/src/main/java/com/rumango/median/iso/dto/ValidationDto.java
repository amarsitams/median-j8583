package com.rumango.median.iso.dto;

import java.util.List;

public class ValidationDto {

	private String from, to, defalutValue, queryValue, conditionValue;
	private List<String> lovValues;
	private boolean isRequired;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getDefalutValue() {
		return defalutValue;
	}

	public void setDefalutValue(String defalutValue) {
		this.defalutValue = defalutValue;
	}

	public String getQueryValue() {
		return queryValue;
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}

	public String getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}

	public List<String> getLovValues() {
		return lovValues;
	}

	public void setLovValues(List<String> lovValues) {
		this.lovValues = lovValues;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
}
