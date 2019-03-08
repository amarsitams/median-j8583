package com.rumango.median.iso.service;

import com.rumango.median.iso.dto.IsoDetailsDto;

public interface GetResponse {
	
	public String convertAndRespond(String stringMessage, IsoDetailsDto dto);
	
//	public String convertAndRespond(String stringMessage, Map<String, String> map);
//
//	public String convertAndRespond(String stringMessage);
//
//	public boolean setMandatory(Map<String, String> map);
}
