package com.rumango.median.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tags database table.
 * 
 */
@Entity
@Table(name = "tags")
@NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@Column(name = "created_dt_stamp")
	private Timestamp createdDtStamp;

	@Column(name = "creator_id")
	private Integer creatorId;

	@Column(name = "err_queue_name_to_pick")
	private String errQueueNameToPick;

	@Column(name = "err_queue_name_to_send")
	private String errQueueNameToSend;

	@Column(name = "external_system_id")
	private Long externalSystemId;

	@Column(name = "latest_amend_no")
	private Integer latestAmendNo;

	@Column(name = "record_status")
	private String recordStatus;

	@Column(name = "req_queue_name_to_pick")
	private String reqQueueNameToPick;

	@Column(name = "req_queue_name_to_send")
	private String reqQueueNameToSend;

	@Column(name = "resp_queue_name_to_pick")
	private String respQueueNameToPick;

	@Column(name = "resp_queue_name_to_send")
	private String respQueueNameToSend;

	@Column
	private String tag;

	@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	private Boolean verified;

	@Column(name = "verified_ever")
	private Boolean verifiedEver;

	@Column(name = "verifier_dt_stamp")
	private Timestamp verifierDtStamp;

	@Column(name = "verifier_id")
	private Integer verifierId;

	public Tag() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getCreatedDtStamp() {
		return this.createdDtStamp;
	}

	public void setCreatedDtStamp(Timestamp createdDtStamp) {
		this.createdDtStamp = createdDtStamp;
	}

	public Integer getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getErrQueueNameToPick() {
		return this.errQueueNameToPick;
	}

	public void setErrQueueNameToPick(String errQueueNameToPick) {
		this.errQueueNameToPick = errQueueNameToPick;
	}

	public String getErrQueueNameToSend() {
		return this.errQueueNameToSend;
	}

	public void setErrQueueNameToSend(String errQueueNameToSend) {
		this.errQueueNameToSend = errQueueNameToSend;
	}

	public Long getExternalSystemId() {
		return this.externalSystemId;
	}

	public void setExternalSystemId(Long externalSystemId) {
		this.externalSystemId = externalSystemId;
	}

	public Integer getLatestAmendNo() {
		return this.latestAmendNo;
	}

	public void setLatestAmendNo(Integer latestAmendNo) {
		this.latestAmendNo = latestAmendNo;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getReqQueueNameToPick() {
		return this.reqQueueNameToPick;
	}

	public void setReqQueueNameToPick(String reqQueueNameToPick) {
		this.reqQueueNameToPick = reqQueueNameToPick;
	}

	public String getReqQueueNameToSend() {
		return this.reqQueueNameToSend;
	}

	public void setReqQueueNameToSend(String reqQueueNameToSend) {
		this.reqQueueNameToSend = reqQueueNameToSend;
	}

	public String getRespQueueNameToPick() {
		return this.respQueueNameToPick;
	}

	public void setRespQueueNameToPick(String respQueueNameToPick) {
		this.respQueueNameToPick = respQueueNameToPick;
	}

	public String getRespQueueNameToSend() {
		return this.respQueueNameToSend;
	}

	public void setRespQueueNameToSend(String respQueueNameToSend) {
		this.respQueueNameToSend = respQueueNameToSend;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getVerified() {
		return this.verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Boolean getVerifiedEver() {
		return this.verifiedEver;
	}

	public void setVerifiedEver(Boolean verifiedEver) {
		this.verifiedEver = verifiedEver;
	}

	public Timestamp getVerifierDtStamp() {
		return this.verifierDtStamp;
	}

	public void setVerifierDtStamp(Timestamp verifierDtStamp) {
		this.verifierDtStamp = verifierDtStamp;
	}

	public Integer getVerifierId() {
		return this.verifierId;
	}

	public void setVerifierId(Integer verifierId) {
		this.verifierId = verifierId;
	}

}