package com.rumango.median.iso.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumango.median.iso.dao.service.ValidationsService;
import com.rumango.median.iso.service.IsoConstants;
import com.rumango.median.iso.service.ValidationLogic;

@Service
public class ValidationLogicImpl implements ValidationLogic {

	@Autowired
	private ValidationsService validationsService;
	private final static Logger logger = Logger.getLogger(ValidationLogicImpl.class);

	private String ruleType = "default";

	@Override
	public String validate(int fieldNo, String currentValue) {
		if (ruleType.equalsIgnoreCase(IsoConstants.rule_default)) {
			logger.info("Inside " + ruleType);
			return currentValue;
		} else if (ruleType.equalsIgnoreCase(IsoConstants.rule_query)) {
			return currentValue;
		} else if (ruleType.equalsIgnoreCase(IsoConstants.rule_lov)) {
			return currentValue;
		} else if (ruleType.equalsIgnoreCase(IsoConstants.rule_condition)) {
			return currentValue;
		} else if (ruleType.equalsIgnoreCase(IsoConstants.rule_wildcard)) {
			return currentValue;
		} else
			return currentValue;
	}
}
