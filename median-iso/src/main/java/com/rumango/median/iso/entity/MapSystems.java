package com.rumango.median.iso.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "map_systems")
public class MapSystems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "from_system")
	private String fromSystem;

	@Column(name = "to_system")
	private String toSystem;

	@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@Column(name = "to_port")
	private int toPort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}

	public String getToSystem() {
		return toSystem;
	}

	public void setToSystem(String toSystem) {
		this.toSystem = toSystem;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getToPort() {
		return toPort;
	}

	public void setToPort(int toPort) {
		this.toPort = toPort;
	}

	@Override
	public String toString() {
		return "MapSystems [id=" + id + ", fromSystem=" + fromSystem + ", toSystem=" + toSystem + ", updatedAt="
				+ updatedAt + ", createdAt=" + createdAt + ", toPort=" + toPort + "]";
	}

}
