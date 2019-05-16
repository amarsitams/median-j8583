package com.rumango.median.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmbedId implements Serializable {
	public EmbedId() {
	}

	public EmbedId(String one, String two) {
		this.externalSystem = one;
		this.processName = two;
	}

	private static final long serialVersionUID = 1L;
	@Column(name = "external_system")
	private String externalSystem;
	@Column(name = "process_name")
	private String processName;

	public String getExternalSystem() {
		return externalSystem;
	}

	public void setExternalSystem(String externalSystem) {
		this.externalSystem = externalSystem;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((externalSystem == null) ? 0 : externalSystem.hashCode());
		result = prime * result + ((processName == null) ? 0 : processName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmbedId other = (EmbedId) obj;
		if (externalSystem == null) {
			if (other.externalSystem != null)
				return false;
		} else if (!externalSystem.equals(other.externalSystem))
			return false;
		if (processName == null) {
			if (other.processName != null)
				return false;
		} else if (!processName.equals(other.processName))
			return false;
		return true;
	}
}
