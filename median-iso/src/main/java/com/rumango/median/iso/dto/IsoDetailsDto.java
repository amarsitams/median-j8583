package com.rumango.median.iso.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class IsoDetailsDto implements Serializable {

	private static final long serialVersionUID = -7415465024991563873L;

	private String originalRequestString = null, modifiedRequestString = null, originalResponseString = null,
			modifiedResponseString = null, response, fromIp, uuid, sourceVersion, targetVersion,
			receivedMsgStatus = "FAIL", sentMsgStatus = "FAIL", externalSystemName, reason;
	private Timestamp createdAt;
	private int port, externalSystemId;

	public String getModifiedRequestString() {
		return modifiedRequestString;
	}

	public void setModifiedRequestString(String modifiedRequestString) {
		this.modifiedRequestString = modifiedRequestString;
	}

	public String getOriginalResponseString() {
		return originalResponseString;
	}

	public void setOriginalResponseString(String originalResponseString) {
		this.originalResponseString = originalResponseString;
	}

	public String getModifiedResponseString() {
		return modifiedResponseString;
	}

	public void setModifiedResponseString(String modifiedResponseString) {
		this.modifiedResponseString = modifiedResponseString;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getFromIp() {
		return fromIp;
	}

	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getReceivedMsgStatus() {
		return receivedMsgStatus;
	}

	public void setReceivedMsgStatus(String receivedMsgStatus) {
		this.receivedMsgStatus = receivedMsgStatus;
	}

	public String getSentMsgStatus() {
		return sentMsgStatus;
	}

	public void setSentMsgStatus(String sentMsgStatus) {
		this.sentMsgStatus = sentMsgStatus;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getOriginalRequestString() {
		return originalRequestString;
	}

	public void setOriginalRequestString(String originalRequestString) {
		this.originalRequestString = originalRequestString;
	}

	public String getSourceVersion() {
		return sourceVersion;
	}

	public void setSourceVersion(String sourceVersion) {
		this.sourceVersion = sourceVersion;
	}

	public String getTargetVersion() {
		return targetVersion;
	}

	public void setTargetVersion(String targetVersion) {
		this.targetVersion = targetVersion;
	}

	public String getExternalSystemName() {
		return externalSystemName;
	}

	public void setExternalSystemName(String externalSystemName) {
		this.externalSystemName = externalSystemName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getExternalSystemId() {
		return externalSystemId;
	}

	public void setExternalSystemId(int externalSystemId) {
		this.externalSystemId = externalSystemId;
	}

	public void clear(IsoDetailsDto dto) {
//		dto.setCreatedAt(null);
//		dto.setFromIp("");
//		dto.setModifiedRequestString("");
//		dto.setModifiedResponseString("");
//		dto.setOriginalRequestString("");
//		dto.setOriginalResponseString("");
//		dto.setPort(-1);
//		dto.setReceivedMsgStatus("");
//		dto.setResponse("");
//		dto.setSentMsgStatus("");
//		dto.setSourceVersion("");
//		dto.setTargetVersion("");
//		dto.setUuid("");
//		dto.setExternalSystemId(-1);
//		dto.setExternalSystemName("");
//		dto.setReason("");

		dto.setCreatedAt(null);
		dto.setFromIp(null);
		dto.setModifiedRequestString(null);
		dto.setModifiedResponseString(null);
		dto.setOriginalRequestString(null);
		dto.setOriginalResponseString(null);
		dto.setPort(-1);
		dto.setReceivedMsgStatus(null);
		dto.setResponse(null);
		dto.setSentMsgStatus(null);
		dto.setSourceVersion(null);
		dto.setTargetVersion(null);
		dto.setUuid(null);
		dto.setExternalSystemId(-1);
		dto.setExternalSystemName(null);
		dto.setReason(null);
	}

	@Override
	public String toString() {
		return "IsoDetailsDto [originalRequestString=" + originalRequestString + ", modifiedRequestString="
				+ modifiedRequestString + ", originalResponseString=" + originalResponseString
				+ ", modifiedResponseString=" + modifiedResponseString + ", response=" + response + ", fromIp=" + fromIp
				+ ", uuid=" + uuid + ", sourceVersion=" + sourceVersion + ", targetVersion=" + targetVersion
				+ ", receivedMsgStatus=" + receivedMsgStatus + ", sentMsgStatus=" + sentMsgStatus
				+ ", externalSystemName=" + externalSystemName + ", reason=" + reason + ", createdAt=" + createdAt
				+ ", port=" + port + ", externalSystemId=" + externalSystemId + "]";
	}

}
