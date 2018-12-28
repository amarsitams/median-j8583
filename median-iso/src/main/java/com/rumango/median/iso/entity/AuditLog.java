package com.rumango.median.iso.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Entity
@Table(name = "median_audit_logs")
@JsonAutoDetect
public class AuditLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "external_system_name", nullable = false)
	private String externalSystemName;

	@Column(name = "external_system_id")
	private Long externalSystemId;

	@Column(name = "request_ip")
	private String ipAddress;

	@Column(name = "request_status")
	private String requestStatus;

	@Column(name = "response_status")
	private String responseStatus;

	@Column(name = "reason")
	private String reason;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "median_uuid")
	private String medianUuid;

	@Column(columnDefinition = "text", name = "original_request_isomsg", length = 10000)
	private String original_request_isomsg;

	@Column(columnDefinition = "text", name = "modified_request_isomsg", length = 10000)
	private String modified_request_isomsg;

	@Column(columnDefinition = "text", name = "original_response_isomsg", length = 10000)
	private String original_response_isomsg;

	@Column(columnDefinition = "text", name = "modified_response_isomsg", length = 10000)
	private String modified_response_isomsg;

	@Column(columnDefinition = "text", name = "original_request_splitted", length = 10000)
	private String original_request_splitted;

	@Column(columnDefinition = "text", name = "modified_request_splitted", length = 10000)
	private String modified_request_splitted;

	@Column(columnDefinition = "text", name = "original_response_splitted", length = 10000)
	private String original_response_splitted;

	@Column(columnDefinition = "text", name = "modified_response_splitted", length = 10000)
	private String modified_response_splitted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getExternalSystemId() {
		return externalSystemId;
	}

	public void setExternalSystemId(Long externalSystemId) {
		this.externalSystemId = externalSystemId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getMedianUuid() {
		return medianUuid;
	}

	public void setMedianUuid(String medianUuid) {
		this.medianUuid = medianUuid;
	}

	public String getModified_request_isomsg() {
		return modified_request_isomsg;
	}

	public void setModified_request_isomsg(String modified_request_isomsg) {
		this.modified_request_isomsg = modified_request_isomsg;
	}

	public String getOriginal_response_isomsg() {
		return original_response_isomsg;
	}

	public void setOriginal_response_isomsg(String original_response_isomsg) {
		this.original_response_isomsg = original_response_isomsg;
	}

	public String getModified_response_isomsg() {
		return modified_response_isomsg;
	}

	public void setModified_response_isomsg(String modified_response_isomsg) {
		this.modified_response_isomsg = modified_response_isomsg;
	}

	public String getOriginal_request_splitted() {
		return original_request_splitted;
	}

	public void setOriginal_request_splitted(String original_request_splitted) {
		this.original_request_splitted = original_request_splitted;
	}

	public String getModified_request_splitted() {
		return modified_request_splitted;
	}

	public void setModified_request_splitted(String modified_request_splitted) {
		this.modified_request_splitted = modified_request_splitted;
	}

	public String getOriginal_response_splitted() {
		return original_response_splitted;
	}

	public void setOriginal_response_splitted(String original_response_splitted) {
		this.original_response_splitted = original_response_splitted;
	}

	public String getModified_response_splitted() {
		return modified_response_splitted;
	}

	public void setModified_response_splitted(String modified_response_splitted) {
		this.modified_response_splitted = modified_response_splitted;
	}

	@Override
	public String toString() {
		return "AuditLog [id=" + id + ", externalSystemName=" + externalSystemName + ", externalSystemId="
				+ externalSystemId + ", ipAddress=" + ipAddress + ", requestStatus=" + requestStatus
				+ ", responseStatus=" + responseStatus + ", reason=" + reason + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", medianUuid=" + medianUuid + ", original_request_isomsg="
				+ original_request_isomsg + ", modified_request_isomsg=" + modified_request_isomsg
				+ ", original_response_isomsg=" + original_response_isomsg + ", modified_response_isomsg="
				+ modified_response_isomsg + ", original_request_splitted=" + original_request_splitted
				+ ", modified_request_splitted=" + modified_request_splitted + ", original_response_splitted="
				+ original_response_splitted + ", modified_response_splitted=" + modified_response_splitted + "]";
	}

	public String getOriginal_request_isomsg() {
		return original_request_isomsg;
	}

	public void setOriginal_request_isomsg(String original_request_isomsg) {
		this.original_request_isomsg = original_request_isomsg;
	}

	public String getExternalSystemName() {
		return externalSystemName;
	}

	public void setExternalSystemName(String externalSystemName) {
		this.externalSystemName = externalSystemName;
	}
}
