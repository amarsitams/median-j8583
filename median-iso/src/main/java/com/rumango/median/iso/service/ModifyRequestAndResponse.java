package com.rumango.median.iso.service;

public interface ModifyRequestAndResponse {

	// public String modifyRequest(String requestMsg, String sourceVersion, String
	// targetVersion) throws Exception;
	// public String modifyResponse(String requestMsg, String sourceVersion, String
	// targetVersion) throws Exception;

	public String modifyRequest(String requestMsg, String isoVersion) throws Exception;

	public String modifyResponse(String responseMsg, String isoVersion) throws Exception;

}
