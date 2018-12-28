package com.abc.rumango.median.iso.entity2;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the external_systems database table.
 * 
 */
@Entity
@Table(name="external_systems")
@NamedQuery(name="ExternalSystem.findAll", query="SELECT e FROM ExternalSystem e")
public class ExternalSystem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=2147483647)
	private String action;

	@Column(name="comm_channel", length=2147483647)
	private String commChannel;

	@Column(name="created_at", nullable=false)
	private Timestamp createdAt;

	@Column(name="creator_dt_stamp")
	private Timestamp creatorDtStamp;

	@Column(name="creator_id")
	private Integer creatorId;

	@Column(length=2147483647)
	private String destination;

	@Column(name="destination_tag", length=2147483647)
	private String destinationTag;

	@Column(name="dtd_file", length=2147483647)
	private String dtdFile;

	@Column(name="err_directory_name", length=2147483647)
	private String errDirectoryName;

	@Column(name="err_queue_name", length=2147483647)
	private String errQueueName;

	@Column(name="err_schema_name", length=2147483647)
	private String errSchemaName;

	@Column(name="err_xslt_name", length=2147483647)
	private String errXsltName;

	@Column(name="ext_sys_code", length=2147483647)
	private String extSysCode;

	@Column(name="ext_sys_name", length=2147483647)
	private String extSysName;

	@Column(name="ext_sys_tag", length=2147483647)
	private String extSysTag;

	@Column(name="format_type", length=2147483647)
	private String formatType;

	@Column(name="is_dest_node")
	private Boolean isDestNode;

	@Column(name="is_ext_sys_node")
	private Boolean isExtSysNode;

	@Column(name="is_module_node")
	private Boolean isModuleNode;

	@Column(name="is_process_node")
	private Boolean isProcessNode;

	@Column(name="is_service_node")
	private Boolean isServiceNode;

	@Column(name="latest_amend_no")
	private Integer latestAmendNo;

	@Column(name="message_type", length=2147483647)
	private String messageType;

	@Column(name="module_code", length=2147483647)
	private String moduleCode;

	@Column(name="module_tag", length=2147483647)
	private String moduleTag;

	@Column(name="msg_duplication", length=2147483647)
	private String msgDuplication;

	@Column(name="process_code", length=2147483647)
	private String processCode;

	@Column(name="process_tag", length=2147483647)
	private String processTag;

	@Column(name="record_status", length=2147483647)
	private String recordStatus;

	@Column(name="req_directory_name", length=2147483647)
	private String reqDirectoryName;

	@Column(name="req_queue_name", length=2147483647)
	private String reqQueueName;

	@Column(name="req_schema_name", length=2147483647)
	private String reqSchemaName;

	@Column(name="req_xslt_name", length=2147483647)
	private String reqXsltName;

	@Column(name="request_tag", length=2147483647)
	private String requestTag;

	@Column(name="resp_directory_name", length=2147483647)
	private String respDirectoryName;

	@Column(name="resp_queue_name", length=2147483647)
	private String respQueueName;

	@Column(name="resp_schema_name", length=2147483647)
	private String respSchemaName;

	@Column(name="resp_xslt_name", length=2147483647)
	private String respXsltName;

	@Column(name="response_tag", length=2147483647)
	private String responseTag;

	@Column(name="service_code", length=2147483647)
	private String serviceCode;

	@Column(name="service_tag", length=2147483647)
	private String serviceTag;

	@Column(name="updated_at", nullable=false)
	private Timestamp updatedAt;

	private Boolean verified;

	@Column(name="verified_ever")
	private Boolean verifiedEver;

	@Column(name="verifier_dt_stamp")
	private Timestamp verifierDtStamp;

	@Column(name="verifier_id")
	private Integer verifierId;

	@Column(name="xsd_file", length=2147483647)
	private String xsdFile;

	public ExternalSystem() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCommChannel() {
		return this.commChannel;
	}

	public void setCommChannel(String commChannel) {
		this.commChannel = commChannel;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getCreatorDtStamp() {
		return this.creatorDtStamp;
	}

	public void setCreatorDtStamp(Timestamp creatorDtStamp) {
		this.creatorDtStamp = creatorDtStamp;
	}

	public Integer getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDestinationTag() {
		return this.destinationTag;
	}

	public void setDestinationTag(String destinationTag) {
		this.destinationTag = destinationTag;
	}

	public String getDtdFile() {
		return this.dtdFile;
	}

	public void setDtdFile(String dtdFile) {
		this.dtdFile = dtdFile;
	}

	public String getErrDirectoryName() {
		return this.errDirectoryName;
	}

	public void setErrDirectoryName(String errDirectoryName) {
		this.errDirectoryName = errDirectoryName;
	}

	public String getErrQueueName() {
		return this.errQueueName;
	}

	public void setErrQueueName(String errQueueName) {
		this.errQueueName = errQueueName;
	}

	public String getErrSchemaName() {
		return this.errSchemaName;
	}

	public void setErrSchemaName(String errSchemaName) {
		this.errSchemaName = errSchemaName;
	}

	public String getErrXsltName() {
		return this.errXsltName;
	}

	public void setErrXsltName(String errXsltName) {
		this.errXsltName = errXsltName;
	}

	public String getExtSysCode() {
		return this.extSysCode;
	}

	public void setExtSysCode(String extSysCode) {
		this.extSysCode = extSysCode;
	}

	public String getExtSysName() {
		return this.extSysName;
	}

	public void setExtSysName(String extSysName) {
		this.extSysName = extSysName;
	}

	public String getExtSysTag() {
		return this.extSysTag;
	}

	public void setExtSysTag(String extSysTag) {
		this.extSysTag = extSysTag;
	}

	public String getFormatType() {
		return this.formatType;
	}

	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}

	public Boolean getIsDestNode() {
		return this.isDestNode;
	}

	public void setIsDestNode(Boolean isDestNode) {
		this.isDestNode = isDestNode;
	}

	public Boolean getIsExtSysNode() {
		return this.isExtSysNode;
	}

	public void setIsExtSysNode(Boolean isExtSysNode) {
		this.isExtSysNode = isExtSysNode;
	}

	public Boolean getIsModuleNode() {
		return this.isModuleNode;
	}

	public void setIsModuleNode(Boolean isModuleNode) {
		this.isModuleNode = isModuleNode;
	}

	public Boolean getIsProcessNode() {
		return this.isProcessNode;
	}

	public void setIsProcessNode(Boolean isProcessNode) {
		this.isProcessNode = isProcessNode;
	}

	public Boolean getIsServiceNode() {
		return this.isServiceNode;
	}

	public void setIsServiceNode(Boolean isServiceNode) {
		this.isServiceNode = isServiceNode;
	}

	public Integer getLatestAmendNo() {
		return this.latestAmendNo;
	}

	public void setLatestAmendNo(Integer latestAmendNo) {
		this.latestAmendNo = latestAmendNo;
	}

	public String getMessageType() {
		return this.messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getModuleCode() {
		return this.moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleTag() {
		return this.moduleTag;
	}

	public void setModuleTag(String moduleTag) {
		this.moduleTag = moduleTag;
	}

	public String getMsgDuplication() {
		return this.msgDuplication;
	}

	public void setMsgDuplication(String msgDuplication) {
		this.msgDuplication = msgDuplication;
	}

	public String getProcessCode() {
		return this.processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getProcessTag() {
		return this.processTag;
	}

	public void setProcessTag(String processTag) {
		this.processTag = processTag;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getReqDirectoryName() {
		return this.reqDirectoryName;
	}

	public void setReqDirectoryName(String reqDirectoryName) {
		this.reqDirectoryName = reqDirectoryName;
	}

	public String getReqQueueName() {
		return this.reqQueueName;
	}

	public void setReqQueueName(String reqQueueName) {
		this.reqQueueName = reqQueueName;
	}

	public String getReqSchemaName() {
		return this.reqSchemaName;
	}

	public void setReqSchemaName(String reqSchemaName) {
		this.reqSchemaName = reqSchemaName;
	}

	public String getReqXsltName() {
		return this.reqXsltName;
	}

	public void setReqXsltName(String reqXsltName) {
		this.reqXsltName = reqXsltName;
	}

	public String getRequestTag() {
		return this.requestTag;
	}

	public void setRequestTag(String requestTag) {
		this.requestTag = requestTag;
	}

	public String getRespDirectoryName() {
		return this.respDirectoryName;
	}

	public void setRespDirectoryName(String respDirectoryName) {
		this.respDirectoryName = respDirectoryName;
	}

	public String getRespQueueName() {
		return this.respQueueName;
	}

	public void setRespQueueName(String respQueueName) {
		this.respQueueName = respQueueName;
	}

	public String getRespSchemaName() {
		return this.respSchemaName;
	}

	public void setRespSchemaName(String respSchemaName) {
		this.respSchemaName = respSchemaName;
	}

	public String getRespXsltName() {
		return this.respXsltName;
	}

	public void setRespXsltName(String respXsltName) {
		this.respXsltName = respXsltName;
	}

	public String getResponseTag() {
		return this.responseTag;
	}

	public void setResponseTag(String responseTag) {
		this.responseTag = responseTag;
	}

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceTag() {
		return this.serviceTag;
	}

	public void setServiceTag(String serviceTag) {
		this.serviceTag = serviceTag;
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

	public String getXsdFile() {
		return this.xsdFile;
	}

	public void setXsdFile(String xsdFile) {
		this.xsdFile = xsdFile;
	}

}