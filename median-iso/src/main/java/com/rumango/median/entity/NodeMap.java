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
 * The persistent class for the node_maps database table.
 * 
 */
@Entity
@Table(name = "node_maps")
@NamedQuery(name = "NodeMap.findAll", query = "SELECT n FROM NodeMap n")
public class NodeMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@Column(name = "default_val")
	private String def;

	@Column
	private String node1;

	@Column
	private String node2;

	@Column
	private String query;

	@Column
	private String required;

	@Column(name = "tag_map_id")
	private Long tagMapId;

	@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	public NodeMap() {
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

	public String getNode1() {
		return this.node1;
	}

	public void setNode1(String node1) {
		this.node1 = node1;
	}

	public String getNode2() {
		return this.node2;
	}

	public void setNode2(String node2) {
		this.node2 = node2;
	}

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getRequired() {
		return this.required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public Long getTagMapId() {
		return this.tagMapId;
	}

	public void setTagMapId(Long tagMapId) {
		this.tagMapId = tagMapId;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	@Override
	public String toString() {
		return "NodeMap [id=" + id + ", createdAt=" + createdAt + ", def=" + def + ", node1=" + node1 + ", node2="
				+ node2 + ", query=" + query + ", required=" + required + ", tagMapId=" + tagMapId + ", updatedAt="
				+ updatedAt + "]";
	}

}