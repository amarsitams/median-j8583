package com.rumango.median.iso.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the tag_maps database table.
 * 
 */
@Entity
@Table(name = "tag_maps")
@NamedQuery(name = "TagMap.findAll", query = "SELECT t FROM TagMap t")
public class TagMap implements Serializable {
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

	@Column(name = "from_system_id")
	private Long fromSystemId;

	@Column(name = "to_system_id")
	private Long toSystemId;

	@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	@Column(name = "verified_dt_stamp")
	private Timestamp verifiedDtStamp;

	@Column(name = "verifier_id")
	private Integer verifierId;

	public TagMap() {
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

	public Long getFromSystemId() {
		return this.fromSystemId;
	}

	public void setFromSystemId(Long fromSystemId) {
		this.fromSystemId = fromSystemId;
	}

	public Long getToSystemId() {
		return this.toSystemId;
	}

	public void setToSystemId(Long toSystemId) {
		this.toSystemId = toSystemId;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getVerifiedDtStamp() {
		return this.verifiedDtStamp;
	}

	public void setVerifiedDtStamp(Timestamp verifiedDtStamp) {
		this.verifiedDtStamp = verifiedDtStamp;
	}

	public Integer getVerifierId() {
		return this.verifierId;
	}

	public void setVerifierId(Integer verifierId) {
		this.verifierId = verifierId;
	}

}