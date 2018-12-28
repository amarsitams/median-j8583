package com.rumango.median.iso.serviceimpl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.iso.service.ValidateFields;
import com.rumango.median.iso.service.ValidationLogic;

@Service
public class ValidateFieldsImpl implements ValidateFields {

	private final static Logger logger = Logger.getLogger(ValidateFieldsImpl.class);
	@Autowired
	private ValidationLogic validation;
	private Map<Integer, String> tempMap;

	@Override
	public Map<Integer, String> incoming(Map<Integer, String> isoMsg) {
		tempMap = new LinkedHashMap<>();
		String value = null;
		for (Map.Entry<Integer, String> entry : isoMsg.entrySet()) {
			value = validation.validate(entry.getKey(), entry.getValue());
			tempMap.put(entry.getKey(), value == null ? entry.getValue() : value);
			logger.info(entry.getKey() + " " + ":" + value);
		}
		return tempMap;
		// return isoMsg;
	}

	@Override
	public Map<Integer, String> outgoing(Map<Integer, String> isoMsg) {
		tempMap = new LinkedHashMap<>();
		String value = null;
		for (Map.Entry<Integer, String> entry : isoMsg.entrySet()) {
			value = validation.validate(entry.getKey(), entry.getValue());
			tempMap.put(entry.getKey(), value == null ? entry.getValue() : value);
			logger.info(entry.getKey() + " " + ":" + value);
		}
		return tempMap;
		// return isoMsg;
	}
}