package com.rumango.median.iso.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tag_map_values database table.
 * 
 */
@Entity
@Table(name="tag_map_values")
@NamedQuery(name="TagMapValue.findAll", query="SELECT t FROM TagMapValue t")
public class TagMapValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column
	private String condition;

	@Column(name="created_at", nullable=false)
	private Timestamp createdAt;

	@Column(name="node_map_id")
	private Long nodeMapId;

	@Column
	private String result;

	@Column(name="updated_at", nullable=false)
	private Timestamp updatedAt;

	public TagMapValue() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Long getNodeMapId() {
		return this.nodeMapId;
	}

	public void setNodeMapId(Long nodeMapId) {
		this.nodeMapId = nodeMapId;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}