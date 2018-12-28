package com.abc.rumango.median.iso.entity2;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="created_at", nullable=false)
	private Timestamp createdAt;

	@Column(name="creator_dt_stamp")
	private Timestamp creatorDtStamp;

	@Column(name="creator_id")
	private Integer creatorId;

	@Column(length=2147483647)
	private String name;

	@Column(name="record_status", length=2147483647)
	private String recordStatus;

	@Column(name="updated_at", nullable=false)
	private Timestamp updatedAt;

	private Boolean verified;

	@Column(name="verifier_dt_stamp")
	private Timestamp verifierDtStamp;

	@Column(name="verifier_id")
	private Integer verifierId;

	public Role() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
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