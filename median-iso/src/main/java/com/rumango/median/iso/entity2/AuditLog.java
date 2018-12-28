package com.rumango.median.iso.entity2;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the audit_logs database table.
 * 
 */
@Entity
@Table(name="audit_logs")
@NamedQuery(name="AuditLog.findAll", query="SELECT a FROM AuditLog a")
public class AuditLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=2147483647)
	private String action;

	@Column(name="created_at", nullable=false)
	private Timestamp createdAt;

	@Column(name="creator_id")
	private Integer creatorId;

	@Column(name="creator_time")
	private Timestamp creatorTime;

	@Column(name="loggable_id")
	private Integer loggableId;

	@Column(name="loggable_type", length=2147483647)
	private String loggableType;

	@Column(name="maintanance_status", length=2147483647)
	private String maintananceStatus;

	@Column(length=2147483647)
	private String modification;

	@Column(name="number_of_modification")
	private Integer numberOfModification;

	@Column(name="updated_at", nullable=false)
	private Timestamp updatedAt;

	@Column(name="verification_status", length=2147483647)
	private String verificationStatus;

	@Column(name="verifier_id")
	private Integer verifierId;

	@Column(name="verifier_time")
	private Timestamp verifierTime;

	public AuditLog() {
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

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Timestamp getCreatorTime() {
		return this.creatorTime;
	}

	public void setCreatorTime(Timestamp creatorTime) {
		this.creatorTime = creatorTime;
	}

	public Integer getLoggableId() {
		return this.loggableId;
	}

	public void setLoggableId(Integer loggableId) {
		this.loggableId = loggableId;
	}

	public String getLoggableType() {
		return this.loggableType;
	}

	public void setLoggableType(String loggableType) {
		this.loggableType = loggableType;
	}

	public String getMaintananceStatus() {
		return this.maintananceStatus;
	}

	public void setMaintananceStatus(String maintananceStatus) {
		this.maintananceStatus = maintananceStatus;
	}

	public String getModification() {
		return this.modification;
	}

	public void setModification(String modification) {
		this.modification = modification;
	}

	public Integer getNumberOfModification() {
		return this.numberOfModification;
	}

	public void setNumberOfModification(Integer numberOfModification) {
		this.numberOfModification = numberOfModification;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getVerificationStatus() {
		return this.verificationStatus;
	}

	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public Integer getVerifierId() {
		return this.verifierId;
	}

	public void setVerifierId(Integer verifierId) {
		this.verifierId = verifierId;
	}

	public Timestamp getVerifierTime() {
		return this.verifierTime;
	}

	public void setVerifierTime(Timestamp verifierTime) {
		this.verifierTime = verifierTime;
	}

}