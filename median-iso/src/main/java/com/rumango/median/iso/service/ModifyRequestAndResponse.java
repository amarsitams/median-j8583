package com.rumango.median.iso.service;

import com.rumango.median.iso.dto.IsoDetailsDto;

public interface ModifyRequestAndResponse {

	public String modifyRequest(IsoDetailsDto isoDetailsDto) throws Exception;

	public String modifyResponse(IsoDetailsDto isoDetailsDto) throws Exception;

//	public String modifyRequest(String requestMsg, String isoVersion) throws Exception;
//
//	public String modifyResponse(String responseMsg, String isoVersion) throws Exception;

	// public String modifyRequest(String requestMsg, String sourceVersion, String
	// targetVersion) throws Exception;
	// public String modifyResponse(String requestMsg, String sourceVersion, String
	// targetVersion) throws Exception; Map<String, String> map

//	public String modifyRequest(String requestMsg, Map<String, String> map) throws Exception;
//
//	public String modifyResponse(String responseMsg, Map<String, String> map) throws Exception;

}
