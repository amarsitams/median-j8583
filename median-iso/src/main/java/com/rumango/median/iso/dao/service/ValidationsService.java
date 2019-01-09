package com.rumango.median.iso.dao.service;

import java.util.List;

public interface ValidationsService {

	public String getQuery(String from, String to, int field);

	public String getDefaultValue(String from, String to, int field);

	public List<String> getLov(String from, String to, int field);

}
