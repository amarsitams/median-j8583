package com.rumango.median.iso.dto;

import java.util.List;

public class ValidationDto {

	private String field, defalutValue, queryValue, conditionValue;
	private List<String> lovValues;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
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
}
